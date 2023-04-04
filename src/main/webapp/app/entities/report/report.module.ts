import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import {
  ReportComponent,
  ReportDetailComponent,
  ReportUpdateComponent,
  ReportDeletePopupComponent,
  ReportDeleteDialogComponent,
  reportRoute,
  reportPopupRoute
} from './';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';

const ENTITY_STATES = [...reportRoute, ...reportPopupRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES), CKEditorModule],
  declarations: [ReportComponent, ReportDetailComponent, ReportUpdateComponent, ReportDeleteDialogComponent, ReportDeletePopupComponent],
  entryComponents: [ReportComponent, ReportUpdateComponent, ReportDeleteDialogComponent, ReportDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersReportModule {}
