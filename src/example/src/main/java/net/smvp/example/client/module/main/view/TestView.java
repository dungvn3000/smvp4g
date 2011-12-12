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

import com.google.gwt.user.client.ui.Label;
import net.smvp.example.client.constant.DomIdConstant;
import net.smvp.mvp.client.core.view.AbstractView;
import net.smvp.mvp.client.core.view.annotation.View;

/**
 * The Class TestView.
 *
 * @author Nguyen Duc Dung
 * @since 11/21/11, 5:28 PM
 */
@View(parentDomId = DomIdConstant.CONTENT_PANEL)
public class TestView extends AbstractView {
    @Override
    protected void initializeView() {
        setWidget(new Label("Main Test View"));
    }
}
