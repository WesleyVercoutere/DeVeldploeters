import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';
import { ISponsor } from 'app/shared/model/sponsor.model';
import { SponsorService } from './sponsor.service';

@Component({
  selector: 'jhi-sponsor-update',
  templateUrl: './sponsor-update.component.html'
})
export class SponsorUpdateComponent implements OnInit {
  sponsor: ISponsor;
  isSaving: boolean;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected sponsorService: SponsorService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sponsor }) => {
      this.sponsor = sponsor;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, entity, field, isImage) {
    this.dataUtils.setFileData(event, entity, field, isImage);
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.dataUtils.clearInputImage(this.sponsor, this.elementRef, field, fieldContentType, idInput);
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.sponsor.id !== undefined) {
      this.subscribeToSaveResponse(this.sponsorService.update(this.sponsor));
    } else {
      this.subscribeToSaveResponse(this.sponsorService.create(this.sponsor));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISponsor>>) {
    result.subscribe((res: HttpResponse<ISponsor>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
