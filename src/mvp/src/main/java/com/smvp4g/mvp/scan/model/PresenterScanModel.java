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

package com.smvp4g.mvp.scan.model;

/**
 * The Class PresenterScanModel.
 *
 * @author Nguyen Duc Dung
 * @since 11/23/11, 9:00 PM
 */
public class PresenterScanModel {

    private String presenterClassName;
    private String viewClassName;
    private String placeClassName;
    private String moduleClassName;
    private String token;
    private String viewParentDomID;

    public String getPresenterClassName() {
        return presenterClassName;
    }

    public void setPresenterClassName(String presenterClassName) {
        this.presenterClassName = presenterClassName;
    }

    public String getViewClassName() {
        return viewClassName;
    }

    public void setViewClassName(String viewClassName) {
        this.viewClassName = viewClassName;
    }

    public String getPlaceClassName() {
        return placeClassName;
    }

    public void setPlaceClassName(String placeClassName) {
        this.placeClassName = placeClassName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getViewParentDomID() {
        return viewParentDomID;
    }

    public void setViewParentDomID(String viewParentDomID) {
        this.viewParentDomID = viewParentDomID;
    }

    public String getModuleClassName() {
        return moduleClassName;
    }

    public void setModuleClassName(String moduleClassName) {
        this.moduleClassName = moduleClassName;
    }
}
