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

package org.apache.portals.samples;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.annotations.ActionMethod;
import javax.portlet.annotations.PortletSerializable;
import javax.portlet.annotations.RenderMethod;
import javax.portlet.annotations.RenderStateScoped;
import javax.servlet.DispatcherType;

/**
 * Render state scoped bean. The bean is stored as a render parameter, so it
 * needs to be portlet serializable.
 */
@RenderStateScoped
@Named("adb")
public class AsyncDialogBean implements PortletSerializable {
   private static final Logger LOGGER = Logger.getLogger(AsyncDialogBean.class.getName());

   public enum OutputType {
      TEXT, INC, FWD, DISPATCH, AUTO
   }

   public static final String PARAM_MSG      = "msg";
   public static final String PARAM_DELAY    = "delay";
   public static final String PARAM_REPS     = "reps";
   public static final String PARAM_AUTO     = "auto";
   public static final String PARAM_FILTER   = "filter";
   public static final String PARAM_TYPE     = "type";
   public static final String PARAM_TYPE_TXT = OutputType.TEXT.toString();
   public static final String PARAM_TYPE_INC = OutputType.INC.toString();
   public static final String PARAM_TYPE_FWD = OutputType.FWD.toString();
   public static final String PARAM_TYPE_DIS = OutputType.DISPATCH.toString();

   private int                delay;
   private int                reps;
   private OutputType         type;
   private String             msg;
   private boolean            autoDispatch;
   private boolean            useFilter;

   /**
    * This method is called by the portlet container to initialize the bean at
    * the beginning of a request.
    */
   @Override
   public void deserialize(String[] state) {
      if (state.length == 0) {
         delay = 1000;
         reps = 1;
         type = OutputType.TEXT;
         msg = null;
         autoDispatch = true;
         useFilter = false;
      } else {
         delay = Integer.parseInt(state[0]);
         reps = Integer.parseInt(state[1]);
         type = OutputType.valueOf(state[2]);
         msg = state[3];
         autoDispatch = Boolean.parseBoolean(state[4]);
         useFilter = Boolean.parseBoolean(state[5]);
      }
      LOGGER.fine("deserialized: " + Arrays.asList(state).toString());
   }

   /**
    * Called by the portlet container at the end of an action or event request
    * to store the serialized data as a portlet render parameter.
    */
   @Override
   public String[] serialize() {
      String[] state = { "" + delay, "" + reps, type.toString(), msg, ""+autoDispatch, ""+useFilter };
      LOGGER.fine("serialized: " + Arrays.asList(state).toString());
      return state;
   }

   /**
    * @return the delay
    */
   public int getDelay() {
      return delay;
   }

   /**
    * @param delay
    *           the delay to set
    */
   public void setDelay(int delay) {
      this.delay = delay;
   }

   /**
    * @return the reps
    */
   public int getReps() {
      return reps;
   }

   /**
    * @param reps
    *           the reps to set
    */
   public void setReps(int reps) {
      this.reps = reps;
   }

   /**
    * @return the type
    */
   public OutputType getType() {
      return type;
   }

   /**
    * @param type
    *           the type to set
    */
   public void setType(OutputType type) {
      this.type = type;
   }

   /**
    * @return the msg
    */
   public String getMsg() {
      return msg == null ? "" : msg;
   }

   /**
    * @param msg
    *           the msg to set
    */
   public void setMsg(String msg) {
      this.msg = msg;
   }

   /**
    * @return the autoDispatch
    */
   public boolean isAutoDispatch() {
      return autoDispatch;
   }

   /**
    * @param autoDispatch
    *           the autoDispatch to set
    */
   public void setAutoDispatch(boolean autoDispatch) {
      this.autoDispatch = autoDispatch;
   }

   /**
    * @return the useFilter
    */
   public boolean isUseFilter() {
      return useFilter;
   }

   /**
    * @param useFilter the useFilter to set
    */
   public void setUseFilter(boolean useFilter) {
      this.useFilter = useFilter;
   }

   /**
    * Displays the dialog
    * 
    * @return the action form as string
    */
   @RenderMethod(portletNames = "AsyncPortlet", ordinal = 100, include = "/WEB-INF/jsp/asyncDialog.jsp")
   public void getDialog() {
   }

   /**
    * Sets values based on form input
    */
   @ActionMethod(portletName = "AsyncPortlet")
   public void handleDialog(ActionRequest req, ActionResponse resp) throws PortletException, IOException {

      msg = null;

      String strReps = req.getActionParameters().getValue(PARAM_REPS);
      if (strReps != null) {
         try {
            reps = Integer.parseInt(strReps);
            if (reps <= 0 || reps > 8)
               throw new Exception("broken");
         } catch (Exception e) {
            msg = "try again. bad repetitions.";
         }
      }

      String strDelay = req.getActionParameters().getValue(PARAM_DELAY);
      if (strDelay != null) {
         try {
            delay = Integer.parseInt(strDelay);
            if (delay < 0)
               throw new Exception("broken");
         } catch (Exception e) {
            msg = "try again. bad delay.";
         }
      }

      String strType = req.getActionParameters().getValue(PARAM_TYPE);
      if (strType != null) {
         try {
            type = OutputType.valueOf(strType);
            if (reps > 1) {
               if ((type == OutputType.FWD) || (type == OutputType.DISPATCH)) {
                  msg = "Repetitions cannot be > 1 for forwards or dispatches.";
                  reps = 1;
               }
            }
         } catch (Exception e) {
            msg = "try again. bad type: " + strType;
         }
      }

      String auto = req.getActionParameters().getValue(PARAM_AUTO);
      if (auto != null) {
         autoDispatch = true;
      } else {
         autoDispatch = false;
         if (reps > 1) {
            msg = "Repetitions cannot be > 1 for non-recursive use.";
            reps = 1;
         }
      }

      String filter = req.getActionParameters().getValue(PARAM_FILTER);
      if (filter != null) {
         useFilter = true;
         if ((type == OutputType.FWD)) {
            msg = "Filter can't generate output with forward processing.";
            useFilter = false;
         }
      } else {
         useFilter = false;
      }

      String[] state = { "" + delay, "" + reps, type.toString(), msg, "" + autoDispatch };
      LOGGER.fine("Resulting params: " + Arrays.asList(state).toString());
   }

}