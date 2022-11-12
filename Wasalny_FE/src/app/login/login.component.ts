import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { UsersService } from '../users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  
public username ="";
public password ="";
  constructor(private _userService: UsersService,private router: Router) { }

  ngOnInit(): void {
  }

  signin() {
    const data = {
      username: this.username,
      password: this.password
    };
      this. _userService.signin(data)
      .subscribe({
            next: _data => {
              console.log(_data);
              console.log("*********************");
              this.router.navigate(['/']);
              
            },
            error: error => {
                console.error('There was an error!', error);
            }
        })
    }
  
}

