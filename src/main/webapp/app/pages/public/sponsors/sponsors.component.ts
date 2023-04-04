import { Component, OnInit } from '@angular/core';
import { ISponsor } from 'app/shared/model/sponsor.model';
import { SponsorService } from 'app/entities/sponsor';
import { HttpResponse } from '@angular/common/http';
import { IBannerImage } from 'app/shared/model/banner-image.model';

@Component({
  selector: 'jhi-calendar',
  templateUrl: './sponsors.component.html',
  styleUrls: ['./sponsors.component.scss']
})
export class SponsorsComponent implements OnInit {
  sponsors: ISponsor[];

  constructor(private sponsorService: SponsorService) {}

  ngOnInit() {
    this.getSponsors();
  }

  getSponsors() {
    this.sponsorService.queryPublic().subscribe((res: HttpResponse<IBannerImage[]>) => {
      this.sponsors = res.body;
    });
  }
}
