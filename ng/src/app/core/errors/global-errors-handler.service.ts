import { ErrorHandler, Injectable, Injector } from '@angular/core'

import { Subscription, timer } from 'rxjs'

import { ErrorsService } from './redux/errors.service'

export let ERRORS_COUNTER: number = 0

@Injectable()
export class GlobalErrorsHandler implements ErrorHandler {

  readonly errorTimeFrame: number = 10000
  readonly errorMaxInFrame: number = 10
  private subscription: Subscription = new Subscription()

  constructor(private injector: Injector) {
  }

  handleError(error: Error): void {
    const errorsService = this.injector.get(ErrorsService)
    this.increaseCounter()
    this.subscription.add(timer(this.errorTimeFrame).subscribe(this.decreaseCounter))

    if (ERRORS_COUNTER < this.errorMaxInFrame) {
      errorsService.sendError({exception: error.message ? error.message : error.toString(), cause: error.stack ? error.stack : ''})
    }
    if (ERRORS_COUNTER === this.errorMaxInFrame) {
      errorsService.sendError({exception: `UI exceptions count is greater than ${this.errorMaxInFrame} in period ${this.errorTimeFrame} millis`})
    }
    if (ERRORS_COUNTER >= this.errorMaxInFrame) {
      this.subscription.unsubscribe()
    }
    console.error(error) // just to show error in console
  }

  increaseCounter(): void {
    ERRORS_COUNTER++
  }

  decreaseCounter(): void {
    ERRORS_COUNTER--
  }

}
