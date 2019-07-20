import { ComponentFixture, TestBed } from '@angular/core/testing'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { RouterTestingModule } from '@angular/router/testing'

import { AngularMaterialModule } from '../../../common/libs/angular-material.module'

import { AppLayoutComponent } from './app-layout.component'
import { AppNavbarComponent } from '../app-navbar/app-navbar.component'

describe('AppLayoutComponent', () => {
  let component: AppLayoutComponent
  let fixture: ComponentFixture<AppLayoutComponent>

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        BrowserAnimationsModule,
        RouterTestingModule,
        AngularMaterialModule,
      ],
      declarations: [
        AppLayoutComponent,
        AppNavbarComponent,
      ]
    })
  })

  beforeEach(() => {
    fixture = TestBed.createComponent(AppLayoutComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })
})
