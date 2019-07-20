import { Action } from '@ngrx/store'
import { Actions } from '@ngrx/effects'
import { hot } from 'jasmine-marbles'
import Spy = jasmine.Spy

import { UserActionType } from './user-action.type'
import { UserService } from './user.service'
import { UserEffectsService } from './user-effects.service'
import { UserDataServiceMock } from '../data/user.data.service.mock'
import { createRouterNavigationEvent } from '../../unit-tests/create-router-navigation-event.fn'
import { userDataMocked } from '../data/user.mock'

describe('UserEffectsService', () => {
  let userService: UserService

  function createEffects(actions: Actions<Action>): UserEffectsService {
    return new UserEffectsService(
      actions,
      userService,
      new UserDataServiceMock(),
    )
  }

  beforeEach(() => {
    userService = jasmine.createSpyObj('userService', Object.keys(UserService.prototype))
  })

  it('should dispatch action to fetch user data', () => {
    const userContext = true;
    (userService.selectUserData as Spy).and.returnValue(hot('-aab-', {a: null, b: {}}))
    const actions = new Actions(hot('-aba-', {
      a: createRouterNavigationEvent('', {userContext}, {}),
      b: createRouterNavigationEvent('', {}, {}),
    }))

    const effects = createEffects(actions)

    expect(effects.routeEnter$).toBeObservable(hot('-a---', {a: {type: UserActionType.FETCH_USER_DATA}}))
  })

  it('should dispatch action to update user data', () => {
    const userData = userDataMocked()
    const actions = new Actions(hot('-a-', {a: {type: UserActionType.FETCH_USER_DATA}}))

    const effects = createEffects(actions)

    expect(effects.fetchUserData$).toBeObservable(hot('-a-', {
      a: {type: UserActionType.UPDATE_USER_DATA, userData},
    }))
  })
})
