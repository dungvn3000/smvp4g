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

package com.smvp4g.mvp.scan.analyzer;

import com.google.gwt.core.ext.GeneratorContext;
import com.smvp4g.generator.scan.analyzer.Analyzer;
import com.smvp4g.mvp.scan.model.ModuleScanModel;
import com.smvp4g.mvp.scan.model.PresenterScanModel;
import com.smvp4g.mvp.scan.reader.ModuleReader;
import com.smvp4g.mvp.scan.reader.PresenterReader;

import java.util.List;

/**
 * The Class PresenterAnalyzer.
 *
 * @author Nguyen Duc Dung
 * @since 12/23/11, 8:21 AM
 */
public class PresenterAnalyzer implements Analyzer {

    private PresenterReader presenterReader;
    private ModuleReader moduleReader;

    public PresenterAnalyzer(PresenterReader presenterReader, ModuleReader moduleReader) {
        this.presenterReader = presenterReader;
        this.moduleReader = moduleReader;
    }
    
    @Override
    public void analyze(GeneratorContext context) {
        List<PresenterScanModel> presenters = presenterReader.getData();
        List<ModuleScanModel> modules = moduleReader.getData();
        for (PresenterScanModel presenter : presenters) {
            for (ModuleScanModel module : modules) {
                if (presenter.getPresenterClassName().contains(module.getModulePackageName())) {
                    presenter.setModuleClassName(module.getClassName());
                }
            }
        }
    }

    public void setModuleReader(ModuleReader moduleReader) {
        this.moduleReader = moduleReader;
    }

    public void setPresenterReader(PresenterReader presenterReader) {
        this.presenterReader = presenterReader;
    }
}
