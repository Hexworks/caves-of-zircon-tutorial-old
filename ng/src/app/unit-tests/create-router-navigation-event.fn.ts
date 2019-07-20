import { Data, Params, RoutesRecognized } from '@angular/router'

import { ROUTER_NAVIGATION, RouterNavigationAction } from '@ngrx/router-store'

import { AppRouterStateUrl } from '../core/routing/app-router-state-serializer.service'

export function createRouterNavigationEvent(
  url: string,
  data: Data,
  params: Params,
  queryParams?: Params): RouterNavigationAction<AppRouterStateUrl> {
  const routerState = {
    url,
    data,
    params,
    queryParams,
    root: null,
  }
  return {
    type: ROUTER_NAVIGATION,
    payload: {event: new RoutesRecognized(1, url, url, routerState), routerState},
  }
}
