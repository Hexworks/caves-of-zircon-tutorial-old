import { ChangeDetectionStrategy, Component } from '@angular/core'

@Component({
  selector: 'koj-app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppLayoutComponent {
}
