import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { TourSelectComponent } from 'app/entities/tour/tour-select.component';

export const tourRoute: Routes = [
  {
    path: 'select',
    component: TourSelectComponent,
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Verwijder Toertocht'
    },
    canActivate: [UserRouteAccessService]
  }
];
