import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IActivity } from 'app/shared/model/activity.model';

type EntityResponseType = HttpResponse<IActivity>;
type EntityArrayResponseType = HttpResponse<IActivity[]>;

@Injectable({ providedIn: 'root' })
export class TourService {
  public externalResourceUrl = SERVER_API_URL + 'api/externalTours';

  public selectedTour: IActivity;

  constructor(protected http: HttpClient) {}

  queryExternal(date: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<IActivity[]>(`${this.externalResourceUrl}/${date}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tour: IActivity) => {
        tour.date = tour.date != null ? moment(tour.date) : null;
      });
    }
    return res;
  }

  deleteSelectedTour() {
    this.selectedTour = null;
  }
}
