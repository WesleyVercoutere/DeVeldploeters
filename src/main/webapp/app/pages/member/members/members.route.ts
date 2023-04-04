import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';

import { MembersIcesComponent, MembersOverviewComponent } from './index';
import { IUser, User, UserRouteAccessService, UserService } from 'app/core';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { filter, map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class MemberResolve implements Resolve<IUser> {
  constructor(private service: UserService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IUser> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.findById(id).pipe(
        filter((response: HttpResponse<User>) => response.ok),
        map((user: HttpResponse<User>) => user.body)
      );
    }
    return of(new User());
  }
}

export const memberRoute: Routes = [
  {
    path: 'overview',
    component: MembersOverviewComponent,
    data: {
      authorities: ['ROLE_MEMBER'],
      pageTitle: 'De Veldploeters - Leden'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/ices',
    component: MembersIcesComponent,
    resolve: {
      user: MemberResolve
    },
    data: {
      authorities: ['ROLE_MEMBER'],
      pageTitle: 'De Veldploeters - Leden'
    },
    canActivate: [UserRouteAccessService]
  }
];
