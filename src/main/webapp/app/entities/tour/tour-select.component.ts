import { Component, OnInit } from '@angular/core';
import { CalendarService } from 'app/pages/calendar';
import { TourService } from 'app/entities/tour/tour.service';
import { filter, map } from 'rxjs/operators';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { JhiAlertService } from 'ng-jhipster';
import { IActivity } from 'app/shared/model/activity.model';

@Component({
  selector: 'jhi-tour-select',
  templateUrl: './tour-select.component.html',
  styleUrls: ['./tour-select.scss']
})
export class TourSelectComponent implements OnInit {
  tours: IActivity[];
  selectedDate: string;
  loading: boolean;

  constructor(protected jhiAlertService: JhiAlertService, private calendarService: CalendarService, private tourService: TourService) {}

  ngOnInit() {
    console.log('tour select');

    this.tours = [];
    this.selectedDate = this.calendarService.date;
    this.loadAll();
  }

  previousState() {
    window.history.back();
  }

  loadAll() {
    this.loading = true;

    this.tourService
      .queryExternal(this.selectedDate)
      .pipe(
        filter((res: HttpResponse<IActivity[]>) => res.ok),
        map((res: HttpResponse<IActivity[]>) => res.body)
      )
      .subscribe(
        (res: IActivity[]) => {
          this.loading = false;
          this.tours = res;
        },
        (res: HttpErrorResponse) => {
          this.loading = false;
          this.onError(res.message);
        }
      );
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  selectTour(tour: IActivity) {
    this.tourService.selectedTour = tour;
    this.previousState();
  }
}
