<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
table {
	width: 95%;
	align: right;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#01 th {
	background-color: black;
	color: white;
}

.btn {
	background-color: DodgerBlue;
	border: none;
	color: white;
	padding: 12px 30px;
	cursor: pointer;
	font-size: 20px;
	float: right;
}

/* Darker background on mouse-over */
.btn:hover {
	background-color: RoyalBlue;
}
</style>

<!-- Add icon library -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<%-- SCRIPTS FOR PDF CONVERTER --%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>

<script>
	function getPDF() {

		var HTML_Width = $(".canvas_div_pdf").width();
		var HTML_Height = $(".canvas_div_pdf").height();
		var top_left_margin = 15;
		var PDF_Width = HTML_Width + (top_left_margin * 2);
		var PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
		var canvas_image_width = HTML_Width;
		var canvas_image_height = HTML_Height;

		var totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;

		html2canvas($(".canvas_div_pdf")[0], {
			allowTaint : true
		}).then(
				function(canvas) {
					canvas.getContext('2d');

					console.log(canvas.height + "  " + canvas.width);

					var imgData = canvas.toDataURL("image/jpeg", 1.0);
					var pdf = new jsPDF('p', 'pt', [ PDF_Width, PDF_Height ]);
					pdf.addImage(imgData, 'JPG', top_left_margin,
							top_left_margin, canvas_image_width,
							canvas_image_height);

					for (var i = 1; i <= totalPDFPages; i++) {
						pdf.addPage(PDF_Width, PDF_Height);
						pdf.addImage(imgData, 'JPG', top_left_margin,
								-(PDF_Height * i) + (top_left_margin * 4),
								canvas_image_width, canvas_image_height);
					}

					pdf.save("Application.pdf");
				});
	};
</script>
</head>
<body>

	<c:forEach items="${requestScope.Applications}" var="application">
		<c:if test="${application.id eq requestScope['refidd'] }">
			<div class="canvas_div_pdf" style="align: center;">
				<h3 style="text-align: center;">
					CONGRATULATIONS! Your Reference No : <span style="color: green;"><c:out
							value="${application.getREFNO()}"></c:out></span>
				</h3>

				<table id="t01">

					<caption style="text-align: center;padding: 10px;">
						<strong>APPLICATION FOR JOURNAL PUBLICATION INCENTIVE</strong>
					</caption>

					<tr>
						<td style="width: 30%">PAPER TITLE</td>
						<td style="width: 70%"><c:out
								value="${application.getTitle()}"></c:out></td>
					</tr>

					<tr>
						<td width="30%">JOURNAL NAME</td>
						<td><c:out value="${application.getjournal()}"></c:out></td>
					</tr>

					<tr>
						<td width="30%">DOI</td>
						<td><c:out value="${application.getDOI() }"></c:out></td>
					</tr>

					<tr>
						<td width="30%">JOURNAL CATEGORY (Q1, Q2, Q3, Q4)</td>
						<td><c:out value="${application.getJCAT() }"></c:out></td>
					</tr>

					<tr>
						<td>FIRST AUTHOR</td>
						<td><c:out value="${application.getFIRSTAUTHOR() }"></c:out></td>
					</tr>

					<tr>
						<td>MEMBER OF FIRST AUTHOR</td>
						<td><c:out value="${application.getFAUTHOR() }"></c:out></td>
					</tr>

					<tr>
						<td>SECOND AUTHOR</td>
						<td><c:out value="${application.getSECONDAUTHOR() }"></c:out></td>
					<tr>
					<tr>
						<td>MEMBER OF SECOND AUTHOR</td>
						<td><c:out value="${application.getSAUTHOR() }"></c:out></td>
					</tr>

					<tr>
						<td>THIRD AUTHOR</td>
						<td><c:out value="${application.getTHIRDAUTHOR() }"></c:out></td>
					</tr>

					<tr>
						<td>MEMBER OF THIRD AUTHOR</td>
						<td><c:out value="${application.getTAUTHOR() }"></c:out></td>
					</tr>

					<tr>
						<td>OTHER AUTHORS FROM RIT</td>
						<td><c:out value="${application.getOTHERAUTHOR() }"></c:out></td>
					</tr>
				</table>
			</div>
			
			<p hidden>alert()</p>
			<script>
				alert("Your Application has been submitted!!!");
			</script>
		</c:if>

	</c:forEach>

	<button onclick="getPDF()" class="btn">
		<i class="fa fa-download"></i>Download
	</button>
	<%--
<c:forEach items="${requestScope.Applications}" var="application">
		<c:if test="${application.id eq requestScope['refidd'] }">
				<h3 style="text-align:center;">CONGRATULATIONS! Your Reference No : <span style="color:green;"><c:out value="${application.getREFNO()}"></c:out></span></h3>
				<table>
					<tbody>
						<tr>
							<th>ID</th>
							<th>Paper Title</th>
							<th>Journal Name</th>
							<th>DOI</th>
							<th>Journal Category</th>
							<th>First Author Name</th>
							<th>First Author</th>
							<th>Second Author Name</th>
							<th>Second Author</th>
							<th>Third Author Name</th>
							<th>Third Author</th>
							<th>Incentive for First Author</th>
							<th>Incentive for Second Author</th>
							<th>Incentive for Third Author</th>
						</tr>
						<tr>
							<td><c:out value="${application.id}"></c:out></td>
							<td><c:out value="${application.getTitle()}"></c:out></td>
							<td><c:out value="${application.getjournal()}"></c:out></td>
							<td><c:out value="${application.getDOI() }"></c:out></td>
							<td><c:out value="${application.getJCAT() }"></c:out></td>
							<td><c:out value="${application.getFIRSTAUTHOR() }"></c:out></td>
							<td><c:out value="${application.getFAUTHOR() }"></c:out></td>
							<td><c:out value="${application.getSECONDAUTHOR() }"></c:out></td>
							<td><c:out value="${application.getSAUTHOR() }"></c:out></td>
							<td><c:out value="${application.getTHIRDAUTHOR() }"></c:out></td>
							<td><c:out value="${application.getTAUTHOR() }"></c:out></td>
							<td><c:out value="${application.getINCENTIVEF() }"></c:out></td>
							<td><c:out value="${application.getINCENTIVES() }"></c:out></td>
							<td><c:out value="${application.getINCENTIVET() }"></c:out></td>								
						</tr>
					</tbody>
				</table>
			</c:if>
	</c:forEach>

--%>



	<%--
	<c:if test="${not empty requestScope.Applications}">
	
		<table>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Paper Title</th>
					<th>Journal Name</th>
					<th>DOI</th>
					<th>Journal Category</th>
					<th>First Author Name</th>
					<th>First Author</th>
					<th>Second Author Name</th>
					<th>Second Author</th>
					<th>Third Author Name</th>
					<th>Third Author</th>
					<th>Incentive for First Author</th>
					<th>Incentive for Second Author</th>
					<th>Incentive for Third Author</th>
				</tr>
				<c:forEach items="${requestScope.Applications}" var="application">
					
					<tr>
						<td><c:out value="${application.id}"></c:out></td>
						<td><c:out value="${application.getTitle()}"></c:out></td>
						<td><c:out value="${application.getjournal()}"></c:out></td>
						<td><c:out value="${application.getDOI() }"></c:out></td>
						<td><c:out value="${application.getJCAT() }"></c:out></td>
						<td><c:out value="${application.getFIRSTAUTHOR() }"></c:out></td>
						<td><c:out value="${application.getFAUTHOR() }"></c:out></td>
						<td><c:out value="${application.getSECONDAUTHOR() }"></c:out></td>
						<td><c:out value="${application.getSAUTHOR() }"></c:out></td>
						<td><c:out value="${application.getTHIRDAUTHOR() }"></c:out></td>
						<td><c:out value="${application.getTAUTHOR() }"></c:out></td>
						<td><c:out value="${application.getINCENTIVEF() }"></c:out></td>
						<td><c:out value="${application.getINCENTIVES() }"></c:out></td>
						<td><c:out value="${application.getINCENTIVET() }"></c:out></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

--%>
</body>
</html>