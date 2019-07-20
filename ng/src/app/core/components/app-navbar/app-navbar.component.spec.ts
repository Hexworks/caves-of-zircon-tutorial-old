import { ComponentFixture, TestBed } from '@angular/core/testing'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { AngularMaterialModule } from '../../../common/libs/angular-material.module'

import { AppNavbarComponent } from './app-navbar.component'

describe('AppNavbarComponent', () => {
  let component: AppNavbarComponent
  let fixture: ComponentFixture<AppNavbarComponent>

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        BrowserAnimationsModule,
        AngularMaterialModule,
      ],
      declarations: [AppNavbarComponent]
    })
  })

  beforeEach(() => {
    fixture = TestBed.createComponent(AppNavbarComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })
})
