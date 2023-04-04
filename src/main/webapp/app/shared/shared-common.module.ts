import { NgModule } from '@angular/core';

import { DeVeldploetersSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [DeVeldploetersSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [DeVeldploetersSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class DeVeldploetersSharedCommonModule {}
