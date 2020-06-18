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

@Component("createChairServlet")
public class CreateChairServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(ChairServlet.class.getName());
    private ChairService chairService;

    @Autowired
    public CreateChairServlet(ChairService chairService) {
        this.chairService = chairService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            request.getRequestDispatcher("/WEB-INF/pages/chair/createChairView.jsp").forward(request, response);
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            Chair chair = new Chair(request.getParameter("name"));
            try {
                chair = chairService.create(chair);
                request.setAttribute("chair", chair);
            } catch (ServiceException e) {
                log.error("Can't create " + chair, e);
                throw new ServletException("Can't create " + chair, e);
            }
            request.getRequestDispatcher("/WEB-INF/pages/chair/chairInfo.jsp").forward(request, response);
        }

    }

}