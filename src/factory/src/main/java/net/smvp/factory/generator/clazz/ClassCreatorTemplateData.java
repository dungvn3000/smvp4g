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

package net.smvp.factory.generator.clazz;

import net.smvp.generator.generator.AbstractTemplateData;
import net.smvp.generator.scan.model.ClassScanModel;

import java.util.List;

/**
 * The Class ClassCreatorTemplateData.
 *
 * @author Nguyen Duc Dung
 * @since 12/13/11, 11:15 AM
 */
public class ClassCreatorTemplateData extends AbstractTemplateData {

    private List<ClassScanModel> classScanModels;

    public ClassCreatorTemplateData(String generateClassName, String generatePackageName) {
        super(generateClassName, generatePackageName);
    }

    public List<ClassScanModel> getClassScanModels() {
        return classScanModels;
    }

    public void setClassScanModels(List<ClassScanModel> classScanModels) {
        this.classScanModels = classScanModels;
    }
}
