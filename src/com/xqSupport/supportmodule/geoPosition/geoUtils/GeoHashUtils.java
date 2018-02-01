package com.xqSupport.supportmodule.geoPosition.geoUtils;

import java.util.List;

public class GeoHashUtils {
    /**
     * 根据一个中心坐标点和半径大小 返回相邻9个模块hash值
     *
     * @param currentLat
     * @param currentLng
     * @param radius     单位米
     * @return
     */
    public static List<String> getSearchHeaderHash(Double currentLat, Double currentLng, Integer radius) {
        GeoHash geoHash = new GeoHash(currentLat, currentLat);
        int hashSize = 8;
        if (radius <= 0.019 * 1000) {
            hashSize = 8;
        } else if (radius <= 0.076 * 1000) {
            hashSize = 7;
        } else if (radius <= 0.61 * 1000) {
            hashSize = 6;
        } else if (radius < 2.4 * 1000) {
            hashSize = 5;
        } else if (radius < 19.5 * 1000) {
            hashSize = 4;
        } else if (radius < 78 * 1000) {
            hashSize = 3;
        } else if (radius < 630 * 1000) {
            hashSize = 2;
        } else if (radius < 2500 * 1000) {
            hashSize = 1;
        }
        return geoHash.getGeoHashBase32For9();
    }

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

}
