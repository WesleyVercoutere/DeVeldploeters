import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { createRequestOption } from 'app/shared';
import { ICalendar } from 'app/shared/model/calendar.model';

type EntityArrayResponseType = HttpResponse<ICalendar[]>;

@Injectable({ providedIn: 'root' })
export class CalendarService {
  public resourceUrl = SERVER_API_URL + 'api/calendar';

  date: string;

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICalendar[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
