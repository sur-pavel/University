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
import ru.surpavel.university.services.ChairService;
import ru.surpavel.university.services.GroupService;
import ru.surpavel.university.services.ServiceException;

@Component("createGroupServlet")
public class CreateGroupServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(GroupServlet.class.getName());
    private GroupService groupService;
    private ChairService chairService;

    @Autowired
    public CreateGroupServlet(ChairService chairService, GroupService groupService) {
        this.chairService = chairService;
        this.groupService = groupService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            try {
                request.setAttribute("chairs", chairService.findAll());
            } catch (ServiceException e) {
                log.error("Can't find all chairs", e);
                throw new ServletException("Can't find all chairs", e);
            }
            request.getRequestDispatcher("/WEB-INF/pages/group/createGroupView.jsp").forward(request, response);
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            Group group = new Group(request.getParameter("name"));
            int chairId;
            try {
                chairId = Integer.parseInt(request.getParameter("chairId"));
            } catch (NumberFormatException e1) {
                log.error("Chair can't be null. Please choose chair for " + group, e1);
                throw new ServletException("Chair can't be null. Please choose chair for " + group);
            }
            try {
                group.setChair(chairService.findById(chairId));
                group = groupService.create(group);
                request.setAttribute("group", group);
            } catch (ServiceException e) {
                log.error("Can't create " + group, e);
                throw new ServletException("Can't create " + group, e);
            }
            request.getRequestDispatcher("/WEB-INF/pages/group/groupInfo.jsp").forward(request, response);
        }

    }

}