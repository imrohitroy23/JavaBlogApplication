import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetcatComponent } from './getcat.component';

describe('GetcatComponent', () => {
  let component: GetcatComponent;
  let fixture: ComponentFixture<GetcatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetcatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetcatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
