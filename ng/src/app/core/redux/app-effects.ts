import { Type } from '@angular/core'

import { AppEffectsService } from './app-effects.service'
import { ErrorsEffectsService } from '../errors/redux/errors-effects.service'
import { UserEffectsService } from '../../user/redux/user-effects.service'

export const AppEffects: Type<any>[] = [
  AppEffectsService,
  ErrorsEffectsService,
  UserEffectsService
]
