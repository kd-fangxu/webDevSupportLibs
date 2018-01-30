package com.xqSupport.supportmodule.geoPosition.dao;

import com.xqSupport.common.Dao.BaseDao;
import com.xqSupport.supportmodule.geoPosition.entity.GeoPositionEntity;
import com.xqSupport.supportmodule.geoPosition.geoUtils.GeoHash;

public class GeoPositionDao extends BaseDao<GeoPositionEntity> implements IGeoPositionDao {

    @Override
    public GeoPositionEntity saveEntity(GeoPositionEntity o) {
        if (o.getLat() != null && o.getLon() != null) {
            o.setGeoHash(new GeoHash(o.getLat(), o.getLon()).getGeoHashBase32());
        }
        return super.saveEntity(o);
    }

    @Override
    public Object save(Object entity) {
        return saveEntity((GeoPositionEntity) entity);
    }


}
