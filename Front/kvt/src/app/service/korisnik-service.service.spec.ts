import { TestBed } from '@angular/core/testing';

import { KorisnikServiceService } from './korisnik-service.service';

describe('KorisnikServiceService', () => {
  let service: KorisnikServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KorisnikServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
