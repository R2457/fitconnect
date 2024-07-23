import { Component, OnInit } from '@angular/core';
import { Equipment } from 'src/app/models/equipment.model';
import { GymService } from 'src/app/services/gym.service';

@Component({
  selector: 'app-equipments',
  templateUrl: './equipments.component.html',
  styleUrls: ['./equipments.component.css']
})
export class EquipmentsComponent implements OnInit {

  constructor(private gymService: GymService) { }

  ngOnInit() {
    this.getEquipmentList();
  }
  equipmentList: Equipment[] = [];

  selectedEquipment: Equipment | null = null;
  formMode = false;
  updateMode = false;
  newEquipmentName = '';
  newEquipmentDescription = '';
  newQuantity = 0;
  selectedImageFile: File | null = null;

  selectEquipment(equipment: Equipment) {
    this.selectedEquipment = equipment;
  }



  updateEquipmentForm(equipment: Equipment) {
    this.formMode = true;
    this.updateMode = true;
    this.selectedEquipment = equipment;
    this.newEquipmentName = equipment.equipmentName;
    this.newEquipmentDescription = equipment.equipmentDescription;
    this.newQuantity = equipment.quantity;
  }

  addEquipmentForm() {
    this.formMode = true;
    this.updateMode = false;
    this.selectedEquipment = null;
    this.newEquipmentName = '';
    this.newEquipmentDescription = '';
    this.newQuantity = 0;
    this.selectedImageFile = null;
  }

  closeForm() {
    this.formMode = false;
    this.updateMode = false;
  }

  private getEquipmentList() {
    this.gymService.getEquipmentList().subscribe((data) => {
      console.log("Equipment List:", data);
      this.equipmentList = data;
      if (data == null || data.length == 0) {
        this.addEquipmentForm();
      } else {
        this.selectedEquipment = this.equipmentList[0];
      }
    });
  }

  createEquipment() {
    if (this.selectedImageFile && this.newEquipmentName && this.newEquipmentDescription && this.newQuantity) {
      const equipment = new FormData();
      equipment.append('equipmentName', this.newEquipmentName);
      equipment.append('equipmentDescription', this.newEquipmentDescription);
      equipment.append('quantity', this.newQuantity.toString());
      equipment.append('equipmentImage', this.selectedImageFile);

      this.gymService.addAnEquipment(equipment).subscribe((data) => {
        console.log("Equipment Added:", data);
        this.getEquipmentList();
        this.formMode = false;
        this.updateMode = false;
        this.newEquipmentName = '';
        this.newEquipmentDescription = '';
        this.newQuantity = 0;
        this.selectedImageFile = null;
      }
      );
    }
  }

  updateEquipment() {
    if (this.selectedEquipment) {
      const equipment = new FormData();
      equipment.append('equipmentName', this.newEquipmentName);
      equipment.append('equipmentDescription', this.newEquipmentDescription);
      equipment.append('quantity', this.newQuantity.toString());
      if (this.selectedImageFile) {
        equipment.append('equipmentImage', this.selectedImageFile);
      }

      this.gymService.updateEquipment(this.selectedEquipment.equipmentId, equipment).subscribe((data) => {
        console.log("Equipment Updated:", data);
        this.getEquipmentList();
        this.formMode = false;
        this.updateMode = false;
        this.newEquipmentName = '';
        this.newEquipmentDescription = '';
        this.newQuantity = 0;
        this.selectedImageFile = null;
      }
      );
    }
  }

  deleteEquipment(equipmentId: string) {
    if (this.selectedEquipment && this.selectedEquipment.equipmentId == equipmentId && confirm("Are you sure you want to delete this equipment?")) {

      this.gymService.deleteEquipment(equipmentId).subscribe(
        (data) => {
          console.log("Equipment is successfully deleted");
          this.getEquipmentList();
          this.closeForm();
        }
        , (error) => {
          console.log("Error in deleting equipment", error);
        });
    }

  }

  isEquipmentAvailable(equipment: Equipment): boolean {
    return equipment.quantity > 0;
  }

  onImageSelect(event: any) {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files.length > 0) {
      this.selectedImageFile = fileInput.files[0];
    }
  }
}
