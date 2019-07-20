import { ErrorHandler, NgModule } from '@angular/core'
import { registerLocaleData } from '@angular/common'
import localePl from '@angular/common/locales/pl'

import { API_URL } from './api/api'
import { AppCommonModule } from '../common/app-common.module'
import { AppReduxModule } from './redux/app-redux.module'
import { AppRoutingModule } from './routing/app-routing.module'
import { GlobalErrorsHandler } from './errors/global-errors-handler.service'
import { AppLayoutComponent } from './components/app-layout/app-layout.component'
import { AppNavbarComponent } from './components/app-navbar/app-navbar.component'

registerLocaleData(localePl, 'pl')

const APP_CORE_MODULE_COMPONENTS = [
  AppLayoutComponent,
  AppNavbarComponent
]

@NgModule({
  imports: [
    AppCommonModule,
    AppReduxModule,
    AppRoutingModule
  ],
  exports: [
    AppRoutingModule,
    ...APP_CORE_MODULE_COMPONENTS
  ],
  providers: [
    {provide: API_URL, useValue: '/api/'},
    GlobalErrorsHandler,
    {provide: ErrorHandler, useExisting: GlobalErrorsHandler}
  ],
  declarations: [
    ...APP_CORE_MODULE_COMPONENTS
  ]
})
export class AppCoreModule {
}
