package com.jm.mymaps;

public class UserHelperClass { String mapName, state, city, mapType, observation;

    public UserHelperClass() {

    }

    public UserHelperClass (String mapName, String state, String city, String observation, String mapType) {
        this.mapName = mapName;
        this.state = state;
        this.city = city;
        this.observation = observation;
        this.mapType = mapType;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

}


