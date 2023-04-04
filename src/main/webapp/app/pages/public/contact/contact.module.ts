import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { DeVeldploetersSharedModule } from 'app/shared';
import { RouterModule } from '@angular/router';
import { CONTACT_ROUTE, ContactComponent } from './index';

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild([CONTACT_ROUTE])],
  declarations: [ContactComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersContactModule {}
