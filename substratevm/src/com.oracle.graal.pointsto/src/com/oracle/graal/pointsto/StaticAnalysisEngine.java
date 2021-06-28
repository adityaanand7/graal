/*
 * Copyright (c) 2021, 2021, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.graal.pointsto;

import com.oracle.graal.pointsto.api.HostVM;
import com.oracle.graal.pointsto.constraints.UnsupportedFeatures;
import com.oracle.graal.pointsto.meta.AnalysisField;
import com.oracle.graal.pointsto.meta.AnalysisMetaAccess;
import com.oracle.graal.pointsto.meta.AnalysisMethod;
import com.oracle.graal.pointsto.meta.AnalysisType;
import com.oracle.graal.pointsto.meta.AnalysisUniverse;
import com.oracle.graal.pointsto.meta.HostedProviders;
import com.oracle.graal.pointsto.typestate.TypeState;
import com.oracle.graal.pointsto.util.Timer;
import jdk.vm.ci.meta.ConstantReflectionProvider;
import org.graalvm.compiler.api.replacements.SnippetReflectionProvider;
import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.debug.DebugHandlersFactory;
import org.graalvm.compiler.options.OptionValues;

import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.util.List;

public interface StaticAnalysisEngine {
    AnalysisMetaAccess getMetaAccess();

    TypeState getAllSynchronizedTypeState();

    HostVM getHostVM();

    UnsupportedFeatures getUnsupportedFeatures();

    void cleanupAfterAnalysis();

    boolean finish() throws InterruptedException;

    void checkUserLimitations();

    AnalysisType addSystemClass(Class<?> clazz, boolean addFields, boolean addArrayClass);

    AnalysisType addSystemField(Class<?> clazz, String fieldName);

    AnalysisMethod addRootMethod(Executable method);

    AnalysisUniverse getUniverse();

    void addSystemMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes);

    OptionValues getOptions();

    HostedProviders getProviders();

    AnalysisPolicy analysisPolicy();

    boolean reportAnalysisStatistics();

    List<DebugHandlersFactory> getDebugHandlerFactories();

    void forceUnsafeUpdate(AnalysisField field);

    AnalysisMethod addRootMethod(AnalysisMethod aMethod);

    Timer getAnalysisTimer();

    Timer getProcessFeaturesTimer();

    void printTimers();

    void printTimerStatistics(PrintWriter out);

    AnalysisType[] skippedHeapTypes();

    ConstantReflectionProvider getConstantReflectionProvider();

    SnippetReflectionProvider getSnippetReflectionProvider();

    HeapScanningPolicy scanningPolicy();

    DebugContext getDebug();
}
