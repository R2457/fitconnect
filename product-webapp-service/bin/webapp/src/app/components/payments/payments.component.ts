import { Component } from '@angular/core';
import { Payment } from 'src/app/models/payment.model';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent {
  paymentList: Payment[] = [
    {
      paymentId: 1,
      userEmail: 'user1@example.com',
      paymentTitle: 'Payment 1',
      amount: 100.0,
      paymentStatus: 'Success',
      paymentMode: 'Credit Card',
      timestamp: '2023-09-08T12:00:00',
    },
    {
      paymentId: 2,
      userEmail: 'user2@example.com',
      paymentTitle: 'Payment 2',
      amount: 150.0,
      paymentStatus: 'Fail',
      paymentMode: 'PayPal',
      timestamp: '2023-09-08T13:30:00',
    },
    {
      paymentId: 1,
      userEmail: 'user1@example.com',
      paymentTitle: 'Payment 1',
      amount: 100.0,
      paymentStatus: 'Success',
      paymentMode: 'Credit Card',
      timestamp: '2023-09-08T12:00:00',
    },
    {
      paymentId: 2,
      userEmail: 'user2@example.com',
      paymentTitle: 'Payment 2',
      amount: 150.0,
      paymentStatus: 'Fail',
      paymentMode: 'PayPal',
      timestamp: '2023-09-08T13:30:00',
    },
    {
      paymentId: 1,
      userEmail: 'user1@example.com',
      paymentTitle: 'Payment 1',
      amount: 100.0,
      paymentStatus: 'Success',
      paymentMode: 'Credit Card',
      timestamp: '2023-09-08T12:00:00',
    },
    {
      paymentId: 2,
      userEmail: 'user2@example.com',
      paymentTitle: 'Payment 2',
      amount: 150.0,
      paymentStatus: 'Fail',
      paymentMode: 'PayPal',
      timestamp: '2023-09-08T13:30:00',
    },
    {
      paymentId: 1,
      userEmail: 'user1@example.com',
      paymentTitle: 'Payment 1',
      amount: 100.0,
      paymentStatus: 'Success',
      paymentMode: 'Credit Card',
      timestamp: '2023-09-08T12:00:00',
    },
    {
      paymentId: 2,
      userEmail: 'user2@example.com',
      paymentTitle: 'Payment 2',
      amount: 150.0,
      paymentStatus: 'Fail',
      paymentMode: 'PayPal',
      timestamp: '2023-09-08T13:30:00',
    },
    {
      paymentId: 1,
      userEmail: 'user1@example.com',
      paymentTitle: 'Payment 1',
      amount: 100.0,
      paymentStatus: 'Success',
      paymentMode: 'Credit Card',
      timestamp: '2023-09-08T12:00:00',
    },
    {
      paymentId: 2,
      userEmail: 'user2@example.com',
      paymentTitle: 'Payment 2',
      amount: 150.0,
      paymentStatus: 'Fail',
      paymentMode: 'PayPal',
      timestamp: '2023-09-08T13:30:00',
    }
  ];

  selectedPayment: Payment | null = null;

  ngOnInit() {
    if (this.paymentList.length > 0) {
      this.selectedPayment = this.paymentList[0];
    }
  }
  selectPayment(payment: Payment) {
    this.selectedPayment = payment;
  }
}
