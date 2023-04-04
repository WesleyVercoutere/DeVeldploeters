import { Component, HostListener, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, AccountService, LoginModalService } from 'app/core';
import { BannerImageService } from 'app/entities/banner-image';
import { HttpResponse } from '@angular/common/http';
import { IBannerImage } from 'app/shared/model/banner-image.model';
import { IReportOverview } from 'app/shared/model/report-overview.model';
import { ReportService } from 'app/entities/report';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit {
  account: Account;
  modalRef: NgbModalRef;
  images: Array<IBannerImage>;
  imgHeight: string;
  reports: Array<IReportOverview>;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private bannerImageService: BannerImageService,
    private reportService: ReportService
  ) {}

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.account = account;
    });
    this.registerAuthenticationSuccess();
    this.getImages();
    this.setImageHeight();
    this.loadActivities();
  }

  registerAuthenticationSuccess() {
    this.eventManager.subscribe('authenticationSuccess', message => {
      this.accountService.identity().then(account => {
        this.account = account;
      });
    });
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }

  login() {
    this.modalRef = this.loginModalService.open();
  }

  getImages() {
    this.bannerImageService.queryPublic().subscribe((res: HttpResponse<IBannerImage[]>) => {
      this.images = res.body;
    });
  }

  @HostListener('window:resize', ['$event'])
  setImageHeight() {
    const factor = 1140 / 640;
    const element = document.getElementById('containerWidth');
    const size = element.offsetWidth;
    this.imgHeight = (size / factor).toString();
  }

  loadActivities() {
    this.reportService.queryLast().subscribe(res => (this.reports = res.body));
  }
}
