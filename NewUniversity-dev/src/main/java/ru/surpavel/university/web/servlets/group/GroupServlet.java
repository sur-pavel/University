package ru.surpavel.university.web.servlets.group;

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

import ru.surpavel.university.domain.Group;
import ru.surpavel.university.services.GroupService;
import ru.surpavel.university.services.ServiceException;

@Component("groupServlet")
public class GroupServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(GroupServlet.class.getName());

    private GroupService groupService;

    @Autowired
    public GroupServlet(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String id = request.getParameter("id");
        String jspPath;
        if (id != null) {
            try {
                Group group = groupService.findById(Integer.parseInt(id));
                request.setAttribute("group", group);
            } catch (ServiceException e) {
                log.error("Can't find group with id " + id, e);
                throw new ServletException("Can't find group with id " + id, e);
            }
            jspPath = "/WEB-INF/pages/group/groupInfo.jsp";
        } else {
            try {
                List<Group> groups = groupService.findAll();
                request.setAttribute("groups", groups);
            } catch (ServiceException e) {
                log.error("Can't find all groups", e);
                throw new ServletException("Can't find all groups", e);
            }
            jspPath = "/WEB-INF/pages/group/groupList.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);

    }

}
