<%@ page pageEncoding="UTF-8" %>
</div>

<div id="sidebar">
    <table border=1>
        <tr>
            <td width="252" align="left">
                <font color=white>
                        Ви не авторізовані
                </font>
            </td>
        </tr>
    </table>
    <font color=white>
    <h3>Отримати гороскоп без реєстрації та СМС :)</h3>
    </font>
    <ul>
        <font color=white>

            <table>
                <form action="impersonal" method="get">
                    <tr>
                      <td>Ваше ім'я:</td>
                      <td><input type="text" name="name" value=""></td>
                    </tr>
                    <tr>
                        <td>День Вашого народження:</td>
                        <td><input type="text" name="day" value=""></td>
                    </tr>
                    <tr>
                        <td>Місяць Вашого народження:</td>
                        <td><input type="text" name="month" value=""></td>
                    </tr>
                    <tr>
                        <td>Рік Вашого народження:</td>
                        <td><input type="text" name="year" value=""></td>
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
                        <td><input type="submit" value="SEND"/></td>
                    </tr>
                </form>
            </table>
    </ul>
    </font>
</div>
</div>
</div>
</div>
</div>
<div id="footer">
    <p>Copyright (c) </p>
</div>
<!-- end #footer -->
</body>

<script>

    function sum(id, data) {
        var val1 = document.getElementById(id).value;
        if ((+val1 + +data) > 0) {
            document.getElementById(id).value = +document.getElementById(id).value + +data;
        }
    }

    function sayInfo(qId, product) {
        console.log("Вы купили " + product + " в количестве " + document.getElementById(qId).value + " шт.");
    }

    function sendParam(operId, quanPid, pId) {
        var oper = document.getElementById(operId).value;
        var quan = document.getElementById(quanPid).value;
        var prod = document.getElementById(pId).value;

        //quantity=1&id=1&operation=Buy
        //quantity=1&id=1&operation=Change

        $.ajax({
            url: "cart",         /* Куда пойдет запрос */
            method: "post",             /* Метод передачи (post или get) */
            data: "quantity=" + +quan + "&id=" + +prod + "&operation=" + oper,     /* Параметры передаваемые в запросе. */
            success: function (data) {   /* функция которая будет выполнена после успешного запроса.  */
                var comma = ",";
                var arrayOfStrings = data.split(comma);
                document.getElementById("cartSize").innerHTML = arrayOfStrings[0];
				document.getElementById("totalSum").innerHTML = arrayOfStrings[1];
            }
        });
    }

    function sumCart(id, data) {
        var val1 = document.getElementById(id).value;
        if ((+val1 + +data) > -1) {
            document.getElementById(id).value = +document.getElementById(id).value + +data;
        }
    }

</script>
</html>
