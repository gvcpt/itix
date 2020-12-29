import { Component } from '@angular/core';
import { ScoreService } from "./services/score.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'itix-app';

  constructor() {
  }
  // list() {
  //   let list = this.scoreService.list();
  //   return list;
  // }

}
