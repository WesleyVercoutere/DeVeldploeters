import { Route } from '@angular/router';

import { MyActivitiesComponent } from './index';
import { UserRouteAccessService } from 'app/core';

export const MY_ACTIVITIES_ROUTE: Route = {
  path: '',
  component: MyActivitiesComponent,
  data: {
    authorities: ['ROLE_ADMIN', 'ROLE_BOARD', 'ROLE_MEMBER'],
    pageTitle: 'De Veldploeters - Activiteiten'
  },
  canActivate: [UserRouteAccessService]
};
