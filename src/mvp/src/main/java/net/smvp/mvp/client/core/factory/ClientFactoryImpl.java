/*
 * Copyright (C) 2009 - 2012 SMVP.NET
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

package net.smvp.mvp.client.core.factory;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.SimplePanel;
import net.smvp.factory.client.utils.ClassUtils;
import net.smvp.mvp.client.core.place.AbstractPlace;
import net.smvp.mvp.client.core.place.DefaultPlace;
import net.smvp.mvp.client.core.presenter.Presenter;
import net.smvp.mvp.client.core.utils.StringUtils;
import net.smvp.mvp.client.core.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ClientFactoryImpl.
 *
 * @author Nguyen Duc Dung
 * @since 11/17/11, 5:16 PM
 */
public class ClientFactoryImpl implements IClientFactory {

    protected List<FactoryModel> factoryModels = new ArrayList<FactoryModel>();
    protected List<Presenter<? extends View>> presenters =
            new ArrayList<Presenter<? extends View>>();

    private EventBus eventBus = new SimpleEventBus();
    private PlaceController placeController = new PlaceController(eventBus);

    @Override
    public ActivityMapper createActivityMapper() {
        return new ActivityMapperImpl();
    }

    @Override
    public PlaceHistoryMapper createHistoryMapper() {
        return new PlaceHistoryMapperImpl();
    }

    @Override
    public void createAndHandleHistory() {
        ActivityManager activityManager = new ActivityManager(createActivityMapper(), eventBus);
        activityManager.setDisplay(new SimplePanel());
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(createHistoryMapper());
        placeHistoryHandler.register(placeController, eventBus, Place.NOWHERE);
        placeHistoryHandler.handleCurrentHistory();
    }

    protected <V extends View> void configurePresenter(Presenter<V> presenter, V view, AbstractPlace place) {
        presenter.setView(view);
        presenter.setPlace(place);
        presenter.setPlaceController(placeController);
        presenter.bind();
        presenters.add(presenter);
    }

    @SuppressWarnings("unchecked")
    protected <T extends Presenter<? extends View>> T getExitsPresenter(Class<T> presenterClass) {
        for (Presenter<? extends View> presenter : presenters) {
            if (ClassUtils.getRealClass(presenter) == presenterClass) {
                return (T) presenter;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    protected <P extends AbstractPlace> P getExitsPlace(Class<P> placeClass) {
        for (Presenter<? extends View> presenter : presenters) {
            if (ClassUtils.getRealClass(presenter.getPlace()) == placeClass) {
                return (P) presenter.getPlace();
            }
        }
        return null;
    }

    @Override
    public void createDefaultPresenter() {
        for (FactoryModel model : factoryModels) {
            if (model.getPlaceClass() == DefaultPlace.class) {
                Presenter presenter = createPresenter(model);
                presenter.start(null, eventBus);
            }
        }
    }

    protected Presenter createPresenter(FactoryModel model) {
        Presenter presenter = instantiate(model.getPresenterClass());
        if (presenter != null) {
            View view = createView(model);
            AbstractPlace place = createPlace(model);
            configurePresenter(presenter, view, place);
        }
        return presenter;
    }

    protected View createView(FactoryModel model) {
        View view = instantiate(model.getViewClass());
        if (view != null) {
            view.setParentDomId(model.getViewParentDomID());
        }
        return view;
    }

    protected AbstractPlace createPlace(FactoryModel model) {
        AbstractPlace place = instantiate(model.getPlaceClass());
        if (place != null) {
            place.setToken(model.getToken());
        }
        return place;
    }

    @Override
    public void configure() {
        //Don't do any thing, GWT Generator will do that.
    }

    protected <T, V extends T> T instantiate(Class<V> clazz) {
        return ClassUtils.instantiate(clazz);
    }

    private class PlaceHistoryMapperImpl implements PlaceHistoryMapper {
        @Override
        public Place getPlace(String token) {
            for (FactoryModel model : factoryModels) {
                if (StringUtils.isNotBlank(model.getToken()) && model.getToken().equals(token)) {
                    AbstractPlace place = getExitsPlace(model.getPlaceClass());
                    if (place == null) {
                        place = createPlace(model);
                    }
                    return place;
                }
            }
            return null;
        }

        @Override
        public String getToken(Place place) {
            for (FactoryModel model : factoryModels) {
                if (ClassUtils.getRealClass(place) == model.getPlaceClass()) {
                    return model.getToken();
                }
            }
            return null;
        }
    }

    private class ActivityMapperImpl implements ActivityMapper {
        @Override
        public Activity getActivity(Place place) {
            for (FactoryModel model : factoryModels) {
                if (ClassUtils.getRealClass(place) == model.getPlaceClass()) {
                    Presenter presenter = getExitsPresenter(model.getPresenterClass());
                    if (presenter == null) {
                        presenter = createPresenter(model);
                    }
                    return presenter;
                }
            }
            return null;
        }
    }
}
