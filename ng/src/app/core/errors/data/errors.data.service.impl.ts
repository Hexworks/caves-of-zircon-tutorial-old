import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { Observable } from 'rxjs'

import { SendErrorBody } from './errors.model'
import { ErrorsDataService } from './errors.data.service'

@Injectable()
export class ErrorsDataServiceImpl implements ErrorsDataService {

  constructor(private http: HttpClient) {
  }

  sendError(error: SendErrorBody): Observable<SendErrorBody> {
    const headers = new HttpHeaders()
    headers.append('X-Requested-With', 'XMLHttpRequest')
    return this.http.post<SendErrorBody>('/users/ui-log', error, {headers})
  }

}
