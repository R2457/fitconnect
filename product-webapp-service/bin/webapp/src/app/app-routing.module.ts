import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UsersComponent } from './components/users/users.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { SlotsComponent } from './components/slots/slots.component';
import { PlansComponent } from './components/plans/plans.component';
import { EquipmentsComponent } from './components/equipments/equipments.component';
import { TrainersComponent } from './components/trainers/trainers.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { ChatsComponent } from './components/chats/chats.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'users', component: UsersComponent },
  { path: 'payments', component: PaymentsComponent },
  { path: 'slots', component: SlotsComponent },
  { path: 'plans', component: PlansComponent },
  { path: 'equipments', component: EquipmentsComponent },
  { path: 'trainers', component: TrainersComponent },
  { path: 'gallery', component: GalleryComponent },
  { path: 'chats', component: ChatsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
