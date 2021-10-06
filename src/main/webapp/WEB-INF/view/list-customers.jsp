<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
    <title>List Customers</title>

    <!-- reference our style sheet -->

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <!-- put new button: Add Customer -->

        <input type="button" value="Add Customer"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button"
        />

        <!--  add our html table here -->

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <!-- loop over and print our customers -->
            <c:forEach var="tempCustomer" items="${customers}">

                <!-- construct an update link with customer id, we create a variable called updateLink and clicking
                on this link will go to customer/showFormForUpdate so we can use it later in table data down below-->
                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>
                <!-- construct a delete link !-->
                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>

                <tr>
                    <td> ${tempCustomer.firstName} </td>
                    <td> ${tempCustomer.lastName} </td>
                    <td> ${tempCustomer.email} </td>
                    <td>
                        <!-- display the update link ! and we use the variable we created above called updateLink-->
                        <a href="${updateLink}"> Update </a>
                    </td>
                    <td>
                        <!-- display the delete link ! and we use the variable we created above called deleteLink
                        we will add some javascript code to prompt the user with the delete dialog, if the user
                        clicks cancel then we return false and id he press ok then we proceed with the delete link-->
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this customer?')))
                               return false">Delete </a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>


</body>

</html>









