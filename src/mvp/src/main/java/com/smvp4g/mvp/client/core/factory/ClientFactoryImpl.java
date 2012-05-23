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

package com.smvp4g.mvp.client.core.factory;

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
import com.smvp4g.mvp.client.core.factory.configure.EventHandlerConfigure;
import com.smvp4g.mvp.client.core.factory.configure.HistoryHandlerConfigure;
import com.smvp4g.mvp.client.core.mapper.ActivityMapperImpl;
import com.smvp4g.mvp.client.core.mapper.PlaceHistoryMapperImpl;
import com.smvp4g.mvp.client.core.module.Module;
import com.smvp4g.mvp.client.core.place.AbstractPlace;
import com.smvp4g.mvp.client.core.place.DefaultPlace;
import com.smvp4g.mvp.client.core.presenter.AbstractPresenter;
import com.smvp4g.mvp.client.core.presenter.Presenter;
import com.smvp4g.mvp.client.core.security.ViewSecurity;
import com.smvp4g.mvp.client.core.security.ViewSecurityConfigurator;
import com.smvp4g.mvp.client.core.utils.LoginUtils;
import com.smvp4g.mvp.client.core.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ClientFactoryImpl.
 *
 * @author Nguyen Duc Dung
 * @since 11/17/11, 5:16 PM
 */
public class ClientFactoryImpl implements ClientFactory {

    protected List<FactoryModel> factoryModels = new ArrayList<FactoryModel>();
    protected List<Presenter<? extends View>> presenters =
            new ArrayList<Presenter<? extends View>>();
    protected List<Module> modules = new ArrayList<Module>();

    private EventBus eventBus = new SimpleEventBus();
    private PlaceController placeController = new PlaceController(eventBus);
    private EventHandlerConfigure eventHandlerConfigure = new EventHandlerConfigure(eventBus);
    private HistoryHandlerConfigure historyHandlerConfigure = new HistoryHandlerConfigure(eventBus, factoryModels);

    @Override
    public ActivityMapper createActivityMapper() {
        return new ActivityMapperImpl(factoryModels, this);
    }

    @Override
    public PlaceHistoryMapper createHistoryMapper() {
        return new PlaceHistoryMapperImpl(factoryModels, this);
    }

    @Override
    public void createAndHandleHistory() {
        ActivityManager activityManager = new ActivityManager(createActivityMapper(), eventBus);
        activityManager.setDisplay(new SimplePanel());
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(createHistoryMapper());
        placeHistoryHandler.register(placeController, eventBus, getDefaultPlace());
        placeHistoryHandler.handleCurrentHistory();
    }
    
    private Place getDefaultPlace() {
        for (FactoryModel model : factoryModels) {
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
        return Place.NOWHERE;
    }

    protected <V extends View> void configurePresenter(Presenter<V> presenter, V view, AbstractPlace place) {
        presenter.setView(view);
        ((AbstractPresenter)presenter).setPlace(place);
        ((AbstractPresenter)presenter).setPlaceController(placeController);
        presenter.bind();
        presenters.add(presenter);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Presenter<? extends View>> T getExitsPresenter(Class<T> presenterClass) {
        for (Presenter<? extends View> presenter : presenters) {
            if (ClassUtils.getRealClass(presenter) == presenterClass) {
                return (T) presenter;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <P extends AbstractPlace> P getExitsPlace(Class<P> placeClass) {
        for (Presenter<? extends View> presenter : presenters) {
            if (ClassUtils.getRealClass(((AbstractPresenter)presenter).getPlace()) == placeClass) {
                return (P) ((AbstractPresenter)presenter).getPlace();
            }
        }
        return null;
    }

    @Override
    public void createDefaultPresenter() {
        for (FactoryModel model : factoryModels) {
            if (model.getPlaceClass() == DefaultPlace.class) {
                Presenter presenter = createPresenter(model);
                ((AbstractPresenter)presenter).start(null, eventBus);
            }
        }
    }

    @Override
    public Presenter createPresenter(FactoryModel model) {
        Presenter presenter = instantiate(model.getPresenterClass());
        if (presenter != null) {
            View view = createView(model);
            AbstractPlace place = createPlace(model);
            if (getExitsModule(model.getModuleClass()) == null) {
                createModule(model);
            }
            configurePresenter(presenter, view, place);
            eventHandlerConfigure.configure(presenter);
        }
        return presenter;
    }

    protected View createView(FactoryModel model) {
        View view = instantiate(model.getViewClass());
        if (view != null) {
            historyHandlerConfigure.configure(view);
        }
        return view;
    }

    @Override
    public AbstractPlace createPlace(FactoryModel model) {
        AbstractPlace place = instantiate(model.getPlaceClass());
        if (place != null) {
            place.setToken(model.getToken());
        }
        return place;
    }

    @Override
    public Module createModule(FactoryModel model) {
        Module module = instantiate(model.getModuleClass());
        if (module != null) {
            configureModule(module);
            modules.add(module);
        }
        return module;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <M extends Module> M getExitsModule(Class<M> moduleClass) {
        for (Module module : modules) {
            if (module.getClass() == ClassUtils.getRealClass(moduleClass)) {
                return (M) module;
            }
        }
        return null;
    }

    private void configureModule(Module module) {
        module.configure();
        module.start();
    }

    @Override
    public void configure() {
        createDefaultPresenter();
        createAndHandleHistory();
    }

    protected <T, V extends T> T instantiate(Class<V> clazz) {
        return ClassUtils.instantiate(clazz);
    }
}
