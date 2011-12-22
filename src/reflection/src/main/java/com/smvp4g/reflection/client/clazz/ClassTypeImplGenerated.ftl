[#ftl]
[#-- @ftlvariable name="data" type="com.smvp4g.reflection.generator.reflection.ClassTypeTemplateData" --]
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
package ${data.generatePackageName};

import com.smvp4g.reflection.client.clazz.*;
import com.smvp4g.reflection.client.annotation.*;
import com.smvp4g.reflection.client.field.*;
import com.smvp4g.reflection.client.method.*;
import com.smvp4g.reflection.client.executor.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ClassTypeImplGenerated.
 *
 * @author Nguyen Duc Dung
 * @since 12/3/11, 9:07 AM
 */
public class ${data.generateClassName} implements ClassType {

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> clazz) {
        [#list data.annotationScanModels as annotation]
        if (clazz == ${annotation.annotationClassName}.class) {
            return (T) new ${annotation.annotationClassName}() {
                [#list annotation.methods as method]
                @Override
                public ${method.returnType} ${method.name}() {
                    return ${method.returnValue};
                }
                [/#list]

                @Override
                public Class<? extends Annotation> annotationType() {
                    return ${annotation.annotationClassName}.class;
                }
            };
        }
        [/#list]
        return null;
    }

    @Override
    public List<FieldType> getFields() {
        List<FieldType> fields = new ArrayList<FieldType>();
        [#list data.fieldScanModels as field]
        FieldTypeImpl field${field_index} = new FieldTypeImpl();
        field${field_index}.setName("${field.name}" );
        field${field_index}.setType(${field.typeClassName}.class);
        [#list field.annotationScanModels as annotation]
        field${field_index}.addAnnotation(
                new ${annotation.annotationClassName}() {
                [#list annotation.methods as method]
                    @Override
                    public ${method.returnType} ${method.name}() {
                        return ${method.returnValue};
                    }
                [/#list]

                @Override
                public Class<? extends Annotation> annotationType() {
                    return ${annotation.annotationClassName}.class;
                }
         });
        [/#list]
        field${field_index}.setGetterExecutor(new Executor() {
            @Override
            public Object execute(Object object, Object... params) {
                return ((${data.className}) object).${field.name};
            }
        });
        field${field_index}.setSetterExecutor(new Executor() {
            @Override
            public Object execute(Object object, Object... params) {
                ((${data.className}) object).${field.name} = (${field.typeClassName})params[0];
                return null;
            }
        });
        fields.add(field${field_index});
        [/#list]
        return fields;
    }
    
    @Override
    public FieldType getField(String fieldName) {
        for (FieldType field : getFields()) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public List<MethodType> getMethods() {
        List<MethodType> methods = new ArrayList<MethodType>();
        [#list data.methodScanModels as method]
        MethodTypeImpl method${method_index} = new MethodTypeImpl();
        method${method_index}.setName("${method.name}" );
        method${method_index}.setReturnType(${method.returnType}.class);
        [#list method.annotationScanModels as annotation]
        method${method_index}.addAnnotation(
                new ${annotation.annotationClassName}() {
                [#list annotation.methods as method]
                @Override
                public ${method.returnType} ${method.name}() {
                    return ${method.returnValue};
                }
                [/#list]

                @Override
                public Class<? extends Annotation> annotationType() {
                    return ${annotation.annotationClassName}.class;
                }
        });
        [/#list]
        method${method_index}.setGetterExecutor(new Executor() {
            @Override
            public Object execute(Object object, Object... params) {
                [#if method.returnType != "void"]
                    return ((${data.className}) object).${method.name}(${method.params});
                [#else]
                    ((${data.className}) object).${method.name}(${method.params});
                    return null;
                [/#if]
            }
        });
        methods.add(method${method_index});
        [/#list]
        return methods;
    }

    @Override
    public MethodType getMethod(String name) {
        for (MethodType method : getMethods()) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }
}
