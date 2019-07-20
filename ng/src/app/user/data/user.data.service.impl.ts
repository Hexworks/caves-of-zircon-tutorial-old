import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'

import {Observable} from 'rxjs'
import {UserData} from './user.model'
import {UserDataService} from './user.data.service'

@Injectable()
export class UserDataServiceImpl implements UserDataService {

  constructor(private http: HttpClient) {
  }

  fetchUserData(): Observable<UserData> {
    return this.http.get<UserData>('/users/auth')
  }

}
