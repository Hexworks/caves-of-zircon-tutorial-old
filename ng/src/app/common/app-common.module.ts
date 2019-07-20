import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { HttpClientModule } from '@angular/common/http'

import { AngularMaterialModule } from './libs/angular-material.module'
import { FlexLayoutModule } from '@angular/flex-layout'

const APP_COMMON_MODULE_MODULES = [
  CommonModule,
  HttpClientModule,
  AngularMaterialModule,
  FlexLayoutModule,
]

@NgModule({
  imports: [...APP_COMMON_MODULE_MODULES],
  exports: [...APP_COMMON_MODULE_MODULES],
})
export class AppCommonModule {
}
