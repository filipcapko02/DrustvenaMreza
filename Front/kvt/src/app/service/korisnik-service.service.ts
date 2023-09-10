import { Injectable } from '@angular/core';
import { ApiServiceService } from './api-service.service';
import { ConfigServiceService } from './config-service.service';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class KorisnikServiceService {

  currentUser: any;

  constructor(
    private apiService: ApiServiceService,
    private config: ConfigServiceService
  ) {
  }

  changePassword(user: any, oldPassword: string){
    return this.apiService.put(this.config.change_password_url+"/"+oldPassword, user,);
  }

  getMyInfo() {
    return this.apiService.get(this.config.profile_url)
      .pipe(map(user => {
        this.currentUser = user;
        return user;
      }));
  }

  getAll() {
    return this.apiService.get(this.config.users_url);
  }
}
