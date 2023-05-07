<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Photoshoot by FCT</title>
<link href="/static/css/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type=/"text/javascript" src="/static/scripts/jquery-3.6.0.js"></script>
<script type=/"text/javascript" src="/static/scripts/jquery.poptrox-0.1.js"></script>
</head>
<body>
<div id="header" class="container">
	<div id="logo">
		<h1 style=font-size:30px><a href="#"> Гороскоп - це наше ВСЕ! </a></h1>
		<%-- <p>я - ботан и тоже лентяй <a href="http://www.freecsstemplates.org"></a></p> --%>
	</div>
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="main">Головна</a></li>
			<li><a href="registration">Реєстрація</a></li>
			<li><a href="login">Вхід</a></li>
			<li><a href="main_user">Персональна сторінка</a></li>
		</ul>
	</div>
</div>
<!-- end #header -->
<div id="poptrox">
	<!-- start -->
	<ul id="gallery">
		<li><a href="/static/images/img001.gif"><img src="/static/images/img001.gif" title="Number one!" alt="" /></a></li>
		<li><a href="/static/images/img002.gif"><img src="/static/images/img002.gif" title="Cannot resist :)" alt="" /></a></li>
		<li><a href="/static/images/img003.gif"><img src="/static/images/img003.gif" title="Get immediately!" alt="" /></a></li>
		<li><a href="/static/images/img004.gif"><img src="/static/images/img004.gif" title="Super!" alt="" /></a></li>
		<li></li>
	</ul>
	<br class="clear" />
	<script type="text/javascript">
		$('#gallery').poptrox();
		</script>
	<!-- end -->
</div>


<font color=white>
    <c:if test="${horoscope!=null}">

    <h3 align="center" >${horoscope.title}</h3>

<table id="my_table" border="3" style="margin-top:10px; margin-left:250px; margin-right:250px;">
		<tr>
			<td rowspan="5">
			<li><a href="/static/images/${horoscope.sign}.jpg"><img src="/static/images/${horoscope.sign}.jpg" title="Sign" alt="" width="500" height="100%"/></a></li>
			</td>
			<td>${horoscope.introduction}</td>
		</tr>
		<tr>
			<td>${horoscope.moonDesc}</td>
		</tr>
		<tr>
			<td>${horoscope.monthDesc}</td>
		</tr>
		<tr>
			<td>${horoscope.yearDesc}</td>
		</tr>
		<tr>
			<td>${horoscope.bodyInfo}</td>
		</tr>
</table>
    </c:if>
</font>

<div id="footer">
    <p>Copyright (c) </p>
</div>
<!-- end #footer -->
</body>

</script>
</html>
