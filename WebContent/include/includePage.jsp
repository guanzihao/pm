<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<link href="${basePath }/resource/css/xiaozujian.css" rel="stylesheet" />
<script type="text/javascript">
	function goFirstPage(){$("#currentPage").val(1);goPage();}
    function goLastPage(){$("#currentPage").val(${pageBean.totalPage});goPage();}
    function goNextPage(){$("#currentPage").val(${pageBean.nextPage});goPage();}
    function goPrePage(){$("#currentPage").val(${pageBean.prePage});goPage();}
    function goPage(){$("#pageForm").submit();}
    function goSubmitSort(orderName,orderType){$("#searchOrderName").val(orderName);$("#searchOrderType").val(orderType);goPage();}
    function goSubmit(){$("#searchOrderName").val("");goPage();}
    $(function(){
    	document.onkeydown = function(e){var ev = document.all ? window.event : e;if(ev.keyCode==13){goSubmit();}}
    });
</script>
<style>

	

</style>
<input type="hidden" name="searchOrderName" id="searchOrderName" value="${pageBean.searchBean.searchOrderName }">
<input type="hidden" name="searchOrderType" id="searchOrderType" value="${pageBean.searchBean.searchOrderType }">
<div class="row" style="margin: 20px 0px;">
	<div class="col-xs-12 col-sm-6">
		<label class="tables_search_label" style="   font-weight: 400; ">
			<span class="pagSpan">显示</span><select name="pageSize" id="pageSize" class="form-control input-sm my_select" onchange="goPage()">${pageBean.pageSize}
				<option value="10" <c:if test="${pageBean.pageSize==10}">selected="selected"</c:if>>10</option>
				<option value="20" <c:if test="${pageBean.pageSize==20}">selected="selected"</c:if>>20</option>
				<option value="50" <c:if test="${pageBean.pageSize==50}">selected="selected"</c:if>>50</option>
				<option value="100" <c:if test="${pageBean.pageSize==100}">selected="selected"</c:if>>100</option>
			</select><span class="pagSpan">条&nbsp;共&nbsp;</span><strong>${pageBean.totalSize }</strong><span class="pagSpan">&nbsp;条&nbsp;&nbsp;第&nbsp;</span><input style="width: 40px!important;padding: 4px !important;" type="text" digits="true" required class="form-control input-sm my_input" name="currentPage" id="currentPage" size="1" value="${pageBean == null ? 0 : pageBean.currentPage }" onchange="goPage()">&nbsp;/&nbsp;<span class="pagSpan">
			<strong>
			
			${pageBean.totalPage }</strong>&nbsp;页</span>
		</label>
	</div>
	<div class="col-xs-12 col-sm-6">
		<div style="float:right;margin:0;">
			<ul class="pagination" style="margin: 2px 0;white-space: nowrap;">
				<c:choose>
					<c:when test="${pageBean.hasPrePage}">
						<li class="paginate_button"><a href="javascript:;" onclick="goFirstPage()">首页</a></li>
						<li class="paginate_button"><a href="javascript:;" onclick="goPrePage()">前一页</a></li>
					</c:when>
					<c:otherwise>
						<li class="paginate_button disabled"><a href="javascript:;">首页</a></li>
						<li class="paginate_button disabled"><a href="javascript:;">前一页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageBean.hasNextPage}">
						<li class="paginate_button"><a href="javascript:;" onclick="goNextPage()">后一页</a></li>
						<li class="paginate_button"><a href="javascript:;" onclick="goLastPage()">末页</a></li>
					</c:when>
					<c:otherwise>
						<li class="paginate_button disabled"><a href="javascript:;">后一页</a></li>
						<li class="paginate_button disabled"><a href="javascript:;">末页</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>