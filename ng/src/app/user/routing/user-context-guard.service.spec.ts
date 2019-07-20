import { TestBed } from '@angular/core/testing'

import { configureTestSuite } from '../../unit-tests/configuration'

import { Store } from '@ngrx/store'
import { of } from 'rxjs'
import { cold, hot } from 'jasmine-marbles'

import { UserService } from '../redux/user.service'
import { UserContextGuardService } from './user-context-guard.service'

describe('UserContextGuardService', () => {
  let userService: UserService
  let userContextGuardService: UserContextGuardService

  configureTestSuite()

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {provide: Store, useValue: of()},
        UserService,
        UserContextGuardService,
      ]
    })

    userService = TestBed.get(UserService)
    userContextGuardService = TestBed.get(UserContextGuardService)
  })

  it('should not allow to enter the route', () => {
    spyOn(userService, 'selectUserData').and.returnValue(cold('a', {a: null}))

    expect(userContextGuardService.canActivate(null, null))
      .toBeObservable(hot('-'))
  })

  it('should allow to enter the route after fetching product definition data', () => {
    spyOn(userService, 'selectUserData').and.returnValue(cold('aba', {a: null, b: {}}))

    expect(userContextGuardService.canActivate(null, null))
      .toBeObservable(hot('-a-', {a: true}))
  })
})
