import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Gym } from '../models/gym.model';
import { Slot } from '../models/slot.model';
import { Trainer } from '../models/trainer.model';
import { Plan } from '../models/plan.model';
import { MediaFile } from '../models/mediafile.model';
import { Equipment } from '../models/equipment.model';


@Injectable({
  providedIn: 'root',
})
export class GymService {
  private apiUrl = 'http://localhost:8008/api/v1/gym-service';

  constructor(private http: HttpClient) {}

  getGymInfo(): Observable<Gym> {
    return this.http.get<Gym>(`${this.apiUrl}/gym-info`);
  }

  updateGymInfo(gymInfo: Gym): Observable<Gym> {
    return this.http.put<Gym>(`${this.apiUrl}/gym-info`, gymInfo);
  }



  getSlotList(): Observable<Slot[]> {
    return this.http.get<Slot[]>(`${this.apiUrl}/slots`);
  }

  addASlot(slot: Slot): Observable<Slot> {
    return this.http.post<Slot>(`${this.apiUrl}/slots`, slot);
  }

  updateSlot(slotId: string, slot: Slot): Observable<Slot> {
    return this.http.put<Slot>(`${this.apiUrl}/slots/${slotId}`, slot);
  }

  deleteSlot(slotId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/slots/${slotId}`);
  }



  getPlanList(): Observable<Plan[]> {
    return this.http.get<Plan[]>(`${this.apiUrl}/plans`);
  }

  addAPlan(plan: Plan): Observable<Plan> {
    return this.http.post<Plan>(`${this.apiUrl}/plans`, plan);
  }

  updatePlan(planId: string, plan: Plan): Observable<Plan> {
    return this.http.put<Plan>(`${this.apiUrl}/plans/${planId}`, plan);
  }

  deletePlan(planId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/plans/${planId}`);
  }



  getEquipmentList(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/equipments`);
  }

  addAnEquipment(equipment: FormData): Observable<Equipment> {
    return this.http.post<Equipment>(`${this.apiUrl}/equipments`, equipment);
  }

  updateEquipment(equipmentId: string, newEquipment: FormData): Observable<Equipment> {
    return this.http.put<Equipment>(`${this.apiUrl}/equipments/${equipmentId}`, newEquipment);
  }

  deleteEquipment(equipmentId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/equipments/${equipmentId}`);
  }



  getTrainers(): Observable<Trainer[]> {
    return this.http.get<Trainer[]>(`${this.apiUrl}/trainers`);
  }

  addATrainer(trainer: FormData): Observable<Trainer> {
    return this.http.post<Trainer>(`${this.apiUrl}/trainers`, trainer);
  }

  updateTrainer(trainerId: string, trainer: FormData): Observable<Trainer> {
    return this.http.put<Trainer>(`${this.apiUrl}/trainers/${trainerId}`, trainer);
  }

  deleteTrainer(trainerId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/trainers/${trainerId}`);
  }

  getTrainerById(trainerId: string): Observable<Trainer> {
    return this.http.get<Trainer>(`${this.apiUrl}/trainers/${trainerId}`);
  }

  getTrainerBySlotId(slotId: string): Observable<Trainer> {
    return this.http.get<Trainer>(`${this.apiUrl}/trainers/slot/${slotId}`);
  }


  getMediaList(): Observable<MediaFile[]> {
    return this.http.get<MediaFile[]>(`${this.apiUrl}/medias`);
  }

  addAMedia(media: FormData): Observable<MediaFile> {
    return this.http.post<MediaFile>(`${this.apiUrl}/medias`, media);
  }

  updateMedia(mediaId: string, media: FormData): Observable<MediaFile> {
    return this.http.put<MediaFile>(`${this.apiUrl}/medias/${mediaId}`, media);
  }

  deleteMedia(mediaId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/medias/${mediaId}`);
  }




}
