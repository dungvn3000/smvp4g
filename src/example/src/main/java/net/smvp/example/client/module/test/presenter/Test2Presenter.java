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

import net.smvp.example.client.module.test.place.Test2Place;
import net.smvp.example.client.module.test.view.Test2View;
import net.smvp.mvp.client.core.presenter.AbstractPresenter;
import net.smvp.mvp.client.core.presenter.annotation.Presenter;

/**
 * The Class Test2Presenter.
 *
 * @author Nguyen Duc Dung
 * @since 11/18/11, 9:40 PM
 */
@Presenter(view = Test2View.class, place = Test2Place.class)
public class Test2Presenter extends AbstractPresenter<Test2View> {
    @Override
    public void onActivate() {
        view.show();
    }
}
