import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from "./app.component";
import { ScoreComponent } from './score.component';
import { EditionComponent } from "./editions.component";
import { ScoreService } from "./services/score.service";
import { EditionListComponent } from './edition-list/edition-list.component';
import { TeamComponent } from './team/team.component';

@NgModule({
  declarations: [AppComponent,
    EditionListComponent,
    EditionComponent,
    ScoreComponent,
    TeamComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    ScoreService],
  bootstrap: [AppComponent]
})
export class AppModule { }
