<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${shipTypeInfo.medicineTypeCode } and ${shipTypeInfo.weightRange } : PharmaApplication</title>
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


	<c:if test="${not empty shipTypeInfo }">
		<div class="container">
			<div class="row p-5">
				<div class="col-md-6 offset-md-4">
					<div class="card">
						<div class="card-body">
							<h4 class="text-center text-dark">${shipTypeInfo.medicineTypeCode } and ${shipTypeInfo.weightRange }</h4>
							<form action="UpdateShippingServlet" method="post">
								<div class="form-group">
									<label for="exampleInputPassword1">Weight Range</label> <input
										type="text" name="medicineTypeCode" class="form-control"
										id="exampleInputPassword1" readonly="readonly" value="${shipTypeInfo.medicineTypeCode }">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Weight Range</label> <input
										type="text" name="weightRange" class="form-control"
										id="exampleInputPassword1" readonly="readonly" value="${shipTypeInfo.weightRange }">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Shipping Charge</label> <input
										type="number" name="shippingCharge" min="0" max="1000"
										step="0.01" class="form-control" id="exampleInputPassword1"
										value="${shipTypeInfo.shippingCharge }">
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary">Edit
										Shipping Master</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<%@ include file="allcomponent/footer.jsp"%>
</body>
</html>