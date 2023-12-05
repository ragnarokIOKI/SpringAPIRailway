package com.example.AuthRegSpring5.DTO;

public class RouteCompositionDTO {
    private int id;
    private int trainId;
    private int routeId;

    public RouteCompositionDTO() {
    }

    public RouteCompositionDTO(int id, int trainId, int routeId) {
        this.id = id;
        this.trainId = trainId;
        this.routeId = routeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
}
