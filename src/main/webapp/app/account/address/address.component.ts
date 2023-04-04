import { Component, OnInit } from '@angular/core';

import { AccountService } from 'app/core';
import { AddressService } from 'app/entities/address';
import { Address, IAddress } from 'app/shared/model/address.model';

@Component({
  selector: 'jhi-settings',
  templateUrl: './address.component.html'
})
export class AddressComponent implements OnInit {
  error: string;
  success: string;
  address: IAddress;
  currentUser: any;

  constructor(private addressService: AddressService, private accountService: AccountService) {}

  ngOnInit() {
    this.address = new Address();

    this.accountService.identity().then(account => {
      this.currentUser = account;
    });
    this.addressService.findCurrentUser().subscribe(res => (this.address = res.body));
  }

  save() {
    if (this.address.id == null) {
      this.addressService.create(this.address).subscribe(() => (this.success = 'OK'));
    } else {
      this.addressService.update(this.address).subscribe(() => (this.success = 'OK'));
    }
  }
}
