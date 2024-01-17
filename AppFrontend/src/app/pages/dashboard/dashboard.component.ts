import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/services/project.service';

export interface Project {
  name: string;
  description: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  public admin:boolean = false;
  public logged:boolean = false;  
  public scrum:boolean = false;
  public projects = [];
  public addProjectFrom!:FormGroup;

  constructor(private router:Router, private projectService:ProjectService, private formBuilder:FormBuilder) {

  }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkUserRole();
    this.getProjects();
    this.addProjectFrom = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    })
  }

  checkIfLoggedIn(){
    if ("email" in sessionStorage){
      this.logged = true;
    }
  }

  checkUserRole(){
    if (sessionStorage.getItem("role") === "admin") {
      this.admin = true;
    } else {
      if (sessionStorage.getItem("scrum") === "scrum") {
        this.scrum = true;
        this.projectService.getAllProjects().subscribe((response : any) => {
          this.projects = response;
        })
      } 
    }
  }

  getProjects() {
    var userId = sessionStorage.getItem("userId");

    this.projectService.getProjects(userId).subscribe((response:any) => {
      console.log(response);
      this.projects = response;
    })
  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
    this.router.navigateByUrl('/dashboard');
  }

  addProject() {
    console.log(this.addProjectFrom.value);
    this.projectService.addProject(this.addProjectFrom.value).subscribe((response:any) => {
      console.log(response);
    })
  }
}
