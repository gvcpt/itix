import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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


  matchList = [{
    id: String,
    homeTeam: String,
    awayTeam: String,
    HScore: String,
    AScore: String,
    HxG: String,
    AxG: String,
  }];

  constructor(private http: HttpClient) {
    this.http.get<TestComponent[]>(this.realItixSparkRestUrl)
  }

  ngOnInit() {
    this.getTest();
  }

  getTest(): Observable<TestComponent[]> {
    // retrieve and organize infos from url
    this.http.get<any>(this.realItixSparkRestUrl).subscribe(data => {
      this.matchList = [];
      for (let m of data) {
        this.matchList.push(m);
        // console.log('matchList ' + m.id, m.homeTeam, m.awayTeam, m.HScore, m.AScore, m.HxG, m.AxG);
      }

      // console.log('matchList ' + this.matchList.length);
    })
    return this.http.get<TestComponent[]>(this.realItixSparkRestUrl);
  }

}
