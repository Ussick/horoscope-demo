<%@ page pageEncoding="UTF-8" %><%@include file="header.jsp"%>
<font color=white>
    <table>
        <form action="registration" method="post">
            <tr>
                <td>Логін(Email):</td>
                <td><input type="email" name="email" value=${user.email}></td>
                                <c:if test="${errors!=null}">
                                    <div class="invalid-feedback">
                                        <c:out value="${errors['email Error']}"/>
                                    </div>
                                </c:if>
            </tr>
            <tr>
                <td>пароль:</td>
                <td><input type="password" name="password" value=${user.password}></td>
                                <c:if test="${errors!=null}">
                                    <div class="invalid-feedback">
                                        <c:out value="${errors['password Error']}"/>
                                    </div>
                                </c:if>
            </tr>

            <tr>
                <td>Ваше ім'я:</td>
                <td><input type="text" name="username" value=""  </td>
                                <c:if test="${errors!=null}">
                                    <div class="invalid-feedback">
                                        <c:out value="${errors['username Error']}"/>
                                    </div>
                                </c:if>
            </tr>
             <tr>
                <td>День Вашого народження:</td>
                <td><input type="text" name="day" value=""></td>
                                <c:if test="${errors!=null}">
                                    <div class="invalid-feedback">
                                        <c:out value="${errors['day Error']}"/>
                                    </div>
                                </c:if>
             </tr>
             <tr>
                <td>Місяць Вашого народження:</td>
                <td><input type="text" name="month" value=""></td>
                                <c:if test="${errors!=null}">
                                    <div class="invalid-feedback">
                                        <c:out value="${errors['month Error']}"/>
                                    </div>
                                </c:if>
             </tr>
            <tr>
                 <td>Рік Вашого народження:</td>
                 <td><input type="text" name="year" value=""></td>
                                <c:if test="${errors!=null}">
                                    <div class="invalid-feedback">
                                        <c:out value="${errors['year Error']}"/>
                                    </div>
                                </c:if>
            </tr>

            <tr>
                 <td>Gender:</td>
                  <td>male<input type="radio" name="gender"
                                     value="male" >
                             female<input type="radio" name="gender"
                                 value="female" checked/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                <div class="g-recaptcha" data-sitekey="6LecIkMhAAAAAEp6h_WE0DgN92uVJWw-kbIgLDZW"></div>
                <input type="submit" value="SEND"/></td>
            </tr>
        </form>
    </table>
    <h1 style='font-size:25px' align='center'>
            ${result}
    </h1>

</font>
<%@include file="footer2.jsp"%>





