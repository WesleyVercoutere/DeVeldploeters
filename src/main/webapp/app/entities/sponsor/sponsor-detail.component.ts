import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISponsor } from 'app/shared/model/sponsor.model';

@Component({
  selector: 'jhi-sponsor-detail',
  templateUrl: './sponsor-detail.component.html'
})
export class SponsorDetailComponent implements OnInit {
  sponsor: ISponsor;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sponsor }) => {
      this.sponsor = sponsor;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  previousState() {
    window.history.back();
  }
}
