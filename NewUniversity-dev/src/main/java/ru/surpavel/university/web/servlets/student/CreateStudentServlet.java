package ru.surpavel.university.web.servlets.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import ru.surpavel.university.domain.Student;
import ru.surpavel.university.services.GroupService;
import ru.surpavel.university.services.ServiceException;
import ru.surpavel.university.services.StudentService;

@Component("createStudentServlet")
public class CreateStudentServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(StudentServlet.class.getName());
    private StudentService studentService;
    private GroupService groupService;

    @Autowired
    public CreateStudentServlet(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            try {
                request.setAttribute("groups", groupService.findAll());
            } catch (ServiceException e) {
                log.error("Can't find all groups", e);
                throw new ServletException("Can't find all groups", e);
            }
            request.getRequestDispatcher("/WEB-INF/pages/student/createStudentView.jsp").forward(request, response);
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            Student student = new Student(request.getParameter("firstName"), request.getParameter("lastName"));
            int groupId;
            try {
                groupId = Integer.parseInt(request.getParameter("groupId"));
            } catch (NumberFormatException e1) {
                log.error("Group can't be null. Please choose chair for " + student, e1);
                throw new ServletException("Group can't be null. Please choose chair for " + student);
            }
            try {
                student.setGroup(groupService.findById(groupId));
                student = studentService.create(student);
                request.setAttribute("student", student);
            } catch (ServiceException e) {
                log.error("Can't create " + student, e);
                throw new ServletException("Can't create " + student, e);
            }
            request.getRequestDispatcher("/WEB-INF/pages/student/studentInfo.jsp").forward(request, response);
        }

    }

}