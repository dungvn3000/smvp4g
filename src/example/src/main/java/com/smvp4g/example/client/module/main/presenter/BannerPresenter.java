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

package com.smvp4g.example.client.module.main.presenter;

import com.google.gwt.user.client.Window;
import com.smvp4g.example.client.module.main.view.BannerView;
import com.smvp4g.example.client.module.test.presenter.Test2Presenter;
import com.smvp4g.mvp.client.core.eventbus.annotation.EventHandler;
import com.smvp4g.mvp.client.core.presenter.BasicPresenter;
import com.smvp4g.mvp.client.core.presenter.annotation.Presenter;

/**
 * The Class BannerPresenter.
 *
 * @author Nguyen Duc Dung
 * @since 11/20/11, 8:13 PM
 */
@Presenter(view = BannerView.class)
public class BannerPresenter extends BasicPresenter<BannerView> {
    @Override
    public void onActivate() {
        view.show();
    }

    @EventHandler(eventName = Test2Presenter.EVENT_HELLO_NAME)
    public void hello(String name) {
        Window.alert("Hello " + name);
    }
}
