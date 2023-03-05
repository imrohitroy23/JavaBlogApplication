import { Component, OnInit } from '@angular/core'
import { Router, RouterLink } from '@angular/router';
import { LoginService } from 'src/app/services/login.service'

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  isLoggedIn = false;
  user = null;

  constructor(public login: LoginService, private _router:Router) {}

  ngOnInit(): void {

    };


  public logout() {
    this.login.logout();
  this._router.navigate(['/'])
    // this.login.loginStatusSubject.next(false);
  }
}
