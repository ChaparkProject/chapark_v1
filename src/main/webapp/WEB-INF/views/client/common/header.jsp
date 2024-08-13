<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-dark bg-dark fixed-top header">
	<div class="container-fluid">
		<a class="navbar-brand" href="/main.do">chapark</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
			<div class="offcanvas-header">
				<c:choose>
					<c:when test="${not empty sessionScope.userInfo}">
						<nav class="nav">
							<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">
								<span id="mberName">${sessionScope.mberName}</span>
							</h5>
							<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">
								<a class="nav-link " href="/logout.do">Logout</a>
							</h5>
							<c:if test="${userInfo.MBER_AUTH eq 'A'}">
								<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">
									<a class="nav-link" href="/admin/main.do">관리자 페이지</a>
								</h5>
							</c:if>
						</nav>
					</c:when>
					<c:otherwise>
						<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">
							<a class="nav-link" href="/login.do" >Login</a>
						</h5>
						<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">
							<a class="nav-link" href="/join.do" >Join</a>
						</h5>
					</c:otherwise>
				</c:choose>
				<button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
					<li class="nav-item">
						<a class="nav-link" href="/main.do">Home</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>

<script>
	//사용자 이름 김**으로 표시되게 처리
	document.addEventListener("DOMContentLoaded", function() {
		const mberNameElement = document.getElementById('mberName');
		if (mberNameElement) {
			let mberName = mberNameElement.textContent;
			if (mberName.length > 1) {
				mberNameElement.textContent = mberName.charAt(0) + '**';
			}
		}
	});
</script>
