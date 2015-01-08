package org.jolokia.request;

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

import org.jolokia.config.ProcessingParameters;
import org.jolokia.util.RequestType;

import javax.management.MalformedObjectNameException;
import java.util.Map;
import java.util.Stack;

/**
 * A JMX request for a <code>health</code> operation, i.e. for performing health checks
 */
public class JmxHealthRequest extends JmxObjectNameRequest {

    /**
     * Constructor for GET requests.
     *
     * @param pObjectName object name pattern to search for, which must not be null.
     * @param pParams optional processing parameters
     * @throws javax.management.MalformedObjectNameException if the name is not a proper object name
     */
    JmxHealthRequest(String pObjectName, ProcessingParameters pParams) throws MalformedObjectNameException {
        super(RequestType.HEALTH, pObjectName, null, pParams);
    }

    /**
     * Constructor for POST requests
     *
     * @param pRequestMap object representation of the request
     * @param pParams processing parameters
     * @throws javax.management.MalformedObjectNameException if the name is not a proper object name
     */
    JmxHealthRequest(Map<String, ?> pRequestMap, ProcessingParameters pParams) throws MalformedObjectNameException {
        super(pRequestMap, pParams);
    }


    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer("JmxHealthRequest[");
        String baseInfo = getInfo();
        if (baseInfo != null) {
            ret.append(baseInfo);
        }
        ret.append("]");
        return ret.toString();
    }

    // ===========================================================================================

    /**
     * Creator for {@link org.jolokia.request.JmxHealthRequest}s
     *
     * @return the creator implementation
     */
    static RequestCreator<JmxHealthRequest> newCreator() {
        return new RequestCreator<JmxHealthRequest>() {
            /** {@inheritDoc} */
            public JmxHealthRequest create(Stack<String> pStack, ProcessingParameters pParams) throws MalformedObjectNameException {
                return new JmxHealthRequest(pStack.pop(),pParams);
            }

            /** {@inheritDoc} */
            public JmxHealthRequest create(Map<String, ?> requestMap, ProcessingParameters pParams)
                    throws MalformedObjectNameException {
                return new JmxHealthRequest(requestMap,pParams);
            }
        };
    }
}
