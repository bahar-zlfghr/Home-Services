<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css"/>
    <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/createOrder.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/error.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/map.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/createOrder.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>Create Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/panel.css" media="screen">
    <script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.js"
            defer=""></script>
    <script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"
            defer=""></script>
    <meta name="generator" content="Nicepage 3.18.2, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
    <script type="application/ld+json">{
        "@context": "http://schema.org",
        "@type": "Organization",
        "name": "home-services"
    }</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="Customer">
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
                            href="/customer" style="padding: 10px 20px;">Customer</a>
                    </li>
                    <li class="u-nav-item"><a
                            class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
                            href="/change-password" style="padding: 10px 20px;">Change Password</a>
                    </li>
                    <li class="u-nav-item"><a
                            class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
                            href="/logout" style="padding: 10px 20px;">Logout</a>
                    </li>
                </ul>
            </div>
            <div class="u-custom-menu u-nav-container-collapse">
                <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
                    <div class="u-sidenav-overflow">
                        <div class="u-menu-close"></div>
                        <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
                            <li class="u-nav-item"><a class="u-button-style u-nav-link"
                                                      href="/"
                                                      style="padding: 10px 20px;">Home</a>
                            </li>
                            <li class="u-nav-item"><a class="u-button-style u-nav-link"
                                                      href="/customer"
                                                      style="padding: 10px 20px;">Customer</a>
                            </li>
                            <li class="u-nav-item"><a class="u-button-style u-nav-link"
                                                      href="/change-password"
                                                      style="padding: 10px 20px;">Change Password</a>
                            </li>
                            <li class="u-nav-item"><a class="u-button-style u-nav-link" href="/logout"
                                                      style="padding: 10px 20px;">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
            </div>
        </nav>
    </div>
</header>

<c:if test="${successCreatOrder eq true}">
    <div style="text-align: center" class="alert alert-success" role="alert">Order Crate Successfully</div>
</c:if>

<form:form method='POST'
           action="/customer/create-order"
           cssClass="p-3 m-3"
           onsubmit="return validateCreateOrderForm()">
    <div class="table-responsive" style="padding: 100px;">
        <table class="table">
            <tr>
                <td>
                    <label> Sub Service </label>
                </td>
                <td>
                    <select class="form-select" name="subServiceName" id="subServiceName">
                        <option value=""> Select one...</option>
                        <c:forEach items="${subServices}" var="subService">
                            <option value="${subService.name}"> ${subService.name} (Base price: ${subService.basePrice}) </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <span class="error" id="subServiceError"></span>
                    <span class="error" id="invalidSuggestedPrice">${invalidSuggestedPrice}</span>
                </td>
            </tr>

            <tr>
                <td><label> Suggested Price </label></td>
                <td><input type="number" name="suggestedPrice" id="suggestedPrice"/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <span class="error" id="suggestedPriceError"></span>
                </td>
            </tr>

            <tr>
                <td><label> Description (Optional) </label></td>
                <td><textarea name="description" id="description"></textarea></td>
            </tr>

            <tr>
                <td>
                    <label> Do Date </label>
                </td>
                <td>
                    <input type="date" id="doDate" name="doDate">
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <span class="error" id="doDateError"></span>
                </td>
            </tr>

            <tr>
                <td>
                    <label> Address </label>
                </td>
                <td>
                    <div id="mapLocation"></div>
                    <input type="hidden" id="latitude" name="Location.latitude"/>
                    <input type="hidden" id="longitude" name="Location.longitude"/>
                    <input type="hidden" id="state" name="state"/>
                    <input type="hidden" id="city" name="city"/>
                    <input type="hidden" id="formatted_address" name="formatted_address"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <span class="error" id="addressError"></span>
                </td>
            </tr>

            <tr>
                <td colspan="1">
                    <input type="submit" value="Create">
                </td>
                <td></td>
            </tr>
        </table>
    </div>
</form:form>

</body>
</html>
