/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import entity.myPayment;
import it.sauronsoftware.cron4j.Scheduler;
import it.sauronsoftware.cron4j.TaskCollector;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 * @author DuMaster
 */
@WebListener()
public class ScheduleServletContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        // 1. Creates the scheduler.
        Scheduler scheduler = new Scheduler();
        // 2. Registers a custom task collector.
        TaskCollector collector = new MyTaskCollector();
        scheduler.addTaskCollector(collector);
        // 3. Starts the scheduler.
        scheduler.start();
        // 4. Registers the scheduler.
        context.setAttribute(myPayment.SCHEDULER, scheduler);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        // 1. Retrieves the scheduler from the context.
        Scheduler scheduler = (Scheduler) context.getAttribute(myPayment.SCHEDULER);
        // 2. Removes the scheduler from the context.
        context.removeAttribute(myPayment.SCHEDULER);
        // 3. Stops the scheduler.
        scheduler.stop();
    }
}
