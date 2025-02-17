
import { HttpClient } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { Admin } from './Admin';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(private http: HttpClient) { }
  signin(data: any) {
    console.log(data);
    // console.log(this.admin);
    let url ='http://be2-trip2-scheduling-app.apps.eu410.prod.nextcle.com/api/user/login';
    // "http://localhost:8080/api/user/login"
    console.log(this.http.post(url, data));
    return this.http.post(url, data);
  }

  signup(data: any) {
    console.log(data);
    let url ='http://be2-trip2-scheduling-app.apps.eu410.prod.nextcle.com/api/user/signup';
    // "http://localhost:8080/api/user/signup"
    console.log(this.http.post(url, data));
    return this.http.post(url, data);
  }
}
