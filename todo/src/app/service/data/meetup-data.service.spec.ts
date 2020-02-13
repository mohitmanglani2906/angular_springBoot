import { TestBed } from '@angular/core/testing';

import { MeetupDataService } from './meetup-data.service';

describe('MeetupDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MeetupDataService = TestBed.get(MeetupDataService);
    expect(service).toBeTruthy();
  });
});
