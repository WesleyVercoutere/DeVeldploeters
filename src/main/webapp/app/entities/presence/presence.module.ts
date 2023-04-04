import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import {
  PresenceActivityComponent,
  PresenceComponent,
  PresenceDeleteDialogComponent,
  PresenceDeletePopupComponent,
  PresenceAddUserDialogComponent,
  PresenceAddUserPopupComponent,
  PresenceDetailComponent,
  presencePopupRoute,
  presenceRoute,
  PresenceUpdateComponent
} from './';

const ENTITY_STATES = [...presenceRoute, ...presencePopupRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PresenceComponent,
    PresenceDetailComponent,
    PresenceUpdateComponent,
    PresenceDeleteDialogComponent,
    PresenceDeletePopupComponent,
    PresenceActivityComponent,
    PresenceAddUserDialogComponent,
    PresenceAddUserPopupComponent
  ],
  entryComponents: [
    PresenceComponent,
    PresenceUpdateComponent,
    PresenceDeleteDialogComponent,
    PresenceDeletePopupComponent,
    PresenceActivityComponent,
    PresenceAddUserDialogComponent,
    PresenceAddUserPopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersPresenceModule {}
