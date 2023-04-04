export interface IPresenceOverview {
  user?: string;
  qtyPresences?: number;
  presences?: Array<boolean>;
}

export class PresenceOverview implements IPresenceOverview {
  constructor(public user?: string, public qtyPresences?: number, public presences?: Array<boolean>) {}
}
