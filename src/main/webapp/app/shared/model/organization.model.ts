export interface IOrganization {
  id?: number;
  name?: string;
  website?: string;
  email?: string;
}

export class Organization implements IOrganization {
  constructor(public id?: number, public name?: string, public website?: string, public email?: string) {}
}
