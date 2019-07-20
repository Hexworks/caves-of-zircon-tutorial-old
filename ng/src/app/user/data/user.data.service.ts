import { Injectable } from '@angular/core'

import { Observable } from 'rxjs'

import { UserData } from './user.model'

@Injectable()
export abstract class UserDataService {

  abstract fetchUserData(): Observable<UserData>

}
