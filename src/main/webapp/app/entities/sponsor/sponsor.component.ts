import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils, JhiEventManager } from 'ng-jhipster';

import { ISponsor } from 'app/shared/model/sponsor.model';
import { AccountService } from 'app/core';
import { SponsorService } from './sponsor.service';

@Component({
  selector: 'jhi-sponsor',
  templateUrl: './sponsor.component.html'
})
export class SponsorComponent implements OnInit, OnDestroy {
  sponsors: ISponsor[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected sponsorService: SponsorService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.sponsorService
      .query()
      .pipe(
        filter((res: HttpResponse<ISponsor[]>) => res.ok),
        map((res: HttpResponse<ISponsor[]>) => res.body)
      )
      .subscribe(
        (res: ISponsor[]) => {
          this.sponsors = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInSponsors();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISponsor) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInSponsors() {
    this.eventSubscriber = this.eventManager.subscribe('sponsorListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
