import { NgModule } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'

import { RouterStateSerializer } from '@ngrx/router-store'

import { environment } from '../../../environments/environment'
import { AppCustomRouterStateSerializer } from './app-router-state-serializer.service'
import { UserContextGuardService } from '../../user/routing/user-context-guard.service'
import { AppLayoutComponent } from '../components/app-layout/app-layout.component'

const routes: Routes = [
  {
    path: '',
    component: AppLayoutComponent,
    canActivate: [UserContextGuardService],
    data: {userContext: true},
    children: [ // with app layout
      {
        path: '',
        loadChildren: '../../dashboard/dashboard.module#DashboardModule',
      },
      {
        path: 'reports',
        loadChildren: '../../reports/reports.module#ReportsModule',
      }
    ]
  },
  // without app layout
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: '',
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: !environment.production})],
  exports: [RouterModule],
  providers: [
    {provide: RouterStateSerializer, useClass: AppCustomRouterStateSerializer},
    UserContextGuardService,
  ]
})
export class AppRoutingModule {
}
