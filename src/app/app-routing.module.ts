import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ScoreComponent} from "./score.component";

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  // declarations: [AppComponent, ScoreComponent],
  // bootstrap: [AppComponent]
})
export class AppRoutingModule { }
