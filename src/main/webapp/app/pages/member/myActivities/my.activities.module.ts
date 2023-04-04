import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { DeVeldploetersSharedModule } from 'app/shared';
import { RouterModule } from '@angular/router';
import { MY_ACTIVITIES_ROUTE, MyActivitiesComponent } from './index';

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild([MY_ACTIVITIES_ROUTE])],
  declarations: [MyActivitiesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersMyActivitiesModule {}
