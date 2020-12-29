import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Score} from "../models/Score";

@Injectable({
  providedIn: 'root'
})
export class ScoreService {
private scoreList: Score;

  constructor(private httpClient: HttpClient) {
  }

  list() {

    let res = [{year: 2000}, {year: 2001}];
    this.scoreList = this.getScoreFromServer();
    return res;
  }

  getScoreFromServer() {
    this.httpClient
    .get<any[]>('https://localhost:8080/appareils.json')
    .subscribe(
      (response) => {
        this.scoreList = response;
      },
      (error) => {
        console.log('Erreur ! : ' + error);
      }
    );
  }

}
