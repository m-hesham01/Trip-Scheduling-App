import { style } from '@angular/animations';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Station, Trip } from './Trip';

@Injectable({
  providedIn: 'root'
})
export class TripsService {


  constructor( private http: HttpClient) { }
  private tripsUrl = 'https://be3-trip-scheduling-app.apps.eu410.prod.nextcle.com/api/trips';  // URL to web api
  getTrips():Observable<Trip[]> {
      return this.http.get<Trip[]>(this.tripsUrl)
  }
  deleteTrip(id:any){
    let stringID = id.toString();
    let url = 'https://be3-trip-scheduling-app.apps.eu410.prod.nextcle.com/api/trips/'+stringID;
    console.log(url);
    this.http.delete(url).subscribe();
    window.location.reload();
  }
  getStations():Observable<Station[]> {
    let url = 'https://be3-trip-scheduling-app.apps.eu410.prod.nextcle.com/api/stations'
    return this.http.get<Station[]>(url )
  }
  deleteStation(id:any){
    let stringID = id.toString();
    let url = 'https://be3-trip-scheduling-app.apps.eu410.prod.nextcle.com/api/stations/'+stringID;
    console.log(url);
    this.http.delete(url).subscribe();
    window.location.reload();
  }
  updateTrip(data:any,_id:any){
    let stringID = _id.toString();
    let url = 'https://be3-trip-scheduling-app.apps.eu410.prod.nextcle.com/api/trips/'+stringID;
    console.log(_id);
    console.log(url);
    console.log(data);
    return this.http.put(url,data);
  }
  updateStation(id:any,data:any){
    let url = 'https://be3-trip-scheduling-app.apps.eu410.prod.nextcle.com/api/stations/'+id;
    return this.http.put(url,data);
  }
  addStation(data:any){
    let url = 'https://be3-trip-scheduling-app.apps.eu410.prod.nextcle.com/api/stations';
    return this.http.post(url,data);
  }
  addTrip(data:any){
    let url = 'https://be3-trip-scheduling-app.apps.eu410.prod.nextcle.com/api/trips/';
    console.log(data);
    return this.http.post(url,data);
  }
}

