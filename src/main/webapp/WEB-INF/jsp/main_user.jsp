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
			<li>			<form action="/logout" method="post">
            					<tr><td> </td><td><input type="submit" name="logout" value="logout"/></td></tr>
            				</form>
            </li>
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
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<br class="clear" />
	<script type="text/javascript">
		$('#gallery').poptrox();
		</script>
	<!-- end -->
</div>
<div id="page">

<font color=white>

    <c:if test="${userDTO!=null}">
    <h2>Вітаємо, ${userDTO.username}!</h2>
        <table class="horo" border="3">
        <caption>Мої гороскопи</caption>
        	<thead>
        	<tr>
        		<th>Титул</th>
        		<th>Введення</th>
        		<th>Зодіак місяца</th>
        		<th>Місячний зодіак</th>
        		<th>Річний зодіак</th>
        		<th>Зодіак тіла</th>
        		<th>Видалення</th>
        	</tr>
        	</thead>
        	<tbody>
        <c:forEach items="${userDTO.horoscopes}" var="horoscope">
        	<tr>
        		<td>${horoscope.title}</td>
        		<td>${horoscope.introduction}</td>
        		<td>${horoscope.moonDesc}</td>
        		<td>${horoscope.monthDesc}</td>
        		<td>${horoscope.yearDesc}</td>
        		<td>${horoscope.bodyInfo}</td>
        		<td>
        		            <form "main_user" method="post">
                            <input type="hidden" name="id" id="${horoscope.id}" value="${horoscope.id}"/>
                            <input type="submit" value="Видалити"/>
                            </form>
        		</td>
        	</tr>
        </c:forEach>
        	</tbody>
        </table>
    </c:if>
</font>
</div>

<div id="sidebar">
    <font color=white>
    <h3 style="margin-left:300px;">Отримати гороскоп :)</h3>
    </font>
    <ul>
        <font color=white>

            <table style="margin-left:250px;">
                <form action="main_user" method="post">
                    <tr>
                      <td>Iм'я:</td>
                      <td><input type="text" name="name" value=""></td>
                    </tr>
                    <tr>
                        <td>День народження:</td>
                        <td><input type="text" name="day" value=""></td>
                    </tr>
                    <tr>
                        <td>Місяць народження:</td>
                        <td><input type="text" name="month" value=""></td>
                    </tr>
                    <tr>
                        <td>Рік народження:</td>
                        <td><input type="text" name="year" value=""></td>
                    </tr>
                    <tr>
                        <td>Gender:</td>
                        <td>male<input type="radio" name="gender"
                                    value="male" >
                            female<input type="radio" name="gender"
                                value="female" checked/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="SEND"/></td>
                    </tr>
                </form>
            </table>
    </ul>
    </font>
</div>

<div id="footer" style="margin-bottom:50px;">
    <p>Copyright (c) </p>
</div>
<!-- end #footer -->
</body>
</html>