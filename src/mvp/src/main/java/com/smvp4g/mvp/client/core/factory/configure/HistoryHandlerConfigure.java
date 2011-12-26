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

package com.smvp4g.mvp.client.core.factory.configure;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.smvp4g.factory.client.utils.ClassUtils;
import com.smvp4g.mvp.client.core.eventbus.annotation.HistoryHandler;
import com.smvp4g.mvp.client.core.factory.FactoryModel;
import com.smvp4g.mvp.client.core.place.AbstractPlace;
import com.smvp4g.mvp.client.widget.MenuLink;
import com.smvp4g.reflection.client.field.FieldType;
import com.smvp4g.reflection.client.method.MethodType;

import java.util.List;

/**
 * The Class HistoryHandlerConfigure.
 *
 * @author Nguyen Duc Dung
 * @since 12/18/11, 9:18 AM
 */
public class HistoryHandlerConfigure implements Configure<Object> {

    private List<FactoryModel> factoryModels;
    private EventBus eventBus;

    public HistoryHandlerConfigure(EventBus eventBus, List<FactoryModel> factoryModels) {
        this.eventBus = eventBus;
        this.factoryModels = factoryModels;
    }

    @Override
    public void configure(final Object object) {
        if (object != null) {
            for (final FieldType fieldType : ClassUtils.getFields(object.getClass())) {
                HistoryHandler historyHandler = fieldType.getAnnotation(HistoryHandler.class);
                if (historyHandler != null) {
                    final Object field = fieldType.get(object);
                    if (field instanceof MenuLink) {
                        final MenuLink menuLink = (MenuLink) field;
                        eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
                            @Override
                            public void onPlaceChange(PlaceChangeEvent event) {
                                AbstractPlace place = (AbstractPlace) event.getNewPlace();
                                if (place != null && menuLink.getPlaceClass() == place.getClass()) {
                                    menuLink.setActive(true);
                                } else {
                                    menuLink.setActive(false);
                                }
                            }
                        });
                        for (FactoryModel model : factoryModels) {
                            if (model.getPlaceClass() == menuLink.getPlaceClass()) {
                                menuLink.setTargetHistoryToken(model.getToken());
                            }
                        }
                    }
                }
            }
            for (final MethodType methodType : ClassUtils.getMethods(object.getClass())) {
                HistoryHandler historyHandler = methodType.getAnnotation(HistoryHandler.class);
                if (historyHandler != null) {
                    eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
                        @Override
                        public void onPlaceChange(PlaceChangeEvent event) {
                            methodType.invoke(object, event);
                        }
                    });
                }
            }
        }
    }
}
