<%@page import="com.excelr.pharma.vo.MedicineInfo"%>
<%@page import="com.excelr.pharma.dao.PharmaDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${medicine.medicineCode }: PharmaApplication</title>
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


	<c:if test="${not empty medicine}">
		<div class="container">
			<div class="row p-5">
				<div class="col-md-6 offset-md-4">
					<div class="card">
						<div class="card-body">
							<h4 class="text-center text-dark">${medicine.medicineCode }</h4>
							<form action="UpdateMedicineServlet" method="post">
								<div class="form-group">
									<label for="exampleInputEmail1">Medicine Code</label> <input
										type="text" name="medicineCode" class="form-control"
										id="exampleInputEmail1" value="${medicine.medicineCode }" readonly="readonly"
										aria-describedby="emailHelp" placeholder="MC_XXX">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Medicine Name</label> <input
										type="text" name="medicineName" class="form-control"
										id="exampleInputPassword1" value="${medicine.medicineName }" placeholder="Dolo">
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary">Edit
										Medicine</button>
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