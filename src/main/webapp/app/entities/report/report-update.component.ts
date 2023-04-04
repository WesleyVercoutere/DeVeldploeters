import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IReport } from 'app/shared/model/report.model';
import { ReportService } from './report.service';
import { ActivityService } from 'app/entities/activity';
import { IActivity } from 'app/shared/model/activity.model';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'jhi-report-update',
  templateUrl: './report-update.component.html'
})
export class ReportUpdateComponent implements OnInit {
  report: IReport;
  activity: IActivity;
  isSaving: boolean;
  Editor = ClassicEditor;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected reportService: ReportService,
    protected activityService: ActivityService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;

    let id;

    this.activatedRoute.params.subscribe(params => {
      id = +params['id'];
    });

    this.activityService.find(id).subscribe(res => {
      this.activity = res.body;
      this.loadReport();
    });
  }

  loadReport() {
    this.reportService.findByActivity(this.activity.id).subscribe(res => (this.report = res.body));
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.reportService.update(this.report).subscribe(res => this.previousState());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
