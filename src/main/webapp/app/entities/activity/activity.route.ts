import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Activity, IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from './activity.service';
import { ActivityComponent } from './activity.component';
import { ActivityDetailComponent } from './activity-detail.component';
import { ActivityUpdateComponent } from './activity-update.component';
import { ActivityDeletePopupComponent } from './activity-delete-dialog.component';
import { ActivityNewComponent } from 'app/entities/activity/activity-new.component';

@Injectable({ providedIn: 'root' })
export class ActivityResolve implements Resolve<IActivity> {
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

export const activityRoute: Routes = [
  {
    path: '',
    component: ActivityComponent,
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Activiteiten'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ActivityDetailComponent,
    resolve: {
      activity: ActivityResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD', 'ROLE_MEMBER'],
      pageTitle: 'Activiteiten'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ActivityNewComponent,
    resolve: {
      activity: ActivityResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Activiteiten'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ActivityUpdateComponent,
    resolve: {
      activity: ActivityResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Activiteiten'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const activityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ActivityDeletePopupComponent,
    resolve: {
      activity: ActivityResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Activiteiten'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
