<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/views/common/includeHeader.jsp" %>
<div class="container">
  	<form class="form-horizontal formValidate" role="form" action="${basePath }/cms/board/addCmsBoard" method="post">
  		<div class="col-md-12" style=" margin-top:30px;">
			<div class="panel panel-grey">
	        	<div class="yhxx">
	          		<label class="label" style="color:#333">填写留言信息</label>
	          	</div>
	          	<div class="panel-body">
	            	<div class="col-sm-12">
						<div class="form-group">
	                        <label class="col-sm-2 control-label">姓名<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" name="name" class="form-control" required="" maxlength="200"></div>
	                        <label class="col-sm-2 control-label">联系电话<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" name="tel" class="form-control" required="" maxlength="50"></div>
	                    </div>
	                    <div class="form-group">
	                      	
						</div>
	                    <div class="form-group">
	                    	<label class="col-sm-2 control-label">公司名称<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" name="company" class="form-control" required="" minlength="1" maxlength="200" ></div>
	                        <label class="col-sm-2 control-label">邮箱<font>*</font></label>
	                        <div class="col-sm-4"><input type="text" name="email" class="form-control" email="email" required=""  minlength="5" maxlength="200" ></div>
	                    </div>
	                    <div class="form-group">
	                      	<label class="col-sm-2 control-label">留言标题<font>*</font></label>
							<div class="col-sm-10"><input type="text" name="stitle" class="form-control" required="" minlength="1" maxlength="200"></div>
						</div>
							<div class="form-group">
	                        	<label class="col-sm-2 control-label">内容<font>*</font></label>
	                            <div class="col-sm-10">
	                            	<textarea id="noticeText" name="content" style="display: none;" required>${notice.noticeText }</textarea>
	                            	<div class="summernote" htmlTxt="content">${notice.noticeText }</div>
	                            </div>
	                        </div>
	                    
					</div>
				</div>
			</div>
		</div>
        <div class="text-center zc-bt">
      		<button class="btn btn-primary btn-1" type="submit">提交</button>
        	<button class="btn btn-1" type="reset">重置</button>
      	</div>
	</form>
</div>

<%@ include file="/include/includeJs.jsp" %>
<script>
function saveNoticeDraft(){
	$("#noticeDraft").val(true);
	$('.formValidate').submit();
}
</script>
<%@ include file="/include/includeEditHtml.jsp" %>
<%@ include file="/include/includeFooter.jsp" %>