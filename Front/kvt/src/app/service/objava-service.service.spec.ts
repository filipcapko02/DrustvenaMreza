import { TestBed } from '@angular/core/testing';

import { ObjavaServiceService } from './objava-service.service';

describe('ObjavaServiceService', () => {
  let service: ObjavaServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObjavaServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
