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

package com.smvp4g.aop.scan.reader

import com.google.gwt.core.ext.GeneratorContext
import com.google.gwt.core.ext.typeinfo.JClassType
import com.smvp4g.aop.client.marker.Aspectable
import com.smvp4g.aop.scan.model.ClassWrapperModel
import com.smvp4g.generator.scan.reader.Reader
import com.smvp4g.generator.utils.ClassUtils

/**
 * The Class ClassWrapperReader.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 3:12 PM
 *
 */

class ClassWrapperReader extends Reader[ClassWrapperModel] {

  def read(classType: JClassType, context: GeneratorContext) {
    val aspectable = ClassUtils.getJClassType(classOf[Aspectable].getName, context)
    if (classType.isAssignableTo(aspectable)) {
        val model = new ClassWrapperModel
        model.setClassName(classType.getSimpleSourceName)
        model.setPackageName(classType.getPackage.getName)
        model.setRealClassName(classType.getQualifiedSourceName)
      _data += model
    }
  }

}
