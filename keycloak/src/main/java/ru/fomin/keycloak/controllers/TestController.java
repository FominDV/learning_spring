package ru.fomin.keycloak.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fomin.keycloak.annotations.MyAnnotation;
import ru.fomin.keycloak.annotations.ThreadLocalService;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.net.http.HttpRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api")
@MyAnnotation(ThreadLocalService.d)
public class TestController {


    @GetMapping("/anonymous")
    public String getAnonymousInfo() {
        return ThreadLocalService.threadLocal.get();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String getUserInfo() {

        return "user info";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminInfo() {
        return "admin info";
    }

    @GetMapping("/service")
    @PreAuthorize("hasRole('SERVICE')")
    public String getServiceInfo() {
        return "service info";
    }

    @GetMapping("/me")
    public Object getMe() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
