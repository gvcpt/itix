import {Component} from '@angular/core';

@Component({
  selector: 'edition-root',
  template: `<h1>6 Nations editions</h1>
  <div>
    <ul>
      <li *ngFor="let edition of editions">{{ edition.year }}
        <score-root></score-root>
      </li>
    </ul>
  </div>`,
  styleUrls: ['./app.component.scss']
})

export class EditionComponent {
  editions: Array<{year: number}> =[
    {year: 2000}, {year: 2001}, {year: 2002}, {year: 2003}, {year: 2004}, {year: 2005}, {year: 2006}, {year: 2007}, {year: 2008}, {year: 2009}, {year: 2010}];
}
