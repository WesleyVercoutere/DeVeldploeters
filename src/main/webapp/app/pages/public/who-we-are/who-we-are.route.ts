import { Route } from '@angular/router';

import { WhoWeAreComponent } from './index';

export const WHOWEARE_ROUTE: Route = {
  path: '',
  component: WhoWeAreComponent,
  data: {
    authorities: [],
    pageTitle: 'De Veldploeters - Wie zijn wij'
  }
};
