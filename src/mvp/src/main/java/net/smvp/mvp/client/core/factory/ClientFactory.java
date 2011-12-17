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

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import net.smvp.mvp.client.core.place.AbstractPlace;
import net.smvp.mvp.client.core.presenter.Presenter;
import net.smvp.mvp.client.core.view.View;

/**
 * The Class ClientFactory.
 *
 * @author Nguyen Duc Dung
 * @since 11/2/11, 12:07 PM
 */
public interface ClientFactory {
    public static final ClientFactory INSTANCE = GWT.create(ClientFactoryImpl.class);
    ActivityMapper createActivityMapper();
    PlaceHistoryMapper createHistoryMapper();
    void createAndHandleHistory();
    void configure();
    void createDefaultPresenter();
    <T extends Presenter<? extends View>> T getExitsPresenter(Class<T> presenterClass);
    Presenter createPresenter(FactoryModel model);
    AbstractPlace createPlace(FactoryModel model);
    <P extends AbstractPlace> P getExitsPlace(Class<P> placeClass);
}
