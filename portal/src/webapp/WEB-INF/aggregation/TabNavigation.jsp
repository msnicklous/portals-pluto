<%--
Copyright 2004 The Apache Software Foundation
Licensed  under the  Apache License,  Version 2.0  (the "License");
you may not use  this file  except in  compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed  under the  License is distributed on an "AS IS" BASIS,
WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
implied.

See the License for the specific language governing permissions and
limitations under the License.
--%>
<%@ page session="false" buffer="none" %>
<%@ page import="org.apache.pluto.portalImpl.core.PortalURL" %>
<%@ page import="org.apache.pluto.portalImpl.core.PortalEnvironment" %>
<%@ page import="org.apache.pluto.portalImpl.aggregation.navigation.Navigation" %>
<%@ page import="org.apache.pluto.portalImpl.aggregation.navigation.NavigationTreeBean" %>
<jsp:useBean id="fragment" type="org.apache.pluto.portalImpl.aggregation.navigation.TabNavigation" scope="request" />
<table class="nav">
<%
    PortalURL url = PortalEnvironment.getPortalEnvironment(request).getRequestedPortalURL();
    NavigationTreeBean[] tree = fragment.getNavigationView(url);
    for (int i=0; i<tree.length; i++) {
            %><TR><%
            Navigation nav = tree[i].navigation;
            boolean partOfNav = tree[i].partOfGlobalNav;
            if (partOfNav) {
                %><td class="nav" nowrap><%
            } else {
                %><td bgcolor="#AAAAFF" nowrap><%
            }
            for (int k=0; k<tree[i].depth; k++) {
                %>&nbsp;&nbsp;&nbsp;<%
            }
            %>&nbsp;<a href="<%=new PortalURL(request, nav.getLinkedFragment()).toString()%>"><%
            if (partOfNav) {
                %><B><%
            }
            %><%=nav.getTitle()%><%
            if (partOfNav) {
                %></B><%
            }
            %></A>&nbsp;</TD><%
            %><TR><%
    }

%>
</table>
