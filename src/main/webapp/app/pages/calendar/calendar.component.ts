import { AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import nlLocale from '@fullcalendar/core/locales/nl';
import { Router } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import momentPlugin from '@fullcalendar/moment';
import { FullCalendarComponent } from '@fullcalendar/angular';
import { Subscription } from 'rxjs';
import { CalendarService } from 'app/pages/calendar/calendar.service';
import { ICalendar } from 'app/shared/model/calendar.model';

@Component({
  selector: 'jhi-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent implements OnInit {
  @ViewChild('calendar') calendarComponent: FullCalendarComponent;
  calendarPlugins: any[];
  activities: ICalendar[];
  firstDay: number;
  locale;

  @ViewChild('calendar') calendar: CalendarComponent;

  constructor(private router: Router, private calendarService: CalendarService) {}

  ngOnInit() {
    this.activities = [];
    this.setCalendar();
    this.loadActivities();

    console.log(this.calendar);
  }

  handleDateClick(arg) {
    this.calendarService.date = arg.dateStr;

    setTimeout(() => {
      this.router.navigate(['/activity', 'new']);
    }, 0);
  }

  handleEventClick(arg) {
    const activity = this.activities.find(act => act.id.toString() === arg.event.id);
    const url = '/activity/' + activity.id + '/view';
    this.router.navigate([url]);
  }

  private setCalendar(): void {
    this.calendarPlugins = [dayGridPlugin, timeGridPlugin, interactionPlugin, momentPlugin];
    this.firstDay = 1;
    this.locale = nlLocale;
  }

  private loadActivities(): void {
    this.calendarService
      .query()
      .pipe(
        filter((res: HttpResponse<ICalendar[]>) => res.ok),
        map((res: HttpResponse<ICalendar[]>) => res.body)
      )
      .subscribe((res: ICalendar[]) => {
        this.activities = res;
      });
  }
}
