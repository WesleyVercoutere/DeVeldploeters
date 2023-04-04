import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ICE, IICE } from 'app/shared/model/ice.model';
import { ICEService } from './ice.service';
import { ICEComponent } from './ice.component';
import { ICEDetailComponent } from './ice-detail.component';
import { ICEUpdateComponent } from './ice-update.component';
import { ICEDeletePopupComponent } from './ice-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class ICEResolve implements Resolve<IICE> {
  constructor(private service: ICEService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IICE> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ICE>) => response.ok),
        map((iCE: HttpResponse<ICE>) => iCE.body)
      );
    }
    return of(new ICE());
  }
}

export const iCERoute: Routes = [
  {
    path: '',
    component: ICEComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ICES'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ICEDetailComponent,
    resolve: {
      iCE: ICEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ICES'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ICEUpdateComponent,
    resolve: {
      iCE: ICEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ICES'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ICEUpdateComponent,
    resolve: {
      iCE: ICEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ICES'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const iCEPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ICEDeletePopupComponent,
    resolve: {
      iCE: ICEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ICES'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
