import { Component } from '@angular/core';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent {
  userList: User[] = [
    {
      userEmail: 'user1@example.com',
      userPassword: 'password1',
      userName: 'User One',
      userAge: 25,
      userMobile: '123-456-7890',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '5\'10"',
      userWeight: 160,
      planName: 'Basic Plan',
      planPrice: 29.99,
      planDuration: '1 month',
      expirationDate: new Date('2023-12-31'),
    },
    {
      userEmail: 'user2@example.com',
      userPassword: 'password2',
      userName: 'User Two',
      userAge: 30,
      userMobile: '987-654-3210',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '6\'0"',
      userWeight: 180,
      planName: 'Premium Plan',
      planPrice: 49.99,
      planDuration: '3 months',
      expirationDate: new Date('2024-03-31'),
    },
    {
      userEmail: 'user3@example.com',
      userPassword: 'password3',
      userName: 'User Three',
      userAge: 28,
      userMobile: '555-123-4567',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '5\'8"',
      userWeight: 150,
      planName: 'Pro Plan',
      planPrice: 79.99,
      planDuration: '6 months',
      expirationDate: new Date('2024-06-30'),
    },
    {
      userEmail: 'user4@example.com',
      userPassword: 'password4',
      userName: 'User Four',
      userAge: 35,
      userMobile: '444-555-6666',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '5\'9"',
      userWeight: 170,
      planName: 'Gold Plan',
      planPrice: 99.99,
      planDuration: '12 months',
      expirationDate: new Date('2024-12-31'),
    },
    {
      userEmail: 'user5@example.com',
      userPassword: 'password5',
      userName: 'User Five',
      userAge: 22,
      userMobile: '111-222-3333',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '6\'2"',
      userWeight: 190,
      planName: null,
      planPrice: null,
      planDuration: null,
      expirationDate: null,
    },
    {
      userEmail: 'user6@example.com',
      userPassword: 'password6',
      userName: 'User Six',
      userAge: 33,
      userMobile: '777-888-9999',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '5\'11"',
      userWeight: 170,
      planName: 'Standard Plan',
      planPrice: 39.99,
      planDuration: '2 months',
      expirationDate: new Date('2024-02-29'),
    },
    {
      userEmail: 'user7@example.com',
      userPassword: 'password7',
      userName: 'User Seven',
      userAge: 29,
      userMobile: '123-987-4567',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '5\'7"',
      userWeight: 155,
      planName: null,
      planPrice: null,
      planDuration: null,
      expirationDate: null,
    },
    {
      userEmail: 'user8@example.com',
      userPassword: 'password8',
      userName: 'User Eight',
      userAge: 26,
      userMobile: '555-555-5555',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '6\'1"',
      userWeight: 175,
      planName: 'Elite Plan',
      planPrice: 69.99,
      planDuration: '4 months',
      expirationDate: new Date('2024-04-30'),
    },
    {
      userEmail: 'user9@example.com',
      userPassword: 'password9',
      userName: 'User Nine',
      userAge: 31,
      userMobile: '999-111-7777',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '5\'6"',
      userWeight: 145,
      planName: null,
      planPrice: null,
      planDuration: null,
      expirationDate: null,
    },
    {
      userEmail: 'user10@example.com',
      userPassword: 'password10',
      userName: 'User Ten',
      userAge: 27,
      userMobile: '333-666-8888',
      userProfilePicUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      userHeight: '5\'5"',
      userWeight: 140,
      planName: 'Premium Plan',
      planPrice: 49.99,
      planDuration: '3 months',
      expirationDate: new Date('2024-03-15'),
    },
  ];
  

  selectedUser: User | null = null;


  ngOnInit() {
    if (this.userList.length > 0) {
      this.selectedUser = this.userList[0];
    }
  }

  selectUser(user: User) {
    this.selectedUser = user;
  }
}
