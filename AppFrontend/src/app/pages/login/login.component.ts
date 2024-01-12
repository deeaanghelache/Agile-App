import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public loginTitle:String = "Sign Up";
  public loginForm!:FormGroup;
  public userNotFound:String = "";
  public ok:boolean = false;
  public tryAgain: String = "";

  constructor(private formBuilder:FormBuilder, private router: Router, private userAuthentication:UserAuthenticationService) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group(
      {      
          email : ['', Validators.compose([Validators.required, Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
          password : ['', [Validators.required]]
      }
    );
  }

  loginUserFunction() {
    if (this.loginForm.valid) {
      this.ok = true;
      this.userAuthentication.login(
        this.loginForm.value
      ).subscribe((response : any) => {
        if (response != null) {
          this.router.navigateByUrl("/dashboard");
        } else {
          this.loginForm.reset();
          this.userNotFound = "User not found";
        }
      })
    }
  }
}
