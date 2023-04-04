import { Component, OnInit } from '@angular/core';
import { PresenceService } from 'app/entities/presence';
import { ActivityService } from 'app/entities/activity';
import { IActivity } from 'app/shared/model/activity.model';
import { IPresenceOverview } from 'app/shared/model/presence-overview.model';

@Component({
  selector: 'jhi-calendar',
  templateUrl: './activities.overview.component.html',
  styleUrls: ['./activities.overview.component.scss']
})
export class ActivitiesOverviewComponent implements OnInit {
  selectedYear: number;
  activities: Array<IActivity>;
  presences: Array<IPresenceOverview>;

  constructor(private presenceService: PresenceService, private activityService: ActivityService) {}

  ngOnInit() {
    this.selectedYear = 2019;

    this.activityService.findActivitiesByYear(this.selectedYear).subscribe(res => (this.activities = res.body));

    this.presenceService.findPresencesByYear(this.selectedYear).subscribe(res => (this.presences = res.body));
  }
}
