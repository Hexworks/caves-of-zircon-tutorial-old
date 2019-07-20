import { UserState } from '../data/user.model'
import { UserActionType } from './user-action.type'
import { initialState, userReducer } from './user-reducer'
import { userDataMocked } from '../data/user.mock'

describe('userReducer', () => {
  const state: UserState = userReducer()

  it('should create initial state', () => {
    expect(state).toEqual(initialState)
  })

  it('should not modify initial state', () => {
    const newState = userReducer(state, {} as any)

    expect(newState).toEqual(state)
  })

  it('should update user data', () => {
    const userData = userDataMocked()
    const newState = userReducer(state, {type: UserActionType.UPDATE_USER_DATA, userData})

    expect(newState).toEqual(jasmine.objectContaining({userData}))
  })
})
