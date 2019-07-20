import { Injectable } from '@angular/core'

import { Observable, of } from 'rxjs'

import { UserData } from './user.model'
import { UserDataService } from './user.data.service'
import { userDataMocked } from './user.mock'

@Injectable()
export class UserDataServiceMock implements UserDataService {

  fetchUserData(): Observable<UserData> {
    return of(userDataMocked())
  }

}
