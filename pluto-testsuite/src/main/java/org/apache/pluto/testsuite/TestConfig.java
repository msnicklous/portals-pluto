/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pluto.testsuite;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TestPortlet Configuration.
 *
 * @author <a href="ddewolf@apache.org">David H. DeWolf</a>
 * @version 1.0
 * @since Sep 15, 2004
 */
public class TestConfig implements Serializable {

    private String testClassName;
    private String name;
    private String plt;
    private String displayURI;
    private Map initParams = new HashMap();
    private Map actionParameters = new HashMap();
    private Map renderParameters = new HashMap();

    public TestConfig() {

    }

    public String getTestClassName() {
        return testClassName;
    }

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String testName) {
        this.name = testName;
    }

    public String getPlt() {
    	return plt;
    }
    
    public void setPlt(String plt) {
    	this.plt = plt;
    }
    
    public String getDisplayURI() {
        return displayURI;
    }

    public void setDisplayURI(String displayURI) {
        this.displayURI = displayURI;
    }

    public void addInitParameter(String parameter, String value) {
        this.initParams.put(parameter, value);
    }

    public Map getInitParameters() {
        return Collections.unmodifiableMap(initParams);
    }

    public void addActionParameter(String parameter, String value) {
        this.actionParameters.put(parameter, value);
    }

    public Map getActionParameters() {
        return Collections.unmodifiableMap(actionParameters);
    }

    public void addRenderParameter(String parameter, String value) {
        this.renderParameters.put(parameter, value);
    }

    public Map getRenderParameters() {
        return Collections.unmodifiableMap(renderParameters);
    }

    public String toString() {
    	StringBuffer buffer = new StringBuffer(super.toString());
    	buffer.append("<").append(getName()).append(">");
    	return buffer.toString();
    }
}
