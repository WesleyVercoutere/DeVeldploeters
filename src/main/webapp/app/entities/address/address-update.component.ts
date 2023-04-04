import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Address, IAddress } from 'app/shared/model/address.model';
import { AddressService } from './address.service';

@Component({
  selector: 'jhi-address-update',
  templateUrl: './address-update.component.html'
})
export class AddressUpdateComponent implements OnInit {
  address: IAddress;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    street: [],
    number: [],
    zipCode: [],
    city: []
  });

  constructor(protected addressService: AddressService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ address }) => {
      this.updateForm(address);
      this.address = address;
    });
  }

  updateForm(address: IAddress) {
    this.editForm.patchValue({
      id: address.id,
      street: address.street,
      number: address.number,
      zipCode: address.zipCode,
      city: address.city
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const address = this.createFromForm();
    if (address.id !== undefined) {
      this.subscribeToSaveResponse(this.addressService.update(address));
    } else {
      this.subscribeToSaveResponse(this.addressService.create(address));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAddress>>) {
    result.subscribe((res: HttpResponse<IAddress>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  private createFromForm(): IAddress {
    const entity = {
      ...new Address(),
      id: this.editForm.get(['id']).value,
      street: this.editForm.get(['street']).value,
      number: this.editForm.get(['number']).value,
      zipCode: this.editForm.get(['zipCode']).value,
      city: this.editForm.get(['city']).value
    };
    return entity;
  }
}
