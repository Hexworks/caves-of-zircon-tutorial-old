import { TestBed } from '@angular/core/testing'
import { RouterTestingModule } from '@angular/router/testing'

import { AppModule } from '../app.module'
import { AppComponent } from './app.component'

describe('AppComponent', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        AppModule,
      ]
    })
  })

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent)
    const app = fixture.debugElement.componentInstance
    expect(app).toBeTruthy()
  })
})
