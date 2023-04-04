import { Component, OnInit } from '@angular/core';
import { IUser } from 'app/core';
import { ActivatedRoute } from '@angular/router';
import { IICE } from 'app/shared/model/ice.model';
import { ICEService } from 'app/entities/ice';

@Component({
  selector: 'jhi-calendar',
  templateUrl: './members.ices.component.html'
})
export class MembersIcesComponent implements OnInit {
  user: IUser;
  ices: IICE[];

  constructor(private activatedRoute: ActivatedRoute, private icesService: ICEService) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ user }) => {
      this.user = user;
      this.loadIces();
    });
  }

  previousState() {
    window.history.back();
  }

  private loadIces(): void {
    this.icesService.findByUserId(this.user.id).subscribe(res => (this.ices = res.body));
  }
}
