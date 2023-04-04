import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '',
        loadChildren: './public/home/home.module#DeVeldploetersHomeModule'
      },
      {
        path: 'whoweare',
        loadChildren: './public/who-we-are/who-we-are.module#DeVeldploetersWhoWeAreModule'
      },
      {
        path: 'activities',
        loadChildren: './public/activities/activities.module#DeVeldploetersActivitiesModule'
      },
      {
        path: 'sponsors',
        loadChildren: './public/sponsors/sponsors.module#DeVeldploetersSponsorsModule'
      },
      {
        path: 'contact',
        loadChildren: './public/contact/contact.module#DeVeldploetersContactModule'
      },
      {
        path: 'calendar',
        loadChildren: './calendar/calendar.module#DeVeldploetersCalendarModule'
      },
      {
        path: 'members',
        loadChildren: './member/members/members.module#DeVeldploetersMembersModule'
      },
      {
        path: 'members/activities',
        loadChildren: './member/activities/activities.overview.module#DeVeldploetersActivitiesOverviewModule'
      },
      {
        path: 'members/myActivities',
        loadChildren: './member/myActivities/my.activities.module#DeVeldploetersMyActivitiesModule'
      }
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersPagesModule {}
