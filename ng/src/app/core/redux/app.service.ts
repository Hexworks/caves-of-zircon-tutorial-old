import { Injectable } from '@angular/core'

import { select, Store } from '@ngrx/store'
import { RouterReducerState } from '@ngrx/router-store'
import { Observable } from 'rxjs'

import { AppRouterStateUrl } from '../routing/app-router-state-serializer.service'

@Injectable()
export class AppService {

  constructor(private store: Store<{ router: RouterReducerState<AppRouterStateUrl> }>) {
  }

  selectRouterState(): Observable<AppRouterStateUrl> {
    return this.store.pipe(select(s => s.router && s.router.state))
  }

}
