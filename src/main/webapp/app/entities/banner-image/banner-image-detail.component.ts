import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IBannerImage } from 'app/shared/model/banner-image.model';

@Component({
  selector: 'jhi-banner-image-detail',
  templateUrl: './banner-image-detail.component.html',
  styleUrls: ['./banner-image-detail.component.scss']
})
export class BannerImageDetailComponent implements OnInit {
  bannerImage: IBannerImage;
  imgHeight: string;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bannerImage }) => {
      this.bannerImage = bannerImage;
    });

    this.setImageHeight();
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  previousState() {
    window.history.back();
  }

  @HostListener('window:resize', ['$event'])
  setImageHeight() {
    const factor = 1140 / 640;
    const element = document.getElementById('containerWidth');
    const size = element.offsetWidth;
    this.imgHeight = (size / factor).toString();
  }
}
