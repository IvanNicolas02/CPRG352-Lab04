<%-- 
    Document   : editnote
    Created on : Feb 4, 2022, 6:17:07 PM
    Author     : ivanc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Note Keeper</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>Edit Note</h2>
        <div>
            <form action = "note" method = "post">
                <label>Title:</label>
                <input type ="text" name="title" value="${note.title}">
                <br>
                <label>Contents:</label>
                <textarea name="message" rows="10" cols="25">${note.message}</textarea>
                <br>
                <input type="submit" name="action" value="Save">
                <input type="submit" name="action" value="Delete">
                <input type="submit" name="action" value="Create">
            </form>
        </div>
    </body>
</html>
