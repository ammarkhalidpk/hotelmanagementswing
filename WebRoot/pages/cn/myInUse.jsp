<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<head>
	<jsp:include page="inc_head.jsp" flush="true" />
</head>
<body>
	<ec:table 
		items="myInUse" 
		var="obj"
		title="我正在使用的房间"
		action=""
		sortable="false" filterable="false" autoIncludeParameters="false">
		<ec:row highlightRow="true">
			<ec:column property="obj.room.roomNum" title="房间号" width="10%">
			${obj.room.roomNum}
		</ec:column>
		<ec:column property="obj.room.type" title="类型" width="10%">
			${obj.room.type}
		</ec:column>
		<ec:column property="obj.room.floor" title="楼层" width="10%">
			${obj.room.floor}
		</ec:column>
		<ec:column property="obj.room.prise" title="价格" width="10%">
			${obj.room.prise}
		</ec:column>
		</ec:row>
	</ec:table>
</body>
</html>