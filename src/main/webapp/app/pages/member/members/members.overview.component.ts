import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/core';
import { IMember } from 'app/shared/model/member.model';

@Component({
  selector: 'jhi-calendar',
  templateUrl: './members.overview.component.html',
  styleUrls: ['./members.overview.component.scss']
})
export class MembersOverviewComponent implements OnInit {
  members: IMember[];

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.findActiveMembers().subscribe(res => (this.members = res.body));
  }
}
