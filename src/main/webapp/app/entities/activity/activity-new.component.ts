import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { CalendarService } from 'app/pages/calendar';
import { JhiAlertService } from 'ng-jhipster';
import { ActivityService } from 'app/entities/activity/activity.service';
import { Activity, IActivity } from 'app/shared/model/activity.model';
import { TourService } from 'app/entities/tour';

@Component({
  selector: 'jhi-tour-update',
  templateUrl: './activity-new.component.html'
})
export class ActivityNewComponent implements OnInit, OnDestroy {
  activity: IActivity;
  isSaving: boolean;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected activityService: ActivityService,
    protected tourService: TourService,
    protected activatedRoute: ActivatedRoute,
    protected calendarService: CalendarService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isSaving = false;

    this.activity = new Activity();
    this.activity.type = 'tour';
    this.activity.date = moment(this.calendarService.date);

    this.initSelectedTour();
  }

  ngOnDestroy(): void {
    this.resetActivity();
    this.tourService.deleteSelectedTour();
  }

  previousState() {
    window.history.back();
  }

  private initSelectedTour(): void {
    if (this.tourService.selectedTour != null) {
      this.activity.title = this.tourService.selectedTour.title;
      this.activity.location = this.tourService.selectedTour.location;
      this.activity.street = this.tourService.selectedTour.street;
      this.activity.number = this.tourService.selectedTour.number;
      this.activity.zip = this.tourService.selectedTour.zip;
      this.activity.city = this.tourService.selectedTour.city;
      this.activity.name = this.tourService.selectedTour.name;
      this.activity.email = this.tourService.selectedTour.email;
      this.activity.website = this.tourService.selectedTour.website;
    }
  }

  save() {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.activityService.create(this.activity));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActivity>>) {
    result.subscribe((res: HttpResponse<IActivity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.router.navigate(['/calendar']);
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  resetActivity() {
    this.activity = new Activity();
  }

  setDate() {
    this.calendarService.date = this.activity.date.format('YYYY-MM-DD');
    this.activity = new Activity();
  }
}
