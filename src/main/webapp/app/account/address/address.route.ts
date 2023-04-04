import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { AddressComponent } from './address.component';

export const addressRoute: Route = {
  path: 'account/address',
  component: AddressComponent,
  data: {
    authorities: ['ROLE_MEMBER'],
    pageTitle: 'Adres'
  },
  canActivate: [UserRouteAccessService]
};
