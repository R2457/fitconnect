export class Equipment {
    equipmentId!: string;
    equipmentName: string;
    equipmentImage!: string;
    equipmentDescription: string;
    quantity: number;

    constructor(equipmentName: string, equipmentDescription: string, quantity: number) {
        this.equipmentName = equipmentName;
        this.equipmentDescription = equipmentDescription;
        this.quantity = quantity;
    }
}
