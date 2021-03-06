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


package org.apache.pluto.container.reconcile.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.portlet.PortletMode;
import javax.xml.namespace.QName;

import org.apache.pluto.container.bean.processor.AnnotatedConfigBean;
import org.apache.pluto.container.bean.processor.AnnotatedMethodStore;
import org.apache.pluto.container.bean.processor.PortletCDIExtension;
import org.apache.pluto.container.bean.processor.fixtures.InvocationResults;
import org.apache.pluto.container.om.portlet.PortletApplicationDefinition;
import org.apache.pluto.container.om.portlet.impl.ConfigurationHolder;
import org.apache.pluto.container.om.portlet.impl.PortletApplicationDefinitionImpl;
import org.apache.pluto.container.om.portlet.impl.jsr362.MergePortletAppTest;
import org.apache.pluto.container.reconcile.fixtures.TestPortlet1;
import org.apache.pluto.container.reconcile.fixtures.TestPortlet1a;
import org.apache.pluto.container.reconcile.fixtures.TestPortlet2;
import org.apache.pluto.container.reconcile.fixtures.TestPortlet3;
import org.apache.pluto.container.reconcile.fixtures.TestPortlet4;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests that the expected portlet methods are present for a portlet app with several
 * portlets, including bean portlets with no explicit configuration.
 * 
 * @author Scott Nicklous
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({PortletCDIExtension.class, InvokeHelper.class, TestPortlet1.class, TestPortlet1a.class, 
   TestPortlet2.class, TestPortlet3.class, TestPortlet4.class})
public class PortletInvokeTest {
   
   @Inject
   private InvocationResults meths;
   
   @Inject
   private InvokeHelper helper;

   private static final Class<?> TEST_ANNOTATED_CLASS1 = TestPortlet1.class;
   private static final Class<?> TEST_ANNOTATED_CLASS2 = TestPortlet2.class;
   private static final String XML_FILE = 
         "org/apache/pluto/container/om/portlet/portlet362Reconcile.xml";

   private static PortletApplicationDefinition pad;
   
   @Inject
   AnnotatedConfigBean acb;
   
   // Classes under test
   private AnnotatedMethodStore ams = null;
   private PortletApplicationDefinition app;

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      
      InputStream in = MergePortletAppTest.class
            .getClassLoader().getResourceAsStream(XML_FILE);

      Set<Class<?>> classes = new HashSet<Class<?>>();
      classes.add(TEST_ANNOTATED_CLASS1);
      classes.add(TEST_ANNOTATED_CLASS2);

      ConfigurationHolder ch = new ConfigurationHolder();
      try {
         ch.processConfigAnnotations(classes);
         ch.processPortletDD(in);     // process portlet xml after annotations
         try {
            ch.validate();         // validate and ignore any validation problems.
         } catch (Exception e) {}   
         pad = ch.getPad();
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   @Before
   public void setUpBefore() throws Exception {
      assertNotNull(acb);
      ams = acb.getMethodStore();
      assertNotNull(ams);
      assertNotNull(helper);

      app = new PortletApplicationDefinitionImpl(pad);
      ConfigurationHolder coho = new ConfigurationHolder(app);
      coho.reconcileBeanConfig(ams);
      
      helper.init("Portlet1", null);
      helper.init("Portlet2", null);
      helper.init("Portlet3", null);
      helper.init("Portlet4", null);
   }
  
   // Begin portlet 1 tests ================================== 

   @Test
   public void test1init() throws Exception {
      String expectedMeth = TestPortlet1.class.getSimpleName() + "#init";
      helper.init("Portlet1", expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1destroy() throws Exception {
      String expectedMeth = TestPortlet1.class.getSimpleName() + "#destroy";
      helper.destroy("Portlet1", expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1action() throws Exception {
      String expectedMeth = TestPortlet1.class.getSimpleName() + "#processAction";
      helper.action("Portlet1", null, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1aaction() throws Exception {
      String expectedMeth = TestPortlet1a.class.getSimpleName() + "#doAction";
      helper.action("Portlet1", "Fred", expectedMeth);
      assertFalse(meths.isConfigExists());
   }

   @Test
   public void test1event1() throws Exception {
      String expectedMeth = TestPortlet1.class.getSimpleName() + "#processEvent";
      QName qn = new QName("http://www.apache.org/", "event2");
      helper.event("Portlet1", qn, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1event2() throws Exception {
      String expectedMeth = TestPortlet1a.class.getSimpleName() + "#doEvent";
      QName qn = new QName("http://www.apache.org/", "event1");
      helper.event("Portlet1", qn, expectedMeth);
      assertFalse(meths.isConfigExists());
   }

   @Test
   public void test1event3() throws Exception {
      String expectedMeth = TestPortlet1a.class.getSimpleName() + "#doEvent";
      QName qn = new QName("https://www.java.net/", "event3");
      helper.event("Portlet1", qn, expectedMeth);
      assertFalse(meths.isConfigExists());
   }

   @Test
   public void test1header() throws Exception {
      String expectedMeth = TestPortlet1.class.getSimpleName() + "#renderHeaders";
      PortletMode pm = PortletMode.VIEW;
      helper.header("Portlet1", pm, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1render() throws Exception {
      String expectedMeth = TestPortlet1.class.getSimpleName() + "#render";
      PortletMode pm = PortletMode.VIEW;
      helper.render("Portlet1", pm, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1res() throws Exception {
      String expectedMeth = TestPortlet1.class.getSimpleName() + "#serveResource";
      String resid = null;
      helper.resource("Portlet1", resid, expectedMeth);
      assertTrue(meths.isConfigExists());
   }
   
   // Begin portlet 2 tests ================================== 

   @Test
   public void test2action() throws Exception {
      String expectedMeth = TestPortlet2.class.getSimpleName() + "#doAction";
      helper.action("Portlet2", null, expectedMeth);
      assertFalse(meths.isConfigExists());
   }

   @Test
   public void test2render() throws Exception {
      String expectedMeth = TestPortlet2.class.getSimpleName() + "#myView";
      PortletMode pm = PortletMode.VIEW;
      helper.render("Portlet2", pm, expectedMeth);
      assertFalse(meths.isConfigExists());
   }

   @Test
   public void test2event1() throws Exception {
      String expectedMeth = TestPortlet2.class.getSimpleName() + "#doEvent";
      QName qn = new QName("http://www.apache.org/", "event2");
      helper.event("Portlet2", qn, expectedMeth);
      assertFalse(meths.isConfigExists());
   }

   @Test
   public void test2event2() throws Exception {
      String expectedMeth = TestPortlet2.class.getSimpleName() + "#doEvent";
      QName qn = new QName("https://www.java.net/", "event4");
      helper.event("Portlet2", qn, expectedMeth);
      assertFalse(meths.isConfigExists());
   }
   
   // Begin portlet 3 tests ================================== 

   @Test
   public void test3init() throws Exception {
      String expectedMeth = TestPortlet3.class.getSimpleName() + "#init";
      helper.init("Portlet3", expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test3destroy() throws Exception {
      String expectedMeth = TestPortlet3.class.getSimpleName() + "#destroy";
      helper.destroy("Portlet3", expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test3action() throws Exception {
      String expectedMeth = TestPortlet3.class.getSimpleName() + "#processAction";
      helper.action("Portlet3", null, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test3render() throws Exception {
      String expectedMeth = TestPortlet3.class.getSimpleName() + "#render";
      PortletMode pm = PortletMode.VIEW;
      helper.render("Portlet3", pm, expectedMeth);
      assertTrue(meths.isConfigExists());
   }
   
   // Begin portlet 4 tests ================================== 

   @Test
   public void test4action() throws Exception {
      String expectedMeth = TestPortlet4.class.getSimpleName() + "#doAction";
      helper.action("Portlet4", null, expectedMeth);
   }

   @Test
   public void test4event1() throws Exception {
      String expectedMeth = TestPortlet4.class.getSimpleName() + "#doEvent";
      QName qn = new QName("http://www.apache.org/", "event2");
      helper.event("Portlet4", qn, expectedMeth);
   }

   @Test
   public void test4event2() throws Exception {
      String expectedMeth = TestPortlet4.class.getSimpleName() + "#doEvent";
      QName qn = new QName("https://www.java.net/", "event4");
      helper.event("Portlet4", qn, expectedMeth);
   }

   @Test
   public void test4render() throws Exception {
      String expectedMeth = TestPortlet4.class.getSimpleName() + "#myView";
      PortletMode pm = PortletMode.HELP;
      helper.render("Portlet4", pm, expectedMeth);
   }

   @Test
   public void test4resource() throws Exception {
      String expectedMeth = TestPortlet4.class.getSimpleName() + "#res";
      String resid = "something";
      helper.resource("Portlet4", resid, expectedMeth);
   }

}
