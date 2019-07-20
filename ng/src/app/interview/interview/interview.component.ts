import { ChangeDetectionStrategy, Component } from '@angular/core'

import { Observable } from 'rxjs'

import { InterviewQuestion } from '../data/interview.model'
import { InterviewService } from '../redux/interview.service'

@Component({
  selector: 'koj-interview',
  templateUrl: './interview.component.html',
  styleUrls: ['./interview.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class InterviewComponent {

  constructor(private interviewService: InterviewService) {
  }

  get questions$(): Observable<InterviewQuestion[]> {
    return this.interviewService.selectInterviewQuestions()
  }

  trackByFn(_: number, {question}: InterviewQuestion): string {
    return question
  }

}
