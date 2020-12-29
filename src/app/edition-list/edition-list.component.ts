import { Component, OnInit } from '@angular/core';
import { ScoreService } from "../services/score.service";

@Component({
  selector: 'app-edition-list',
  templateUrl: './edition-list.component.html',
  styleUrls: ['./edition-list.component.css']
})
export class EditionListComponent implements OnInit {
  // private editions: Array<{ year: number }> = [
  //   {year: 2000}, {year: 2001}, {year: 2002}, {year: 2003}, {year: 2004}, {year: 2005}, {year: 2006}, {year: 2007}, {year: 2008}, {year: 2009}, {year: 2010}];

  private editions: Array<{ year: number }>;

  constructor(private scoreService: ScoreService) {
  }

  ngOnInit() {
    this.getScoreList();
  }

  getScoreList() {
    // TODO set up REST call
    this.editions = this.scoreService.list();
  }


}
