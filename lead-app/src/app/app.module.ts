import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './auth/signin/signin.component';
import { SignupComponent } from './auth/signup/signup.component';
import { FooterComponent } from './component/footer/footer.component';
import { HeaderComponent } from './component/header/header.component';
import { HomeComponent } from './container/home/home.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ContactusComponent } from './component/contactus/contactus.component';
import { AdminpanelComponent } from './container/adminpanel/adminpanel.component';
import { LoginComponent } from './container/adminpanel/login/login.component';
import { RegisterComponent } from './container/adminpanel/register/register.component';
import { TablesComponent } from './container/adminpanel/tables/tables.component';
import { ForgetPasswordComponent } from './container/adminpanel/forget-password/forget-password.component'; // If You need animations
@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    SignupComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    ContactusComponent,
    AdminpanelComponent,
    LoginComponent,
    RegisterComponent,
    TablesComponent,
    ForgetPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MDBBootstrapModule.forRoot(),
BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
