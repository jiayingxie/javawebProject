<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register Page</title>
		<%-- static includes the base URL, CSS style, jQuery lib --%>
		<%@ include file="/pages/common/head.jsp"%>
		<script type="text/javascript">
			<!-- after the page loading successfully -->
			$(function (){

				// check whether the username exists or not
				$("#username").blur(function (){
					// 1. get the username
					let username = this.value;

					// 2. send ajax request
					// $.getJSON(url, data, callback);
					$.getJSON("http://localhost:8080/book/userServlet", "action=ajaxExistsUsername&username=" + username, function (data) {
						if (data.existsUsername) {
							// display hint：the username is already in the database
							$("span.errorMsg").text("Username is already exists.");
						} else {
							// ??? 不应是 errorMsg
							$("span.errorMsg").text("Username is valid.");
						}
					});
				});

				// bound action event for captcha image, so that click the picture will refresh the picture
				$("#code_img").click(function () {
					// 在事件响应的function 函数中有一个this 对象。这个this 对象，是当前正在响应事件的dom 对象
					/* 如果只是下面这样写，在火狐、IE中，验证码刷新一次就不再刷新。因为：浏览器为了让请求速度更快，
						会把每次请求的内容缓存到浏览器端（要么硬盘上，要么内存中） */
					// this.src = "${basePath}kaptcha.jpg";
					this.src = "${basePath}kaptcha.jpg?d=" + new Date();
				});


				//	bound action event for register
				$("#sub_btn").click(function (){
					// 验证用户名：必须由字母，数字下划线组成，并且长度为5 到12 位
					// 1. get the text int username input field
					let usernameText = $("#username").val();
					// 2. create a regex object
					let usernamePattern = /^\w{5,12}$/;
					// 3. valid the format of username using the regex pattern
					if (!usernamePattern.test(usernameText)) {
						// display hint
						$("span.errorMsg").text("Username is invalid.");
						return false;
					}

					// 验证密码：必须由字母，数字下划线组成，并且长度为5 到12 位
					let passwordText = $("#password").val();
					let passwordPattern = /^\w{5,12}$/;
					if (!passwordPattern.test(passwordText)) {
						// display hint
						$("span.errorMsg").text("Password is invalid.");
						return false;
					}

					// 验证确认密码：和密码相同
					let repwdText = $("#repwd").val();
					if (repwdText !== passwordText) {
						// display hint
						$("span.errorMsg").text("Password you input twice is inconsistent.");
						return false;
					}

					// 邮箱验证：xxxxx@xxx.com
					let emailText = $("#email").val();
					let emailPattern = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					if (!emailPattern.test(emailText)) {
						// display hint
						$("span.errorMsg").text("Email is invalid.");
						return false;
					}

					// ??? 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
					let codeText = $("#code").val();
					// trim the space between the code text
					codeText = $.trim(codeText);
					if (codeText == null || codeText == "") {
						// display hint
						$("span.errorMsg").text("Code is invalid.");
						return false;
					}

					// if all input is valid, erase the error message
					$("span.errorMsg").text("");
				});
			});
		</script>
		<style type="text/css">
			.login_form{
				height:420px;
				margin-top: 25px;
			}

		</style>
	</head>

	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

		<div class="login_banner">
			<div id="l_content">
				<span class="login_word">Register Page</span>
			</div>

			<div id="content">
				<div class="login_form">
					<div class="login_box">
						<div class="tit">
							<h1>Register</h1>
							<%-- display hint--%>
							<span class="errorMsg">${ requestScope.msg }</span>
						</div>

						<div class="form">
							<form action="userServlet" method="post">
								<%-- to determine which method(method has the same name with the action's value) to use in Servlet --%>
								<%-- 方便调用Servlet中对应的方法，方法名要和action的value的值一致 --%>
								<input type="hidden" name="action" value="regist" />

								<label>Username：</label>
								<input class="itxt" type="text" placeholder="Please enter your username"
									   autocomplete="off" tabindex="1" name="username" id="username"
									   value="${requestScope.username}"/>
								<br /> <br />

								<label>Password：</label>
								<input class="itxt" type="password" placeholder="Please enter your password"
									   autocomplete="off" tabindex="1" name="password" id="password" />
								<br /> <br />

								<label>Re-Password：</label>
								<input class="itxt" type="password" placeholder="Confirm your password"
									   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
								<br /> <br />

								<label>Email：</label>
								<input class="itxt" type="text" placeholder="Please enter your email"
									   autocomplete="off" tabindex="1" name="email" id="email"
									   value="${requestScope.email}"/>
								<br /> <br />

								<label>Captcha：</label>
								<input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
								<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 100px; height: 28px">
								<br /> <br />

								<input type="submit" value="Register" id="sub_btn" />

							</form>
						</div>

					</div>
				</div>
			</div>
		</div>

		<!-- static includes the footer page  -->
		<%@ include file="/pages/common/footer.jsp"%>
	</body>
</html>