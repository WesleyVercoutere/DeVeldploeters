import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPresence, Presence } from 'app/shared/model/presence.model';
import { PresenceService } from './presence.service';

@Component({
  selector: 'jhi-presence-update',
  templateUrl: './presence-update.component.html'
})
export class PresenceUpdateComponent implements OnInit {
  presence: IPresence;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    present: []
  });

  constructor(protected presenceService: PresenceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ presence }) => {
      this.updateForm(presence);
      this.presence = presence;
    });
  }

  updateForm(presence: IPresence) {
    this.editForm.patchValue({
      id: presence.id,
      present: presence.present
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const presence = this.createFromForm();
    if (presence.id !== undefined) {
      this.subscribeToSaveResponse(this.presenceService.update(presence));
    } else {
      this.subscribeToSaveResponse(this.presenceService.create(presence));
    }
  }

  private createFromForm(): IPresence {
    const entity = {
      ...new Presence(),
      id: this.editForm.get(['id']).value,
      present: this.editForm.get(['present']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPresence>>) {
    result.subscribe((res: HttpResponse<IPresence>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
