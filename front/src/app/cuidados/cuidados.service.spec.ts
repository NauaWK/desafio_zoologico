import { TestBed } from '@angular/core/testing';

import { CuidadosService } from './cuidados.service';

describe('CuidadosService', () => {
  let service: CuidadosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CuidadosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
