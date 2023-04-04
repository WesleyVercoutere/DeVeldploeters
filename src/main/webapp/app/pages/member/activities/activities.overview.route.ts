import { Route } from '@angular/router';

import { ActivitiesOverviewComponent } from './index';
import { UserRouteAccessService } from 'app/core';

export const ACTIVITIES_OVERVIEW_ROUTE: Route = {
  path: '',
  component: ActivitiesOverviewComponent,
  data: {
    authorities: ['ROLE_ADMIN', 'ROLE_BOARD', 'ROLE_MEMBER'],
    pageTitle: 'De Veldploeters - Activiteiten'
  },
  canActivate: [UserRouteAccessService]
};
