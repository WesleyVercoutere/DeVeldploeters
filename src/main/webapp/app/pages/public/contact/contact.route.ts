import { Route } from '@angular/router';

import { ContactComponent } from './index';

export const CONTACT_ROUTE: Route = {
  path: '',
  component: ContactComponent,
  data: {
    authorities: [],
    pageTitle: 'De Veldploeters - Contact'
  }
};
