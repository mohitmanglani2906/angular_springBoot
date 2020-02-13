import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MeetUp } from 'src/app/meet-up-list/meet-up-list.component';
import { TODO_JPA_API_URL } from 'src/app/app.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MeetupDataService {

  constructor(private http: HttpClient) { }

  retrieveMeetUpEvents(): Observable<MeetUp[]>{
      console.log('in service class')
      return this.http.get<MeetUp[]>(`${TODO_JPA_API_URL}/events/meetup`)
        
  }

  // private extractData(res: Response){
  //   let body = res.json()

  // }

}
