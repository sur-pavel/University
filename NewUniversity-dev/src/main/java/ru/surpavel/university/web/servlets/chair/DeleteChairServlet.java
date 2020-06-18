package ru.surpavel.university.web.servlets.chair;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import ru.surpavel.university.domain.Chair;
import ru.surpavel.university.services.ChairService;
import ru.surpavel.university.services.ServiceException;

@Component("deleteChairServlet")
public class DeleteChairServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(ChairServlet.class.getName());
    private ChairService chairService;

    @Autowired
    public DeleteChairServlet(ChairService chairService) {
        this.chairService = chairService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String id = request.getParameter("id");
            try {
                Chair chair = chairService.findById(Integer.parseInt(id));
                request.setAttribute("chair", chair);
            } catch (ServiceException e) {
                log.error("Can't find chair with id " + id, e);
                throw new ServletException("Can't find chair with id " + id, e);
            }
            request.getRequestDispatcher("/WEB-INF/pages/chair/deleteChairView.jsp").forward(request, response);
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String id = request.getParameter("id");
            try {
                chairService.delete(Integer.parseInt(id));
            } catch (ServiceException e) {
                log.error("Can't delete chair with id " + id, e);
                throw new ServletException("Can't delete chair with id " + id, e);
            }
            response.sendRedirect(request.getContextPath() + "/chairs");
        }

    }

}
