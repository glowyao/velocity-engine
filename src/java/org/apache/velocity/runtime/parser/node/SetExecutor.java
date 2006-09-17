package org.apache.velocity.runtime.parser.node;

/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.velocity.runtime.log.Log;

/**
 * Abstract class that is used to execute an arbitrary
 * method that is in introspected. This is the superclass
 * for the PutExecutor and SetPropertyExecutor.
 *
 * There really should be a superclass for this and AbstractExecutor (which should
 * be refactored to GetExecutor) because they differ only in the execute() method.
 *
 * @author <a href="mailto:jvanzyl@apache.org">Jason van Zyl</a>
 * @author <a href="mailto:geirm@apache.org">Geir Magnusson Jr.</a>
 * @author <a href="mailto:henning@apache.org">Henning P. Schmiedehausen</a>
 * @version $Id$
 */
public abstract class SetExecutor
{
    /** Class logger */
    protected Log log = null;
    
    /**
     * Method to be executed.
     */
    private Method method = null;
    
    /**
     * Execute method against context.
     * @param o 
     * @param value 
     * @return The result of the invocation.
     * @throws IllegalAccessException 
     * @throws InvocationTargetException 
     */
    public abstract Object execute(Object o, Object value)
         throws IllegalAccessException, InvocationTargetException;

    /**
     * Tell whether the executor is alive by looking
     * at the value of the method.
     * @return True if the executor is alive.
     */
    public boolean isAlive()
    {
        return (method != null);
    }

    /**
     * @return The method to invoke.
     */
    public Method getMethod()
    {
        return method;
    }

    /**
     * @param method
     */
    protected void setMethod(final Method method)
    {
        this.method = method;
    }
}
