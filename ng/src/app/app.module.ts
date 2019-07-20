import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { AppCommonModule } from './common/app-common.module'
import { AppCoreModule } from './core/app-core.module'
import { AppComponent } from './app/app.component'

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppCommonModule,
    AppCoreModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
