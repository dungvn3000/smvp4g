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

package com.smvp4g.mvp.scan.reader;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.smvp4g.generator.scan.reader.Reader;
import com.smvp4g.mvp.client.core.presenter.annotation.Presenter;
import com.smvp4g.mvp.scan.model.PresenterScanModel;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PresenterReader.
 *
 * @author Nguyen Duc Dung
 * @since 11/20/11, 2:30 PM
 */
public class PresenterReader implements Reader<PresenterScanModel> {

    private List<PresenterScanModel> models = new ArrayList<PresenterScanModel>();

    @Override
    public void read(JClassType classType, GeneratorContext context) {
        Presenter presenterAnnotation = classType.getAnnotation(Presenter.class);
        if (isMath(presenterAnnotation)) {
            PresenterScanModel model = new PresenterScanModel();
            model.setPresenterClassName(classType.getParameterizedQualifiedSourceName());
            model.setViewClassName(presenterAnnotation.view().getName());
            model.setPlaceClassName(presenterAnnotation.place().getName());
            models.add(model);
        }
    }

    @Override
    public boolean isMath(Annotation checkAnnotation) {
        return checkAnnotation != null;
    }

    @Override
    public List<PresenterScanModel> getData() {
        return models;
    }
}
