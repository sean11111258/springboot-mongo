<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�D��</title>
</head>
<body>
	<form action="addUser">
		<div>��Jid�Ωm�W�W�[user</div>
		<div>
			id: <input type="text" name="id" value="id"><br>
		</div>
		<div>
			�m�W: <input type="text" name="name"><br>
		</div>
		<div>
			<input type="submit"><br>
		</div>
	</form>

	<form action="getUser">
		<div>�ϥ�id�j�Muser</div>
		<div>
			<input type="text" name="id" value="id"><br> <input
				type="submit"><br>
		</div>
	</form>

	<form action="deleteUser">
		<div>�ϥ�id�R��user</div>
		<div>
			<input type="text" name="id" value="id"><br> <input
				type="submit"><br>
		</div>
	</form>
</body>
</html>