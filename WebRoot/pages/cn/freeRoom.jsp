<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<head>
	<jsp:include page="inc_head.jsp" flush="true" />
</head>
<body>
	<ec:table 
		items="freeRoom" 
		var="obj"
		title="可用房间"
		action=""
		sortable="false" filterable="false" autoIncludeParameters="false">
		<ec:row highlightRow="true">
			<ec:column property="roomNum" title="房间号" width="10%"/>
			<ec:column property="type" title="房间类型" width="10%"/>
			<ec:column property="floor" title="房间楼层" width="10%"/>
			<ec:column property="prise" title="房间价格" width="10%"/>
			<ec:column property="description" title="备注" width="10%"/>
		</ec:row>
	</ec:table>
</body>
</html>