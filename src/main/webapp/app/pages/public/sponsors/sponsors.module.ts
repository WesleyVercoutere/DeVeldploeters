import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { DeVeldploetersSharedModule } from 'app/shared';
import { RouterModule } from '@angular/router';
import { SPONSOR_ROUTE, SponsorsComponent } from 'app/pages/public/sponsors/index';

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild([SPONSOR_ROUTE])],
  declarations: [SponsorsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersSponsorsModule {}
