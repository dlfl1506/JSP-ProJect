<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진올리기 페이지</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/photo.css">
</head>
<body>
	<a href="/JspProject/index.jsp"> <img src="../images/loginlogo.png"></a>
	<h2>사진 올리기</h2>

	<form action="/JspProject/photo_board?cmd=upload" method="post"  enctype="multipart/form-data" >
		<section>
			<input type="hidden" name="userId" value="${sessionScope.principal.id}" />
			<input type="file" id="file" name="photo" onchange="changeValue(this)" />
			<button type="button" id="btn-upload">

				<svg id="btn_svg" class="icon" width="48" height="48"
					viewBox="0 0 48 48" fill="currentColor"
					preserveAspectRatio="xMidYMid meet">
					<path
						d="M11.952 9.778l2.397-5.994A1.778 1.778 0 0 1 16 2.667h16c.727 0 1.38.442 1.65 1.117l2.398 5.994h10.174c.982 0 1.778.796 1.778 1.778v32c0 .981-.796 1.777-1.778 1.777H1.778A1.778 1.778 0 0 1 0 43.556v-32c0-.982.796-1.778 1.778-1.778h10.174zM24 38c6.075 0 11-4.925 11-11s-4.925-11-11-11-11 4.925-11 11 4.925 11 11 11z"></path></svg>

				<div id="div_text">사진 올리기</div>
				
				<div class="img_box">
					<img id="blah" />
				</div>

			</button>

			<div>
				<textarea cols="20" placeholder="사진에 대해서 설명해주세요."
					autofocus="autofocus"  name="content"></textarea>
			</div>
		</section>
		<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
		<hr />
		<div class="section_div">
			<button type="submit" class="btn btn-primary btn_submit">올리기</button>
		</div>
	</form>


	<script type="text/javascript">
		$(function() {
			$('#btn-upload').click(function(e) {
				e.preventDefault();
				$('#file').click();
			});
		});
	</script>


	<script>
		$(document).ready(function() {
			function readURL(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
					reader.onload = function(e) {
						//파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
						
						$("#blah").attr('src', e.target.result);
						$("#div_text").remove();
						$("#btn_svg").remove();
						//이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
						//(아래 코드에서 읽어들인 dataURL형식)
					}
					reader.readAsDataURL(input.files[0]);
					//File내용을 읽어 dataURL형식의 문자열로 저장
				}
			}//readURL()--

			//file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
			$("#file").change(function() {
				readURL(this);
			});
		});
	</script>

	<script type="text/javascript">
	$('.img_box').each(function(index) { // 1번 
		$(this).children('img').one("load", function() { // 2번
			}).each(function() { imageSizeSame($(this).parent(), 0.7); // 3번
			}); $(this).parents('.card').find('.desc-noimg').addClass('desc').removeClass('desc-noimg'); // 4번 
			});

	function imageSizeSame(wrapImgClass, ratio=0) { // 1번
		var divHeight; var div = wrapImgClass; var img = div.children('img'); var divWidth = div.width(); if(!ratio || ratio == 0) { // 2번 
			divHeight = div.height(); // 3번
			} else { divHeight = divWidth * ratio; // 4번
			div.height(divHeight + 'px'); // 5번
			} var divAspect = divHeight / divWidth; // 6번
			var imgAspect = img.height() / img.width(); // 7번 
			if (imgAspect <= divAspect) { // 8번 
				// 이미지가 div보다 납작한 경우 세로를 div에 맞추고 가로는 중앙으로 맞춤
				var imgWidthActual = div.outerHeight(true) / imgAspect; var imgWidthToBe = div.outerHeight(true) / divAspect; var marginLeft = -Math.round((imgWidthActual - imgWidthToBe) / 2); img.css({ width: 'auto', 'margin-left': marginLeft + 'px', height: '100%' }); } else { // 9번
					// div가 이미지보다 납작한 경우 가로를 img에 맞추고 세로는 중앙으로 맞춤 
					var imgHeightActual = div.outerWidth(true) * imgAspect; var imgHeightToBe = div.outerWidth(true) * divAspect; var marginTop = Math.round((imgHeightActual - imgHeightToBe) / 2); img.css({ width: '100%', 'margin-left': 0, 'margin-top': - marginTop + 'px', height: 'auto' }); } }

		
</script>



</body>
</html>