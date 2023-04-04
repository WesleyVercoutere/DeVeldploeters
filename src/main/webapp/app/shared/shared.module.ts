import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { DeVeldploetersSharedCommonModule, DeVeldploetersSharedLibsModule, HasAnyAuthorityDirective, JhiLoginModalComponent } from './';

@NgModule({
  imports: [DeVeldploetersSharedLibsModule, DeVeldploetersSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [DeVeldploetersSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersSharedModule {
  static forRoot() {
    return {
      ngModule: DeVeldploetersSharedModule
    };
  }
}
