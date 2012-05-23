/*
 * Copyright (C) 2009 - 2012 SMVP4G.COM
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.smvp4g.mvp.client.core.mapper;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.smvp4g.factory.client.utils.ClassUtils;
import com.smvp4g.mvp.client.core.factory.ClientFactory;
import com.smvp4g.mvp.client.core.factory.FactoryModel;
import com.smvp4g.mvp.client.core.place.AbstractPlace;
import com.smvp4g.mvp.client.core.utils.StringUtils;

import java.util.List;

/**
 * The Class PlaceHistoryMapperImpl.
 *
 * @author Nguyen Duc Dung
 * @since 12/17/11, 12:58 PM
 */
public class PlaceHistoryMapperImpl implements PlaceHistoryMapper {

    private ClientFactory factory;

    public PlaceHistoryMapperImpl(ClientFactory factory) {
        this.factory = factory;
    }

    @Override
    public Place getPlace(String token) {
        for (FactoryModel model : factory.getFactoryModels()) {
            if (StringUtils.isNotBlank(model.getToken()) && model.getToken().equals(token)) {
                AbstractPlace place = factory.getExitsPlace(model.getPlaceClass());
                if (place == null) {
                    place = factory.createPlace(model);
                }
                return place;
            }
        }
        return null;
    }

    @Override
    public String getToken(Place place) {
        for (FactoryModel model : factory.getFactoryModels()) {
            if (ClassUtils.getRealClass(place) == model.getPlaceClass()) {
                return model.getToken();
            }
        }
        return null;
    }

}
