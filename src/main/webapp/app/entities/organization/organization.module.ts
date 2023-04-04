import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import {
  OrganizationComponent,
  OrganizationDeleteDialogComponent,
  OrganizationDeletePopupComponent,
  OrganizationDetailComponent,
  organizationPopupRoute,
  organizationRoute,
  OrganizationUpdateComponent
} from './';

const ENTITY_STATES = [...organizationRoute, ...organizationPopupRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrganizationComponent,
    OrganizationDetailComponent,
    OrganizationUpdateComponent,
    OrganizationDeleteDialogComponent,
    OrganizationDeletePopupComponent
  ],
  entryComponents: [
    OrganizationComponent,
    OrganizationUpdateComponent,
    OrganizationDeleteDialogComponent,
    OrganizationDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersOrganizationModule {}
