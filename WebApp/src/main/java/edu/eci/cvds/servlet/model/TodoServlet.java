package edu.eci.cvds.servlet.model;

import edu.eci.cvds.servlet.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(
        urlPatterns = "/todo"
)

public class TodoServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter = resp.getWriter();
        Optional<String> optId = Optional.ofNullable(req.getParameter("id"));
        String id = optId.isPresent() && !optId.get().isEmpty() ? optId.get() : "";

        try
        {
            int intId = Integer.parseInt(id);
            Todo todo = Service.getTodo(intId);
            List<Todo> todoList = new ArrayList<>();
            todoList.add(todo);
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(Service.todosToHTMLTable(todoList));
            responseWriter.flush();
        }
        catch (MalformedURLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        catch (FileNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseWriter.write("No existe un item con el identificador dado");
        }
        catch (Exception e)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
