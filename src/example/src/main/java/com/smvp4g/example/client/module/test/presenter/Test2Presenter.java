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

package com.smvp4g.example.client.module.test.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.smvp4g.example.client.module.test.place.Test2Place;
import com.smvp4g.example.client.module.test.view.Test2View;
import com.smvp4g.mvp.client.core.eventbus.Event;
import com.smvp4g.mvp.client.core.presenter.AbstractPresenter;
import com.smvp4g.mvp.client.core.presenter.annotation.Presenter;

/**
 * The Class Test2Presenter.
 *
 * @author Nguyen Duc Dung
 * @since 11/18/11, 9:40 PM
 */
@Presenter(view = Test2View.class, place = Test2Place.class)
public class Test2Presenter extends AbstractPresenter<Test2View> {

    public static final String EVENT_HELLO_NAME = "hello";

    @Override
    public void onActivate() {
        view.show();
    }

    @Override
    protected void doBind() {
        view.getButton5().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new Event(EVENT_HELLO_NAME, getClass().getName()));
            }
        });
    }
}
