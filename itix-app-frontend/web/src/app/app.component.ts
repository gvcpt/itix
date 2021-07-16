import { Component, Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

@Injectable()
export class AppComponent implements OnInit {
  private testWsUrl: "hello-servlet";

  constructor(private http: HttpClient) {
  }

  title = 'itixAppFrontend';
  matchList: any[];

  ngOnInit() {
    this.http.get<string>(this.testWsUrl)
  }
}
