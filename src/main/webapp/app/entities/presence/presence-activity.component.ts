import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { IPresence } from 'app/shared/model/presence.model';
import { PresenceService } from './presence.service';
import { ActivityService } from 'app/entities/activity';
import { IActivity } from 'app/shared/model/activity.model';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-presence-update',
  templateUrl: './presence-activity.component.html'
})
export class PresenceActivityComponent implements OnInit, OnDestroy {
  isSaving: boolean;

  attendances: IPresence[];
  activity: IActivity;

  eventSubscriber: Subscription;

  constructor(
    protected presenceService: PresenceService,
    private activatedRoute: ActivatedRoute,
    protected eventManager: JhiEventManager,
    private activityService: ActivityService,
    protected jhiAlertService: JhiAlertService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.loadAll();
    this.registerChangeInPresences();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  private loadAll(): void {
    let id;
    this.activatedRoute.params.subscribe(params => {
      id = +params['id'];
    });

    this.activityService.find(id).subscribe(res => {
      this.activity = res.body;
      this.initAttendances();
    });
  }

  private initAttendances() {
    const id = this.activity.id;

    this.presenceService
      .findActivityPresence(id)
      .pipe(
        filter((res: HttpResponse<IPresence[]>) => res.ok),
        map((res: HttpResponse<IPresence[]>) => res.body)
      )
      .subscribe(
        (res: IPresence[]) => {
          this.attendances = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPresence>>) {
    result.subscribe((res: HttpResponse<IPresence>) => this.onSaveSuccess());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  public setActive(presence: IPresence) {
    this.isSaving = true;
    presence.present = !presence.present;
    this.presenceService.update(presence).subscribe(res => {
      presence = res.body;
      this.isSaving = false;
    });
  }

  registerChangeInPresences() {
    this.eventSubscriber = this.eventManager.subscribe('presenceListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
