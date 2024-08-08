package org.perscholas.librarydb.security;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.UserDAO;
import org.perscholas.librarydb.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticatedUserUtilities {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String getCurrentUsername() {
        // the goal of this method is to either return the logged in username or null if the user is not logged in
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            // this is if the user has a security context
            if (context.getAuthentication() instanceof AnonymousAuthenticationToken) {
                // not logged in so return null
                return null;
            }

            final org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal();
            return principal.getUsername();
        } else {
            return null;
        }
    }

    public User getCurrentUser() {
        // the goal of this method is to either return the logged in user object or null if the user is not logged in
        String username = getCurrentUsername();
        if (username == null) {
            return null;
        }
        return userDAO.findByEmailIgnoreCase(username);
    }

    // this method is what makes it so that once a new user is created it will automatically log them in
    // (as well as some code in the else statement of the login controller)
    public void manualAuthentication(HttpSession session, String username, String unencryptedPassword) {
        // reset security principal to be the new user information
        Authentication request = new UsernamePasswordAuthenticationToken(username, unencryptedPassword);
        Authentication result = authenticationManager.authenticate(request);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(result);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        return (authentication != null && authentication.isAuthenticated());
    }

}