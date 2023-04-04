import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils, JhiEventManager } from 'ng-jhipster';

import { IBannerImage } from 'app/shared/model/banner-image.model';
import { AccountService } from 'app/core';
import { BannerImageService } from './banner-image.service';

@Component({
  selector: 'jhi-banner-image',
  templateUrl: './banner-image.component.html',
  styleUrls: ['./banner-image.component.scss']
})
export class BannerImageComponent implements OnInit, OnDestroy {
  bannerImages: IBannerImage[];
  currentAccount: any;
  eventSubscriber: Subscription;
  error: any;
  success: any;

  constructor(
    protected bannerImageService: BannerImageService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.bannerImageService
      .query()
      .pipe(
        filter((res: HttpResponse<IBannerImage[]>) => res.ok),
        map((res: HttpResponse<IBannerImage[]>) => res.body)
      )
      .subscribe(
        (res: IBannerImage[]) => {
          this.bannerImages = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInBannerImages();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IBannerImage) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInBannerImages() {
    this.eventSubscriber = this.eventManager.subscribe('bannerImageListModification', response => this.loadAll());
  }

  setActive(image, isActivated) {
    image.active = isActivated;

    this.bannerImageService.update(image).subscribe(response => {
      if (response.status === 200) {
        this.error = null;
        this.success = 'OK';
        this.loadAll();
      } else {
        this.success = null;
        this.error = 'ERROR';
      }
    });
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
