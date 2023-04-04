import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPresence } from 'app/shared/model/presence.model';
import { AccountService } from 'app/core';
import { PresenceService } from './presence.service';

@Component({
  selector: 'jhi-presence',
  templateUrl: './presence.component.html'
})
export class PresenceComponent implements OnInit, OnDestroy {
  presences: IPresence[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected presenceService: PresenceService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.presenceService
      .query()
      .pipe(
        filter((res: HttpResponse<IPresence[]>) => res.ok),
        map((res: HttpResponse<IPresence[]>) => res.body)
      )
      .subscribe(
        (res: IPresence[]) => {
          this.presences = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPresences();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPresence) {
    return item.id;
  }

  registerChangeInPresences() {
    this.eventSubscriber = this.eventManager.subscribe('presenceListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
