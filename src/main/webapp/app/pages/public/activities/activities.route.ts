import { Route } from '@angular/router';

import { ActivitiesComponent } from './index';

export const ACTIVITIES_ROUTE: Route = {
  path: '',
  component: ActivitiesComponent,
  data: {
    authorities: [],
    pageTitle: 'De Veldploeters - Activiteiten'
  }
};
