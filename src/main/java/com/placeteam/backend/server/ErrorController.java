package com.placeteam.backend.server;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public String error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/");
        return null;
    }

}
