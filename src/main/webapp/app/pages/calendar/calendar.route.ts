import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { CalendarComponent } from './calendar.component';

export const calendarRoute: Routes = [
  {
    path: '',
    component: CalendarComponent,
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD', 'ROLE_MEMBER'],
      pageTitle: 'Agenda'
    },
    canActivate: [UserRouteAccessService]
  }
];
