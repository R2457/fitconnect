import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackListComponent } from './feedback.component';

describe('FeedbackComponent', () => {
  let component: FeedbackListComponent;
  let fixture: ComponentFixture<FeedbackListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
