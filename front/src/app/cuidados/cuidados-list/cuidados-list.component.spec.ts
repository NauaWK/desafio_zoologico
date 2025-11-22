import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CuidadosListComponent } from './cuidados-list.component';

describe('CuidadosListComponent', () => {
  let component: CuidadosListComponent;
  let fixture: ComponentFixture<CuidadosListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CuidadosListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CuidadosListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
