<jsp:useBean id="doeCommonData" class="edu.mass.doe.doecommon.startup.DoeCommonData" scope="application"/>
<% String vision_css_url = doeCommonData.getMainCSSFileURL().trim(); %>
<head>
<META NAME="search" CONTENT="Department of Elementary and Secondary Education">
<LINK TITLE="Doe Main Style Sheet" REL=STYLESHEET HREF="<%=vision_css_url%>" TYPE="text/css">
</head>
 <%=doeCommonData.getHeaderFileOne()%>