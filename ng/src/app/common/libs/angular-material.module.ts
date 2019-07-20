import { NgModule } from '@angular/core'

import {
  DateAdapter,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE,
  MAT_DIALOG_DEFAULT_OPTIONS,
  MatAutocompleteModule,
  MatButtonModule,
  MatCheckboxModule,
  MatDateFormats,
  MatDialogConfig,
  MatDialogModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule,
  MatSidenavModule,
  MatSliderModule,
  MatToolbarModule,
  MatTooltipModule,
} from '@angular/material'
import { MomentDateAdapter } from '@angular/material-moment-adapter'

const ANGULAR_MATERIAL_FUNCTIONALITIES = [
  MatAutocompleteModule,
  MatButtonModule,
  MatCheckboxModule,
  MatDialogModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule,
  MatSidenavModule,
  MatSliderModule,
  MatToolbarModule,
  MatTooltipModule,
]

const DATE_FORMATS: MatDateFormats = {
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
  parse: {
    dateInput: 'YYYY-MM-DD',
  }
}

@NgModule({
  imports: [
    ...ANGULAR_MATERIAL_FUNCTIONALITIES,
  ],
  exports: [
    ...ANGULAR_MATERIAL_FUNCTIONALITIES,
  ],
  providers: [
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {...(new MatDialogConfig()), panelClass: 'koj-dialog--panel'}},
    {provide: MAT_DATE_LOCALE, useValue: 'pl-PL'},
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: DATE_FORMATS},
  ]
})
export class AngularMaterialModule {
}
