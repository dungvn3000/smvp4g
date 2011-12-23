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

package com.smvp4g.mvp.generator.factory;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.smvp4g.generator.generator.AbstractGenerator;
import com.smvp4g.generator.scan.ClassScanner;
import com.smvp4g.mvp.client.core.factory.ClientFactoryImpl;
import com.smvp4g.mvp.scan.analyzer.PresenterAnalyzer;
import com.smvp4g.mvp.scan.analyzer.TokenAnalyzer;
import com.smvp4g.mvp.scan.reader.ModuleReader;
import com.smvp4g.mvp.scan.reader.PresenterReader;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class ClientFactoryGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 11/17/11, 5:16 PM
 */
public class ClientFactoryGenerator extends AbstractGenerator<ClientFactoryTemplateData> {

    private static final String TEMPLATE_FILE = "ClientFactoryImplGenerated.ftl";
    private static final String DATA_TEMPLATE = "data";

    @Override
    protected Map<String, ClientFactoryTemplateData> scan() {
        PresenterReader presenterReader = new PresenterReader();
        ModuleReader moduleReader = new ModuleReader();
        TokenAnalyzer tokenAnalyzer = new TokenAnalyzer(presenterReader, moduleReader);
        PresenterAnalyzer presenterAnalyzer = new PresenterAnalyzer(presenterReader, moduleReader);

        ClassScanner scanner = new ClassScanner();
        scanner.addReader(presenterReader);
        scanner.addReader(moduleReader);
        scanner.addAnalyzer(tokenAnalyzer);
        scanner.addAnalyzer(presenterAnalyzer);
        TypeOracle typeOracle = context.getTypeOracle();
        for (JPackage jPackage : typeOracle.getPackages()) {
            for (JClassType jClassType : jPackage.getTypes()) {
                scanner.scan(jClassType, context);
            }
        }
        scanner.analyzeResult(context);
        Map<String, ClientFactoryTemplateData> dataTemplateMap = new HashMap<String, ClientFactoryTemplateData>();
        ClientFactoryTemplateData data = new ClientFactoryTemplateData(getClassName(), getPackageName());
        data.setPresenterScanModels(presenterReader.getData());
        dataTemplateMap.put(DATA_TEMPLATE, data);
        return dataTemplateMap;
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILE;
    }

    @Override
    protected Class<?> getResourceClass() {
        return ClientFactoryImpl.class;
    }

}
