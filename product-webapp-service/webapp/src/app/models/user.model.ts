export interface User {
  userEmail: string;
  userPassword: string;
  userName: string;
  userAge: number;
  userMobile: string;
  userProfilePicUrl: string;
  userHeight: string;
  userWeight: number;
  planName: string | null;
  planPrice: number | null;
  planDuration: string | null;
  expirationDate: Date | null;
}
