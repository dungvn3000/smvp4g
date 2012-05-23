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

package com.smvp4g.generator

import _root_.java.io.StringWriter
import _root_.java.util.HashMap
import com.google.gwt.core.ext.typeinfo.JClassType
import com.google.gwt.core.ext.{GeneratorContext, TreeLogger, Generator}
import freemarker.template.{Configuration, Template}
import freemarker.cache.URLTemplateLoader
import collection.JavaConverters._

/**
 * The Class AbstractGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 1:36 PM
 *
 */

abstract class AbstractGenerator[M <: AbstractTemplateData] extends Generator {

  val filePrefix = "Generated"

  protected var context: GeneratorContext = _
  protected var classType: JClassType = _

  def generate(logger: TreeLogger, context: GeneratorContext, typeName: String): String = {
    try {
      this.context = context
      classType = context.getTypeOracle.getType(typeName)
      val sourceWriter = context.tryCreate(logger, packageName, className)
      if (sourceWriter != null) {
        val templateWriter = new StringWriter()
        createTemplate.process(scan.asJava, templateWriter)
        sourceWriter.print(templateWriter.toString)
        context.commit(logger, sourceWriter)
      }
      return packageName + "." + className
    } catch {
      case e: Exception => {
        e.printStackTrace
        logger.log(TreeLogger.Type.ERROR, e.getMessage)
      }
    }
    null
  }

  private def createTemplate: Template = {
    val configuration = new Configuration()
    configuration.setTemplateLoader(new URLTemplateLoader() {
      override def getURL(url: String) = resourceClass.getResource(templateFileName)
    })
    try {
      return configuration.getTemplate(templateFileName)
    } catch {
      case e: Exception => e.printStackTrace()
    }
    null
  }

  protected def packageName: String = {
    if (classType != null) {
      return classType.getPackage.getName
    }
    null
  }

  protected def className: String = {
    if (classType != null) {
      return classType.getSimpleSourceName + filePrefix
    }
    null
  }

  protected def templateFileName: String

  protected def resourceClass: Class[_]

  protected def scan: Map[String, M]

}
