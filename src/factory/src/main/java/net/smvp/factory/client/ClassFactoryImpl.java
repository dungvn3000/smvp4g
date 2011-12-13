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

package net.smvp.factory.client;

import com.google.gwt.core.client.GWT;
import net.smvp.factory.client.aop.AopCreator;
import net.smvp.factory.client.classtype.ClassTypeCreator;
import net.smvp.factory.client.creator.Creator;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ClassFactoryImpl.
 *
 * @author Nguyen Duc Dung
 * @since 11/24/11, 5:55 PM
 */
public class ClassFactoryImpl implements ClassFactory {

    protected List<Creator> creators = new ArrayList<Creator>();
    protected Creator aopCreator = GWT.create(AopCreator.class);
    protected Creator classTypeCreator = GWT.create(ClassTypeCreator.class);

    public ClassFactoryImpl() {
        creators.add(aopCreator);
        creators.add(classTypeCreator);
    }
    
    @Override
    public <T> T instantiate(Class<T> clazz) {
        for (Creator creator : creators) {
            T obj = creator.create(clazz);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }
}
