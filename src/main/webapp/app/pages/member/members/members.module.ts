import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { DeVeldploetersSharedModule } from 'app/shared';
import { RouterModule } from '@angular/router';
import { MembersIcesComponent, memberRoute, MembersOverviewComponent } from './index';

const ENTITY_STATES = [...memberRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MembersOverviewComponent, MembersIcesComponent],
  entryComponents: [MembersOverviewComponent, MembersIcesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersMembersModule {}
