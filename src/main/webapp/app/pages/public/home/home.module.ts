import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import { HOME_ROUTE, HomeComponent } from './index';

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersHomeModule {}
