import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import { Subject } from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import { AuthServiceService } from '../service/auth-service.service';
import { KorisnikServiceService } from '../service/korisnik-service.service';
import { RegisterRequestPayload } from './register-request.payload';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  title = 'Registracija';
  form!: FormGroup;

  submitted = false;

  returnUrl!: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private router: Router,
    private userService: KorisnikServiceService,
    private authService: AuthServiceService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {

  }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      lozinka: ['', Validators.required],
      ime: [''],
      prezime: [''],
      email: ['']
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {

    this.submitted = true;

    this.authService.signup(this.form.value)
      .subscribe(data => {
        this.authService.login(this.form.value).subscribe(() => {
          this.userService.getMyInfo().subscribe();
          this.router.navigate(['/login']);
        });
        this.router.navigate(['/login']);
      },
        error => {
          this.submitted = false;
          console.log('Greska');
        });

  }
}
