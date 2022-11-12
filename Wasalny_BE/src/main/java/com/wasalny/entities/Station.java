package com.wasalny.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class  Station implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String name;
    public Station(){}
    public Station(String name){
        this.name = name;
    }
    public void setName(String stationName){
        this.name = stationName;
    }
    public String getName(){
        return name;
    }
    public void setId(int Id) {this.Id = Id;}
    public int getId() {return Id;}
}
