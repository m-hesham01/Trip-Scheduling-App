import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewTripsComponent } from './view-trips/view-trips.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule }   from '@angular/forms';
import { ViewStationsComponent } from './view-stations/view-stations.component';
import { UpdateTripComponent } from './update-trip/update-trip.component';
import { UpdateStationComponent } from './update-station/update-station.component';
import { AddStationComponent } from './add-station/add-station.component';

import { AddTripComponent } from './add-trip/add-trip.component';
const routes: Routes = [
  {path: 'viewTrips', component: ViewTripsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'viewStations', component: ViewStationsComponent},
  {path: 'updateTrips', component: UpdateTripComponent},
  {path: 'updateStation', component: UpdateStationComponent},
  {path: 'addStation', component: AddStationComponent},
  {path: 'addTrip', component: AddTripComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [ViewTripsComponent,LoginComponent,RegisterComponent]
