import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICE, IICE } from 'app/shared/model/ice.model';
import { ICEService } from './ice.service';
import { IMember } from 'app/shared/model/member.model';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-ice-update',
  templateUrl: './ice-update.component.html'
})
export class ICEUpdateComponent implements OnInit {
  iCE: IICE;
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    lastName: [],
    firstName: [],
    phone: [],
    memberId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected iCEService: ICEService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ iCE }) => {
      this.updateForm(iCE);
      this.iCE = iCE;
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMember[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMember[]>) => response.body)
      )
      .subscribe((res: IMember[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(iCE: IICE) {
    this.editForm.patchValue({
      id: iCE.id,
      lastName: iCE.lastName,
      firstName: iCE.firstName,
      phone: iCE.phone,
      memberId: iCE.memberId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const iCE = this.createFromForm();
    if (iCE.id !== undefined) {
      this.subscribeToSaveResponse(this.iCEService.update(iCE));
    } else {
      this.subscribeToSaveResponse(this.iCEService.create(iCE));
    }
  }

  trackMemberById(index: number, item: IMember) {
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

  private createFromForm(): IICE {
    const entity = {
      ...new ICE(),
      id: this.editForm.get(['id']).value,
      lastName: this.editForm.get(['lastName']).value,
      firstName: this.editForm.get(['firstName']).value,
      phone: this.editForm.get(['phone']).value,
      memberId: this.editForm.get(['memberId']).value
    };
    return entity;
  }
}
