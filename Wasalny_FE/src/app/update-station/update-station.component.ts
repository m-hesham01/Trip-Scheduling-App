import { Component, OnInit } from '@angular/core';
import { TripsService } from '../trips.service';
import { Router} from '@angular/router';
@Component({
  selector: 'app-update-station',
  templateUrl: './update-station.component.html',
  styleUrls: ['./update-station.component.css']
})
export class UpdateStationComponent implements OnInit {
  public id="";
  public name = "";
  constructor(private _tripService: TripsService, private router: Router) { }

  ngOnInit(): void {
  }

  updateTrip(){
    const data = {
      Id:this.id,
      name:this.name
    };
    this._tripService.updateStation(this.id,data).subscribe({
        next: _data=> {
          this.router.navigate(['/viewStations'])
        }
      })

  }

}
