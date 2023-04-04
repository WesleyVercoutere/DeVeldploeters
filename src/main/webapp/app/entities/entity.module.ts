import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'sponsor',
        loadChildren: './sponsor/sponsor.module#DeVeldploetersSponsorModule'
      },
      {
        path: 'banner-image',
        loadChildren: './banner-image/banner-image.module#DeVeldploetersBannerImageModule'
      },
      {
        path: 'address',
        loadChildren: './address/address.module#DeVeldploetersAddressModule'
      },
      {
        path: 'ice',
        loadChildren: './ice/ice.module#DeVeldploetersICEModule'
      },
      {
        path: 'report',
        loadChildren: './report/report.module#DeVeldploetersReportModule'
      },
      {
        path: 'activity',
        loadChildren: './activity/activity.module#DeVeldploetersActivityModule'
      },
      {
        path: 'tour',
        loadChildren: './tour/tour.module#DeVeldploetersTourModule'
      },
      {
        path: 'organization',
        loadChildren: './organization/organization.module#DeVeldploetersOrganizationModule'
      },
      {
        path: 'presence',
        loadChildren: './presence/presence.module#DeVeldploetersPresenceModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersEntityModule {}
