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

import net.smvp.mvp.client.core.module.Module;
import net.smvp.mvp.client.core.place.AbstractPlace;
import net.smvp.mvp.client.core.presenter.Presenter;
import net.smvp.mvp.client.core.view.View;

/**
 * The Class FactoryModel.
 *
 * @author Nguyen Duc Dung
 * @since 11/21/11, 8:37 PM
 */
public class FactoryModel {

    private Class<? extends Presenter> presenterClass;
    private Class<? extends View> viewClass;
    private Class<? extends AbstractPlace> placeClass;
    private Class<? extends Module> moduleClass;
    private String token;

    public Class<? extends Presenter> getPresenterClass() {
        return presenterClass;
    }

    public void setPresenterClass(Class<? extends Presenter> presenterClass) {
        this.presenterClass = presenterClass;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public void setViewClass(Class<? extends View> viewClass) {
        this.viewClass = viewClass;
    }

    public Class<? extends AbstractPlace> getPlaceClass() {
        return placeClass;
    }

    public void setPlaceClass(Class<? extends AbstractPlace> placeClass) {
        this.placeClass = placeClass;
    }

    public Class<? extends Module> getModuleClass() {
        return moduleClass;
    }

    public void setModuleClass(Class<? extends Module> moduleClass) {
        this.moduleClass = moduleClass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
