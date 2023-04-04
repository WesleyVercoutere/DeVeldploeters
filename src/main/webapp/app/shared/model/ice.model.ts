export interface IICE {
  id?: number;
  lastName?: string;
  firstName?: string;
  phone?: string;
  memberId?: number;
}

export class ICE implements IICE {
  constructor(public id?: number, public lastName?: string, public firstName?: string, public phone?: string, public memberId?: number) {}
}
