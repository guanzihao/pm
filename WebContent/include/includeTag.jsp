<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pm" uri="/www.v5y.cn/pm/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" scope="request"/>
<c:set var="currUser" value="${sessionScope.PM_USER_SESSION_KEY}" />
<c:set var="currSup" value="${sessionScope.PM_SUP_SESSION_KEY}" />


