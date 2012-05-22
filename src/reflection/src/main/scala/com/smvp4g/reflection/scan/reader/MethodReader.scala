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

package com.smvp4g.reflection.scan.reader

import com.google.gwt.core.ext.GeneratorContext
import com.google.gwt.core.ext.typeinfo.JClassType
import com.smvp4g.generator.scan.model.MethodScanModel
import com.smvp4g.generator.scan.reader.Reader
import com.smvp4g.generator.scan.utils.ScanUtils
import com.google.gwt.core.ext.typeinfo.JPrimitiveType
import org.apache.commons.lang.StringUtils

/**
 * The Class MethodReader.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 4:21 PM
 *
 */

class MethodReader extends Reader[MethodScanModel] {
  def read(classType: JClassType, context: GeneratorContext) {
    classType.getMethods.foreach(method => {
      if (method.isPublic || method.isDefaultAccess) {
        val model = new MethodScanModel()
        model.setName(method.getName)
        if (method.getReturnType.isPrimitive != null) {
          val returnType = method.getReturnType.isPrimitive
          model.setReturnType(returnType.getQualifiedBoxedSourceName)
          if (returnType == JPrimitiveType.VOID) {
            model.setVoidMethod(true)
          }
        } else {
          model.setReturnType(method.getReturnType.getQualifiedSourceName)
        }
        model.setAnnotationScanModels(ScanUtils.getAnnotations(method))
        model.setPramsLength(method.getParameters.length)
        var params = StringUtils.EMPTY
        var i = 0
        method.getParameters.foreach(parameter => {
          if (i > 0) {
            params = params + ","
          }
          params += "("
          if (parameter.getType.isPrimitive == null) {
            params += parameter.getType.
              getQualifiedSourceName
          } else {
            params += parameter.getType.isPrimitive.
              getQualifiedBoxedSourceName
          }
          params += ")params[" + i + "]"
          i += 1
        })
        model.setParams(params)
        _data += model
      }
    })
  }
}
