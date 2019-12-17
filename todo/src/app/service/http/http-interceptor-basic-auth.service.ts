import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { BasicAuthenticationService } from '../basic-authentication.service';


@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorBasicAuthService implements HttpInterceptor {

  constructor(
    private basicAuthenticationService: BasicAuthenticationService

  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {

    // console.log("___ In InterCeptor ____ ")

    // let username='Mohit';
    // let password = 'dummy';
    // let basicAuthHeadersString = 'Basic ' + window.btoa(username +':'+ password);

    let basicAuthHeadersString = this.basicAuthenticationService.getAuthenticatedToken();
    let userName = this.basicAuthenticationService.getAuthenticatedUser();

    console.log("___ TOKEN ____ " + basicAuthHeadersString)

    if (basicAuthHeadersString && userName) {
      request = request.clone({
        setHeaders: {
          Authorization: basicAuthHeadersString
        }
      })
    }
    return next.handle(request);

  }
}
{
}
