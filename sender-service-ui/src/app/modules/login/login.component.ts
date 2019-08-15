import {Component, OnInit} from '@angular/core';
import {SecurityService} from '../../service/security.service';
import {LoginInfo} from '../../model/loginInfo';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginInfo: LoginInfo = {
    userName: '',
    password: ''
  };

  constructor(private _service: SecurityService) {
  }

  login() {
    localStorage.removeItem('token');
    this._service.obtainAccessToken(this.loginInfo);
    // this._service.redirectToIAM('/home');
  }

}
