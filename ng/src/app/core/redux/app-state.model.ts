import { RouterReducerState } from '@ngrx/router-store'

import { AppRouterStateUrl } from '../routing/app-router-state-serializer.service'
import { UserState } from '../../user/data/user.model'

export interface AppState {
  router: RouterReducerState<AppRouterStateUrl>
  user: UserState
}
