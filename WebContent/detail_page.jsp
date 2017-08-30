<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="detail" class="box">
<h3>Program Details</h3>
<ul  class="linkedList">
<li>
<c:forEach items="${programs}" var="p" varStatus="count"> 
	<div id="${p.programId}">
	<p><strong>${p.programName}</strong></p>
	<c:forEach items="${p.reviews}" var="r" varStatus="count">
		<c:if test="${r.detail}">
		${r.review}
		</c:if>
		<c:if test="${r.firstReview}">
			<form action="posting" method="post">
			<div class="blockquote">
			${r.review}
			<cite>
			${r.fullName}
			<c:if test="${uid==r.userId || utype=='admin'}">
				<input type="submit" value="Delete" class="comment"/>
				<input type="hidden" name="pid" value="${p.programId}">
				<input type="hidden" name="rid" value="${r.reviewId}"/>
				<input type="hidden" name="action" value="Delete"/>
			</c:if>
			</cite>
			</div>
			</form>
		</c:if>
		<c:if test="${r.totalCount == 1 && loggedIn}">
		<div class="blockquote">
			<form action="posting" method="post">
		    <div align="right">
		        <textarea cols="65" rows="7" name="Post" class="comment"></textarea>
		        <br><input type="submit" value="Post Review" class="comment"/>
		        <input type="hidden" name="action" value="Post">
		        <input type="hidden" name="uid" value="${uid}">
		        <input type="hidden" name="pid" value="${p.programId}">
		    </div> 
			</form>
		</div>
		</c:if>
		<c:if test="${r.reply}">
			<form action="posting" method="post">
			<div class="blockquote_reply">
			${r.review} 
			<cite>
			${r.fullName}
			<c:if test="${uid==r.userId || utype=='admin'}">
				<input type="submit" value="Delete" class="comment"/>
				<input type="hidden" name="rid" value="${r.reviewId}"/>
				<input type="hidden" name="pid" value="${p.programId}">
				<input type="hidden" name="action" value="Delete"/>
			</c:if>
			</cite>
			</div>
			</form>
		</c:if>
		<c:if test="${count.last && r.totalCount != 1 && loggedIn}">
		<div class="blockquote_reply">
			<form action="posting" method="post">
		    <div align="right">
		        <textarea cols="60" rows="1" name="Reply" class="comment"></textarea>
		        <br><input type="submit" value="Reply" class="comment"/>
		        <input type="hidden" name="action" value="Reply">
		        <input type="hidden" name="uid" value="${uid}">
		        <input type="hidden" name="pid" value="${p.programId}">
		    </div> 
			</form>
		</div>
		</c:if>
	</c:forEach>
	<br class="clear" />
	</div>
</c:forEach>
</li>
</ul>
</div>