package com.wasalny.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Trip  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "start_station_id")
    private Station startStation;
    @ManyToOne
    @JoinColumn(name = "end_station_id")
    private Station endStation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public Trip(){}
    public Trip(Station startStation, Station endStation, Date startTime, Date endTime){
        this.startStation = startStation;
        this.endStation = endStation;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId(){return Id;}
    public void setId(int Id) {this.Id = Id;}
    public void setStartStation(Station s){
        startStation = s;
    }
    public void setEndStation(Station s){
        endStation = s;
    }
    public Station
    getStartStation(){
        return startStation;
    }
    public  Station getEndStation(){
        return endStation;
    }

    public void setStartTime(Date d){startTime = d;}
    public void setEndTime(Date d){endTime = d;}

    public Date getEndTime() {
        return endTime;
    }

    public Date getStartTime() {
        return startTime;
    }
}