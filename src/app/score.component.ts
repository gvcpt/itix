import {Component} from '@angular/core';

@Component({
  selector: 'score-root',
  template: `
    <ul>
      <li *ngFor="let score of scores; even as isEven"
              [style.color]="isEven ? 'green' : 'blue'"> {{ score.scorehome }} - {{ score.scoreaway }}
      </li>
    </ul>
    <button (click)="initScore()">Init score</button>
  `,
  styleUrls: ['./app.component.scss']
})

export class ScoreComponent {
  title = 'itix-app';
  scores: Array<{scorehome: number, scoreaway: number}> =[
    {scorehome: 0, scoreaway: 0}, {scorehome: 0, scoreaway: 0},{scorehome: 0, scoreaway: 0},{scorehome: 0, scoreaway: 0},{scorehome: 0, scoreaway: 0}];

  initScore() {

  }

}
