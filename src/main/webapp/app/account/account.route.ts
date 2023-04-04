import { Routes } from '@angular/router';

import { addressRoute, passwordResetFinishRoute, passwordResetInitRoute, passwordRoute, settingsRoute } from './';

const ACCOUNT_ROUTES = [passwordRoute, passwordResetFinishRoute, passwordResetInitRoute, settingsRoute, addressRoute];

export const accountState: Routes = [
  {
    path: '',
    children: ACCOUNT_ROUTES
  },
  {
    path: 'account/ice',
    loadChildren: './ice/ice.module#DeVeldploetersICEModule'
  }
];
