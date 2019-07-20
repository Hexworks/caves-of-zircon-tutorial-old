import { NgModule } from '@angular/core'

import { StoreModule } from '@ngrx/store'
import { storeFreeze } from 'ngrx-store-freeze'
import { EffectsModule } from '@ngrx/effects'
import { StoreRouterConnectingModule } from '@ngrx/router-store'
import { StoreDevtoolsModule } from '@ngrx/store-devtools'

import { environment } from '../../../environments/environment'
import { AppReducers } from './app-reducers'
import { AppEffects } from './app-effects'
import { AppService } from './app.service'
import { ErrorsService } from '../errors/redux/errors.service'
import { ErrorsDataService } from '../errors/data/errors.data.service'
import { ErrorsDataServiceMock } from '../errors/data/errors.data.service.mock'
import { ErrorsDataServiceImpl } from '../errors/data/errors.data.service.impl'
import { UserService } from '../../user/redux/user.service'
import { UserDataService } from '../../user/data/user.data.service'
import { UserDataServiceMock } from '../../user/data/user.data.service.mock'
import { UserDataServiceImpl } from '../../user/data/user.data.service.impl'

@NgModule({
  imports: [
    StoreModule.forRoot(AppReducers, {metaReducers: !environment.production ? [storeFreeze] : []}),
    EffectsModule.forRoot(AppEffects),
    StoreRouterConnectingModule,
    (!environment.production || environment.productionDevelop) ? StoreDevtoolsModule.instrument({maxAge: 25}) : []
  ],
  providers: [
    AppService,
    ErrorsService,
    {provide: ErrorsDataService, useClass: environment.mock ? ErrorsDataServiceMock : ErrorsDataServiceImpl},
    UserService,
    {provide: UserDataService, useClass: environment.mock ? UserDataServiceMock : UserDataServiceImpl}
  ]
})
export class AppReduxModule {
}
