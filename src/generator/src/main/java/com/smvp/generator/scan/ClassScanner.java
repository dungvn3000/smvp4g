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

package com.smvp.generator.scan;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.smvp.generator.scan.analyzer.Analyzer;
import com.smvp.generator.scan.reader.Reader;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ClassScanner.
 *
 * @author Nguyen Duc Dung
 * @since 11/20/11, 2:27 PM
 */
public class ClassScanner {

    private List<Reader> readers = new ArrayList<Reader>();
    private List<Analyzer> analyzers = new ArrayList<Analyzer>();

    public void scan(JClassType classType, GeneratorContext context) {
        for (Reader reader : readers) {
            reader.read(classType, context);
        }
    }

    public void analyzeResult(GeneratorContext context) {
        for (Analyzer analyzer : analyzers) {
            analyzer.analyze(context);
        }
    }

    public void addAnalyzer(Analyzer analyzer) {
        analyzers.add(analyzer);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }
}
