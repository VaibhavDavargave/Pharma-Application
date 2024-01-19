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
<title>Medicine Type Details : Pharma Application</title>
<%@ include file="allcomponent/allcss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<%@ include file="allcomponent/navbar.jsp"%>
	<c:if test="${empty adminObj }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<c:if test="${not empty succMsg}">
		<div class="alert alert-success text-center" role="alert">${succMsg }</div>
		<c:remove var="succMsg" scope="session" />
	</c:if>
	<c:if test="${not empty failedMsg}">
		<div class="alert alert-danger text-center" role="alert">${failedMsg }</div>
		<c:remove var="failedMsg" scope="session" />
	</c:if>
	<div class="container p-4">
		<!-- Search Bar -->
		<nav class="navbar navbar-light justify-content-center">
			<form class="form-inline" action="SearchMedTypeServlet" method="post">
				<input class="form-control mr-sm-2" type="search"
					name="searchMedicineType" placeholder="Search" required="required"
					aria-label="Search">
				<button class="btn btn-info my-2 my-sm-0" type="submit">Search</button>
			</form>
		</nav>
		<!-- Batch Table -->
		<c:if test="${not empty values }">
			<h4 class="text-center text-dark">Medicine Types Detail</h4>
			<table class="table mt-3">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Medicine Type Code</th>
						<th scope="col">Medicine Type Name</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${values}" var="medTypeInfo">
						<tr>
							<td>${medTypeInfo.medicineTypeCode }</td>
							<td>${medTypeInfo.medicineTypeName }</td>
							<td><a href="UpdateMedTypeServlet?medTypeCode=${medTypeInfo.medicineTypeCode }" class="btn btn-sm btn-warning"><i
									class="fa fa-pencil-square-o m-1" aria-hidden="true"></i>Edit</a> <a
								href="DeleteMedTypeServlet?medTypeCode=${medTypeInfo.medicineTypeCode }" class="btn btn-sm btn-danger m-1"><i
									class="fa fa-trash" aria-hidden="true"></i>Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>


		<c:choose>
			<c:when test="${not empty medTypeSearch }">
				<h4 class="text-center text-dark mt-3">Results</h4>
				<table class="table mt-3">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Medicine Type Code</th>
							<th scope="col">Medicine Type Name</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>


						<c:forEach items="${medTypeSearch }" var="medTypeInfo">
							<tr>
								<td>${medTypeInfo.medicineTypeCode }</td>
								<td>${medTypeInfo.medicineTypeName }</td>
								<td><a href="UpdateMedTypeServlet?medTypeCode=${medTypeInfo.medicineTypeCode }" class="btn btn-sm btn-warning"><i
										class="fa fa-pencil-square-o m-1" aria-hidden="true"></i>Edit</a>
									<a href="DeleteMedTypeServlet?medTypeCode=${medTypeInfo.medicineTypeCode }" class="btn btn-sm btn-danger m-1"><i
										class="fa fa-trash" aria-hidden="true"></i>Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</c:when>

			<c:when test="${requestScope.noDataFound eq true }">
				<div class="alert alert-danger text-center" role="alert">No
					Results Found</div>
			</c:when>
		</c:choose>



	</div>




	<%@ include file="allcomponent/footer.jsp"%>

</body>
</html>