import { Component, OnInit } from '@angular/core';
import { TripsService } from '../trips.service';
import { Station } from '../Station';


const s : Station = {
  id: undefined,
  name: ''
};
@Component({
  selector: 'app-view-stations',
  templateUrl: './view-stations.component.html',
  styleUrls: ['./view-stations.component.css']
})
export class ViewStationsComponent implements OnInit {
  public stations:{ id: any; name: string; }[]
  constructor(private _tripService: TripsService) { }

  ngOnInit(): void {
    this._tripService.getStations().subscribe((_data) => { //for when we connect the API
      this.stations =_data;
    });
  }
  delete(id:any):void {
    this._tripService.deleteStation(id);
  }

}
