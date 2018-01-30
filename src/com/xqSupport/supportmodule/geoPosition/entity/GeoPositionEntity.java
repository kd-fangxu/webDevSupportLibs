package com.xqSupport.supportmodule.geoPosition.entity;

import com.xqSupport.Entity.BaseEntity;


public class GeoPositionEntity extends BaseEntity {

    String GeoHash;//位置hash
    Double lon;//经度
    Double lat;//纬度

    public String getGeoHash() {
        return GeoHash;
    }

    public void setGeoHash(String geoHash) {
        GeoHash = geoHash;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
