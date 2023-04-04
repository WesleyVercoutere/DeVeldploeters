import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import {
  AddressComponent,
  AddressDeleteDialogComponent,
  AddressDeletePopupComponent,
  AddressDetailComponent,
  addressPopupRoute,
  addressRoute,
  AddressUpdateComponent
} from './';

const ENTITY_STATES = [...addressRoute, ...addressPopupRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AddressComponent,
    AddressDetailComponent,
    AddressUpdateComponent,
    AddressDeleteDialogComponent,
    AddressDeletePopupComponent
  ],
  entryComponents: [AddressComponent, AddressUpdateComponent, AddressDeleteDialogComponent, AddressDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersAddressModule {}
