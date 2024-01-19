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
<title>Batch Details : Pharma Application</title>
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
			<form class="form-inline" action="SearchBatchServlet" method="post">
				<input class="form-control mr-sm-2" type="search" name="searchBatch"
					placeholder="Search" required="required" aria-label="Search">
				<button class="btn btn-info my-2 my-sm-0" type="submit">Search</button>
			</form>
		</nav>
		<!-- Batch Table -->
		<c:if test="${not empty values }">
		<h4 class="text-center text-dark mt-3">Batches Details</h4>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Batch Code</th>
					<th scope="col">Medicine Code</th>
					<th scope="col">Weight</th>
					<th scope="col">Price</th>
					<th scope="col">Medicine Type Code</th>
					<th scope="col">Shipping Charges</th>
					<th scope="col">Care Level</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${values}" var="batchInfo">
					<tr>
						<td>${batchInfo.batchCode}</td>
						<td>${batchInfo.medicineCode}</td>
						<td>${batchInfo.weight}</td>
						<td>${batchInfo.price}</td>
						<td>${batchInfo.medicineTypeCode}</td>
						<td>${batchInfo.shippingCharge}</td>
						<td>${batchInfo.careLevel}</td>
						<td><a href="UpdateBatchServlet?btc=${batchInfo.batchCode}"
							class="btn btn-sm btn-warning"><i
								class="fa fa-pencil-square-o m-1" aria-hidden="true"></i>Edit</a> <a
							href="DelteBatchServlet?batchCode=${batchInfo.batchCode}"
							class="btn btn-sm btn-danger m-1"><i class="fa fa-trash"
								aria-hidden="true"></i>Delete</a></td>
					</tr>
				</c:forEach>


				<%--  <%
				PharmaDaoImpl dao = new PharmaDaoImpl();
				Set<BatchInfo> values = dao.allBatchesInfo();
				for (BatchInfo batch : values) {
				%>
				<tr>
					<th><%=batch.getBatchCode()%></th>
					<td><%=batch.getMedicineCode()%></td>
					<td><%=batch.getWeight()%></td>
					<td><%=batch.getPrice()%></td>
					<td><%=batch.getMedicineTypeCode()%></td>
					<td><%=batch.getShippingCharge()%></td>
					<td><%=batch.getCareLevel()%></td>
					<td><a
						href="editbatch.jsp?batchcode=<%=batch.getBatchCode()%>"
						class="btn btn-sm btn-warning"><i
							class="fa fa-pencil-square-o m-1" aria-hidden="true"></i>Edit</a> <a
						href="DelteBatchServlet?batchCode=<%=batch.getBatchCode()%>"
						class="btn btn-sm btn-danger m-1"><i class="fa fa-trash"
							aria-hidden="true"></i>Delete</a></td>
				</tr>
				<%
				}
				%> --%>
			</tbody>
		</table>
		</c:if>		
		
		
		<c:choose>
			<c:when test="${not empty batchSearch }">
				<h4 class="text-center text-dark mt-3">Results</h4>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Batch Code</th>
							<th scope="col">Medicine Code</th>
							<th scope="col">Weight</th>
							<th scope="col">Price</th>
							<th scope="col">Medicine Type Code</th>
							<th scope="col">Shipping Charges</th>
							<th scope="col">Care Level</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${batchSearch}" var="batchInfo">
							<tr>
								<td>${batchInfo.batchCode}</td>
								<td>${batchInfo.medicineCode}</td>
								<td>${batchInfo.weight}</td>
								<td>${batchInfo.price}</td>
								<td>${batchInfo.medicineTypeCode}</td>
								<td>${batchInfo.shippingCharge}</td>
								<td>${batchInfo.careLevel}</td>
								<td><a href="UpdateBatchServlet?btc=${batchInfo.batchCode}"
									class="btn btn-sm btn-warning"><i
										class="fa fa-pencil-square-o m-1" aria-hidden="true"></i>Edit</a>
									<a href="DelteBatchServlet?batchCode=${batchInfo.batchCode}"
									class="btn btn-sm btn-danger m-1"><i class="fa fa-trash"
										aria-hidden="true"></i>Delete</a></td>
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