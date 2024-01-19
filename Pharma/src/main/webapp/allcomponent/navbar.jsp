<%@page import="com.excelr.pharma.vo.AdminVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!-- Image and text -->


<div class="container-fluid p-3 bg-light">
	<div class="row ">
		<div class="col-md-3 ">
			<nav class="navbar navbar-light">
				<a class="navbar-brand" href="index.jsp"> <img
					src="images/logo.png" width="30" height="30"
					class="d-inline-block align-top" alt=""> Pharma Application
				</a>
			</nav>
		</div>
		<div class="col-md-6"></div>
		<div class="col-md-3 text-right">
			
			
			
			<c:if test="${not empty adminObj}">
				<a href="" class="btn btn-success" data-toggle="modal" data-target="#exampleModal"> <i class="fa fa-user m-1"
					aria-hidden="true"></i> ${adminObj.name}
				</a>
				<a href="LogoutServlet" class="btn btn-warning"><i
					class="fa fa-sign-out m-1" aria-hidden="true"></i> Logout </a>
			</c:if>
			
			
			
			<%-- <c:if test="${empty adminObj}">
				<a href="login.jsp" class="btn btn-primary"> <i
					class="fa fa-sign-in m-1" aria-hidden="true"></i> Login
				</a>

			</c:if> --%>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title text-dark" id="exampleModalLabel">Admin Profile Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body text-center text-info">
        	<h5><i class="fa fa-user-md m-1" aria-hidden="true"></i>Name: ${adminObj.name}</h5>
        	<h5><i class="fa fa-envelope m-1" aria-hidden="true"></i>Email: ${adminObj.email}</h5>
        	<h5><i class="fa fa-unlock m-1" aria-hidden="true"></i>Password: ${adminObj.password}</h5>
      </div>
    </div>
  </div>
</div>


