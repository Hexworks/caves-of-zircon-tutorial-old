import { Injectable } from '@angular/core'

import { Actions, Effect, ofType } from '@ngrx/effects'
import { switchMap } from 'rxjs/operators'

import { ErrorsActionType, SendErrorDataAction } from './errors-action-type'
import { ErrorsDataService } from '../data/errors.data.service'

@Injectable()
export class ErrorsEffectsService {

  constructor(private actions$: Actions,
              private errorsDataService: ErrorsDataService) {
  }

  @Effect({dispatch: false})
  sendErrorData$ = this.actions$.pipe(
    ofType<SendErrorDataAction>(ErrorsActionType.SEND_ERROR_DATA),
    switchMap(({error}) => this.errorsDataService.sendError(error))
  )

}
