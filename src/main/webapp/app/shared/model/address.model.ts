export interface IAddress {
  id?: number;
  street?: string;
  number?: string;
  zipCode?: string;
  city?: string;
}

export class Address implements IAddress {
  constructor(public id?: number, public street?: string, public number?: string, public zipCode?: string, public city?: string) {}
}
