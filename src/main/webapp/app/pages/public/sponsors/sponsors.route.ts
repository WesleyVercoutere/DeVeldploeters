import { Route } from '@angular/router';

import { SponsorsComponent } from './index';

export const SPONSOR_ROUTE: Route = {
  path: '',
  component: SponsorsComponent,
  data: {
    authorities: [],
    pageTitle: 'De Veldploeters - Sponsors'
  }
};
