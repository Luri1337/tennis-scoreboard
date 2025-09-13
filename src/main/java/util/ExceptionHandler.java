package util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//TODO добавить исключения которые могут произойти
public class ExceptionHandler {
    public static void handleException(HttpServletResponse resp, HttpServletRequest req, int status, String message) throws IOException, ServletException {
        resp.setStatus(status);

        req.setAttribute("message", message);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
    }
}
