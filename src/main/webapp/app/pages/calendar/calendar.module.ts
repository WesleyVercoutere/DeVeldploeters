import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FullCalendarModule } from '@fullcalendar/angular'; // for FullCalendar!
import { DeVeldploetersSharedModule } from 'app/shared';
import { CalendarComponent, calendarRoute } from './';

const ENTITY_STATES = [...calendarRoute];

@NgModule({
  imports: [DeVeldploetersSharedModule, RouterModule.forChild(ENTITY_STATES), FullCalendarModule],
  declarations: [CalendarComponent],
  entryComponents: [CalendarComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeVeldploetersCalendarModule {}
