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

package com.smvp4g.example.client.module.test.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.smvp4g.example.client.constant.DomIdConstant;
import com.smvp4g.example.client.module.test.view.security.Test1ViewSecurity;
import com.smvp4g.mvp.client.core.security.ViewSecurity;
import com.smvp4g.mvp.client.core.view.AbstractView;
import com.smvp4g.mvp.client.core.view.annotation.View;

/**
 * The Class Test1View.
 *
 * @author Nguyen Duc Dung
 * @since 11/1/11, 8:19 PM
 */
@ViewSecurity(configuratorClass = Test1ViewSecurity.class)
@View(parentDomId = DomIdConstant.CONTENT_PANEL)
public class Test1View extends AbstractView {

    private Button button = new Button("Ok");

    public Test1View() {
        initializeView();
    }

    @Override
    protected void initializeView() {
        HorizontalPanel panel = new HorizontalPanel();
        panel.add(new Label("Test View 1"));
        panel.add(button);
        setWidget(panel);
    }

    public Button getButton() {
        return button;
    }
}
