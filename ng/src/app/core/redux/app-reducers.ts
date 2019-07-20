import { ActionReducerMap } from '@ngrx/store'
import { routerReducer } from '@ngrx/router-store'

import { AppState } from './app-state.model'
import { userReducer } from '../../user/redux/user-reducer'

export const AppReducers: ActionReducerMap<AppState> = {
  router: routerReducer,
  user: userReducer,
}
