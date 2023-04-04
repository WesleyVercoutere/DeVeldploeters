import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { PresenceService } from './presence.service';
import { IActivity } from 'app/shared/model/activity.model';
import { IPresence, Presence } from 'app/shared/model/presence.model';
import { IUser, UserService } from 'app/core';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-presence-add-user-dialog',
  templateUrl: './presence-add-user-dialog.component.html'
})
export class PresenceAddUserDialogComponent implements OnInit {
  activity: IActivity;
  presence: IPresence;
  selectedUser: IUser;
  users: IUser[];

  constructor(
    protected presenceService: PresenceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.userService
      .findActive()
      .pipe(
        filter((res: HttpResponse<IUser[]>) => res.ok),
        map((res: HttpResponse<IUser[]>) => res.body)
      )
      .subscribe((res: IPresence[]) => {
        this.users = res;
      });
  }

  clear() {
    this.activeModal.dismiss('cancel');
  }

  addUser() {
    this.presence = new Presence();
    this.presence.activityId = this.activity.id;
    this.presence.userId = this.selectedUser.id;

    this.presenceService.create(this.presence).subscribe(response => {
      this.eventManager.broadcast({
        name: 'presenceListModification',
        content: 'Added an presence'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-presence-add-user-popup',
  template: ''
})
export class PresenceAddUserPopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ presence }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PresenceAddUserDialogComponent as Component, {
          size: 'sm',
          backdrop: 'static'
        });

        this.ngbModalRef.componentInstance.activity = presence;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/presence', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/presence', { outlets: { popup: null } }]);
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
