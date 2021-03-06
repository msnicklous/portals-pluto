/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package javax.portlet.tck.portlets;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import static java.util.logging.Logger.*;
import javax.xml.namespace.QName;
import javax.portlet.*;
import javax.portlet.filter.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.portlet.tck.beans.*;
import javax.portlet.tck.constants.*;
import static javax.portlet.tck.beans.JSR286DispatcherReqRespTestCaseDetails.*;
import static javax.portlet.tck.constants.Constants.*;
import static javax.portlet.PortletSession.*;
import static javax.portlet.ResourceURL.*;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names
 * are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the 
 * additionalTCs.xml file into the complete list of test case names for execution by the driver.
 *
 * This is the main portlet for the test cases. If the test cases call for events, this portlet
 * will initiate the events, but not process them. The processing is done in the companion 
 * portlet DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_event
 *
 */
public class DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest implements Portlet, ResourceServingPortlet {
   private static final String LOG_CLASS = 
         DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest.class.getName();
   private final Logger LOGGER = Logger.getLogger(LOG_CLASS);
   
   private PortletConfig portletConfig = null;

   @Override
   public void init(PortletConfig config) throws PortletException {
      this.portletConfig = config;
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet processAction entry");

      portletResp.setRenderParameters(portletReq.getParameterMap());
      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      StringWriter writer = new StringWriter();

      // Prereq for: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAttribute
      portletReq.setAttribute(ATTR_PREFIX +"V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAttribute", "Value1");

      // Prereq for: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAttributeNames
      portletReq.setAttribute(ATTR_PREFIX + "V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAttributeNames", "Value1");

      // Now do the actual dispatch
      String target = SERVLET_PREFIX + "DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_servlet" + SERVLET_SUFFIX + "?" + QUERY_STRING;
      PortletRequestDispatcher rd = portletConfig.getPortletContext()
            .getRequestDispatcher(target);
      rd.include(portletReq, portletResp);
   }

   @Override
   public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet serveResource entry");

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      PrintWriter writer = portletResp.getWriter();

   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet render entry");

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      PrintWriter writer = portletResp.getWriter();

      PortletSession ps = portletReq.getPortletSession();
      String msg = (String) ps.getAttribute(RESULT_ATTR_PREFIX + "DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest", APPLICATION_SCOPE);
      if (msg != null) {
         writer.write("<p>" + msg + "</p><br/>\n");
         ps.removeAttribute(RESULT_ATTR_PREFIX + "DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest", APPLICATION_SCOPE);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAttribute */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getAttribute must provide the same     */
      /* functionality as ActionRequest.getAttribute"                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAttribute", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAttributeNames */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getAttributeNames must provide the     */
      /* same functionality as ActionRequest.getAttributeNames"               */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAttributeNames", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAuthType */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getAuthType must provide the same      */
      /* functionality as ActionRequest.getAuthType"                          */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getAuthType", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getCharacterEncoding */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getCharacterEncoding must provide      */
      /* the same functionality as ActionRequest.getCharacterEncoding"        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getCharacterEncoding", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getContentLength */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getContentLength must provide the      */
      /* same functionality as ActionRequest.getContentLength"                */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getContentLength", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getContentType */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getContentType must provide the same   */
      /* functionality as ActionRequest.getContentType"                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getContentType", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getContextPath */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getContextPath must provide the same   */
      /* functionality as ActionRequest.getContextPath"                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getContextPath", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getCookies */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getCookies must provide the same       */
      /* functionality as ActionRequest.getCookies"                           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getCookies", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getDateHeader */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getDateHeader must return a value      */
      /* corresponding to that returned by ActionRequest.getProperty for      */
      /* the corresponding header name"                                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getDateHeader", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getHeader */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getHeader must return a value          */
      /* corresponding to that returned by ActionRequest.getProperty for      */
      /* the corresponding header name"                                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getHeader", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getHeaderNames */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the Enumeration returned by HttpServletRequest.getHeaderNames must   */
      /* be contained in the Enumeration returned by                          */
      /* ActionRequest.getPropertyNames"                                      */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getHeaderNames", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getHeaders */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getHeaders must return a value         */
      /* corresponding to that returned by ActionRequest.getProperties for    */
      /* the corresponding header name"                                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getHeaders", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getIntHeader */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getIntHeader must return a value       */
      /* corresponding to that returned by ActionRequest.getProperty for      */
      /* the corresponding header name"                                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getIntHeader", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocalAddr */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getLocalAddr must return null"         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocalAddr", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocalName */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getLocalName must return null"         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocalName", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocalPort */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getLocalPort must return 0"            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocalPort", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocale */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getLocale must provide the same        */
      /* functionality as ActionRequest.getLocale"                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocale", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocales */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getLocales must provide the same       */
      /* functionality as ActionRequest.getLocales"                           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getLocales", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getMethod */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getMethod must provide the same        */
      /* functionality as ActionRequest.getMethod"                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getMethod", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameter */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getParameter must provide the same     */
      /* functionality as ActionRequest.getParameter"                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameter", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterMap */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getParameterMap must provide the       */
      /* same functionality as ActionRequest.getParameterMap"                 */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterMap", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterNames */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getParameterNames must provide the     */
      /* same functionality as ActionRequest.getParameterNames"               */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterNames", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterValues */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getParameterValues must provide the    */
      /* same functionality as ActionRequest.getParameterValues"              */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterValues", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getPathInfo */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getPathInfo must a value               */
      /* corresponding to the path used to obtain the                         */
      /* PortletRequestDispatcher"                                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getPathInfo", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getPathTranslated */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getPathTranslated must a value         */
      /* corresponding to the path used to obtain the                         */
      /* PortletRequestDispatcher"                                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getPathTranslated", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getProtocol */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getProtocol must return                */
      /* \"HTTP/1.1\""                                                        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getProtocol", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getQueryString */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getQueryString must a value            */
      /* corresponding to the query string information used to obtain the     */
      /* PortletRequestDispatcher"                                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getQueryString", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRealPath */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRealPath must return null"          */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRealPath", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRemoteAddr */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRemoteAddr must return null"        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRemoteAddr", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRemoteHost */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRemoteHost must return null"        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRemoteHost", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRemotePort */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRemotePort must return 0"           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRemotePort", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRemoteUser */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRemoteUser must provide the same    */
      /* functionality as ActionRequest.getRemoteUser"                        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRemoteUser", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRequestDispatcher */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRequestDispatcher must provide      */
      /* functionality as defined in the servlet specification"               */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRequestDispatcher", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRequestURI */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRequestURI must a value             */
      /* corresponding to the path and query string information used to       */
      /* obtain the PortletRequestDispatcher"                                 */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRequestURI", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRequestURL */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRequestURL must return null"        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRequestURL", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRequestedSessionId */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getRequestedSessionId must provide     */
      /* the same functionality as ActionRequest.getRequestedSessionId"       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getRequestedSessionId", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getScheme */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getScheme must provide the same        */
      /* functionality as ActionRequest.getScheme"                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getScheme", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getServerName */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getServerName must provide the same    */
      /* functionality as ActionRequest.getServerName"                        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getServerName", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getServerPort */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getServerPort must provide the same    */
      /* functionality as ActionRequest.getServerPort"                        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getServerPort", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getServletPath */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getServletPath must a value            */
      /* corresponding to the path used to obtain the                         */
      /* PortletRequestDispatcher"                                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getServletPath", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getSession */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getSession must provide the same       */
      /* functionality as                                                     */
      /* ActionRequest.getPortletSession(APPLICATION_SCOPE)"                  */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getSession", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getUserPrincipal */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.getUserPrincipal must provide the      */
      /* same functionality as ActionRequest.getUserPrincipal"                */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getUserPrincipal", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_isRequestedSessionIdValid */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.isRequestedSessionIdValid must         */
      /* provide the same functionality as                                    */
      /* ActionRequest.isRequestedSessionIdValid"                             */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_isRequestedSessionIdValid", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_isSecure */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.isSecure must provide the same         */
      /* functionality as ActionRequest.isSecure"                             */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_isSecure", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_isUserInRole */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.isUserInRole must provide the same     */
      /* functionality as ActionRequest.isUserInRole"                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_isUserInRole", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_removeAttribute */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.removeAttribute must provide the       */
      /* same functionality as ActionRequest.removeAttribute"                 */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_removeAttribute", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_setAttribute */
      /* Details: "In a target servlet of a include in the Action phase,      */
      /* the method HttpServletRequest.setAttribute must provide the same     */
      /* functionality as ActionRequest.setAttribute"                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_setAttribute", aurl);
         tb.writeTo(writer);
      }

      // TestSetupLink for: DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest
      {
         String val = portletReq.getParameter(PARM_NAME);
         if (val == null) {
            PortletURL rurl = ((RenderResponse)portletResp).createRenderURL();
            rurl.setParameters(portletReq.getPrivateParameterMap());
            rurl.setParameter(PARM_NAME, PARM_VALUE);
            TestSetupLink tl = new TestSetupLink("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameter", rurl);
            tl.writeTo(writer);
         }
      }

      // TestSetupLink for: DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest
      {
         String val = portletReq.getParameter(PARM_NAME);
         if (val == null) {
            PortletURL rurl = ((RenderResponse)portletResp).createRenderURL();
            rurl.setParameters(portletReq.getPrivateParameterMap());
            rurl.setParameter(PARM_NAME, PARM_VALUE);
            TestSetupLink tl = new TestSetupLink("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterMap", rurl);
            tl.writeTo(writer);
         }
      }

      // TestSetupLink for: DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest
      {
         String val = portletReq.getParameter(PARM_NAME);
         if (val == null) {
            PortletURL rurl = ((RenderResponse)portletResp).createRenderURL();
            rurl.setParameters(portletReq.getPrivateParameterMap());
            rurl.setParameter(PARM_NAME, PARM_VALUE);
            TestSetupLink tl = new TestSetupLink("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterNames", rurl);
            tl.writeTo(writer);
         }
      }

      // TestSetupLink for: DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest
      {
         String val = portletReq.getParameter(PARM_NAME);
         if (val == null) {
            PortletURL rurl = ((RenderResponse)portletResp).createRenderURL();
            rurl.setParameters(portletReq.getPrivateParameterMap());
            rurl.setParameter(PARM_NAME, PARM_VALUE);
            TestSetupLink tl = new TestSetupLink("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletActionRequest_getParameterValues", rurl);
            tl.writeTo(writer);
         }
      }

   }

}
