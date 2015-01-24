<head>
	<title>Product [show]</title>
</head>
<body>
	<p>
		<b>Image:</b>
		<img src="data:image/jpg;base64,<c:out value='${product.imageByteArray}'/>" />
	</p>

	<a href="${pageContext.request.contextPath}/products/${product.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/products">Back</a>
</body>