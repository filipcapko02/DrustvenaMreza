import { TestBed } from '@angular/core/testing';

import { TokeninterceptorInterceptor } from './token-interceptor';
describe('TokeninterceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      TokeninterceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: TokeninterceptorInterceptor = TestBed.inject(TokeninterceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
