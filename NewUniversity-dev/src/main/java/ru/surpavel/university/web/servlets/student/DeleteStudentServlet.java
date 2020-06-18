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
import ru.surpavel.university.services.ServiceException;
import ru.surpavel.university.services.StudentService;

@Component("deleteStudentServlet")
public class DeleteStudentServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(StudentServlet.class.getName());
    private StudentService studentService;

    @Autowired
    public DeleteStudentServlet(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String id = request.getParameter("id");
            try {
                Student student = studentService.findById(Integer.parseInt(id));
                request.setAttribute("student", student);
            } catch (ServiceException e) {
                log.error("Can't find student with id " + id, e);
                throw new ServletException("Can't find student with id " + id, e);
            }
            request.getRequestDispatcher("/WEB-INF/pages/student/deleteStudentView.jsp").forward(request, response);
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String id = request.getParameter("id");
            try {
                studentService.delete(Integer.parseInt(id));
            } catch (ServiceException e) {
                log.error("Can't delete student with id " + id, e);
                throw new ServletException("Can't delete student with id " + id, e);
            }
            response.sendRedirect(request.getContextPath() + "/students");
        }

    }

}
