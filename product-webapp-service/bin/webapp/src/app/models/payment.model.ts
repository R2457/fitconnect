export interface Payment {
    paymentId: number;
    userEmail: string;
    paymentTitle: string;
    amount: number;
    paymentStatus: string;
    paymentMode: string;
    timestamp: string;
}
