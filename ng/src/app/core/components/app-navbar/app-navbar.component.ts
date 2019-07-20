import { ChangeDetectionStrategy, Component } from '@angular/core'

@Component({
  selector: 'koj-app-navbar',
  templateUrl: './app-navbar.component.html',
  styleUrls: ['./app-navbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppNavbarComponent {
}
