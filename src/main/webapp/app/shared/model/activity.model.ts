import { Moment } from 'moment';

export interface IActivity {
  id?: number;
  type?: string;
  title?: string;
  date?: Moment;
  endDate?: Moment;
  time?: string;
  startTime?: string;
  distance?: string;

  location?: string;
  street?: string;
  number?: string;
  zip?: string;
  city?: string;

  name?: string;
  website?: string;
  email?: string;
}

export class Activity implements IActivity {
  constructor(
    public id?: number,
    public type?: string,
    public title?: string,
    public date?: Moment,
    public endDate?: Moment,
    public time?: string,
    public startTime?: string,
    public distance?: string,
    public location?: string,
    public street?: string,
    public number?: string,
    public zip?: string,
    public city?: string,
    public name?: string,
    public website?: string,
    public email?: string
  ) {}
}
