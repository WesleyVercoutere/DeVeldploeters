import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { DeVeldploetersSharedModule } from 'app/shared';
import { RouterModule } from '@angular/router';
import { ACTIVITIES_ROUTE, ActivitiesComponent } from 'app/pages/public/activities/index';

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild([ACTIVITIES_ROUTE])],
  declarations: [ActivitiesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersActivitiesModule {}
