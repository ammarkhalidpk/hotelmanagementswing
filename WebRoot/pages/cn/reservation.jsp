<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="hotel.model.user.User"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<html>
<head>
<title>邯郸水星酒店官方网站 - 实时网络预订</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="author" content="Mamery">
<meta name=”keywords” content=”水星酒店，在线预订，酒店预订，dhotelier”> 
<meta name=”description” content=”水星酒店，豪华舒适的四星级酒店”> 
<link rel="stylesheet" type="text/css" href="common/style.css">
<link href="../hcrm/website/hotelhome/js/greybox/gb_styles.css" rel="stylesheet" type="text/css" /><!--控制弹出窗口的样式-->
<script language="JavaScript" src="../hcrm/website/hotelhome/00165/cn/images/booking.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="common/My97DatePicker/WdatePicker.js"></script>
</head>
<body>




<table width="778" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td class="reservation_top"><img src="images/spacer.gif"></td>
  </tr>
  <tr>
    <td class="reservation_top2"><img src="images/spacer.gif"></td>
  </tr>
</table>
<table width="778" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td rowspan="2" width="181" height="140"><img src="images/logo.jpg" width="181" height="115"><img src="images/star.jpg" width="181" height="25"></td>
    <td height="113"><img src="images/banner_reservation.jpg"></td>
  </tr>
  <tr>
    <td><table width="100%" height="26" border="0" cellspacing="0" cellpadding="0" class="menu_1">
        <tr>
          <td><table border="0" cellspacing="0" cellpadding="0" class="menu_2" align="center">
              <tr>
                <td><a href="reservation.jsp">客房预订</a></td>
                <td><a href="hotelinfo.htm">酒店介绍</a></td>
                <td><a href="guestrooms.htm">客房设施</a></td>
                <td><a href="restaurants.htm">餐饮设施</a></td>
                <td><a href="meetingcenter.htm">会议设施</a></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="181" class="left"><table width="164" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="4"><img src="images/lefttable_top.jpg"></td>
        </tr>
        <tr>
        <%
        User user = (User)session.getAttribute("loginUser");
        if (user == null) {
        	%>
        	<td class="reservation_left_middle">
		        <s:url id="loginAction" action="loginAction" namespace="/apps"></s:url>
		        <s:actionmessage/>
	          	<script type="text/javascript">
	          		function clientValidate() {
	          			var f=document.forms.loginForm;
						if (f.loginName.value==""){
							window.alert("用户名不能为空！");
							return false;
						}
						if (f.password.value==""){
							window.alert("密码不能为空！");
							return false;
						}
						return true;
	          		}
	          	</script>
	          	<form name="loginForm" action="${loginAction}" focus="loginName" method="post" onSubmit="return clientValidate();" >
	              <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	                <tr>
	                  <td class="reservation_login1"><img src="images/title_login.jpg" width="148" height="14"></td>
	                </tr>
	                <tr>
	                  <td class="reservation_login2">用户名
	                    <label>
	                    <input type="text" name="loginName" class="reservation_logintextfield">
	                    </label></td>
	                </tr>
	                <tr>
	                  <td class="reservation_login2">密&nbsp;&nbsp;&nbsp;&nbsp;码
	                    <input type="password" name="password" class="reservation_logintextfield"></td>
	                </tr>
	                <tr>
	                  <td class="reservation_login3"><label>
	                    <input name="image" type="submit" value="登 录" class="reservation_loginbutton">
	                    </label></td>
	                </tr>
	                <tr>
	                  <td class="reservation_login4">申请注册成为酒店的散客会员，您将享受到酒店提供的散客会员优惠价及实时的预订服务。</td>
	                </tr>
	                <tr>
	                <s:url id="regAction" action="register" namespace="/apps"></s:url>
	                  <td class="reservation_login5">&gt;&gt;&nbsp;<a href="${regAction}" target="blank" class="reg">散客会员注册</a></td>
	                </tr>
	              </table>
	              </form>
	            </td>
        	
        	<%
        } else {
        	%>
        	<td class="reservation_left_middle">
        	<s:url id="logoutAction" action="logoutAction" namespace="/apps"></s:url>
        		<%=user.getName() %>您好！<a href="${logoutAction}">注销</a>
        	</td>
        	<%
        }
        %>
          
        </tr>
        <tr>
          <td height="4"><img src="images/pic_reservationleft.jpg" width="164" height="131"></td>
        </tr>
      </table></td>
    <td class="right"><div><img src="images/pic_reservation.jpg" width="598" height="201"></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reservation_rightcontent" align="center">
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td width="302"><table width="287" height="147" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td height="8"><img src="images/spacer.gif"></td>
                      <td><img src="images/spacer.gif"></td>
                      <td><img src="images/spacer.gif"></td>
                    </tr>
                    <tr>
                      <td width="6" height="6"><img src="images/tablecorner_reservation.jpg"></td>
                      <td class="reservation_searchtable_t"><img src="images/spacer.gif"></td>
                      <td class="tablecorner_h" width="6" height="6"><img src="images/tablecorner_reservation.jpg"></td>
                    </tr>
                    <tr>
                      <td class="reservation_searchtable_l"><img src="images/spacer.gif"></td>
                      <td valign="top">
                      
                      <s:form action="queryRoomAction" namespace="/apps" method="post" target="blank">
					  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td class="search1"><img src="images/title_search.jpg" width="65" height="16"></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td class="search2">入住时间:</td>
                            <td>
                            	<input name="checkInDate" id="checkInDate" type="text" class="reservation_searchtextfield" style="width:120px;"/><img src="common/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" onclick="WdatePicker({el:$dp.$('checkInDate')})">
                            </td>
                          </tr>
                          <tr>
                            <td class="search2">离店时间:</td>
                            <td>
                            	<input name="checkOutDate" id="checkOutDate" type="text" class="reservation_searchtextfield" style="width:120px;"/><img src="common/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" onclick="WdatePicker({el:$dp.$('checkOutDate')})">
                            </td>
                          </tr>
                          <tr>
                            <td class="search2">房间数:</td>
                            <td><select name="roomNum" class="reservation_selecttextfield">
                            <option value="1" selected>1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                              </select>
                            </td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td class="search3">
                            <s:submit value="搜索" cssClass="searchbutton"/>
                            <!--<input type="button" name="Submit" value="搜索" class="searchbutton"/>-->
                            </td>
                          </tr>
                      </table>
					</s:form>
					  </td>
                      <td class="reservation_searchtable_r"><img src="images/spacer.gif"></td>
                    </tr>
                    <tr>
                      <td width="6" height="6" class="tablecorner_v"><img src="images/tablecorner_reservation.jpg"></td>
                      <td class="reservation_searchtable_b"><img src="images/spacer.gif"></td>
                      <td width="6" height="6" class="tablecorner_h"><img src="images/tablecorner_reservation.jpg" class="tablecorner_v"></td>
                    </tr>
                  </table>
                  <div><a href="#"><img src="images/pic_reservation2.jpg" width="302" height="110" border="0"></a></div></td>
                <td valign="top">
                <%
                if (user != null) {
                	%>
                	<s:url id="myConsume" action="myConsume" namespace="/apps"></s:url>
                	<iframe  width="295" height="262" name="reservation_main" frameborder="0" src="${myConsume}" scrolling="yes"></iframe>
                	<%
                } else {
                	%>
                	<iframe  width="295" height="262" name="reservation_main" frameborder="0" src="" scrolling="no"></iframe>
                	<%
                }
                 %>
                	
                </td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
<!-- #BeginLibraryItem "/Library/cn_copy.lbi" --> 
<table width="778" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td class="copy_separate"><img src="images/spacer.gif"></td>
  </tr>
  <tr>
    <td class="copy">邯郸水星酒店版权所有</td>
  </tr>
</table><!-- #EndLibraryItem --><map name="Map"><area shape="rect" coords="15,20,125,43" href="javascript:mybooking()"></map></body>
</html>
