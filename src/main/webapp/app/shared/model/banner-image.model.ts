export interface IBannerImage {
  id?: number;
  active?: boolean;
  imageContentType?: string;
  image?: any;
}

export class BannerImage implements IBannerImage {
  constructor(public id?: number, public active?: boolean, public imageContentType?: string, public image?: any) {
    this.active = this.active || false;
  }
}
