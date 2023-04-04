import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from './activity.service';

@Component({
  selector: 'jhi-activity-update',
  templateUrl: './activity-update.component.html'
})
export class ActivityUpdateComponent implements OnInit {
  activity: IActivity;
  isSaving: boolean;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected activityService: ActivityService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ activity }) => {
      this.activity = activity;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.activityService.update(this.activity));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActivity>>) {
    result.subscribe((res: HttpResponse<IActivity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
