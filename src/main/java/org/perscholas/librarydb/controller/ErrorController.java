package org.perscholas.librarydb.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.security.AuthenticatedUserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView accessDenied(HttpServletRequest request, Exception ex) {
        ModelAndView response = new ModelAndView("error/404");
        response.setStatus(HttpStatus.NOT_FOUND);

        if (authenticatedUserUtilities.isAuthenticated()) {
            log.warn("User : " + authenticatedUserUtilities.getCurrentUsername()
                    + " requested url that they do not have permission to " + request.getRequestURL());
        } else {
            log.warn("Unauthenticated user requested url that they do not have permission to " + request.getRequestURL());
        }


        log.warn(ex.getMessage());

        return response;
    }

}
