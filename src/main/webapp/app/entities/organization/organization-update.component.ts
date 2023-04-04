import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IOrganization, Organization } from 'app/shared/model/organization.model';
import { OrganizationService } from './organization.service';

@Component({
  selector: 'jhi-organization-update',
  templateUrl: './organization-update.component.html'
})
export class OrganizationUpdateComponent implements OnInit {
  organization: IOrganization;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    website: [],
    email: []
  });

  constructor(protected organizationService: OrganizationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ organization }) => {
      this.updateForm(organization);
      this.organization = organization;
    });
  }

  updateForm(organization: IOrganization) {
    this.editForm.patchValue({
      id: organization.id,
      name: organization.name,
      website: organization.website,
      email: organization.email
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const organization = this.createFromForm();
    if (organization.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationService.update(organization));
    } else {
      this.subscribeToSaveResponse(this.organizationService.create(organization));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganization>>) {
    result.subscribe((res: HttpResponse<IOrganization>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  private createFromForm(): IOrganization {
    const entity = {
      ...new Organization(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      website: this.editForm.get(['website']).value,
      email: this.editForm.get(['email']).value
    };
    return entity;
  }
}
