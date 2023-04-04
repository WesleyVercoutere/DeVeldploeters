import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BannerImage, IBannerImage } from 'app/shared/model/banner-image.model';
import { BannerImageService } from './banner-image.service';
import { BannerImageComponent } from './banner-image.component';
import { BannerImageDetailComponent } from './banner-image-detail.component';
import { BannerImageUpdateComponent } from './banner-image-update.component';
import { BannerImageDeletePopupComponent } from './banner-image-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class BannerImageResolve implements Resolve<IBannerImage> {
  constructor(private service: BannerImageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBannerImage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BannerImage>) => response.ok),
        map((bannerImage: HttpResponse<BannerImage>) => bannerImage.body)
      );
    }
    return of(new BannerImage());
  }
}

export const bannerImageRoute: Routes = [
  {
    path: '',
    component: BannerImageComponent,
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'BannerImages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BannerImageDetailComponent,
    resolve: {
      bannerImage: BannerImageResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'BannerImages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BannerImageUpdateComponent,
    resolve: {
      bannerImage: BannerImageResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'BannerImages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BannerImageUpdateComponent,
    resolve: {
      bannerImage: BannerImageResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'BannerImages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const bannerImagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BannerImageDeletePopupComponent,
    resolve: {
      bannerImage: BannerImageResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_BOARD'],
      pageTitle: 'BannerImages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
