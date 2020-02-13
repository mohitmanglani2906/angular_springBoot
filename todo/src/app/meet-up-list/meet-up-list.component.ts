import { Component, OnInit } from '@angular/core';
import { MeetupDataService } from '../service/data/meetup-data.service';

export class MeetUp {
  constructor(
    public venue_name: Object,
    public response: Object,
    public member_name: Object,
    public picUrl: Object,
    public event_name:Object,
    public event_url: Object, 
    public latitude: Object,
    public longitude: Object 

  ) {

  }
}

@Component({
  selector: 'app-meet-up-list',
  templateUrl: './meet-up-list.component.html',
  styleUrls: ['./meet-up-list.component.css']
})
export class MeetUpListComponent implements OnInit {

  meetups
  loadMessage = 'Loading...'
  errorMessage
  constructor(private service: MeetupDataService) { }

  ngOnInit() {
    this.retrieveMeetUpEvents()
  }

  retrieveMeetUpEvents(){

    console.log("Called")
    this.meetups = MeetUp[100]
    this.service.retrieveMeetUpEvents().subscribe(
      response =>{
        this.meetups = response
      },
      error =>{
        this.errorMessage = "Please Refresh!!!"
        console.log(error)
      }

    )
  }

}
