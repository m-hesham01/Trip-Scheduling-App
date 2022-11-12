import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule,routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { TripsService } from './trips.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ViewStationsComponent } from './view-stations/view-stations.component';
import { CreateStationComponent } from './create-station/create-station.component';
import { UpdateTripComponent } from './update-trip/update-trip.component';
import { UpdateStationComponent } from './update-station/update-station.component';
import { AddStationComponent } from './add-station/add-station.component';
import { AddTripComponent } from './add-trip/add-trip.component';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    LoginComponent,
    RegisterComponent,
    ViewStationsComponent,
    CreateStationComponent,
    UpdateTripComponent,
    UpdateStationComponent,
    AddStationComponent,
    AddTripComponent,
    // HttpClientModule
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [TripsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
