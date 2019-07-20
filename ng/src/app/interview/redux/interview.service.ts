import { Injectable } from '@angular/core'

import { select, Store } from '@ngrx/store'
import { Observable } from 'rxjs'

import { InterviewQuestion, InterviewState } from '../data/interview.model'

@Injectable()
export class InterviewService {

  constructor(private store: Store<{ interview: InterviewState }>) {
  }

  selectInterviewQuestions(): Observable<InterviewQuestion[]> {
    return this.store.pipe(select(s => s.interview.questions))
  }

}
