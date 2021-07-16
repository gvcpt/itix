import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})

export class TestComponent implements OnInit {
  //Access to XMLHttpRequest at 'http://localhost:8080/greeting' from origin 'http://localhost:4200' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
  // private testUrl = 'http://localhost:8080/greeting';
  private testUrl = 'https://www.googleapis.com/books/v1/volumes?q=extreme%20programming';

  private id;
  private content;
  private items;

  constructor(private http: HttpClient) {
    this.http.get<TestComponent[]>(this.testUrl)
  }

  ngOnInit() {
    this.getTest();
  }

  getTest(): Observable<TestComponent[]> {
    // retrieve and organize infos from url
    this.http.get<any>(this.testUrl).subscribe(data => {
      this.id = data.kind;
      this.content = data.totalItems;
      this.items = data.items;
    })

    return this.http.get<TestComponent[]>(this.testUrl);
  }

}
