import { NgModule } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'

import { DashboardComponent } from '../dashboard/dashboard.component'

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'dashboard',
      },
      {
        path: 'dashboard',
        component: DashboardComponent,
      }
    ]
  }
]

@NgModule({imports: [RouterModule.forChild(routes)]})
export class DashboardRoutingModule {
}
