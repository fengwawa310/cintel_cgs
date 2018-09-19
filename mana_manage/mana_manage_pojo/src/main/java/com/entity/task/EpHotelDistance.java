package com.entity.task;

public class EpHotelDistance {
    private String hotelId;

    private String nearlyHotelId;

    private Integer distance;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId == null ? null : hotelId.trim();
    }

    public String getNearlyHotelId() {
        return nearlyHotelId;
    }

    public void setNearlyHotelId(String nearlyHotelId) {
        this.nearlyHotelId = nearlyHotelId == null ? null : nearlyHotelId.trim();
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}