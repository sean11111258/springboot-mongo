<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>主頁</title>
</head>
<body>
	<form action="addUser">
		<div>輸入id及姓名增加user</div>
		<div>
			id: <input type="text" name="id" value="id"><br>
		</div>
		<div>
			姓名: <input type="text" name="name"><br>
		</div>
		<div>
			<input type="submit"><br>
		</div>
	</form>

	<form action="getUser">
		<div>使用id搜尋user</div>
		<div>
			<input type="text" name="id" value="id"><br> <input
				type="submit"><br>
		</div>
	</form>

	<form action="deleteUser">
		<div>使用id刪除user</div>
		<div>
			<input type="text" name="id" value="id"><br> <input
				type="submit"><br>
		</div>
	</form>
</body>
</html>