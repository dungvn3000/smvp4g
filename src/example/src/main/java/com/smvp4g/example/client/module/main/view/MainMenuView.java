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

package com.smvp4g.example.client.module.main.view;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smvp4g.example.client.constant.DomIdConstant;
import com.smvp4g.mvp.client.core.eventbus.annotation.HistoryHandler;
import com.smvp4g.mvp.client.core.place.AbstractPlace;
import com.smvp4g.mvp.client.core.view.AbstractView;
import com.smvp4g.mvp.client.core.view.annotation.View;
import com.smvp4g.mvp.client.widget.MenuLink;

/**
 * The Class MainMenuView.
 *
 * @author Nguyen Duc Dung
 * @since 10/30/11, 1:44 PM
 */
@View(parentDomId = DomIdConstant.LEFT_PANEL)
public class MainMenuView extends AbstractView {

    private static final String MENU_CSS_STYLE = "mainmenu";

    @HistoryHandler
    public MenuLink mlMainPanel = new MenuLink("Main Panel", "");

    @HistoryHandler
    public MenuLink mlTest1Panel = new MenuLink("Test1 Panel","test/test1");

    @HistoryHandler
    public MenuLink mlTest2Panel = new MenuLink("Test2 Panel","test/test2");

    @Override
    protected void initializeView() {
        VerticalPanel panel = new VerticalPanel();
        panel.add(mlMainPanel);
        panel.add(mlTest1Panel);
        panel.add(mlTest2Panel);
        setWidget(panel);
        setStyleName(MENU_CSS_STYLE);
    }

    @HistoryHandler
    public void testHistoryHandler(PlaceChangeEvent event) {
        Log.debug(((AbstractPlace)event.getNewPlace()).getToken());
    }
}
