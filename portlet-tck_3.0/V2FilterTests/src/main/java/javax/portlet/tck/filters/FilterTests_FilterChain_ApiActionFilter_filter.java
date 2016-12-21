/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */


package javax.portlet.tck.filters;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;

/**
 * Filter for JSR 362 request dispatcher testing. Used by portlet:
 * FilterTests_FilterChain_ApiActionFilter
 *
 * @author ahmed
 *
 */
public class FilterTests_FilterChain_ApiActionFilter_filter implements ActionFilter {

  public static boolean tr0_success = false;


  @Override
  public void init(FilterConfig filterConfig) throws PortletException {}

  @Override
  public void destroy() {}

  @Override
  public void doFilter(ActionRequest portletReq, ActionResponse portletResp, FilterChain chain)
      throws IOException, PortletException {

    /* TestCase: V2FilterTests_FilterChain_ApiActionFilter_invokeActionFilter */
    /* Details: "Invoking doFilter(ActionRequest, ActionResponse): causes */
    /* next filter to be invoked" */
    FilterTests_FilterChain_ApiActionFilter_filter.tr0_success = true;

    chain.doFilter(portletReq, portletResp);
  }
}
