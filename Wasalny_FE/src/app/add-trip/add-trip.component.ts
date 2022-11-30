import { Component, OnInit } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { Station } from '../Station';
import { TripsService } from '../trips.service';
import { ViewTripsComponent } from '../view-trips/view-trips.component';
const routes: Routes = [
  { path: '', redirectTo: '/AppComponent', pathMatch: 'full' },
  { path: 'viewTrips', component: ViewTripsComponent},
];
@Component({
  selector: 'app-add-trip',
  templateUrl: './add-trip.component.html',
  styleUrls: ['./add-trip.component.css']
})
export class AddTripComponent implements OnInit {
  public startStation :Station;
  public endStation :Station;
  public startTime :Date;
  public endTime :Date;
  public stations:{ id: any; name: string; }[]
  constructor(private _tripService: TripsService,private router: Router) { }

  ngOnInit(): void {
    this._tripService.getStations().subscribe((_data) => { //for when we connect the API
      this.stations =_data;
    });
  }
  addTrip() {
    const data = {
      startStation: this.startStation,
      endStation: this.endStation,
      startTime: this.startTime,
      endTime: this.endTime,
      stations:this.stations
    };
    this._tripService.addTrip(data)
    .subscribe((_data) => { //for when we connect the API
      console.log("***************************");
      console.log(this.stations)
      this.router.navigate(['/viewTrips'])
    });
  }
}
