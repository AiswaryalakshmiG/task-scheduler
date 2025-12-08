<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login - Task Scheduler</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body class="bg-light">
	<div class="container">
		<div class="row justify-content-center mt-5">
			<div class="col-md-6">
				<div class="card shadow-sm">
					<div class="card-body">
						<h3 class="card-title mb-3">Sign in</h3>
						<form action="${pageContext.request.contextPath}/login"
							method="post">
							<div class="mb-3">
								<label for="username" class="form-label">Username</label> <input
									id="username" name="username" class="form-control" required
									autofocus>
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Password</label> <input
									id="password" type="password" name="password"
									class="form-control" required>
							</div>

							<!-- Use a hidden CSRF token here later when you add CSRF protection -->
							<button type="submit" class="btn btn-primary">Login</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
