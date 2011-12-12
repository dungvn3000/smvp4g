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

package net.smvp.mvp.generator.factory;

import net.smvp.generator.generator.AbstractGenerator;
import net.smvp.generator.scan.ClassScanner;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import net.smvp.mvp.client.core.factory.ClientFactoryImpl;
import net.smvp.mvp.scan.analyzer.TokenAnalyzer;
import net.smvp.mvp.scan.analyzer.ViewAnalyzer;
import net.smvp.mvp.scan.reader.ModuleReader;
import net.smvp.mvp.scan.reader.PresenterReader;

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
        TokenAnalyzer tokenAnalyzer = new TokenAnalyzer();
        tokenAnalyzer.setModuleReader(moduleReader);
        tokenAnalyzer.setPresenterReader(presenterReader);
        ViewAnalyzer viewAnalyzer = new ViewAnalyzer();
        viewAnalyzer.setPresenterReader(presenterReader);

        ClassScanner scanner = new ClassScanner();
        scanner.addReader(presenterReader);
        scanner.addReader(moduleReader);
        scanner.addAnalyzer(tokenAnalyzer);
        scanner.addAnalyzer(viewAnalyzer);
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
