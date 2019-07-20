import { Injectable } from '@angular/core'
import { Router } from '@angular/router'

import { Actions, Effect, ofType } from '@ngrx/effects'
import { fromPromise } from 'rxjs/internal-compatibility'
import { switchMap } from 'rxjs/operators'

import { AppActionType, AppRouteNavigateAction } from './app-action.type'

@Injectable()
export class AppEffectsService {

  constructor(private actions$: Actions,
              private router: Router) {
  }

  @Effect({dispatch: false})
  routeNavigate$ = this.actions$.pipe(
    ofType<AppRouteNavigateAction>(AppActionType.ROUTE_NAVIGATE),
    switchMap(({commands, extras}) => fromPromise(this.router.navigate(commands, extras))),
  )

}
