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

package net.smvp.example.client.module.main.view;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import net.smvp.example.client.constant.DomIdConstant;
import net.smvp.mvp.client.core.view.AbstractView;
import net.smvp.mvp.client.core.view.annotation.View;
import net.smvp.mvp.client.widget.MenuLink;

/**
 * The Class MainMenuView.
 *
 * @author Nguyen Duc Dung
 * @since 10/30/11, 1:44 PM
 */
@View(parentDomId = DomIdConstant.LEFT_PANEL)
public class MainMenuView extends AbstractView {

    public MainMenuView() {
        RootPanel.get("loading").setVisible(false);
    }

    @Override
    protected void initializeView() {
        VerticalPanel panel = new VerticalPanel();
        panel.add(new MenuLink("Main Panel","main/test"));
        panel.add(new MenuLink("Test1 Panel","test/test1"));
        panel.add(new MenuLink("Test2 Panel","test/test2"));
        setWidget(panel);
    }
}
