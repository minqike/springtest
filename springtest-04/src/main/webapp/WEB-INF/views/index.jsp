<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>
    Hello index <br />
    <a href="<%=request.getContextPath()%>/hello" >hello</a><br />
    <a href="<%=request.getContextPath()%>/string" >返回字符串</a>
    <a href="<%=request.getContextPath()%>/map" >返回JSON字符串</a>
</body>
</html>