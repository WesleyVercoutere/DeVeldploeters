export interface IReport {
  id?: number;
  report?: string;
}

export class Report implements IReport {
  constructor(public id?: number, public report?: string) {}
}
