import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewStationsComponent } from './view-stations.component';

describe('ViewStationsComponent', () => {
  let component: ViewStationsComponent;
  let fixture: ComponentFixture<ViewStationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewStationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewStationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
