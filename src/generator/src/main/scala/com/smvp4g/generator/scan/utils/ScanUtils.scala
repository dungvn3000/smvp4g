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

package com.smvp4g.generator.scan.utils

import com.google.gwt.core.ext.typeinfo.HasAnnotations
import collection.mutable.ListBuffer
import com.smvp4g.generator.scan.model.{MethodScanModel, AnnotationScanModel}
import com.smvp4g.generator.utils.ClassUtils

/**
 * The Class ScanUtils.
 *
 * @author Nguyen Duc Dung
 * @since 5/23/12, 12:26 AM
 *
 */

object ScanUtils {

  def getAnnotations(target: HasAnnotations): List[AnnotationScanModel] = {
    val models = new ListBuffer[AnnotationScanModel]
    target.getAnnotations.foreach(annotation => {
      val annotationScanModel = new AnnotationScanModel()
      annotationScanModel.setAnnotationClassName(annotation.annotationType().getName)
      annotation.annotationType.getMethods.foreach(method => {
        if (method.getDeclaringClass == annotation.annotationType()) {
          val methodScanModel = new MethodScanModel
          methodScanModel.setName(method.getName)
          methodScanModel.setReturnType(method.getReturnType.getName)
          methodScanModel.setReturnValue(buildReturnValueString(ClassUtils.
            invokeMethod(method, annotation)))
          annotationScanModel.methods.add(methodScanModel)
        }
      })
      models += annotationScanModel
    })
    models.toList
  }

  def buildReturnValueString(returnValue: AnyRef): String = {
    if (returnValue.isInstanceOf[Class[_]]) {
      val clazz = returnValue.asInstanceOf[Class[_]]
      return clazz.getName + ".class"
    } else if (returnValue.isInstanceOf[String]) {
      val st = returnValue.asInstanceOf[String]
      return "\"" + st + "\""
    }
    returnValue.toString
  }

}
