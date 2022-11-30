import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TripsService } from '../trips.service';

@Component({
  selector: 'app-add-station',
  templateUrl: './add-station.component.html',
  styleUrls: ['./add-station.component.css']
})
export class AddStationComponent implements OnInit {
  public name="";
  constructor(private _tripService: TripsService, private router: Router) { }

  ngOnInit(): void {
  }

  addTrip(){
    const data = {
      name:this.name
    };
    this._tripService.addStation(data).subscribe({
      next:_data=>{
        this.router.navigate(['/viewStations'])
      }
    })

  }
}
