import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import {
  BannerImageComponent,
  BannerImageDeleteDialogComponent,
  BannerImageDeletePopupComponent,
  BannerImageDetailComponent,
  bannerImagePopupRoute,
  bannerImageRoute,
  BannerImageUpdateComponent
} from './';

const ENTITY_STATES = [...bannerImageRoute, ...bannerImagePopupRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BannerImageComponent,
    BannerImageDetailComponent,
    BannerImageUpdateComponent,
    BannerImageDeleteDialogComponent,
    BannerImageDeletePopupComponent
  ],
  entryComponents: [BannerImageComponent, BannerImageUpdateComponent, BannerImageDeleteDialogComponent, BannerImageDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersBannerImageModule {}
