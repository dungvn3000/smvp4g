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

package com.smvp4g.factory.scan.reader

import com.google.gwt.core.ext.GeneratorContext
import com.google.gwt.core.ext.typeinfo.JClassType
import com.smvp4g.aop.client.marker.Aspectable
import com.smvp4g.generator.scan.model.ClassScanModel
import com.smvp4g.generator.scan.reader.Reader
import com.smvp4g.generator.utils.ClassUtils
import com.smvp4g.reflection.client.marker.Reflection

/**
 * The Class ClassReflectionReader.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 6:20 PM
 *
 */

class ClassReflectionReader extends Reader[ClassScanModel] {
  def read(classType: JClassType, context: GeneratorContext) {
    val reflection = classType.getAnnotation(classOf[Reflection])
    if (reflection != null) {
      classType.getSubtypes.foreach(subType => {
        val model = new ClassScanModel
        model.setClassName(subType.getQualifiedSourceName)
        model.setSimpleClassName(subType.getSimpleSourceName)
        val aspectable = ClassUtils.getJClassType(classOf[Aspectable].getName, context)
        if (subType.isAssignableTo(aspectable)) {
          model.setAspectable(true)
        }
        model.setClassLiteral(reflection.isClassLiteral)
        _data += model
      })
    }
  }
}
