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

package com.smvp4g.mvp.client.core.eventbus;

import com.google.gwt.event.shared.GwtEvent;

/**
 * The Class Event.
 *
 * @author Nguyen Duc Dung
 * @since 12/16/11, 2:05 PM
 */
public class Event extends GwtEvent<EventHandler> {

    public final static Type<EventHandler> TYPE = new Type<EventHandler>();
    private String eventName;
    private Object[] prams;

    public Event(String eventName, Object... prams) {
        this.eventName = eventName;
        this.prams = prams;
    }
    
    @Override
    public Type<EventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EventHandler handler) {
        handler.handle(eventName, prams);
    }
}
