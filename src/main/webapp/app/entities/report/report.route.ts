import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Report } from 'app/shared/model/report.model';
import { ReportService } from './report.service';
import { ReportComponent } from './report.component';
import { ReportDetailComponent } from './report-detail.component';
import { ReportUpdateComponent } from './report-update.component';
import { ReportDeletePopupComponent } from './report-delete-dialog.component';
import { IReport } from 'app/shared/model/report.model';
import { ActivityService } from 'app/entities/activity';
import { Activity, IActivity } from 'app/shared/model/activity.model';

@Injectable({ providedIn: 'root' })
export class ReportResolve implements Resolve<IReport> {
  constructor(private service: ReportService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IReport> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Report>) => response.ok),
        map((report: HttpResponse<Report>) => report.body)
      );
    }
    return of(new Report());
  }
}

@Injectable({ providedIn: 'root' })
export class ActivityReportResolve implements Resolve<IReport> {
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

export const reportRoute: Routes = [
  {
    path: '',
    component: ReportComponent,
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Reports'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ReportDetailComponent,
    resolve: {
      report: ReportResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Reports'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ReportUpdateComponent,
    resolve: {
      report: ReportResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Reports'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ReportUpdateComponent,
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Reports'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const reportPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ReportDeletePopupComponent,
    resolve: {
      report: ReportResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'Reports'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
