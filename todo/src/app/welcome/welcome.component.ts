import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WelcomeDataService } from '../service/data/welcome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  name = ''
  welcomeMessageService:string
  //ActivatedRoute
  constructor(private route: ActivatedRoute,
    private service: WelcomeDataService) { }

  ngOnInit() {
    console.log(this.route.snapshot.params['name']);
    this.name = this.route.snapshot.params['name'];
  }

  getWelcomeMessage() {
    // console.log(this.service.executeHelloWorldBeanService());
    this.service.executeHelloWorldBeanService().subscribe(
      response => this.handleSuccessfulResponse(response.message),
      error => this.handleErrorResponse(error)
    );
    // console.log('Welcome Message')
  }

  getWelcomeMessagePathVariable() {
    // console.log(this.service.executeHelloWorldBeanService());

    this.service.executeHelloWorldBeanServicePathVariable(this.name).subscribe(
      response => {
        this.handleSuccessfulResponse(response)
      },
      error => this.handleErrorResponse(error)
    );
    // console.log('Welcome Message ')

  }

  handleSuccessfulResponse(response){
    console.log(response)
    this.welcomeMessageService = response.message;
  }

  handleErrorResponse(error){
    console.log(error)
    this.welcomeMessageService = "Something went wrong!!"
  }


}
