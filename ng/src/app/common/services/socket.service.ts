import { Injectable } from '@angular/core'

import { Observable } from 'rxjs'

import * as socketIo from 'socket.io-client'

const DEFAULT_SERVER_URL = 'http://localhost:3000'

export interface SocketEventData {
  content: string
}

@Injectable()
export class SocketService {

  private socket

  public openSocket(url: string): void {
    this.socket = socketIo(url || DEFAULT_SERVER_URL)
  }

  public sendEventData(eventId: string = 'message', eventData: SocketEventData): void {
    this.socket.emit(eventId, eventData)
  }

  public receiveEventData<T = {}>(eventId: string = 'message'): Observable<T> {
    return new Observable<T>(o => {
      this.socket.on(eventId, (data: T) => o.next(data))
    })
  }

}
