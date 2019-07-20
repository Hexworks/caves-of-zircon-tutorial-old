import { NgModule } from '@angular/core'

import { StoreModule } from '@ngrx/store'
import { EffectsModule } from '@ngrx/effects'

import { AppCommonModule } from '../common/app-common.module'
import { InterviewRoutingModule } from './routing/interview-routing.module'
import { InterviewDataServiceImpl } from './data/interview.data.service.impl'
import { InterviewService } from './redux/interview.service'
import { InterviewEffectsService } from './redux/interview-effects.service'
import { interviewReducer } from './redux/interview-reducer'
import { InterviewComponent } from './interview/interview.component'
import { InterviewQuestionComponent } from './interview-question/interview-question.component'

@NgModule({
  imports: [
    AppCommonModule,
    InterviewRoutingModule,
    StoreModule.forFeature('interview', interviewReducer),
    EffectsModule.forFeature([InterviewEffectsService])
  ],
  declarations: [
    InterviewComponent,
    InterviewQuestionComponent
  ],
  providers: [
    InterviewDataServiceImpl,
    InterviewService
  ]
})
export class InterviewModule {
}
