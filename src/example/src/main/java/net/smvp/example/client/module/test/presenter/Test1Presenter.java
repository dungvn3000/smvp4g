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

package net.smvp.example.client.module.test.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import net.smvp.aop.client.marker.Aspectable;
import net.smvp.example.client.module.test.place.Test1Place;
import net.smvp.example.client.module.test.place.Test2Place;
import net.smvp.example.client.module.test.view.Test1View;
import net.smvp.mvp.client.core.presenter.AbstractPresenter;
import net.smvp.mvp.client.core.presenter.annotation.Presenter;

/**
 * The Class Test1Presenter.
 *
 * @author Nguyen Duc Dung
 * @since 11/6/11, 1:38 PM
 */
@Presenter(view = Test1View.class, place = Test1Place.class)
public class Test1Presenter extends AbstractPresenter<Test1View> implements Aspectable {
    @Override
    protected void doBind() {
        view.getButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                placeController.goTo(new Test2Place());
            }
        });
    }

    @Override
    public void onActivate() {
        view.show();
    }
}
