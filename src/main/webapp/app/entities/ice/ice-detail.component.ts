import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IICE } from 'app/shared/model/ice.model';

@Component({
  selector: 'jhi-ice-detail',
  templateUrl: './ice-detail.component.html'
})
export class ICEDetailComponent implements OnInit {
  iCE: IICE;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ iCE }) => {
      this.iCE = iCE;
    });
  }

  previousState() {
    window.history.back();
  }
}
