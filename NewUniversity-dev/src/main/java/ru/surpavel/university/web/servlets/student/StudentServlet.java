package ru.surpavel.university.web.servlets.student;

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

import ru.surpavel.university.domain.Student;
import ru.surpavel.university.services.ServiceException;
import ru.surpavel.university.services.StudentService;

@Component("studentServlet")
public class StudentServlet implements HttpRequestHandler {
    private static final Logger log = LogManager.getLogger(StudentServlet.class.getName());

    private StudentService studentService;

    @Autowired
    public StudentServlet(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String id = request.getParameter("id");
        String jspPath;
        if (id != null) {
            try {
                Student student = studentService.findById(Integer.parseInt(id));
                request.setAttribute("student", student);
            } catch (ServiceException e) {
                log.error("Can't find student with id " + id, e);
                throw new ServletException("Can't find student with id " + id, e);
            }
            jspPath = "/WEB-INF/pages/student/studentInfo.jsp";
        } else {
            try {
                List<Student> students = studentService.findAll();
                request.setAttribute("students", students);
            } catch (ServiceException e) {
                log.error("Can't find all students", e);
                throw new ServletException("Can't find all students", e);
            }
            jspPath = "/WEB-INF/pages/student/studentList.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);

    }

}
