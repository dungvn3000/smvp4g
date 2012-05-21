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

package com.smvp4g.mvp.client.core.view;

import com.smvp4g.mvp.client.core.i18n.Constants;
import com.smvp4g.mvp.client.core.utils.StringUtils;

/**
 * The Class ComponentView.
 *
 * @author Nguyen Duc Dung
 * @since 5/21/12, 11:14 PM
 */
public abstract class ComponentView<C extends Constants> extends AbstractView<C> {

    @Override
    public boolean show(boolean replace) {
        return true;
    }

    @Override
    public String getDomId() {
        return StringUtils.EMPTY;
    }
}
