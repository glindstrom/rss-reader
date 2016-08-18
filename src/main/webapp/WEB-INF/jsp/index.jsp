
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>RSS Feed</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>Otsikko</th>
                    <th>Kuvaus</th>
                    <th>Aihealue</th>
                    <th>Julkaistu</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="item" >
                    <tr>
                        <td><a href="${item.link}">${item.title}</a></td>
                        <td>${item.title}</td>
                        <td>${item.category}</td>
                        <td class="publication-date"><fmt:formatDate value="${item.publicationDate}" pattern="dd.MM.yyyy HH:mm"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
