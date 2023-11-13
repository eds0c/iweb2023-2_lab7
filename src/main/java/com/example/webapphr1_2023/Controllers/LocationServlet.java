package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Job;
import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Daos.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        LocationDao locationDao = new LocationDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                ArrayList<Location> listaLocations = locationDao.obtenerListaLocations();
                request.setAttribute("lista", listaLocations);
                view = request.getRequestDispatcher("location/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
                //request.setAttribute("listaPaises",countriesDao.lista());
                view = request.getRequestDispatcher("location/nuevoLocation.jsp");
                view.forward(request, response);
                break;

            case "editar":
                if (request.getParameter("id") != null) {
                    String locationIdString = request.getParameter("id");
                    int locationId = 0;
                    try {
                        locationId = Integer.parseInt(locationIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");

                    }

                    Location loc = locationDao.obtenerLocations(locationId);

                    if (loc != null) {
                        request.setAttribute("Location", loc);
                        view = request.getRequestDispatcher("location/editarLocation.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("LocationServlet");
                    }

                } else {
                    response.sendRedirect("EmployeeServlet");
                }
                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String locationIdString = request.getParameter("id");
                    int locationId  = 0;
                    try {
                        locationId = Integer.parseInt(locationIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("LocationServlet");
                    }

                    Location loc = locationDao.obtenerLocations(locationId);

                    if (loc != null) {
                        locationDao.borrarLocations(locationId);
                    }
                }

                response.sendRedirect("LocationServlet");
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        LocationDao locationDao = new LocationDao();

        Location location = new Location();
        location.setStreet_address(request.getParameter("street_address"));
        location.setPostal_code(request.getParameter("postal_code"));
        location.setCity(request.getParameter("city"));
        location.setState_province(request.getParameter("state_province"));
        location.setCountry_id(request.getParameter("country_id"));


        switch (action) {
            case "guardar":
                locationDao.guardarLocation(location);

                response.sendRedirect("LocationServlet");
                break;
            case "actualizar":
                location.setLocationId(Integer.parseInt(request.getParameter("location_id")));

                locationDao.actualizarLocations(location);

                response.sendRedirect("LocationServlet");

                break;
        }
    }


}