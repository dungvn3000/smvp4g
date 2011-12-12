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

package net.smvp.mvp.client.core.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import net.smvp.reflection.client.marker.Reflection;
import net.smvp.mvp.client.core.place.AbstractPlace;
import net.smvp.mvp.client.core.view.View;

/**
 * The Class AbstractPresenter.
 *
 * @author dungvn3000
 * @since 5/13/11, 10:34 AM
 */
@Reflection
public abstract class AbstractPresenter<V extends View> implements Presenter<V> {

    protected V view;
    protected AbstractPlace place;
    protected EventBus eventBus;
    protected PlaceController placeController;

    @Override
    public void setView(V view) {
        this.view = view;
    }

    @Override
    public V getView() {
        return view;
    }

    @Override
    public AbstractPlace getPlace() {
        return place;
    }

    @Override
    public void setPlace(AbstractPlace place) {
        this.place = place;
    }

    @Override
    public void setPlaceController(PlaceController controller) {
        this.placeController = controller;
    }

    @Override
    public final void bind() {
        view.bind();
        doBind();
    }

    @Override
    public String mayStop() {
        return null;
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public final void start(AcceptsOneWidget panel, EventBus eventBus) {
        this.eventBus = eventBus;
        onActivate();
    }

    @Override
    public void onActivate() {

    }

    /**
     * Do bind with a presenter with a view..
     */
    protected void doBind() {

    }
}
