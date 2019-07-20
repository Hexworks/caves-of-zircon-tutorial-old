import { Injectable } from '@angular/core'

import { Store } from '@ngrx/store'

import { AppState } from '../../redux/app-state.model'
import { SendErrorBody } from '../data/errors.model'
import { ErrorsActionType } from './errors-action-type'

@Injectable()
export class ErrorsService {

  constructor(private store: Store<AppState>) {
  }

  sendError(error: SendErrorBody): void {
    this.store.dispatch({type: ErrorsActionType.SEND_ERROR_DATA, error})
  }

}
