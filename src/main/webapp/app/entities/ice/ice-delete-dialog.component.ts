import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IICE } from 'app/shared/model/ice.model';
import { ICEService } from './ice.service';

@Component({
  selector: 'jhi-ice-delete-dialog',
  templateUrl: './ice-delete-dialog.component.html'
})
export class ICEDeleteDialogComponent {
  iCE: IICE;

  constructor(protected iCEService: ICEService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.iCEService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'iCEListModification',
        content: 'Deleted an iCE'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-ice-delete-popup',
  template: ''
})
export class ICEDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ iCE }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ICEDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.iCE = iCE;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/ice', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/ice', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
