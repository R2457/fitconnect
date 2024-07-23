import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatIcon } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatExpansionModule } from '@angular/material/expansion';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ReactiveFormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './components/header/header.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './components/home/home.component';
import { UsersComponent } from './components/users/users.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { SlotsComponent } from './components/slots/slots.component';
import { PlansComponent } from './components/plans/plans.component';
import { EquipmentsComponent } from './components/equipments/equipments.component';
import { TrainersComponent } from './components/trainers/trainers.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { ChatsComponent } from './components/chats/chats.component';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { UserSidebarComponent } from './components/user-sidebar/user-sidebar.component';
import { MembershipComponent } from './components/membership/membership.component';
import { FeedbackComponent } from './components/feedback/feedback.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    LayoutComponent,
    HomeComponent,
    UsersComponent,
    PaymentsComponent,
    SlotsComponent,
    PlansComponent,
    EquipmentsComponent,
    TrainersComponent,
    GalleryComponent,
    ChatsComponent,
    UserHomeComponent,
    UserSidebarComponent, 
    MembershipComponent,
    FeedbackComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    MatIcon,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatExpansionModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    HttpClientModule,
    MatIconModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    NgModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
