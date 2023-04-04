import { Route } from '@angular/router';

import { HomeComponent } from './index';

export const HOME_ROUTE: Route = {
  path: '',
  component: HomeComponent,
  data: {
    authorities: [],
    pageTitle: 'De Veldploeters - Home'
  }
};
