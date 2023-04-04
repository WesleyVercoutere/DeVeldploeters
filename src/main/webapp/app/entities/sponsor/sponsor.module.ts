import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import {
  SponsorComponent,
  SponsorDeleteDialogComponent,
  SponsorDeletePopupComponent,
  SponsorDetailComponent,
  sponsorPopupRoute,
  sponsorRoute,
  SponsorUpdateComponent
} from './';

const ENTITY_STATES = [...sponsorRoute, ...sponsorPopupRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SponsorComponent,
    SponsorDetailComponent,
    SponsorUpdateComponent,
    SponsorDeleteDialogComponent,
    SponsorDeletePopupComponent
  ],
  entryComponents: [SponsorComponent, SponsorUpdateComponent, SponsorDeleteDialogComponent, SponsorDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersSponsorModule {}
