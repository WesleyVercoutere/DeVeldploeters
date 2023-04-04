import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { IActivity } from 'app/shared/model/activity.model';
import { IPresence } from 'app/shared/model/presence.model';
import { PresenceService } from 'app/entities/presence';
import moment = require('moment');

@Component({
  selector: 'jhi-activity-detail',
  templateUrl: './activity-detail.component.html'
})
export class ActivityDetailComponent implements OnInit {
  activity: IActivity;
  tourHappened: boolean;
  presence: IPresence;

  constructor(protected activatedRoute: ActivatedRoute, private presenceService: PresenceService, private router: Router) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ activity }) => {
      this.activity = activity;

      const date = this.activity.date.format('YYYY-MM-DD') + 'T08:30:00';
      this.tourHappened = !moment().isBefore(date);

      if (!this.tourHappened) {
        this.initPresence();
      }
    });
  }

  previousState() {
    this.router.navigate(['/calendar']);
  }

  private initPresence(): void {
    this.presenceService.findUserActivityPresence(this.activity.id).subscribe(res => {
      this.presence = res.body;
    });
  }

  public setPresence(): void {
    this.presence.present = !this.presence.present;
    this.presenceService.update(this.presence).subscribe(res => (this.presence = res.body));
  }
}
