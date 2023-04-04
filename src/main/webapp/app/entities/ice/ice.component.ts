import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { IICE } from 'app/shared/model/ice.model';
import { AccountService } from 'app/core';
import { ICEService } from './ice.service';

@Component({
  selector: 'jhi-ice',
  templateUrl: './ice.component.html'
})
export class ICEComponent implements OnInit, OnDestroy {
  iCES: IICE[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected iCEService: ICEService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.iCEService
      .query()
      .pipe(
        filter((res: HttpResponse<IICE[]>) => res.ok),
        map((res: HttpResponse<IICE[]>) => res.body)
      )
      .subscribe(
        (res: IICE[]) => {
          this.iCES = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInICES();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IICE) {
    return item.id;
  }

  registerChangeInICES() {
    this.eventSubscriber = this.eventManager.subscribe('iCEListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
