package ru.fomin;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.fomin.config.AppConfig;
import ru.fomin.config.WebInitializer;

import java.net.URL;
import java.security.ProtectionDomain;

public class Launcher {
    public static void main(String[] args) throws Exception {

        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(AppConfig.class);
        final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
        final ServletContextHandler context = new ServletContextHandler();
        context.addServlet(servletHolder, "/*");
        context.setContextPath("/mvc");

        final Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        server.join();
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.setConfigLocation("ru.fomin.config");
//
//        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        contextHandler.setErrorHandler(null);
//
//        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)),"/mvc");
//        contextHandler.setContextPath("/mvc");
//
//
//        Server server = new Server(8085);
//        server.setHandler(contextHandler);
//        server.start();
//        server.join();
    }

}
