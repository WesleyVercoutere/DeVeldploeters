export interface IMember {
  id?: number;
  lastName?: string;
  firstName?: string;
  email?: string;
  phone?: string;
}

export class Member implements IMember {
  constructor(public id?: number, public lastName?: string, public firstName?: string, public email?: string, public phone?: string) {}
}
