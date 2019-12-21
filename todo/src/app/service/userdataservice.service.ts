import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../app.constants';




@Injectable({
  providedIn: 'root'
})
export class UserdataserviceService {

  constructor(private http: HttpClient) { }

  saveUser(user){

    console.log("___ User ___ " + user)

    return this.http.post(`${API_URL}/db/users`,user);
  }

}
