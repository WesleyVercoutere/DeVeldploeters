import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReport } from 'app/shared/model/report.model';
import { map } from 'rxjs/operators';
import { IReportOverview } from 'app/shared/model/report-overview.model';
import * as moment from 'moment';

type EntityResponseType = HttpResponse<IReport>;
type EntityArrayResponseType = HttpResponse<IReport[]>;

@Injectable({ providedIn: 'root' })
export class ReportService {
  public resourceUrl = SERVER_API_URL + 'api/reports';
  public activityResourceUrl = SERVER_API_URL + 'api/activityReports';

  constructor(protected http: HttpClient) {}

  create(report: IReport): Observable<EntityResponseType> {
    return this.http.post<IReport>(this.resourceUrl, report, { observe: 'response' });
  }

  update(report: IReport): Observable<EntityResponseType> {
    return this.http.put<IReport>(this.resourceUrl, report, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReport>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByActivity(id: number): Observable<EntityResponseType> {
    return this.http.get<IReport>(`${this.activityResourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReport[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryLast(): Observable<HttpResponse<IReportOverview[]>> {
    return this.http
      .get<IReportOverview[]>('api/public/reportsByLast', { observe: 'response' })
      .pipe(map((res: HttpResponse<IReportOverview[]>) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: HttpResponse<IReportOverview[]>): HttpResponse<IReportOverview[]> {
    if (res.body) {
      res.body.forEach((report: IReportOverview) => {
        report.date = report.date != null ? moment(report.date) : null;
      });
    }
    return res;
  }
}
