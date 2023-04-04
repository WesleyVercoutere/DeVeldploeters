export interface IPresence {
  id?: number;
  present?: boolean;
  activityId?: number;
  userId?: number;
}

export class Presence implements IPresence {
  constructor(public id?: number, public present?: boolean, public activityId?: number, public userId?: number) {
    this.present = this.present || false;
  }
}
