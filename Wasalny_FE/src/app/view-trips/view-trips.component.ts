import { Component, OnInit } from '@angular/core';
import { Station } from '../Trip';
import { TripsService } from '../trips.service';

@Component({
  selector: 'app-view-trips',
  templateUrl: './view-trips.component.html',
  styleUrls: ['./view-trips.component.css']
})
export class ViewTripsComponent implements OnInit {
  public trips:{ id: any; startStation: Station; endStation: Station; startTime: String; endTime: String; }[]
  public i:number;
  myData:any
  constructor(private _tripService: TripsService) { }
  ngOnInit(): void {
    // this.trips = this._tripService.getTrips();
    this._tripService.getTrips().subscribe((_data) => { //for when we connect the API
      this.trips =_data;
    });
  }
  delete(id:any):void {
    this._tripService.deleteTrip(id);
  }
}
