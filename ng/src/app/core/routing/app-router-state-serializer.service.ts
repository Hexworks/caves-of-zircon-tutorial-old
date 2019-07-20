import { ActivatedRouteSnapshot, Data, Params, RouterStateSnapshot } from '@angular/router'

import { RouterStateSerializer } from '@ngrx/router-store'

export interface AppRouterStateUrl {
  url: string
  data: Data
  params: Params
  queryParams: Params
}

export class AppCustomRouterStateSerializer implements RouterStateSerializer<AppRouterStateUrl> {

  serialize(routerState: RouterStateSnapshot): AppRouterStateUrl {
    const {url} = routerState
    const data = this.mergeParams('data', routerState.root)
    const params = this.mergeParams('params', routerState.root)
    const queryParams = routerState.root.queryParams

    return {url, data, params, queryParams}
  }

  private mergeParams(attr: string, tree: ActivatedRouteSnapshot): Params {
    return tree.children
      .reduce((pervParams, subTree) => ({...pervParams, ...this.mergeParams(attr, subTree)}), tree[attr])
  }

}
