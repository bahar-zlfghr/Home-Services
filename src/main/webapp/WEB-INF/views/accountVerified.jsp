<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html style="font-size: 16px;">
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Home Repair Services">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>Your Account Verified</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css" media="screen">
    <script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.js"
            defer=""></script>
    <script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"
            defer=""></script>
    <meta name="generator" content="Nicepage 3.18.2, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
    <link id="u-page-google-font" rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Oswald:200,300,400,500,600,700">
    <script type="application/ld+json">{
        "@context": "http://schema.org",
        "@type": "Organization",
        "name": "home-services"
    }</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="Home">
    <meta property="og:type" content="website">
</head>

<body class="u-body">

<header class="u-clearfix u-header u-header" id="sec-1ab9">
    <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1">
            <div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0;">
                <a class="u-button-style u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
                   href="#">
                    <svg>
                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use>
                    </svg>
                    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                        <defs>
                            <symbol id="menu-hamburger" viewBox="0 0 16 16" style="width: 16px; height: 16px;">
                                <rect y="1" width="16" height="2"></rect>
                                <rect y="7" width="16" height="2"></rect>
                                <rect y="13" width="16" height="2"></rect>
                            </symbol>
                        </defs>
                    </svg>
                </a>
            </div>
            <div class="u-custom-menu u-nav-container">
                <ul class="u-nav u-unstyled u-nav-1">
                    <li class="u-nav-item"><a
                            class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
                            href="/" style="padding: 10px 20px;">Home</a>
                    </li>
                    <li class="u-nav-item"><a
                            class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
                            href="/specialist/register" style="padding: 10px 20px;">Register Specialist</a>
                    </li>
                    <li class="u-nav-item"><a
                            class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
                            href="/customer/register" style="padding: 10px 20px;">Register Customer</a>
                    </li>
                    <li class="u-nav-item"><a
                            class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
                            href="/login" style="padding: 10px 20px;">Login</a>
                    </li>
                </ul>
            </div>
            <div class="u-custom-menu u-nav-container-collapse">
                <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
                    <div class="u-sidenav-overflow">
                        <div class="u-menu-close"></div>
                        <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
                            <li class="u-nav-item"><a class="u-button-style u-nav-link"
                                                      href="$/"
                                                      style="padding: 10px 20px;">Home</a>
                            </li>
                            <li class="u-nav-item"><a class="u-button-style u-nav-link"
                                                      href="/specialist/register"
                                                      style="padding: 10px 20px;">Register Specialist</a>
                            </li>
                            <li class="u-nav-item"><a class="u-button-style u-nav-link"
                                                      href="/customer/register"
                                                      style="padding: 10px 20px;">Register Customer</a>
                            </li>
                            <li class="u-nav-item"><a class="u-button-style u-nav-link" href="/login"
                                                      style="padding: 10px 20px;">Login</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
            </div>
        </nav>
    </div>
</header>

<c:if test="${hasError eq false}">
    <div style="text-align: center" class="alert alert-success" role="alert">
        Congratulations! Your account has been activated and email is verified
    </div>
</c:if>

<c:if test="${hasError eq true}">
    <div style="text-align: center" class="alert alert-danger" role="alert">${tokenNotFound}</div>
</c:if>

</body>
</html>
