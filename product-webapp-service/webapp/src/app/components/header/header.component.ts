import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  productTitle = ['Fit', 'Connect'];
  userName = 'User Name';
  isLoggedIn = true;

  signOut() {
    console.log('Signing out...');
  }
}
