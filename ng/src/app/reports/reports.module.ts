import { NgModule } from '@angular/core'

import { AppCommonModule } from '../common/app-common.module'
import { ReportsRoutingModule } from './routing/reports-routing.module'
import { ReportsComponent } from './reports/reports.component'

@NgModule({
  imports: [
    AppCommonModule,
    ReportsRoutingModule,
  ],
  declarations: [ReportsComponent]
})
export class ReportsModule {
}
