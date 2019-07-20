import { ChangeDetectionStrategy, Component } from '@angular/core'

@Component({
  selector: 'koj-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DashboardComponent {
}
