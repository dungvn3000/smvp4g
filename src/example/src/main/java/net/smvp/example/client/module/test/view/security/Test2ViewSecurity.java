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

package net.smvp.example.client.module.test.view.security;

import net.smvp.example.client.constant.UserRoleEnum;
import net.smvp.mvp.client.core.security.HasRole;
import net.smvp.mvp.client.core.security.ViewSecurityConfigurator;

/**
 * The Class Test2ViewSecurity.
 *
 * @author Nguyen Duc Dung
 * @since 12/4/11, 5:25 PM
 */
public class Test2ViewSecurity implements ViewSecurityConfigurator {
    @Override
    public HasRole[] getRoles() {
        return new HasRole[0];
    }

    public HasRole[] button1() {
        return new HasRole[]{UserRoleEnum.USER};
    }
}
