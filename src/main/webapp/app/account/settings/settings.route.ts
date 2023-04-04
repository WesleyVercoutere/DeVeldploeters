import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { SettingsComponent } from './settings.component';

export const settingsRoute: Route = {
  path: 'account/settings',
  component: SettingsComponent,
  data: {
    authorities: ['ROLE_MEMBER'],
    pageTitle: 'Settings'
  },
  canActivate: [UserRouteAccessService]
};
