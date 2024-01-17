import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private privateHttpHeaders = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  getProjects(userId:any) {
    return this.http.get(
      this.baseUrl + "userProject/getAllProjects/" + userId,
      this.privateHttpHeaders
    )
  }

  addProject(project:any){
    return this.http.post(
      this.baseUrl + "project/createProject",
      project,
      this.privateHttpHeaders
    )
  }

  getAllProjects() {
    return this.http.get(
      this.baseUrl + "project/getAllProjects",
      this.privateHttpHeaders
    )
  }
}
