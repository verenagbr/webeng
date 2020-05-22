package de.hsduesseldorf.webeng.prak03.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener()
public class MyListener implements ServletContextListener,
                                   HttpSessionListener, HttpSessionAttributeListener {

    private final AtomicInteger activeSessions = new AtomicInteger();

    // Public constructor is required by servlet spec
    public MyListener() {
    }

    public int getTotalActiveSession() {
        return activeSessions.get();
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        activeSessions.incrementAndGet();
        System.out.println("Session Created: " + sessionEvent.getSession().getId());
        System.out.println("Total Sessions: " + activeSessions.get());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        activeSessions.decrementAndGet();
        System.out.println("Session Destroyed: " + sessionEvent.getSession().getId());
        System.out.println("Total Sessions: " + activeSessions.get());
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
