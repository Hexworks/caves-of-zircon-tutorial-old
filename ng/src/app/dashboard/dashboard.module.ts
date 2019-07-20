import { NgModule } from '@angular/core'

import { AppCommonModule } from '../common/app-common.module'
import { DashboardRoutingModule } from './routing/dashboard-routing.module'
import { DashboardComponent } from './dashboard/dashboard.component'

@NgModule({
  imports: [
    AppCommonModule,
    DashboardRoutingModule,
  ],
  declarations: [DashboardComponent]
})
export class DashboardModule {
}
