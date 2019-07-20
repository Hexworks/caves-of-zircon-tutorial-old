import { TestBed } from '@angular/core/testing'

import { configureTestSuite } from '../../unit-tests/configuration'

import { StoreModule } from '@ngrx/store'
import { storeFreeze } from 'ngrx-store-freeze'
import { cold } from 'jasmine-marbles'

import { UserService } from './user.service'
import { userReducer } from './user-reducer'

describe('UserService', () => {
  let userService: UserService

  configureTestSuite()

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        StoreModule.forRoot({user: userReducer}, {initialState: {user: {userData: null}}, metaReducers: [storeFreeze]}),
      ],
      providers: [UserService],
    })

    userService = TestBed.get(UserService)
  })

  it('should select user data', () => {
    expect(userService.selectUserData()).toBeObservable(cold('a', {a: null}))
  })
})
