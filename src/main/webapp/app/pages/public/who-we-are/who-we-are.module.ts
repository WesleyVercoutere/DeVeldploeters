import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';
import { WHOWEARE_ROUTE, WhoWeAreComponent } from './index';

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild([WHOWEARE_ROUTE])],
  declarations: [WhoWeAreComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersWhoWeAreModule {}
