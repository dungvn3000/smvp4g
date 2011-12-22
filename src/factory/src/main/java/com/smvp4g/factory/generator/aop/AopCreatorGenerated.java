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

package com.smvp4g.factory.generator.aop;

import com.smvp4g.aop.scan.reader.AspectableClassReader;
import com.smvp4g.aop.scan.reader.InterceptorReader;
import com.smvp4g.factory.client.aop.AopCreator;
import com.smvp4g.generator.generator.AbstractGenerator;
import com.smvp4g.generator.scan.ClassScanner;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class AopCreatorGenerated.
 *
 * @author Nguyen Duc Dung
 * @since 12/9/11, 11:50 AM
 */
public class AopCreatorGenerated extends AbstractGenerator<AopCreatorTemplateData> {
    
    private static final String TEMPLATE_FILE = "AopCreatorGenerated.ftl";
    private static final String DATA_TEMPLATE = "data";
    
    @Override
    protected Map<String, AopCreatorTemplateData> scan() {
        Map<String, AopCreatorTemplateData> data = new HashMap<String, AopCreatorTemplateData>();

        ClassScanner scanner = new ClassScanner();
        AspectableClassReader aspectableClassReader = new AspectableClassReader();
        scanner.addReader(aspectableClassReader);
        InterceptorReader interceptorReader = new InterceptorReader();
        scanner.addReader(interceptorReader);
        scanner.scan(classType, context);

        AopCreatorTemplateData templateData = new AopCreatorTemplateData(getClassName(), getPackageName());
        templateData.setClassScanModels(aspectableClassReader.getData());
        templateData.setInterceptorClassModels(interceptorReader.getData());
        data.put(DATA_TEMPLATE, templateData);
        return data;
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILE;
    }

    @Override
    protected Class<?> getResourceClass() {
        return AopCreator.class;
    }
}
