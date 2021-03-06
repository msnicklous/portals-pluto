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
 * portlet DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_event
 *
 */
public class DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest implements Portlet, ResourceServingPortlet {
   private static final String LOG_CLASS = 
         DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest.class.getName();
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

      QName eventQName = new QName(TCKNAMESPACE,
             "DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest");
      portletResp.setEvent(eventQName, "Hi!");
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

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getAttribute */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getAttribute must provide the same         */
      /* functionality as EventRequest.getAttribute"                          */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getAttribute", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getAttributeNames */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getAttributeNames must provide the same    */
      /* functionality as EventRequest.getAttributeNames"                     */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getAttributeNames", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getAuthType */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getAuthType must provide the same          */
      /* functionality as EventRequest.getAuthType"                           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getAuthType", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getCharacterEncoding */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getCharacterEncoding must return null"     */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getCharacterEncoding", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getContentLength */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getContentLength must return 0"            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getContentLength", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getContentType */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getContentType must return null"           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getContentType", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getContextPath */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getContextPath must provide the same       */
      /* functionality as EventRequest.getContextPath"                        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getContextPath", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getCookies */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getCookies must provide the same           */
      /* functionality as EventRequest.getCookies"                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getCookies", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getDateHeader */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getDateHeader must return a value          */
      /* corresponding to that returned by EventRequest.getProperty for the   */
      /* corresponding header name"                                           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getDateHeader", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getHeader */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getHeader must return a value              */
      /* corresponding to that returned by EventRequest.getProperty for the   */
      /* corresponding header name"                                           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getHeader", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getHeaderNames */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* Enumeration returned by HttpServletRequest.getHeaderNames must be    */
      /* contained in the Enumeration returned by                             */
      /* EventRequest.getPropertyNames"                                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getHeaderNames", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getHeaders */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getHeaders must return a value             */
      /* corresponding to that returned by EventRequest.getProperties for     */
      /* the corresponding header name"                                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getHeaders", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getInputStream */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getInputStream must return null"           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getInputStream", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getIntHeader */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getIntHeader must return a value           */
      /* corresponding to that returned by EventRequest.getProperty for the   */
      /* corresponding header name"                                           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getIntHeader", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocalAddr */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getLocalAddr must return null"             */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocalAddr", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocalName */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getLocalName must return null"             */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocalName", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocalPort */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getLocalPort must return 0"                */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocalPort", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocale */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getLocale must provide the same            */
      /* functionality as EventRequest.getLocale"                             */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocale", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocales */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getLocales must provide the same           */
      /* functionality as EventRequest.getLocales"                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getLocales", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getMethod */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getMethod must provide the same            */
      /* functionality as EventRequest.getMethod"                             */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getMethod", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getParameter */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getParameter must provide the same         */
      /* functionality as EventRequest.getParameter"                          */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getParameter", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getParameterMap */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getParameterMap must provide the same      */
      /* functionality as EventRequest.getParameterMap"                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getParameterMap", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getParameterNames */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getParameterNames must provide the same    */
      /* functionality as EventRequest.getParameterNames"                     */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getParameterNames", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getParameterValues */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getParameterValues must provide the same   */
      /* functionality as EventRequest.getParameterValues"                    */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getParameterValues", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getPathInfo */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getPathInfo must a value corresponding     */
      /* to the path used to obtain the PortletRequestDispatcher"             */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getPathInfo", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getPathTranslated */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getPathTranslated must a value             */
      /* corresponding to the path used to obtain the                         */
      /* PortletRequestDispatcher"                                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getPathTranslated", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getProtocol */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getProtocol must return \"HTTP/1.1\""      */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getProtocol", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getQueryString */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getQueryString must a value                */
      /* corresponding to the query string information used to obtain the     */
      /* PortletRequestDispatcher"                                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getQueryString", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getReader */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getReader must return null"                */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getReader", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRealPath */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRealPath must return null"              */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRealPath", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRemoteAddr */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRemoteAddr must return null"            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRemoteAddr", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRemoteHost */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRemoteHost must return null"            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRemoteHost", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRemotePort */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRemotePort must return 0"               */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRemotePort", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRemoteUser */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRemoteUser must provide the same        */
      /* functionality as EventRequest.getRemoteUser"                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRemoteUser", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRequestDispatcher */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRequestDispatcher must provide          */
      /* functionality as defined in the servlet specification"               */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRequestDispatcher", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRequestURI */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRequestURI must a value corresponding   */
      /* to the path and query string information used to obtain the          */
      /* PortletRequestDispatcher"                                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRequestURI", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRequestURL */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRequestURL must return null"            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRequestURL", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRequestedSessionId */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getRequestedSessionId must provide the     */
      /* same functionality as EventRequest.getRequestedSessionId"            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getRequestedSessionId", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getScheme */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getScheme must provide the same            */
      /* functionality as EventRequest.getScheme"                             */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getScheme", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getServerName */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getServerName must provide the same        */
      /* functionality as EventRequest.getServerName"                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getServerName", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getServerPort */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getServerPort must provide the same        */
      /* functionality as EventRequest.getServerPort"                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getServerPort", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getServletPath */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getServletPath must a value                */
      /* corresponding to the path used to obtain the                         */
      /* PortletRequestDispatcher"                                            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getServletPath", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getSession */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getSession must provide the same           */
      /* functionality as                                                     */
      /* EventRequest.getPortletSession(APPLICATION_SCOPE)"                   */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getSession", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getUserPrincipal */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.getUserPrincipal must provide the same     */
      /* functionality as EventRequest.getUserPrincipal"                      */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_getUserPrincipal", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_isRequestedSessionIdValid */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.isRequestedSessionIdValid must provide     */
      /* the same functionality as EventRequest.isRequestedSessionIdValid"    */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_isRequestedSessionIdValid", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_isSecure */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.isSecure must provide the same             */
      /* functionality as EventRequest.isSecure"                              */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_isSecure", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_isUserInRole */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.isUserInRole must provide the same         */
      /* functionality as EventRequest.isUserInRole"                          */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_isUserInRole", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_removeAttribute */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.removeAttribute must provide the same      */
      /* functionality as EventRequest.removeAttribute"                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_removeAttribute", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_setAttribute */
      /* Details: "In a target servlet of a include in the Event phase, the   */
      /* method HttpServletRequest.setAttribute must provide the same         */
      /* functionality as EventRequest.setAttribute"                          */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests4_SPEC2_19_IncludeServletEventRequest_setAttribute", aurl);
         tb.writeTo(writer);
      }

   }

}
