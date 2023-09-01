import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ApiServiceService } from './api-service.service';
import { KorisnikServiceService } from './korisnik-service.service';
import { ConfigServiceService } from './config-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(
    private apiService: ApiServiceService,
    private userService: KorisnikServiceService,
    private config: ConfigServiceService,
    private router: Router
  ) {
  }
  private access_token = null;
  login(korisnik: { username: any; lozinka: any; }) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    const body = {
      'username': korisnik.username,
      'lozinka': korisnik.lozinka
    };
    return this.apiService.post(this.config.login_url, JSON.stringify(body), loginHeaders)
      .pipe(map((res) => {
        console.log('Login uspesan');
        this.access_token = res.accessToken;
        localStorage.setItem("jwt", res.accessToken);
      }));


  }
  signup(user: any) {
    const signupHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.signup_url, JSON.stringify(user), signupHeaders)
      .pipe(map(() => {
        console.log('Registracija uspesna');
      }));
  }

  tokenInUse() {
    return this.access_token != undefined && this.access_token != null;
  }

  findToken() {
    return this.access_token;
  }

  logout() {
    this.router.navigate(['/login']);
    this.access_token = null;
    this.userService.currentUser = null;
  }
}
