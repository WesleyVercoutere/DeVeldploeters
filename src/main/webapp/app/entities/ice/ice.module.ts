import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import {
  ICEComponent,
  ICEDeleteDialogComponent,
  ICEDeletePopupComponent,
  ICEDetailComponent,
  iCEPopupRoute,
  iCERoute,
  ICEUpdateComponent
} from './';

const ENTITY_STATES = [...iCERoute, ...iCEPopupRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ICEComponent, ICEDetailComponent, ICEUpdateComponent, ICEDeleteDialogComponent, ICEDeletePopupComponent],
  entryComponents: [ICEComponent, ICEUpdateComponent, ICEDeleteDialogComponent, ICEDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersICEModule {}
