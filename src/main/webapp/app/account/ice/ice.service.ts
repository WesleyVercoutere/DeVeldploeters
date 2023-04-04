import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IICE } from 'app/shared/model/ice.model';

type EntityResponseType = HttpResponse<IICE>;
type EntityArrayResponseType = HttpResponse<IICE[]>;

@Injectable({ providedIn: 'root' })
export class ICEService {
  public resourceUrl = SERVER_API_URL + 'api/ices';
  public memberResourceUrl = SERVER_API_URL + 'api/userIces';

  constructor(protected http: HttpClient) {}

  create(iCE: IICE): Observable<EntityResponseType> {
    return this.http.post<IICE>(this.resourceUrl, iCE, { observe: 'response' });
  }

  update(iCE: IICE): Observable<EntityResponseType> {
    return this.http.put<IICE>(this.resourceUrl, iCE, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IICE>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IICE[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryUserIces(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IICE[]>(this.memberResourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
