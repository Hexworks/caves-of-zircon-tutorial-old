import { UserState } from '../data/user.model'
import { UserAction, UserActionType } from './user-action.type'

export const initialState: UserState = {
  userData: null,
}

export function userReducer(state: UserState = initialState, action?: UserAction): UserState {
  if (!action) {
    return state
  }
  switch (action.type) {
    case UserActionType.UPDATE_USER_DATA: {
      return {...state, userData: action.userData}
    }
    default: {
      return state
    }
  }
}
