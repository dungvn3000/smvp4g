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

package com.smvp4g.example.client.component.test;

import com.google.gwt.user.client.ui.Label;
import com.smvp4g.example.client.component.test.i18n.TestViewConstants;
import com.smvp4g.example.client.component.test.security.TestViewSecurity;
import com.smvp4g.mvp.client.core.i18n.I18nField;
import com.smvp4g.mvp.client.core.security.ViewSecurity;
import com.smvp4g.mvp.client.core.view.AbstractComponentView;
import com.smvp4g.mvp.client.core.view.annotation.ComponentView;

/**
 * The Class TestComponentView.
 *
 * @author Nguyen Duc Dung
 * @since 5/24/12, 1:03 PM
 */

@ViewSecurity(configuratorClass = TestViewSecurity.class)
@ComponentView(constantsClass = TestViewConstants.class)
public class TestComponentView extends AbstractComponentView<TestViewConstants> {

    @I18nField
    Label lblHello = new Label();

    @Override
    protected void initializeView() {
        setWidget(lblHello);
    }
}
