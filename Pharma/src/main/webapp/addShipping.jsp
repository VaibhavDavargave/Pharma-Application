<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Shipping Master Page : PharmaApplication</title>
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
	<div class="container">
		<div class="row p-5">
			<div class="col-md-6 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h4 class="text-center text-dark">Add Shipping Master</h4>
						<form action="ShippingChargeServlet" method="post">
							<div class="form-group">
								<label for="inputState">Medicine Type Code</label> <select
									id="inputState" name="medicineTypeCode" class="form-control">
									<option selected>--- Select ---</option>
									<c:forEach items="${values}" var="medTypeInfo">
									<option value="${medTypeInfo.medicineTypeCode }">${medTypeInfo.medicineTypeName }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Weight Range</label> <input
									type="text" name="weightRange"  class="form-control"
									id="exampleInputPassword1" placeholder="W1">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Shipping Charge</label> <input
									type="number" min="0" max="10000" step="0.01" name="shippingCharge" class="form-control"
									id="exampleInputPassword1" placeholder="500.00">
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Add
									Shipping Master</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="allcomponent/footer.jsp"%>
</body>
</html>