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

package com.smvp4g.mvp.client.core.module;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.SimplePanel;
import com.smvp4g.factory.client.utils.ClassUtils;
import com.smvp4g.mvp.client.core.factory.ClientFactory;
import com.smvp4g.mvp.client.core.factory.FactoryModel;
import com.smvp4g.mvp.client.core.mapper.ActivityMapperImpl;
import com.smvp4g.mvp.client.core.mapper.PlaceHistoryMapperImpl;
import com.smvp4g.mvp.client.core.presenter.AbstractComponentPresenter;
import com.smvp4g.mvp.client.core.presenter.Presenter;
import com.smvp4g.mvp.client.core.presenter.annotation.ComponentPresenter;
import com.smvp4g.mvp.client.core.security.ViewSecurity;
import com.smvp4g.mvp.client.core.security.ViewSecurityConfigurator;
import com.smvp4g.mvp.client.core.utils.LoginUtils;
import com.smvp4g.reflection.client.marker.Reflection;

/**
 * The Class ApplicationModule.
 *
 * @author Nguyen Duc Dung
 * @since 10/9/11, 2:28 PM
 */
@Reflection
public class ApplicationModule implements Module {

    private EventBus eventBus = new SimpleEventBus();
    private PlaceController placeController = new PlaceController(eventBus);
    private ActivityManager activityManager = new ActivityManager(createActivityMapper(), eventBus);
    private PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(createHistoryMapper());

    @Override
    public void configure() {
        ClientFactory.INSTANCE.configure(eventBus, placeController);
        createDefaultPresenter();
        createAndHandleHistory();
    }

    @Override
    public void start() {
        configure();
    }

    public void createAndHandleHistory() {
        activityManager.setDisplay(new SimplePanel());
        placeHistoryHandler.register(placeController, eventBus, getDefaultPlace());
        placeHistoryHandler.handleCurrentHistory();
    }

    private ActivityMapper createActivityMapper() {
        return new ActivityMapperImpl(ClientFactory.INSTANCE);
    }

    private PlaceHistoryMapper createHistoryMapper() {
        return new PlaceHistoryMapperImpl(ClientFactory.INSTANCE);
    }

    private Place getDefaultPlace() {
        for (FactoryModel model : ClientFactory.INSTANCE.getFactoryModels()) {
            if (!model.isComponent()) {
                com.smvp4g.mvp.client.core.place.Place place = ClassUtils.getAnnotation(model.
                        getPlaceClass(), com.smvp4g.mvp.client.core.place.Place.class);
                if (place != null && place.defaultPlace()) {
                    ViewSecurity viewSecurity = ClassUtils.getAnnotation(model.getViewClass(), ViewSecurity.class);
                    if (viewSecurity != null) {
                        ViewSecurityConfigurator configurator = ClassUtils.instantiate(viewSecurity.configuratorClass());
                        if ((LoginUtils.checkPermission(configurator.getRoles(), LoginUtils.getRole()) && !viewSecurity.showOnlyGuest())
                                || (viewSecurity.showOnlyGuest() && LoginUtils.getRole() == null)) {
                            return ClassUtils.instantiate(model.getPlaceClass());
                        }
                    } else {
                        return ClassUtils.instantiate(model.getPlaceClass());
                    }
                }
            }
        }
        return Place.NOWHERE;
    }

    private void createDefaultPresenter() {
        for (FactoryModel model : ClientFactory.INSTANCE.getFactoryModels()) {
            if (model.isComponent()) {
                ComponentPresenter componentPresenter = ClassUtils.
                        getAnnotation(model.getPresenterClass(), ComponentPresenter.class);
                if (componentPresenter.runOnStart()) {
                    Presenter presenter = ClientFactory.INSTANCE.createPresenter(model);
                    ((AbstractComponentPresenter) presenter).start(eventBus);
                }
            }
        }
    }
}
