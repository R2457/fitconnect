import { Component, OnInit } from '@angular/core';
import { Plan } from 'src/app/models/plan.model';
import { GymService } from 'src/app/services/gym.service';

@Component({
  selector: 'app-plans',
  templateUrl: './plans.component.html',
  styleUrls: ['./plans.component.css'],
})
export class PlansComponent implements OnInit{

  constructor(private gymService: GymService) { }

  ngOnInit() {
    this.getPlanList();
  }

  planList: Plan[] = [];
  selectedPlan: Plan | null = null;
  formMode = false;
  updateMode = false;
  newPlanName = '';
  newPrice = 0;
  newDuration = '';
  durationList: string[] = ['1', '2', '3', '6', '12', '18', '24'];

  selectPlan(plan: Plan) {
    this.selectedPlan = plan;
  }

  getPlanList() {
    this.gymService.getPlanList().subscribe((data) => {
      console.log("Plan List:", data);
      this.planList = data;
      if(data == null || data.length == 0) {
        this.formMode = true;
        this.updateMode = false;
      } else {
        this.selectedPlan = this.planList[0];
      }
    });
  }

  updatePlanForm(plan: Plan) {
    this.formMode = true;
    this.updateMode = true;
    this.newPlanName = plan.planName;
    this.newPrice = plan.planPrice;
    this.newDuration = plan.planDuration;
  }

  addPlanForm() {
    this.formMode = true;
    this.updateMode = false;
    this.newPlanName = '';
    this.newPrice = 0;
    this.newDuration = '';
  }

  closeForm() {
    this.formMode = false;
  }

  createPlan() {
    if(this.newPlanName && this.newPrice && this.newDuration) {
      const plan = new Plan(this.newPlanName, this.newPrice, this.newDuration);
      this.gymService.addAPlan(plan).subscribe((data) => {
        console.log("Plan Added:", data);
        this.getPlanList();
        this.formMode = false;
        this.updateMode = false;
        this.newPlanName = '';
        this.newPrice = 0;
        this.newDuration = '';
      });
    }
  }

  updatePlan() {
    if (this.selectedPlan) {
      const plan = new Plan(this.newPlanName, this.newPrice, this.newDuration);
      this.gymService.updatePlan(this.selectedPlan.planId, plan).subscribe((data) => {
        console.log("Plan Updated:", data);
        this.getPlanList();
        this.formMode = false;
        this.updateMode = false;
        this.newPlanName = '';
        this.newPrice = 0;
        this.newDuration = '';
      });
    }
  }

  deletePlan(planId: string) {
    if (this.selectedPlan && confirm("Are you sure you want to delete this plan?")) {
      this.gymService.deletePlan(planId).subscribe((data) => {
        console.log("Plan Deleted:", data);
        this.getPlanList();
      });
    }
  }
}
