export interface ICalendar {
  id?: number;
  type?: string;
  title?: string;
  start?: string;
  end?: string;
}

export class Calendar implements ICalendar {
  constructor(public id?: number, public type?: string, public title?: string, public start?: string, public end?: string) {}
}
