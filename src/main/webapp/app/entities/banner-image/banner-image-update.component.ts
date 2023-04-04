import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';
import { IBannerImage } from 'app/shared/model/banner-image.model';
import { BannerImageService } from './banner-image.service';

@Component({
  selector: 'jhi-banner-image-update',
  templateUrl: './banner-image-update.component.html'
})
export class BannerImageUpdateComponent implements OnInit {
  bannerImage: IBannerImage;
  isSaving: boolean;
  disableSave: boolean;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected bannerImageService: BannerImageService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ bannerImage }) => {
      this.bannerImage = bannerImage;
    });

    this.disableSave = true;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, entity, field, isImage) {
    this.dataUtils.setFileData(event, entity, field, isImage);

    this.disableSave = this.bannerImage.image === '';

    // if (this.bannerImage.image === '') {
    //     console.log('leeg');
    // } else {
    //     this.disableSave = false;
    // }
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.dataUtils.clearInputImage(this.bannerImage, this.elementRef, field, fieldContentType, idInput);
    this.disableSave = true;
  }

  previousState() {
    window.history.back();
  }

  save() {
    if (this.bannerImage.image === null) {
      return;
    }

    this.isSaving = true;
    this.bannerImage.active = true;
    this.subscribeToSaveResponse(this.bannerImageService.create(this.bannerImage));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBannerImage>>) {
    result.subscribe((res: HttpResponse<IBannerImage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
