import { Component } from '@angular/core';
import { Payment } from 'src/app/models/payment.model';
import { PaymentServiceService } from 'src/app/services/payment-service.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent {
  constructor(private ps: PaymentServiceService) {}

  paymentList: Payment[] = [];

  selectedPayment: Payment | null = null;

  ngOnInit() {
    if (this.paymentList.length > 0) {
      this.selectedPayment = this.paymentList[0];
    }
  }

  getAllTransactions(){
    this.ps.getAllTransactions().subscribe((response: any)=>{
      this.paymentList=response;
    })
  }

  selectPayment(payment: Payment) {
    this.selectedPayment = payment;
  }
}
