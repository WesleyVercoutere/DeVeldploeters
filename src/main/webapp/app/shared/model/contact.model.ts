export interface IContact {
  lastName?: string;
  firstName?: string;
  email?: string;
  text?: string;
  street?: string;
  number?: number;
  zip?: number;
  city?: string;
}

export class Contact implements IContact {
  constructor(
    public lastName?: string,
    public firstName?: string,
    public email?: string,
    public text?: string,
    public street?: string,
    public number?: number,
    public zip?: number,
    public city?: string
  ) {}
}
