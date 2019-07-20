import { InterviewQuestion } from '../data/interview.model'

export enum InterviewActionType {
  FETCH_QUESTIONS_DATA = '[I] fetch questions data action',
  UPDATE_QUESTIONS_DATA = '[I] update questions data action'
}

export type FetchInterviewDataAction = { type: InterviewActionType.FETCH_QUESTIONS_DATA }
export type UpdateInterviewDataAction = { type: InterviewActionType.UPDATE_QUESTIONS_DATA, questions: InterviewQuestion[]}

export type InterviewAction = null
  | UpdateInterviewDataAction
