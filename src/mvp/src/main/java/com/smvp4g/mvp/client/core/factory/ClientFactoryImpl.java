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

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.smvp4g.factory.client.utils.ClassUtils;
import com.smvp4g.mvp.client.core.factory.configure.EventHandlerConfigure;
import com.smvp4g.mvp.client.core.factory.configure.HistoryHandlerConfigure;
import com.smvp4g.mvp.client.core.module.Module;
import com.smvp4g.mvp.client.core.place.AbstractPlace;
import com.smvp4g.mvp.client.core.presenter.AbstractPresenter;
import com.smvp4g.mvp.client.core.presenter.Presenter;
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

    private EventBus eventBus;
    private PlaceController placeController;
    private EventHandlerConfigure eventHandlerConfigure;
    private HistoryHandlerConfigure historyHandlerConfigure;


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
            if (presenter instanceof AbstractPresenter) {
                if (ClassUtils.getRealClass(((AbstractPresenter) presenter).getPlace()) == placeClass) {
                    return (P) ((AbstractPresenter) presenter).getPlace();
                }
            }
        }
        return null;
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
            presenter.setView(view);
            if (presenter instanceof AbstractPresenter) {
                ((AbstractPresenter) presenter).setPlace(place);
                ((AbstractPresenter) presenter).setPlaceController(placeController);
            }
            presenter.bind();
            presenters.add(presenter);
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
            module.configure();
            module.start();
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


    @Override
    public void configure(EventBus eventBus, PlaceController placeController) {
        this.eventBus = eventBus;
        this.placeController = placeController;
        eventHandlerConfigure = new EventHandlerConfigure(eventBus);
        historyHandlerConfigure = new HistoryHandlerConfigure(eventBus, factoryModels);
    }

    protected <T, V extends T> T instantiate(Class<V> clazz) {
        return ClassUtils.instantiate(clazz);
    }

    @Override
    public List<FactoryModel> getFactoryModels() {
        return factoryModels;
    }
}
