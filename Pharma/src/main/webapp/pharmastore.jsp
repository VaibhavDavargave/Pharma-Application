<%@page import="com.excelr.pharma.constants.SQLConstants"%>
<%@page import="com.excelr.pharma.vo.MedicineInfo"%>
<%@page import="com.excelr.pharma.vo.BatchInfo"%>
<%@page import="java.util.Set"%>
<%@page import="com.excelr.pharma.dao.PharmaDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pharma Store : PharmaApplication</title>
<%@ include file="allcomponent/allcss.jsp"%>
<style type="text/css">
a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body style="background-color: #f0f1f2;">
	<%@ include file="allcomponent/navbar.jsp"%>
	<c:if test="${empty adminObj }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<div class="container">
		<div class="row justify-content-center p-5">

			<!-- View Batche Code Details -->
				<div class="col-md-4">
					<a href="BatchCodeServlet">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-heart fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">View Batches</h4>
							<p class="text-info">Search/View/Edit/Delete Batches</p>
							<c:if test="${not empty batchRows}">
								<p class="text-info">Total Batches:${batchRows}</p>
							</c:if>
						</div>
					</div>
				</a>
				</div>

			
			<!-- View Medcine  Details -->
			
			<div class="col-md-4">
				<a href="MedicineServlet">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-stethoscope fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">View Medicines</h4>
							<p class="text-info">Search/View/Edit/Delete Medicines</p>
							<c:if test="${not empty medRows}">
							<p class="text-info">
								Total Medicines :${medRows}</p>
							</c:if>
						</div>
					</div>
				</a>
			</div>	
			<!-- View Medcine  Types Details -->
			<div class="col-md-8 mt-3">
				<a href="MedicineTypeServlet">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-medkit fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">View Medicine Types</h4>
							<p class="text-info">Search/View/Edit/Delete Type of Medicines</p>
							<c:if test="${not empty medTypeRows}">
							<p class="text-info">
								Total Medicines Types:${medTypeRows}</p>
							</c:if>
						</div>
					</div>
				</a>
			</div>
			
			<div class="col-md-8 mt-3">
				<a href="ShippingChargeServlet">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-truck fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">View Shipping Master</h4>
							<p class="text-info">Search/View/Edit/Delete Shipping Details</p>
							<c:if test="${not empty shipRows}">
							<p class="text-info">
								Total Medicines Types:${shipRows}</p>
							</c:if>
						</div>
					</div>
				</a>
			</div>
				
			
		</div>
	</div>

	<%@ include file="allcomponent/footer.jsp"%>
</body>
</html>