import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VistaVestimentaComponent } from './vista-vestimenta.component';

describe('VistaVestimentaComponent', () => {
  let component: VistaVestimentaComponent;
  let fixture: ComponentFixture<VistaVestimentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VistaVestimentaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VistaVestimentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
