import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Slot } from 'src/app/models/slot.model';
import { Trainer } from 'src/app/models/trainer.model';
import { GymService } from 'src/app/services/gym.service';

@Component({
  selector: 'app-slots',
  templateUrl: './slots.component.html',
  styleUrls: ['./slots.component.css'],
})
export class SlotsComponent implements OnInit {
  slotList!: Slot[];

  constructor(private gymService: GymService) {
    this.minDate = new Date();
    this.maxDate = new Date();
    this.maxDate.setDate(this.minDate.getDate() + 5);
  }

  ngOnInit() {
    this.getSlotList();
    this.getTrainersList();
  }

  selectedSlot: Slot | null = null;
  formMode = false;
  updateMode = false;
  updateSlotId!: string;
  minDate: Date;
  maxDate: Date;
  newSlotDate!: string;
  newTimePeriod: string = '06:00 AM - 08:00 AM';
  selectedTrainers: string[] = [];
  newMaxLimit: number = 10;
  trainersList: Trainer[] = [];
  timingsList: string[] = [
    '06:00 AM - 08:00 AM',
    '08:30 AM - 10:30 AM',
    '11:00 AM - 01:00 PM',
    '04:00 PM - 06:00 PM',
    '06:30 PM - 08:30 PM',
    '09:00 PM - 11:00 PM'
  ];

  private getSlotList() {
    this.gymService.getSlotList().subscribe((data) => {
      console.log("Slot List:", data);

      if (data == null || data.length == 0) {
        this.formMode = true;
        this.updateMode = false;
        this.newSlotDate = '';
        this.selectedTrainers = [];
        this.newMaxLimit = 10;
      }
      else {
        this.formMode = false;
        this.updateMode = false;
        this.slotList = data.map((slot) => {
          const startTime = this.convertTo12HourFormat(slot.startTime);
          const endTime = this.convertTo12HourFormat(slot.endTime);
          return { ...slot, startTime, endTime };
        });
        if (this.slotList.length > 0) {
          this.selectedSlot = this.slotList[0];
        }
      }



    });

  }




  selectSlot(slot: Slot) {
    this.selectedSlot = slot;
  }

  closeForm() {
    this.formMode = false;
  }


  updateSlotForm(slot: Slot) {
    this.updateSlotId = slot.slotId;
    this.formMode = true;
    this.updateMode = true;
    this.newSlotDate = slot.slotDate;
    this.selectedTrainers = slot.trainerList;
    this.newMaxLimit = slot.maximumLimit;
    this.newTimePeriod = slot.startTime + " - " + slot.endTime;
  }

  addSlotForm() {
    this.formMode = true;
    this.updateMode = false;
    this.newSlotDate = '';
    this.selectedTrainers = [];
    this.newMaxLimit = 10;
  }

  getTrainerName(trainerId: string): string {
    const trainer = this.trainersList.find((trainer) => trainer.trainerId === trainerId);
    return trainer?.trainerName || '';
  }

  getTrainerImage(trainerId: string): string {
    const trainer = this.trainersList.find((trainer) => trainer.trainerId === trainerId);
    return trainer?.trainerImage || '';
  }

  private getTrainersList() {
    this.gymService.getTrainers().subscribe((data) => {
      console.log("Trainers List:", data);
      this.trainersList = data;
    });
  }
  createSlot() {
    const [startTime, endTime] = this.newTimePeriod.split('-').map((time) => time.trim());
    const newSlot: Slot = {
      slotId: '',
      startTime: this.convertTo24HourFormat(startTime),
      endTime: this.convertTo24HourFormat(endTime),
      maximumLimit: this.newMaxLimit,
      slotDate: this.newSlotDate,
      trainerList: this.selectedTrainers,
    };

    this.gymService.addASlot(newSlot).subscribe((data) => {
      console.log("Slot Added:", data);
      this.getSlotList();
      this.formMode = false;
    });

  }



  updateSlot() {
    const [startTime, endTime] = this.newTimePeriod.split('-').map((time) => time.trim());
    const updatedSlot: Slot = {
      slotId: this.selectedSlot?.slotId || '',
      startTime: this.convertTo24HourFormat(startTime),
      endTime: this.convertTo24HourFormat(endTime),
      maximumLimit: this.newMaxLimit,
      slotDate: this.newSlotDate,
      trainerList: this.selectedTrainers,
    };

    this.gymService.updateSlot(this.updateSlotId, updatedSlot).subscribe((data) => {
      console.log("Slot Updated:", data);
      this.getSlotList();
      this.formMode = false;
    });

  }

  deleteSlot(slotId: string) {
    if (this.selectedSlot) {
      if (confirm(`Are you sure you want to delete the slot ${this.selectedSlot.slotDate} ${this.selectedSlot.startTime} - ${this.selectedSlot.endTime}?`)) {
        this.gymService.deleteSlot(slotId).subscribe(
          (response) => {
            console.log("Slot is Deleted successfully");
            this.getSlotList();
          },
          (error) => {
            console.error("Error deleting Slot:", error);
          }
        );
      }
    }

  }



  isSlotActive(slot: Slot): boolean {
    const currentDate = new Date();
    const startTime = new Date(`${slot.slotDate} ${slot.startTime}`);
    return currentDate < startTime;
  }


  private convertTo12HourFormat(time24: string): string {
    const [hours, minutes] = time24.split(':').map(Number);
    const period = hours >= 12 ? 'PM' : 'AM';
    const convertedHours = hours % 12 === 0 ? 12 : hours % 12;
    return `${convertedHours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')} ${period}`;
  }

  // Convert "08:00 PM" to "20:00:00"
  private convertTo24HourFormat(time12: string): string {
    const [time, period] = time12.split(' ');
    const [hours, minutes] = time.split(':').map(Number);
    const isPM = period.toLowerCase() === 'pm';
    const convertedHours = isPM ? hours + 12 : hours % 12;
    return `${convertedHours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:00`;
  }
}