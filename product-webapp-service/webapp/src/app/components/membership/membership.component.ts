import { Component } from '@angular/core';

@Component({
  selector: 'app-membership',
  templateUrl: './membership.component.html',
  styleUrls: ['./membership.component.css']
})
export class MembershipComponent {
  paymentService: any;

  createTransactionAnsPlaceOrder(){
    this.paymentService.createTransaction().subscribe(
      (response: any)=> {
        console.log(response);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  openTransactionModel(response: any) {
    var options = {
      order_id: response.paymentId,
      key: response.key,
      amount: response.amount,
      currency: response.currency,
      userEmail: response.userEmail,
      paymentStatus: response.paymentStatus,
      description: response.paymentTitle,
      
    };
  }
}
