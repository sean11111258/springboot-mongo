<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<form action="addUser">
		<div>
			id: <input type="text" name="id"><br>
		</div>
		<div>
			name: <input type="text" name="name"><br>
		</div>
		<div>
			<input type="submit"><br>
		</div>
	</form>

	<form action="getUser">
		<div>
			搜尋user <input type="text" name="id" value="id"><br> <input
				type="submit"><br>
		</div>
	</form>
</body>
</html>