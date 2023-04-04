import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPresence, Presence } from 'app/shared/model/presence.model';
import { PresenceService } from './presence.service';
import { PresenceComponent } from './presence.component';
import { PresenceDetailComponent } from './presence-detail.component';
import { PresenceUpdateComponent } from './presence-update.component';
import { PresenceDeletePopupComponent } from './presence-delete-dialog.component';
import { PresenceActivityComponent } from './presence-activity.component';
import { PresenceAddUserPopupComponent } from 'app/entities/presence/presence-add-user-dialog.component';
import { ActivityService } from 'app/entities/activity';
import { Activity, IActivity } from 'app/shared/model/activity.model';

@Injectable({ providedIn: 'root' })
export class PresenceResolve implements Resolve<IPresence> {
  constructor(private service: PresenceService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPresence> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Presence>) => response.ok),
        map((presence: HttpResponse<Presence>) => presence.body)
      );
    }
    return of(new Presence());
  }
}

@Injectable({ providedIn: 'root' })
export class PresenceActivityResolve implements Resolve<IActivity> {
  constructor(private service: ActivityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IActivity> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Activity>) => response.ok),
        map((activity: HttpResponse<Activity>) => activity.body)
      );
    }
    return of(new Activity());
  }
}

export const presenceRoute: Routes = [
  {
    path: '',
    component: PresenceComponent,
    data: {
      authorities: ['ROLE_BOARD', 'ROLE_ADMIN'],
      pageTitle: 'Presences'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PresenceDetailComponent,
    resolve: {
      presence: PresenceResolve
    },
    data: {
      authorities: ['ROLE_BOARD', 'ROLE_ADMIN'],
      pageTitle: 'Presences'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PresenceUpdateComponent,
    resolve: {
      presence: PresenceResolve
    },
    data: {
      authorities: ['ROLE_BOARD', 'ROLE_ADMIN'],
      pageTitle: 'Presences'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PresenceUpdateComponent,
    resolve: {
      presence: PresenceResolve
    },
    data: {
      authorities: ['ROLE_BOARD', 'ROLE_ADMIN'],
      pageTitle: 'Presences'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/activity',
    component: PresenceActivityComponent,
    data: {
      authorities: ['ROLE_BOARD', 'ROLE_ADMIN'],
      pageTitle: 'Aanwezigheden'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const presencePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PresenceDeletePopupComponent,
    resolve: {
      presence: PresenceResolve
    },
    data: {
      authorities: ['ROLE_BOARD', 'ROLE_ADMIN'],
      pageTitle: 'Presences'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: ':id/addUser',
    component: PresenceAddUserPopupComponent,
    resolve: {
      presence: PresenceActivityResolve
    },
    data: {
      authorities: ['ROLE_BOARD', 'ROLE_ADMIN'],
      pageTitle: 'Presences'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
