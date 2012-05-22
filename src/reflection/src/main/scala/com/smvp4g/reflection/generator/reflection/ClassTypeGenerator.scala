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

package com.smvp4g.reflection.generator.reflection

import com.google.gwt.core.ext.GeneratorContext
import com.google.gwt.core.ext.typeinfo.JClassType
import com.smvp4g.generator.AbstractGenerator
import com.smvp4g.generator.scan.ClassScanner
import com.smvp4g.generator.utils.ClassUtils
import com.smvp4g.reflection.client.clazz.ClassType
import com.smvp4g.reflection.client.marker.ReflectionTarget
import collection.mutable.HashMap
import com.smvp4g.reflection.scan.reader.{MethodReader, FieldReader, AnnotationReader}

/**
 * The Class ClassTypeGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 4:34 PM
 *
 */

class ClassTypeGenerator extends AbstractGenerator[ClassTypeTemplateData] {
  protected def templateFileName = "ClassTypeImplGenerated.ftl"

  protected def resourceClass = classOf[ClassType]

  override val filePrefix = "ClassTypeGenerated"

  override protected def scan: Map[String, ClassTypeTemplateData] = {
    val targetClassType = getTargetClassType(context, classType)
    if (targetClassType != null) {
      val dataTemplateMap = new HashMap[String, ClassTypeTemplateData]

      val annotationReader = new AnnotationReader
      val fieldReader = new FieldReader
      val methodReader = new MethodReader
      val scanner = new ClassScanner
      scanner.readers += annotationReader
      scanner.readers += fieldReader
      scanner.readers += methodReader
      scanner.scan(targetClassType, context)

      val classModel = new ClassTypeTemplateData(className, packageName)
      classModel.className = targetClassType.getSimpleSourceName
      classModel.annotationScanModels = annotationReader.data
      classModel.fieldScanModels = fieldReader.data
      classModel.methodScanModels = methodReader.data

      dataTemplateMap.put("data", classModel)
      return dataTemplateMap.toMap
    }
    null
  }

  override protected def packageName: String = {
    getTargetClassType(context, classType).getPackage.getName
  }

  override protected def className: String = {
    getTargetClassType(context, classType).getSimpleSourceName + filePrefix
  }

  private def getTargetClassType(context: GeneratorContext, hostClassType: JClassType): JClassType = {
    val reflectionTarget: ReflectionTarget = hostClassType.getAnnotation(classOf[ReflectionTarget])
    if (reflectionTarget != null) {
      return ClassUtils.getJClassType(reflectionTarget.target.getName, context)
    }
    null
  }
}
