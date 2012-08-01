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


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.smvp4g.aop.client.marker.Aspectable;
import com.smvp4g.example.client.constant.DomIdConstant;
import com.smvp4g.example.client.module.test.view.i18n.Test2ViewConstants;
import com.smvp4g.example.client.module.test.view.security.Test2ViewSecurity;
import com.smvp4g.factory.client.utils.ClassUtils;
import com.smvp4g.mvp.client.core.i18n.I18nField;
import com.smvp4g.mvp.client.core.security.FieldSecurity;
import com.smvp4g.mvp.client.core.security.ViewSecurity;
import com.smvp4g.mvp.client.core.view.AbstractView;
import com.smvp4g.mvp.client.core.view.annotation.View;
import com.smvp4g.reflection.client.field.FieldType;
import com.smvp4g.reflection.client.method.MethodType;

/**
 * The Class Test2View.
 *
 * @author Nguyen Duc Dung
 * @since 11/18/11, 9:39 PM
 */
@View(parentDomId = DomIdConstant.CONTENT_PANEL, constantsClass = Test2ViewConstants.class)
@ViewSecurity(configuratorClass = Test2ViewSecurity.class)
public class Test2View extends AbstractView<Test2ViewConstants> implements Aspectable {

    @Test(name = "b1")
    @FieldSecurity
    public Button button1 = new Button("Get View Annotation");
    @Test(name = "b2")
    public Button button2 = new Button("Get Fields");
    private Button button3 = new Button("Get Methods");
    public Button button4 = new Button("Set Fields");
    public Button button5 = new Button("Fire Event");

    @I18nField
    public Label lblViewTitle = new Label();

    private HorizontalPanel panel = new HorizontalPanel();

    @Override
    protected void initializeView() {
        panel.add(lblViewTitle);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        button1.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                View view = ClassUtils.getAnnotation(Test2View.class, View.class);
                if (view != null) {
                    Window.alert(view.parentDomId());
                }
            }
        });
        button2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                testReflectionFields();
            }
        });
        button3.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                testReflectionMethod();
            }
        });
        button4.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                testSetFiledValue();
            }
        });
        setWidget(panel);
    }

    public Button getButton5() {
        return button5;
    }

    @Test(name = "callMe")
    public void callMe() {
        Window.alert("This method be called by Reflection.");
    }

    private void testReflectionMethod() {
        MethodType methodType = ClassUtils.getMethod(Test2View.class, "callMe");
        methodType.invoke(this);
        Test test = methodType.getAnnotation(Test.class);
        if (test != null) {
            Window.alert(test.name());
        }
    }

    private void testReflectionFields() {
        FieldType fieldType = ClassUtils.getField(Test2View.class, "button2");
        Button button = (Button) fieldType.get(this);
        button.setText("Reflectable");
    }
    
    private void testSetFiledValue() {
        FieldType fieldType = ClassUtils.getField(Test2View.class, "button4" );
        panel.remove((Widget) fieldType.get(this));
        fieldType.set(this, new Button("New Button set by Reflection"));
        panel.add((Widget) fieldType.get(this));
    }

}
