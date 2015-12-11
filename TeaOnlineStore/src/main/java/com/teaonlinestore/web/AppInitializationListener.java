package com.teaonlinestore.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.teaonlinestore.service.*;

/**
 * Application Lifecycle Listener implementation class AppInitializationListener
 *
 */
public class AppInitializationListener implements ServletContextListener {
	
    public AppInitializationListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         /*CategoryManagerInterface categoryManager = new CategoryManager();
         event.getServletContext().setAttribute("CategoryManager", categoryManager);*/
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	/*event.getServletContext().setAttribute("CategoryManager", null);*/
    }
	
}
