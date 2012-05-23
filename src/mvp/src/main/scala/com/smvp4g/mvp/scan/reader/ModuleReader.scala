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

package com.smvp4g.mvp.scan.reader

import com.google.gwt.core.ext.GeneratorContext
import com.google.gwt.core.ext.typeinfo.JClassType
import com.smvp4g.generator.scan.reader.Reader
import com.smvp4g.mvp.client.core.module.annotation.Module
import com.smvp4g.mvp.client.core.utils.StringUtils
import com.smvp4g.mvp.scan.model.ModuleScanModel

/**
 * The Class ModuleReader.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 1:29 PM
 *
 */

class ModuleReader extends Reader[ModuleScanModel] {
  def read(classType: JClassType, context: GeneratorContext) {
    val module = classType.getAnnotation(classOf[Module])
    if (module != null) {
      val model = new ModuleScanModel
      model.modulePackageName = classType.getPackage.getName
      model.className = classType.getQualifiedSourceName
      if (StringUtils.isNotEmpty(module.historyName)) {
        model.token = module.historyName
      } else {
        model.token = StringUtils.getHistoryName(classType.getName)
      }
      _data += model
    }
  }
}
