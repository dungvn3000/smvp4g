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

package net.smvp.mvp.client.core.history;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import net.smvp.mvp.client.widget.MenuLink;

import javax.inject.Singleton;
import java.util.HashMap;

/**
 * The Class to handel history of application.
 *
 * @author Nguyen Duc Dung
 * @since 8/23/11, 9:50 AM
 */
@Singleton
public class HistoryPlaceImpl implements HistoryPlace, ValueChangeHandler<String> {

    private HashMap<String, MenuLink> menuLinks = new HashMap<String, MenuLink>();

    public HistoryPlaceImpl() {
        History.addValueChangeHandler(this);
    }

    @Override
    public void addMenuLink(MenuLink menuLink) {
        menuLinks.put(menuLink.getTargetHistoryToken(), menuLink);
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        for (String historyToken : menuLinks.keySet()) {
            MenuLink menuLink = menuLinks.get(historyToken);
            if (event.getValue().equals(historyToken)) {
                menuLink.setStyleName(MenuLink.ACTIVE_MENU_CSS_STYLE, true);
                menuLink.setActive(true);
            } else {
                menuLink.setStyleName(MenuLink.ACTIVE_MENU_CSS_STYLE, false);
                menuLink.setActive(false);
            }
        }
    }
}
