<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" errorPage="IsErrorPage.jsp"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script></script>
<html>
   <head>
      <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
   </head>
   <body>
      <div class="container">
         <jsp:include page="header.jsp" />
         
         <div class="row">
            <jsp:include page="${mainContent}" />
         </div>
         
         <jsp:include page="footer.jsp" />
      </div>
   </body>
</html>


