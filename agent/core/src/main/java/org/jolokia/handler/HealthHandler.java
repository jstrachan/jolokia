package org.jolokia.handler;

import org.jolokia.backend.executor.MBeanServerExecutor;
import org.jolokia.backend.executor.NotChangedException;
import org.jolokia.request.JmxHealthRequest;
import org.jolokia.restrictor.Restrictor;
import org.jolokia.util.RequestType;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/*
 * Copyright 2009-2013 Roland Huss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Handler responsible for performing health checks
 */
public class HealthHandler extends JsonRequestHandler<JmxHealthRequest> {

    /**
     * Create health handler
     *
     * @param pRestrictor access restriction to apply
     */
    public HealthHandler(Restrictor pRestrictor) {
        super(pRestrictor);
    }

    /** {@inheritDoc} */
    @Override
    public RequestType getType() {
        return RequestType.HEALTH;
    }

    /** {@inheritDoc} */
    @Override
    protected void checkForRestriction(JmxHealthRequest pRequest) {
        checkType();
    }

    /** {@inheritDoc}
     * @param serverManager
     * @param request*/
    @Override
    @SuppressWarnings("PMD.ReplaceHashtableWithMap")
    public Object doHandleRequest(MBeanServerExecutor serverManager, JmxHealthRequest request)
            throws MBeanException, IOException, NotChangedException {
        List<String> answer = new ArrayList<String>();
        // lets load all of the check to perform
        return answer;
    }

    /** {@inheritDoc} */
    @Override
    public boolean handleAllServersAtOnce(JmxHealthRequest pRequest) {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    protected Object doHandleRequest(MBeanServerConnection server, JmxHealthRequest request) throws InstanceNotFoundException, AttributeNotFoundException, ReflectionException, MBeanException, IOException {
        throw new UnsupportedOperationException("Internal: Method must not be called when all MBeanServers are handled at once");
    }
}
