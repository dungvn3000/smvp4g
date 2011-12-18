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

package net.smvp.mvp.client.core.factory.configure;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceChangeEvent;
import net.smvp.factory.client.utils.ClassUtils;
import net.smvp.mvp.client.core.eventbus.annotation.HistoryHandler;
import net.smvp.mvp.client.core.place.AbstractPlace;
import net.smvp.mvp.client.widget.MenuLink;
import net.smvp.reflection.client.field.FieldType;
import net.smvp.reflection.client.method.MethodType;

/**
 * The Class HistoryHandlerConfigure.
 *
 * @author Nguyen Duc Dung
 * @since 12/18/11, 9:18 AM
 */
public class HistoryHandlerConfigure implements Configure<Object> {

    private EventBus eventBus;

    public HistoryHandlerConfigure(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void configure(final Object object) {
        if (object != null) {
            for (final FieldType fieldType : ClassUtils.getFields(object.getClass())) {
                HistoryHandler historyHandler = fieldType.getAnnotation(HistoryHandler.class);
                if (historyHandler != null) {
                    eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
                        @Override
                        public void onPlaceChange(PlaceChangeEvent event) {
                            Object field = fieldType.get(object);
                            if (field instanceof MenuLink) {
                                AbstractPlace place = (AbstractPlace) event.getNewPlace();
                                if (place != null && ((MenuLink) field).
                                        getTargetHistoryToken().equals(place.getToken())) {
                                    ((MenuLink) field).setActive(true);
                                } else {
                                    ((MenuLink) field).setActive(false);
                                }
                            }
                        }
                    });
                }
            }
            for (final MethodType methodType : ClassUtils.getMethods(object.getClass())) {
                HistoryHandler historyHandler = methodType.getAnnotation(HistoryHandler.class);
                if (historyHandler != null) {
                    eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
                        @Override
                        public void onPlaceChange(PlaceChangeEvent event) {
                            methodType.invoke(object);
                        }
                    });
                }
            }
        }
    }
}
