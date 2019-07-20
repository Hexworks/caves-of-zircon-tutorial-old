import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'

import { InterviewComponent } from '../interview/interview.component'

const routes: Routes = [
  {
    path: '',
    component: InterviewComponent,
    data: {interview: true}
  }
]

@NgModule({imports: [RouterModule.forChild(routes)]})
export class InterviewRoutingModule {
}
