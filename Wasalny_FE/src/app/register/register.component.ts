import { Component, OnInit } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { UsersService } from '../users.service';

const routes: Routes = [
  { path: '', redirectTo: '/AppComponent', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },

];
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public username ="";
  public password ="";
  constructor(private _userService: UsersService,private router: Router) { }

  ngOnInit(): void {
  }

  signUp(){
    const data = {
      username: this.username,
      password: this.password
    };
    this. _userService.signup(data)
      .subscribe((_data) => { //for when we connect the API
        console.log(_data);
        this.router.navigate(['/login']);
      });
  }

}
