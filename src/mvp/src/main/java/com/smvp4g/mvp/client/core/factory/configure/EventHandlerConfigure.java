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
import com.smvp4g.factory.client.utils.ClassUtils;
import com.smvp4g.mvp.client.core.eventbus.Event;
import com.smvp4g.mvp.client.core.eventbus.annotation.EventHandler;
import com.smvp4g.reflection.client.method.MethodType;

/**
 * The Class EventHandlerConfigure.
 *
 * @author Nguyen Duc Dung
 * @since 12/18/11, 9:14 AM
 */
public class EventHandlerConfigure implements Configure<Object> {

    private EventBus eventBus;

    public EventHandlerConfigure(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void configure(final Object object) {
        if (object != null) {
            for (final MethodType method : ClassUtils.getMethods(object.getClass())) {
                final EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                if (eventHandler != null) {
                    eventBus.addHandler(Event.TYPE, new com.smvp4g.mvp.client.core.eventbus.EventHandler() {
                        @Override
                        public boolean isMath(String eventName) {
                            return eventHandler.eventName().equals(eventName);
                        }

                        @Override
                        public void doHandle(Object... prams) {
                            method.invoke(object, prams);
                        }
                    });
                }
            }
        }
    }
}
