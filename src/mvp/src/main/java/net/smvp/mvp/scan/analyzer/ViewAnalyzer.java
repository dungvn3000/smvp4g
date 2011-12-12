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

package net.smvp.mvp.scan.analyzer;

import com.google.gwt.core.ext.GeneratorContext;
import com.smvp.generator.scan.analyzer.Analyzer;
import com.smvp.generator.utils.ClassUtils;
import net.smvp.mvp.client.core.view.annotation.View;
import net.smvp.mvp.scan.model.PresenterScanModel;
import net.smvp.mvp.scan.reader.PresenterReader;

import java.util.List;

/**
 * The Class ViewAnalyzer.
 *
 * @author Nguyen Duc Dung
 * @since 11/22/11, 5:54 PM
 */
public class ViewAnalyzer implements Analyzer {

    private PresenterReader presenterReader;

    @Override
    public void analyze(GeneratorContext context) {
        List<PresenterScanModel> models = presenterReader.getData();
        for (PresenterScanModel model : models) {
            View view = ClassUtils.getAnnotation(model.getViewClassName(), View.class, context);
            if (view != null) {
                model.setViewParentDomID(view.parentDomId());
            }
        }
    }

    public PresenterReader getPresenterReader() {
        return presenterReader;
    }

    public void setPresenterReader(PresenterReader presenterReader) {
        this.presenterReader = presenterReader;
    }
}
