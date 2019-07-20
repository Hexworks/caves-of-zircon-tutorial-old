import { UserData } from '../data/user.model'

export enum UserActionType {
  FETCH_USER_DATA = '[USER] fetch user data action',
  UPDATE_USER_DATA = '[USER] update user data action',
}

export type FetchUserDataAction = { type: UserActionType.FETCH_USER_DATA }
export type UpdateUserDataAction = { type: UserActionType.UPDATE_USER_DATA, userData: UserData }

export type UserAction = null
  | UpdateUserDataAction
