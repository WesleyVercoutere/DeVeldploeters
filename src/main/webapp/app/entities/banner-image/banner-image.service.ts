import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBannerImage } from 'app/shared/model/banner-image.model';

type EntityResponseType = HttpResponse<IBannerImage>;
type EntityArrayResponseType = HttpResponse<IBannerImage[]>;

@Injectable({ providedIn: 'root' })
export class BannerImageService {
  public resourceUrl = SERVER_API_URL + 'api/banner-images';
  public publicResourceUrl = SERVER_API_URL + 'api/public/banner-images';

  constructor(protected http: HttpClient) {}

  create(bannerImage: IBannerImage): Observable<EntityResponseType> {
    return this.http.post<IBannerImage>(this.resourceUrl, bannerImage, { observe: 'response' });
  }

  update(bannerImage: IBannerImage): Observable<EntityResponseType> {
    return this.http.put<IBannerImage>(this.resourceUrl, bannerImage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBannerImage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBannerImage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryPublic(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBannerImage[]>(this.publicResourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
