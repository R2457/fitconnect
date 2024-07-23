import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private apiUrl = 'http://localhost:8006/feedback';

  constructor(private http: HttpClient) {}

  createFeedback(feedback: Feedback): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.apiUrl}`, feedback);
  }

  getAllFeedback(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.apiUrl}`);
  }

  getFeedbackById(id: number): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.apiUrl}/${id}`);
  }

  deleteFeedback(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  updateFeedback(id: number, updatedFeedback: Feedback): Observable<Feedback> {
    return this.http.put<Feedback>(`${this.apiUrl}/${id}`, updatedFeedback);
  }
}
