import { NavigationExtras } from '@angular/router'

export enum AppActionType {
  ROUTE_NAVIGATE = '[APP] route navigate action',
}

export type AppRouteNavigateAction = { type: AppActionType.ROUTE_NAVIGATE, commands: (string | number)[], extras?: NavigationExtras }
