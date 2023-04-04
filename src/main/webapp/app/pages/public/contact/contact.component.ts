import { Component, OnInit } from '@angular/core';
import { Contact, IContact } from 'app/shared/model/contact.model';
import { ContactService } from 'app/pages/public/contact/contact.service';

@Component({
  selector: 'jhi-calendar',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  contact: IContact;
  isSending: boolean;

  constructor(private contactService: ContactService) {}

  ngOnInit() {
    this.contact = new Contact();
    this.isSending = false;
  }

  send() {
    this.contactService.create(this.contact).subscribe(() => this.success(), () => this.success());
  }

  previousState(): void {
    this.contact = new Contact();
  }

  success(): void {
    console.log('verzonden');
    this.previousState();
    alert('Uw bericth is vezonden');
  }
}
