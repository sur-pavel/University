package ru.surpavel.university.web.servlets.chair;

import java.io.IOException;
import java.util.List;

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

@Component("chairServlet")
public class ChairServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(ChairServlet.class.getName());

    private ChairService chairService;

    @Autowired
    public ChairServlet(ChairService chairService) {
        this.chairService = chairService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String id = request.getParameter("id");
        String jspPath;
        if (id != null) {
            try {
                Chair chair = chairService.findById(Integer.parseInt(id));
                request.setAttribute("chair", chair);
            } catch (ServiceException e) {
                log.error("Can't find chair with id " + id, e);
                throw new ServletException("Can't find chair with id " + id, e);
            }
            jspPath = "/WEB-INF/pages/chair/chairInfo.jsp";
        } else {
            try {
                List<Chair> chairs = chairService.findAll();
                request.setAttribute("chairs", chairs);
            } catch (ServiceException e) {
                log.error("Can't find all chairs", e);
                throw new ServletException("Can't find all chairs", e);
            }
            jspPath = "/WEB-INF/pages/chair/chairList.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);

    }

}
