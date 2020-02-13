import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from "@angular/forms";
import { ErrorComponent } from './error/error.component';
import { ListTodosComponent } from './list-todos/list-todos.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { LogoutComponent } from './logout/logout.component'; 
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TodoComponent } from './todo/todo.component';
import { HttpInterceptorBasicAuthService } from './service/http/http-interceptor-basic-auth.service';
import { SignupComponent } from './signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MeetUpListComponent } from './meet-up-list/meet-up-list.component';

// Needed to add FormsModule in this file for ngModel for input tag
@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
    ErrorComponent,
    ListTodosComponent,
    MenuComponent,
    FooterComponent,
    LogoutComponent,
    TodoComponent,
    SignupComponent,
    MeetUpListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule  
    
  ],
  providers: [
    {
        //For InterCeptor One Type Of Filter
        provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorBasicAuthService, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
