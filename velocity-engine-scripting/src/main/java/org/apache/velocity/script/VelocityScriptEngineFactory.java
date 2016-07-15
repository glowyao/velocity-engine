package org.apache.velocity.script;

/* dual licencing... */

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved. 
 * Use is subject to license terms.
 *
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met: Redistributions of source code 
 * must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of 
 * conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution. Neither the name of the Sun Microsystems nor the names of 
 * is contributors may be used to endorse or promote products derived from this software 
 * without specific prior written permission. 

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Factory class for the Velocity scripting interface. Please refer to the
 * javax.script.ScriptEngineFactory documentation for details.
 *
 * @author A. Sundararajan
 * @author <a href="mailto:claude.brisson@gmail.com">Claude Brisson</a>
 * @version $Id: VelocityScriptEngineFactory.java$
 */

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class VelocityScriptEngineFactory implements ScriptEngineFactory
{

    private static final String VELOCITY_NAME = "Velocity";
    private static final String VELOCITY_VERSION = "2.0";
    private static final String VELOCITY_LANGUAGE = "VTL";

    private static List<String> names;
    private static List<String> extensions;
    private static List<String> mimeTypes;

    private static Properties parameters;
    
    static
    {
        names = new ArrayList();
        names.add("velocity");
        names.add("Velocity");
        names = Collections.unmodifiableList(names);
        extensions = new ArrayList();
        extensions.add("vm");
        extensions.add("vtl");
        extensions.add("vhtml");
        extensions = Collections.unmodifiableList(extensions);
        mimeTypes = new ArrayList();
        mimeTypes.add("text/x-velocity");
        mimeTypes = Collections.unmodifiableList(mimeTypes);
        parameters = new Properties();
        parameters.put(ScriptEngine.NAME, VELOCITY_NAME);
        parameters.put(ScriptEngine.ENGINE_VERSION, VELOCITY_VERSION);
        parameters.put(ScriptEngine.ENGINE, VELOCITY_NAME);
        parameters.put(ScriptEngine.LANGUAGE, VELOCITY_LANGUAGE);
        parameters.put(ScriptEngine.LANGUAGE_VERSION, VELOCITY_VERSION);
        parameters.put("THREADING", "MULTITHREADED");
    }
    
    public String getEngineName()
    { 
        return VELOCITY_NAME;
    }

    public String getEngineVersion()
    {
        return VELOCITY_VERSION;
    }

    public List<String> getExtensions()
    {
        return extensions;
    }

    public String getLanguageName()
    {
        return VELOCITY_NAME;
    }

    public String getLanguageVersion()
    {
        return VELOCITY_VERSION;
    }

    public String getMethodCallSyntax(String obj, String m, String... args)
    {
        StringBuilder buf = new StringBuilder();
        buf.append("${");
        buf.append(obj);
        buf.append(".");
        buf.append(m);
        buf.append("(");
        if (args.length != 0)
        {
            int i = 0;
            for (; i < args.length - 1; i++)
            {
                buf.append("$" + args[i]);
                buf.append(", ");
            }
            buf.append("$" + args[i]);
        }        
        buf.append(")}");
        return buf.toString();
    }

    public List<String> getMimeTypes()
    {
        return mimeTypes;
    }

    public List<String> getNames()
    {
        return names;
    }

    public String getOutputStatement(String toDisplay)
    {
        StringBuilder buf = new StringBuilder();
        buf.append("#[[").append(toDisplay).append("]]#");
        return buf.toString();
    }

    public String getParameter(String key)
    {
        return parameters.getProperty(key);
    } 

    public String getProgram(String... statements)
    {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < statements.length; i++)
        {
            buf.append(statements[i]);
            buf.append("\n");
        }
        return buf.toString();
    }

    public ScriptEngine getScriptEngine()
    {
        return new VelocityScriptEngine(this);
    }
}
