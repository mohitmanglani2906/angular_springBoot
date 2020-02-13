import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetUpListComponent } from './meet-up-list.component';

describe('MeetUpListComponent', () => {
  let component: MeetUpListComponent;
  let fixture: ComponentFixture<MeetUpListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeetUpListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeetUpListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
