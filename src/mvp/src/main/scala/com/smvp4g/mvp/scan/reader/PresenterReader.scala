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
import com.smvp4g.mvp.client.core.presenter.annotation.Presenter
import com.smvp4g.mvp.scan.model.PresenterScanModel

/**
 * The Class PresenterReader.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 9:53 PM
 *
 */

class PresenterReader extends Reader[PresenterScanModel] {
  def read(classType: JClassType, context: GeneratorContext) {
    val presenterAnnotation = classType.getAnnotation(classOf[Presenter])
    if (presenterAnnotation != null) {
      val model = new PresenterScanModel
      model.presenterClassName = (classType.getQualifiedSourceName)
      model.viewClassName = (presenterAnnotation.view().getName)
      model.placeClassName = (presenterAnnotation.place().getName)
      _data += model
    }
}
}
