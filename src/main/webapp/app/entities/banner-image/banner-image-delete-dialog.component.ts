import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBannerImage } from 'app/shared/model/banner-image.model';
import { BannerImageService } from './banner-image.service';

@Component({
  selector: 'jhi-banner-image-delete-dialog',
  templateUrl: './banner-image-delete-dialog.component.html'
})
export class BannerImageDeleteDialogComponent {
  bannerImage: IBannerImage;

  constructor(
    protected bannerImageService: BannerImageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.bannerImageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'bannerImageListModification',
        content: 'Deleted an bannerImage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-banner-image-delete-popup',
  template: ''
})
export class BannerImageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bannerImage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BannerImageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.bannerImage = bannerImage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/banner-image', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/banner-image', { outlets: { popup: null } }]);
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
