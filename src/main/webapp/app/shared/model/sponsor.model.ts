export interface ISponsor {
  id?: number;
  name?: string;
  website?: string;
  websiteImageContentType?: string;
  websiteImage?: any;
  logoContentType?: string;
  logo?: any;
}

export class Sponsor implements ISponsor {
  constructor(
    public id?: number,
    public name?: string,
    public website?: string,
    public websiteImageContentType?: string,
    public websiteImage?: any,
    public logoContentType?: string,
    public logo?: any
  ) {}
}
