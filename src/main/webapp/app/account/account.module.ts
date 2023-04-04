import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DeVeldploetersSharedModule } from 'app/shared';

import {
  accountState,
  AddressComponent,
  DeVeldploetersICEModule,
  PasswordComponent,
  PasswordResetFinishComponent,
  PasswordResetInitComponent,
  PasswordStrengthBarComponent,
  SettingsComponent
} from './';

@NgModule({
  imports: [DeVeldploetersSharedModule, DeVeldploetersICEModule, RouterModule.forChild(accountState)],
  declarations: [
    AddressComponent,
    PasswordComponent,
    PasswordStrengthBarComponent,
    PasswordResetInitComponent,
    PasswordResetFinishComponent,
    SettingsComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersAccountModule {}
