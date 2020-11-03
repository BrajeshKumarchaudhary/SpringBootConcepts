import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SigninComponent } from './auth/signin/signin.component';
import { SignupComponent } from './auth/signup/signup.component';
import { AdminpanelComponent } from './container/adminpanel/adminpanel.component';

const routes: Routes = [
{path:"signin", 
component:SigninComponent
},
{
path:"signup",
component:SignupComponent
},
{
path:"adminpanel",
component:AdminpanelComponent
}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
