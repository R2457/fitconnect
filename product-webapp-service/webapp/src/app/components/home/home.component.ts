import { Component, OnInit } from '@angular/core';
import { Admin } from 'src/app/models/admin.model';
import { Gym } from 'src/app/models/gym.model';

import { GymService } from 'src/app/services/gym.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

// constructor(private gymService: GymService) {}

// ngOnInit(): void {
//   // Use the service to make API calls
//   this.gymService.getGymInfo().subscribe((data) => {
//     console.log('Gym Info:', data);
//   });
// }

export class HomeComponent implements OnInit {

  gymInfo!: Gym;

  constructor(private gymService: GymService) { }

  ngOnInit(): void {
    this.getGymInfo();
  }

  private getGymInfo() {
    this.gymService.getGymInfo().subscribe((data) => {
      console.log('Gym Info:', data);
      this.gymInfo = data;
    })
  }

  gymUpdate: boolean = false;

  adminInfo: Admin = {
    userEmail: 'admin@example.com',
    userName: 'Admin Name',
    userProfilePicUrl: ""
  }

  adminUpdate: boolean = false;

  updateGymInfo() {
    if(true) {
      this.gymService.updateGymInfo(this.gymInfo).subscribe((data) => {
        console.log("Updated Gym Info:", data);
        this.gymInfo = data;
      })
    }
    this.gymUpdate = false;
  }

  updateAdminInfo() {
    this.adminUpdate = false;
  }
}
