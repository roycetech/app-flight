<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${successList ne null}">    
   <c:choose>
       <c:when test="${successList.size() == 1}">
           <div class="success_message alert alert-success" role="alert" 
                style="display: none">
               <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
               ${successList.get(0)}
           </div>
       </c:when>
       <c:otherwise>
           <ol class="success_message" style="display: none">
           <c:forEach var="success" items="${successList}">
                <li><div class="alert alert-success" role="alert">
                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                ${success}
                </div><li>
           </c:forEach>
           </ol>
       </c:otherwise>
   </c:choose>
</c:if>