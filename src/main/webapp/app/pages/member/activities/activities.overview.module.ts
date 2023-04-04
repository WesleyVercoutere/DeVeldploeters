import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { DeVeldploetersSharedModule } from 'app/shared';
import { RouterModule } from '@angular/router';
import { ACTIVITIES_OVERVIEW_ROUTE, ActivitiesOverviewComponent } from './index';

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild([ACTIVITIES_OVERVIEW_ROUTE])],
  declarations: [ActivitiesOverviewComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersActivitiesOverviewModule {}
