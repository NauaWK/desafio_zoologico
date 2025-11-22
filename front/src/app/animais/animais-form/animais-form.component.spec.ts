import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimaisFormComponent } from './animais-form.component';

describe('AnimaisFormComponent', () => {
  let component: AnimaisFormComponent;
  let fixture: ComponentFixture<AnimaisFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnimaisFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AnimaisFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
