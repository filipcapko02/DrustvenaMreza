import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AuthServiceService } from '../service/auth-service.service';
import { KorisnikServiceService } from '../service/korisnik-service.service';
import { ConfigServiceService } from '../service/config-service.service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title = 'Login';
  form: FormGroup;
  submitted = false;
  notification!: DisplayMessage;

  returnUrl = this.configService.posts_url;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private userService: KorisnikServiceService,
    private authService: AuthServiceService,
    private configService: ConfigServiceService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      lozinka: ['', Validators.required]
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {

    this.notification = undefined;
    this.submitted = true;

    this.authService.login(this.form.value)
      .subscribe(data => {
          this.userService.getMyInfo().subscribe();
          this.router.navigate(['/']);
        },
        error => {
          this.submitted = false;
          this.notification = {msgType: 'error', msgBody: 'Username ili lozinka nisu dobri.'};
        });
  }
}
