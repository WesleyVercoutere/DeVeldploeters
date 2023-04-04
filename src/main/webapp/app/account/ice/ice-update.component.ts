import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IICE } from 'app/shared/model/ice.model';
import { ICEService } from './ice.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-ice-update',
  templateUrl: './ice-update.component.html'
})
export class ICEUpdateComponent implements OnInit {
  iCE: IICE;
  isSaving: boolean;

  users: IUser[];

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected iCEService: ICEService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ iCE }) => {
      this.iCE = iCE;
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.iCE.id !== undefined) {
      this.subscribeToSaveResponse(this.iCEService.update(this.iCE));
    } else {
      this.subscribeToSaveResponse(this.iCEService.create(this.iCE));
    }
  }

  trackMemberById(index: number, item: IUser) {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IICE>>) {
    result.subscribe((res: HttpResponse<IICE>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
