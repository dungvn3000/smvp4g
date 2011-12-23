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

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.smvp4g.factory.client.utils.ClassUtils;
import com.smvp4g.mvp.client.core.i18n.Constants;
import com.smvp4g.mvp.client.core.i18n.I18nField;
import com.smvp4g.mvp.client.core.i18n.I18nText;
import com.smvp4g.mvp.client.core.security.FieldSecurity;
import com.smvp4g.mvp.client.core.security.HasRole;
import com.smvp4g.mvp.client.core.security.ViewSecurity;
import com.smvp4g.mvp.client.core.security.ViewSecurityConfigurator;
import com.smvp4g.mvp.client.core.utils.GWTUtils;
import com.smvp4g.mvp.client.core.utils.LoginUtils;
import com.smvp4g.mvp.client.core.utils.WidgetUtils;
import com.smvp4g.mvp.client.core.view.templateview.AccessDenyView;
import com.smvp4g.reflection.client.field.FieldType;
import com.smvp4g.reflection.client.marker.Reflection;
import com.smvp4g.reflection.client.method.MethodType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class AbstractView..
 *
 * @author Nguyen Duc Dung
 * @since Aug 15, 2010, 6:45:32 PM
 */
@Reflection
public abstract class AbstractView<C extends Constants> extends FlexTable implements View<C> {

    /**
     * Default width of views.
     */
    private static final String WIDTH = "100%";

    /**
     * Parent dom id of view.
     */
    private String parentDomId;

    /**
     * I18nField constant of this view.
     */
    private C constant;

    /**
     * The Security Configurator of this view.
     */
    private ViewSecurityConfigurator securityConfigurator;

    /**
     * Default construct..
     */
    public AbstractView() {
    }

    @Override
    public final void createView() {
        getElement().setId(generateDomId());
        if (isSecurityView()) {
            doSecurity();
        }
        initializeView();
        applyStyle();
    }

    /**
     * Generate unique id for the view.
     */
    protected String generateDomId() {
        //Default using class name of the view.
        return ClassUtils.getRealClass(getClass()).getName();
    }

    /**
     * The unique id of the view.
     *
     * @return
     */
    @Override
    public String getDomId() {
        return getElement().getId();
    }

    /**
     * Initialize content of View.
     */
    protected void initializeView() {
        //Default do no thing.
    }

    /**
     * Define style for View in here.
     */
    protected void applyStyle() {
        setWidth(WIDTH);
    }

    @Override
    public String getParentDomId() {
        if (parentDomId == null) {
            com.smvp4g.mvp.client.core.view.annotation.View view = ClassUtils.getAnnotation(getClass(),
                    com.smvp4g.mvp.client.core.view.annotation.View.class);
            if (view != null) {
                setParentDomId(view.parentDomId());
            }
        }
        return parentDomId;
    }

    @Override
    public void setParentDomId(String parentDomId) {
        this.parentDomId = parentDomId;
    }

    /**
     * Do security rule for this view, do it before we render this view.
     */
    private void doSecurity() {
        for (FieldType fieldType : ClassUtils.getFields(getClass())) {
            FieldSecurity securityAnnotation = fieldType.getAnnotation(FieldSecurity.class);
            if (securityAnnotation != null) {
                Object widget = fieldType.get(this);
                if (securityAnnotation.showOnlyGuest() && LoginUtils.getRole() != null) {
                    WidgetUtils.setVisible(widget, false);
                } else {
                    ViewSecurityConfigurator configurator = getSecurityConfigurator();
                    if (configurator != null) {
                        HasRole[] roles = getRoles(configurator, fieldType.getName());
                        if (!LoginUtils.checkPermission(roles, LoginUtils.getRole())) {
                            WidgetUtils.setVisible(widget, false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Before view be shown.
     */

    protected void onBeforeShow() {
        //Default do no thing
    }

    /**
     * After view was shown.
     */
    protected void onAfterShow() {
        //Default do no thing
    }

    @Override
    public final boolean show() {
        return show(true);
    }

    @Override
    public boolean show(boolean replace) {
        boolean isShown = true;
        onBeforeShow();
        if (getParentDomId() != null) {
            if (replace) {
                GWTUtils.removeAllWidgetFromDOM(getParentDomId());
            }
            ViewSecurity viewSecurity = ClassUtils.getAnnotation(getClass(), ViewSecurity.class);
            if (!LoginUtils.checkPermission(getRoles(), LoginUtils.getRole())
                    || (viewSecurity != null && viewSecurity.showOnlyGuest() && LoginUtils.getRole() != null)) {
                // Show login Requirement Panel, If this View hasn't GUEST ROLE.
                GWTUtils.addWidgetToDOM(getParentDomId(), new AccessDenyView());
                isShown = false;
            } else {
                GWTUtils.addWidgetToDOM(getParentDomId(), this);
            }
        }
        if (isShown) {
            //The view don't display, so we shouldn't call onAfterShow() method.
            onAfterShow();
        }
        return isShown;
    }

    /**
     * Do bind a view with a presenter.
     */
    protected void doBind() {
        //Default do no thing
    }

    /**
     * Do bind i18n to all filed mark by annotation {@link com.smvp4g.mvp.client.core.i18n.I18nField} of a view.
     */
    private void bindI18n() {
        if (getConstant() != null) {
            for (FieldType fieldType : ClassUtils.getFields(getClass())) {
                I18nField fieldAnnotation = fieldType.getAnnotation(I18nField.class);
                if (fieldAnnotation != null) {
                    Object widget = fieldType.get(this);
                    String i18nText = getConstant().getString(fieldType.getName());
                    if (!fieldAnnotation.emptyText()) {
                        //Set text for widget in view.
                        WidgetUtils.setText(widget, i18nText);
                    } else {
                        //Set empty text for widget in view.
                        WidgetUtils.setEmptyText(widget, i18nText);
                    }
                } else if (fieldType.getAnnotation(I18nText.class) != null) {
                    //Set value for i18nText in View.
                    String i18nText = getConstant().getString(fieldType.getName());
                    fieldType.set(this, i18nText);
                }
            }
        }
    }

    @Override
    public final void bind() {
        createView();
        bindI18n();
        doBind();
    }

    @Override
    public void setWidget(Widget w) {
        setWidget(0, 0, w);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final C getConstant() {
        if (constant == null) {
            com.smvp4g.mvp.client.core.view.annotation.View view = ClassUtils.getAnnotation(getClass(),
                    com.smvp4g.mvp.client.core.view.annotation.View.class);
            if (view != null) {
                Class<? extends Constants> constantsClass = view.constantsClass();
                constant = (C) ClassUtils.instantiate(constantsClass);
            }
        }
        return constant;
    }

    @Override
    public boolean isSecurityView() {
        return ClassUtils.getAnnotation(getClass(), ViewSecurity.class) != null;
    }

    @Override
    public List<HasRole> getRoles() {
        if (getSecurityConfigurator() != null) {
            HasRole[] roles = getSecurityConfigurator().getRoles();
            if (roles != null) {
                return Arrays.asList(roles);
            }
        }
        //We should return new empty array list instead of Collections.emptyList() , to add new element in it.
        return new ArrayList<HasRole>();
    }

    @Override
    public boolean isVisible() {
        boolean isVisible = DOM.getElementById(getDomId()) != null;
        return super.isVisible() && isVisible;
    }

    private ViewSecurityConfigurator getSecurityConfigurator() {
        ViewSecurityConfigurator configurator = this.securityConfigurator;
        if (configurator == null) {
            ViewSecurity viewSecurity = ClassUtils.getAnnotation(getClass(), ViewSecurity.class);
            if (viewSecurity != null) {
                try {
                    configurator = ClassUtils.instantiate(viewSecurity.configuratorClass());
                    this.securityConfigurator = configurator;
                } catch (Exception e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }
        return configurator;
    }

    private HasRole[] getRoles(ViewSecurityConfigurator configurator, String configName) {
        MethodType getterMethodType = null;
        if (configurator != null) {
            //Safe find getter method.
            for (MethodType methodType : ClassUtils.getMethods(configurator.getClass())) {
                if (methodType.getName().equals(configName)) {
                    getterMethodType = methodType;
                    break;
                }
            }
        }
        HasRole[] roles = null;
        if (getterMethodType != null) {
            roles = (HasRole[]) getterMethodType.invoke(configurator);
        }
        return roles;
    }
}