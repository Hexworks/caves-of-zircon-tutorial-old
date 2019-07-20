import { TestBed } from '@angular/core/testing'

import { configureTestSuite } from '../../unit-tests/configuration'

import { StoreModule } from '@ngrx/store'
import { storeFreeze } from 'ngrx-store-freeze'
import { routerReducer } from '@ngrx/router-store'
import { cold } from 'jasmine-marbles'

import { AppService } from './app.service'

describe('AppService', () => {
  let appService: AppService

  configureTestSuite()

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [StoreModule.forRoot({router: routerReducer}, {initialState: {router: null}, metaReducers: [storeFreeze]})],
      providers: [AppService],
    })

    appService = TestBed.get(AppService)
  })

  it('should select router state data', () => {
    expect(appService.selectRouterState()).toBeObservable(cold('a', {a: null}))
  })
})
