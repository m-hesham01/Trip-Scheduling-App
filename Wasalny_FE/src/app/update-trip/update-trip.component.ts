import { Component, OnInit } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { Observable } from 'rxjs';
import { Station } from '../Station';
import { TripsService } from '../trips.service';
import { ViewTripsComponent } from '../view-trips/view-trips.component';
const routes: Routes = [
  { path: '', redirectTo: '/AppComponent', pathMatch: 'full' },
  { path: 'viewTrips', component: ViewTripsComponent},
];
@Component({
  selector: 'app-update-trip',
  templateUrl: './update-trip.component.html',
  styleUrls: ['./update-trip.component.css']
})
export class UpdateTripComponent implements OnInit {
  public id:any;
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
  updateTrip(){ 
    const data = {
      startStation: this.startStation,
      endStation: this.endStation,
      startTime: this.startTime,
      endTime: this.endTime,
      id: this.id,
      stations:this.stations
    };
    this._tripService.updateTrip(data,this.id)
    .subscribe((_data) => { //for when we connect the API
      console.log("***************************");
      console.log(this.stations)
      this.router.navigate(['/viewTrips'])
    });
  }

}
