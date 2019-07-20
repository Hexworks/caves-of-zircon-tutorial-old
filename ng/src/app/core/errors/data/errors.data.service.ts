import { Injectable } from '@angular/core'

import { Observable } from 'rxjs'

import { SendErrorBody } from './errors.model'

@Injectable()
export abstract class ErrorsDataService {

  abstract sendError(error: SendErrorBody): Observable<SendErrorBody>

}
