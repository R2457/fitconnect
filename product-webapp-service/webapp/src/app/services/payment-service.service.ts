import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaymentServiceService {

  constructor(private httpClient: HttpClient) {}

    public createTransaction(amount: any) {
      return this.httpClient.get("http://localhost:8012/payment/created/"+amount);
    }

    public getAllTransactions() {
      return this.httpClient.get("http://localhost:8012/payment/all");
    }
}
