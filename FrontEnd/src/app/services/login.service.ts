import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) {
  }

public getCurrentUser(){
  return this.http.get(`${baseUrl}/auth/current-user`)
}

  public generateToken(loginData:any)
  {
    return this.http.post(`${baseUrl}/auth/generatetoken`,loginData)

  }

//login user
public loginUser(token: string){
  localStorage.setItem("token",token)
  return true
}
//islogin function to check status of login
public isLoggedIn(){
  let tokenStr=localStorage.getItem("token")
  if(tokenStr==undefined||tokenStr==''|| tokenStr==null) {
    return false;
  }
  else{
    return true;
  }
}

  public logout() {
    localStorage.removeItem('token');

    return true;


}

//get token
public getToken(){
  return localStorage.getItem('token');
}


public setUser(user: any){
  localStorage.setItem('user',JSON.stringify(user))
}



public getUser(){
 let userStr= localStorage.getItem('user')

 if(userStr!=null){
  return JSON.parse(userStr);
 }
 else{
  this.logout()
  return null;
 }

}

//get user role
public getUserRole(){
  let user=this.getUser()
  return user.roles[0].name;
}

}
