import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthServiceService } from '../auth-service.service';
import { ObjavaServiceService } from '../objava-service.service';

@Injectable()
export class TokeninterceptorInterceptor implements HttpInterceptor {

  constructor(public auth: AuthServiceService, post:ObjavaServiceService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.auth.tokenInUse()) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.auth.findToken()}`
        }
      });
    }
    return next.handle(request);
  }
}