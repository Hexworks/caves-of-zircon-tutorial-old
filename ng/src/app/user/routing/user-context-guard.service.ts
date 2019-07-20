import { Injectable } from '@angular/core'
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router'

import { Observable, of } from 'rxjs'
import { filter, map } from 'rxjs/operators'

import { UserService } from '../redux/user.service'

@Injectable()
export class UserContextGuardService implements CanActivate {

  constructor(private userService: UserService) {
  }

  canActivate(_route: ActivatedRouteSnapshot, _state: RouterStateSnapshot): Observable<boolean> {
    return of(true) || this.userService.selectUserData().pipe( // TODO
      filter(Boolean),
      map(Boolean),
    )
  }

}
