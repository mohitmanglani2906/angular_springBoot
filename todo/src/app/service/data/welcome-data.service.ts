import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { API_URL } from 'src/app/app.constants';

export class HelloWorldBean{
  constructor(public message:string) {}
}

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  constructor(
    private http:HttpClient
  ) { }

  executeHelloWorldBeanService() {
    return this.http.get<HelloWorldBean>(`${API_URL}/hello-world-bean`);
    //console.log('Execute Hello World Bean Service')
  }

  executeHelloWorldBeanServicePathVariable(name) {

    // let basicAuthHeadersString = this.createBasicAuthenticationHeader();

    // let headers = new HttpHeaders({
    //   Authorization: basicAuthHeadersString
    // })

    // headers.append('Access-Control-Allow-Headers', 'Origin, Content-Type, X-Auth-Token, content-type')
    // headers.append('Access-Control-Allow-Origin', '*')
    // headers.append('Content-Type','application/json')
    // headers.append('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE')
    
    return this.http.get<HelloWorldBean>(
      `${API_URL}/hello-world-bean/path/${name}`,
      //{headers : headers}
      );
  }

  // createBasicAuthenticationHeader(){
  //   let username='Mohit';
  //   let password = 'dummy';
  //   let basicAuthHeadersString = 'Basic ' + window.btoa(username +':'+ password);

  //   console.log('Basic Auth ___ ' + basicAuthHeadersString)

  //   return basicAuthHeadersString

  // }

}
