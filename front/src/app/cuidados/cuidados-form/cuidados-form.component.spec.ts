import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CuidadosFormComponent } from './cuidados-form.component';

describe('CuidadosFormComponent', () => {
  let component: CuidadosFormComponent;
  let fixture: ComponentFixture<CuidadosFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CuidadosFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CuidadosFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
