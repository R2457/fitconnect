export class Plan {
    planId!: string;
    planName: string;
    planPrice: number;
    planDuration: string;

    constructor(planName: string, planPrice: number, planDuration: string) {
        this.planName = planName;
        this.planPrice = planPrice;
        this.planDuration = planDuration;
    }
    
}
