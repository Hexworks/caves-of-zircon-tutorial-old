import { InterviewState } from '../data/interview.model'
import { InterviewAction, InterviewActionType } from './interview-action.type'

export const initialState: InterviewState = {
  questions: null,
  answers: null
}

export function interviewReducer(state: InterviewState = initialState, action?: InterviewAction): InterviewState {
  if (!action) {
    return state
  }
  switch (action.type) {
    case InterviewActionType.UPDATE_QUESTIONS_DATA: {
      return {...state, questions: action.questions}
    }
    default: {
      return state
    }
  }
}
