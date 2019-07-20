import { Injectable } from '@angular/core'

import { Observable, of } from 'rxjs'

import { SendErrorBody } from './errors.model'
import { ErrorsDataService } from './errors.data.service'

@Injectable()
export class ErrorsDataServiceMock implements ErrorsDataService {

  sendError(error: SendErrorBody): Observable<SendErrorBody> {
    return of(error)
  }

}
