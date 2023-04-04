import { Moment } from 'moment';

export interface IReportOverview {
  date?: Moment;
  title?: string;
  report?: string;
}

export class ReportOverview implements IReportOverview {
  constructor(public date?: Moment, public title?: string, public report?: string) {}
}
