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

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.smvp4g.factory.client.utils.ClassUtils;
import com.smvp4g.mvp.client.core.factory.ClientFactory;
import com.smvp4g.mvp.client.core.factory.FactoryModel;
import com.smvp4g.mvp.client.core.presenter.AbstractPresenter;
import com.smvp4g.mvp.client.core.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ActivityMapperImpl.
 *
 * @author Nguyen Duc Dung
 * @since 12/17/11, 12:46 PM
 */
public class ActivityMapperImpl implements ActivityMapper {

    private List<FactoryModel> factoryModels = new ArrayList<FactoryModel>();
    private ClientFactory factory;

    public ActivityMapperImpl(List<FactoryModel> factoryModels, ClientFactory factory) {
        this.factoryModels = factoryModels;
        this.factory = factory;
    }

    @Override
    public Activity getActivity(Place place) {
        for (FactoryModel model : factoryModels) {
            if (ClassUtils.getRealClass(place) == model.getPlaceClass()) {
                Presenter presenter = factory.getExitsPresenter(model.getPresenterClass());
                if (presenter == null) {
                    presenter = factory.createPresenter(model);
                }
                return (AbstractPresenter)presenter;
            }
        }
        return null;
    }

}
