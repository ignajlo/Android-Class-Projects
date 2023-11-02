<!DOCTYPE html>
<html>
<head>
    <title>Products List</title>
</head>
<body>
<h1>Products</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <#list products as product>
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
