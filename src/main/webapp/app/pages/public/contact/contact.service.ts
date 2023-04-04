import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IContact } from 'app/shared/model/contact.model';

type EntityResponseType = HttpResponse<IContact>;

@Injectable({ providedIn: 'root' })
export class ContactService {
  public resourceUrl = SERVER_API_URL + 'api/public/contact';

  constructor(protected http: HttpClient) {}

  create(contact: IContact): Observable<EntityResponseType> {
    return this.http.post<IContact>(this.resourceUrl, contact, { observe: 'response' });
  }
}
