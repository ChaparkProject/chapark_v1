<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="IsErrorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en"> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Your Page Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="/asset/css/common.css" rel="stylesheet" type="text/css">
    <link href="/asset/css/layout.css" rel="stylesheet" type="text/css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="container-fluid">
        <div id="mainContent" class="d-flex align-items-center justify-content-center"></div>
    </div>
    <jsp:include page="footer.jsp" />
    
    <script>
		$(document).ready(function() {
		    adjustMainContentHeight();
		
		    $(window).resize(function() {
		        adjustMainContentHeight();
		    });
		
		    function adjustMainContentHeight() {
		        var headerHeight = $('.header').outerHeight() || 0;
		        var footerHeight = $('footer').outerHeight() || 0;
		        var mainContentHeight = headerHeight + footerHeight;
		
		        $('#mainContent').css('min-height', 'calc(100vh - ' + mainContentHeight + 'px)');
		        $('#mainContent').css('max-height', 'calc(100vh - ' + mainContentHeight + 'px)');
		        $('#mainContent').css('margin-top', headerHeight + 'px');
		        $('#mainContent').css('margin-bottom', footerHeight + 'px');
		    }
		});

        function pageChange(page) {
            $.ajax({
                type: "POST",
                url: "/changePage",
                data: {
                    'page': page,
                },
                success: function(result) {
                    $('#mainContent').html(result);
                    $('.nav-link').removeClass('active');
                    $(element).addClass('active');
                },
                error: function() {
                    alert("오류가 발생했습니다.");
                }
            });
        }
    </script>
</body>
</html>
