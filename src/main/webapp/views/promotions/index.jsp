<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
<style>
	body {
	    background-image: url("assets/images/backgrounds/bg2.jpg");
	    background-attachment: fixed;
	    background-position: center center;
	    background-size: cover;
	}
	
	h1 {
		font-family: 'Ringbearer Medium', arial;
        font-size: 48px;
        text-shadow: 4px 4px 4px #aaa; 
	}
	
	h5 {
		font-family: 'Ringbearer Medium', arial;
        font-size: 24px;
	}
</style>
</head>
<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>
	<main class="container">

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>
		
		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/TierraMedia/promotions/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Promocion
				</a>
			</div>
		</c:if>
		
		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son las promociones que te pueden interesar basadas en tus gustos</h1>
		</div>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Promoci&oacute;n</th>
					<th>Costo Total</th>
					<th>Duraci&oacute;n</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${promotions}" var="promotion">
					<tr>
						<td><strong><c:out value="${promotion.name}"></c:out></strong>
							<p>${promotion.toString()}</p></td>
						<td><c:out value="${promotion.getMontoPromo()}"></c:out></td>
						<td><c:out value="${promotion.getTotalDuration()}"></c:out></td>	
						<td><c:if test="${user.admin}">
								<a href="/TierraMedia/promotions/edit.do?id=${promotion.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/TierraMedia/promotions/delete.do?id=${promotion.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if> <c:choose>

								<c:when
									test="${user.allreadyHavePromotion(promotion) && user.canAffordPromotion(promotion) && user.canAttendPromotion(promotion) && promotion.canHost(1)}">
									<a href="/TierraMedia/promotions/buy.do?id=${promotion.id}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son otras promociones que te pueden interesar</h1>
		</div>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Promoci&oacute;n</th>
					<th>Costo Total</th>
					<th>Duraci&oacute;n</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${promotionsAlt}" var="promotion">
					<tr>
						<td><strong><c:out value="${promotion.name}"></c:out></strong>
							<p>${promotion.toString()}</p></td>
						<td><c:out value="${promotion.getMontoPromo()}"></c:out></td>
						<td><c:out value="${promotion.getTotalDuration()}"></c:out></td>
						
						<td><c:if test="${user.admin}">
								<a href="/TierraMedia/promotions/edit.do?id=${promotion.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/TierraMedia/promotions/delete.do?id=${promotion.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if> <c:choose>

								<c:when
									test="${user.allreadyHavePromotion(promotion) && user.canAffordPromotion(promotion) && user.canAttendPromotion(promotion) && promotion.canHost(1)}">
									<a href="/TierraMedia/promotions/buy.do?id=${promotion.id}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		

	</main>

</body>
</html>