import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css'],
  standalone: true,
  imports: [],
})
export class FeedbackListComponent implements OnInit {
  feedbackList: Feedback[] = [];

  constructor(private feedbackService: FeedbackService) {}

  ngOnInit(): void {
    this.feedbackService.getAllFeedback().subscribe(
      (feedback) => {
        this.feedbackList = feedback;
      },
      (error) => {
        console.error('Error fetching feedback:', error);
      }
    );
  }
}