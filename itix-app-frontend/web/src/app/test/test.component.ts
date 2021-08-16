import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})

export class TestComponent implements OnInit {
  // Access to XMLHttpRequest at 'http://localhost:8080/greeting' from origin 'http://localhost:4200' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
  private testUrl = 'https://www.googleapis.com/books/v1/volumes?q=extreme%20programming';
  private realItixSparkRestUrl = 'http://localhost:4567/getMatches';

  private id;
  private content;
  private homeTeam;
  private awayTeam;
  private homeScore;
  private awayScore;
  private homexG;
  private awayxG;

  constructor(private http: HttpClient) {
    this.http.get<TestComponent[]>(this.realItixSparkRestUrl)
  }

  ngOnInit() {
    this.getTest();
  }

  getTest(): Observable<TestComponent[]> {
    // retrieve and organize infos from url
    this.http.get<any>(this.realItixSparkRestUrl).subscribe(data => {
      this.id = data[0].id;
      this.content = data[0].season;
      this.homeTeam = data[0].homeTeam;
      this.awayTeam = data[0].awayTeam;
      this.homeScore = data[0].HScore;
      this.awayScore = data[0].AScore;
      this.homexG = data[0].HxG;
      this.awayxG = data[0].AxG;
    })

    return this.http.get<TestComponent[]>(this.realItixSparkRestUrl);
  }

}
