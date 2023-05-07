<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<script src="https://www.google.com/recaptcha/api.js"></script>

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
	<div id="bg1">
		<div id="bg2">
			<div id="bg3">
				<div id="content">
