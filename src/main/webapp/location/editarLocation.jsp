<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="location" scope="request" type="com.example.webapphr1_2023.Beans.Location" />
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../includes/bootstrap_header.jsp" />
        <title>Editar un Location</title>
    </head>
    <body>
    <div class='container mb-4'>
        <div class="row justify-content-center">
            <h1 class='mb-3'>Editar Location</h1>
            <hr>
            <form method="POST" action="LocationServlet?action=actualizar" class="col-md-6 col-lg-6">
                <input type="hidden" name="location_id" value="<%= location.getLocationId()%>"/>
                <div class="mb-3">
                    <label for="street_address">Street Address</label>
                    <input type="text" class="form-control form-control-sm" name="street_address" id="street_address"
                           value="<%= location.getStreet_address() == null ? "" : location.getStreet_address()%>">
                </div>
                <div class="mb-3">
                    <label for="postal_code">Postal Code</label>
                    <input type="text" class="form-control form-control-sm" name="postal_code" id="postal_code"
                           value="<%= location.getPostal_code() == null ? "" : location.getPostal_code()%>">
                </div>
                <div class="mb-3">
                    <label for="city">City</label>
                    <input type="text" class="form-control form-control-sm" name="city" id="city"
                           value="<%= location.getCity() == null ? "" : location.getCity()%>">
                </div>
                <div class="mb-3">
                    <label for="state_province">State Province</label>
                    <input type="text" class="form-control form-control-sm" name="state_province" id="state_province"
                           value="<%= location.getState_province() == null ? "" : location.getState_province()%>">
                </div>
                <div class="mb-3">
                    <label for="country_id">Country ID</label>
                    <input type="text" class="form-control form-control-sm" name="country_id" id="country_id"
                           value="<%= location.getCountry_id() == null ? "" : location.getCountry_id()%>">
                </div>
                <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Actualizar" class="btn btn-primary"/>
            </form>
        </div>
    </div>
    <jsp:include page="../includes/bootstrap_footer.jsp"/>
    </body>
</html>


