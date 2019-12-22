import { Component, OnInit } from '@angular/core';
import { UserdataserviceService } from '../service/userdataservice.service';
import { Router } from '@angular/router';

export class User {
 // constructor(
    public id: number
    public name: string
    public age: number
    public email: any
    public username:any
    public password:any
    public dateOfBirth: Date
 // ) { }
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user:User = new User();
  
  inValidUser = false
  errorMessage = ""
  
  constructor(private router: Router,private userdataserviceService : UserdataserviceService) { }

  ngOnInit() {
  }



  signUpUser()
  {
      // console.log("____ User ____ " + this.user.age)

      this.userdataserviceService.saveUser(this.user)
        .subscribe(
          data => {
            console.log('Success')
            this.router.navigate(['login'])
            this.inValidUser = false
          },
          error =>{
              console.log('Failure')
              this.inValidUser = true
              this.errorMessage = 'Something Went Wrong!!'
          }
        )  
  }

  

}
