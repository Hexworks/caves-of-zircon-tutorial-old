import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'

import { Observable } from 'rxjs'

import { InterviewQuestionReq, InterviewQuestionRes } from './interview.model'

@Injectable()
export class InterviewDataServiceImpl {

  constructor(private http: HttpClient) {
  }

  fetchQuestions(req: InterviewQuestionReq): Observable<InterviewQuestionRes> {
    const reqParams = Object.keys(req).reduce((params, key) => `${params}&${key}=${req[key]}`, '').slice(1)
    return this.http.get<InterviewQuestionRes>(`https://opentdb.com/api.php?${reqParams}`)
  }

}
