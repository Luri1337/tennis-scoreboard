package exception;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//TODO добавить исключения которые могут произойти
public class ExceptionHandler {
    public static void handleException(HttpServletResponse resp, int status, String message) throws IOException {
        resp.setStatus(status);
        resp.getWriter().write(message);
    }
}
