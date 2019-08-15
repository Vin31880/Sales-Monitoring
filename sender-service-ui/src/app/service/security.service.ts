import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {LoginInfo} from '../model/loginInfo';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TokenInfo} from '../model/token-info';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(private http: HttpClient,
              private router: Router) {
  }

  obtainAccessToken(loginData: LoginInfo) {
    const params = new URLSearchParams();
    params.append('username', loginData.userName);
    params.append('password', loginData.password);
    params.append('grant_type', 'password');
    params.append('client_id', environment.clientId);

    let headers_object = new HttpHeaders();
    headers_object = headers_object.append('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
    headers_object = headers_object.append('Authorization', 'Basic ' + btoa('clientId:clientSecret'));
    const httpOptions = {
      headers: headers_object
    };

    this.http.post(environment.oauthURL,
      params.toString(), httpOptions)
      .subscribe(
        (data: TokenInfo) => this.saveToken(data),
        err => alert('Invalid Credentials'));
  }

  private saveToken(tokenInfo: TokenInfo) {
    console.log('{}' + tokenInfo);
    localStorage.setItem('token', tokenInfo.access_token);
    this.router.navigateByUrl('/home');
  }
}
