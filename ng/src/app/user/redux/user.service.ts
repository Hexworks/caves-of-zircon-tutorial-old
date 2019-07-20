import { Injectable } from '@angular/core'

import { select, Store } from '@ngrx/store'
import { Observable } from 'rxjs'

import { UserData, UserState } from '../data/user.model'

@Injectable()
export class UserService {

  constructor(private store: Store<{ user: UserState }>) {
  }

  selectUserData(): Observable<UserData> {
    return this.store.pipe(select(s => s.user.userData))
  }

}
