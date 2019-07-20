import { SendErrorBody } from '../data/errors.model'

export enum ErrorsActionType {
  SEND_ERROR_DATA = '[ERRORS] send error data action',
}

export type SendErrorDataAction = { type: ErrorsActionType.SEND_ERROR_DATA, error: SendErrorBody }
