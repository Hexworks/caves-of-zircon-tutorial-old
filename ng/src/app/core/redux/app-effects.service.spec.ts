import { Router } from '@angular/router'

import { Action } from '@ngrx/store'
import { Actions } from '@ngrx/effects'
import { hot } from 'jasmine-marbles'
import Spy = jasmine.Spy

import { AppActionType } from './app-action.type'
import { AppEffectsService } from './app-effects.service'

describe('AppEffectsService', () => {
  let router: Router

  function createEffects(actions: Actions<Action>): AppEffectsService {
    return new AppEffectsService(
      actions,
      router,
    )
  }

  beforeEach(() => {
    router = jasmine.createSpyObj('router', Object.keys(Router.prototype))
  })

  it('should call method to router navigate', () => {
    const commands = []
    const extras = {};
    (router.navigate as Spy).and.returnValue(Promise.resolve(true))
    const actions = new Actions(hot('a', {a: {type: AppActionType.ROUTE_NAVIGATE, commands, extras}}))

    const effects = createEffects(actions)

    const subscription = effects.routeNavigate$.subscribe(value => {
      expect(value).toBeTruthy()
      expect(router.navigate).toHaveBeenCalledWith(commands, extras)
      subscription.unsubscribe()
    })
  })
})
