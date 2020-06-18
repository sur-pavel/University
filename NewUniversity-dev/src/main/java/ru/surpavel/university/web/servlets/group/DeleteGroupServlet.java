package ru.surpavel.university.web.servlets.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import ru.surpavel.university.domain.Group;
import ru.surpavel.university.services.GroupService;
import ru.surpavel.university.services.ServiceException;

@Component("deleteGroupServlet")
public class DeleteGroupServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(GroupServlet.class.getName());
    private GroupService groupService;

    @Autowired
    public DeleteGroupServlet(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String id = request.getParameter("id");
            try {
                Group group = groupService.findById(Integer.parseInt(id));
                request.setAttribute("group", group);
            } catch (ServiceException e) {
                log.error("Can't find group with id " + id, e);
                throw new ServletException("Can't find group with id " + id, e);
            }
            request.getRequestDispatcher("/WEB-INF/pages/group/deleteGroupView.jsp").forward(request, response);
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String id = request.getParameter("id");
            try {
                groupService.delete(Integer.parseInt(id));
            } catch (ServiceException e) {
                log.error("Can't delete group with id " + id, e);
                throw new ServletException("Can't delete group with id " + id, e);
            }
            response.sendRedirect(request.getContextPath() + "/groups");
        }

    }

}
