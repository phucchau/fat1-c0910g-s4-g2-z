/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import it.sauronsoftware.cron4j.SchedulingPattern;
import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskCollector;
import it.sauronsoftware.cron4j.TaskTable;

/**
 *
 * @author DuMaster
 */
public class MyTaskCollector implements  TaskCollector{

    @Override
    public TaskTable getTasks() {
        TaskTable ret = new TaskTable();
        //SchedulingPattern pattern = new SchedulingPattern("* * * * *");   //chay 1 phut 1 lan

        SchedulingPattern pattern = new SchedulingPattern("* 12 * * *");   //12h hàng ngay chay 1 lan
        Task task = new CheckUserPay();
        ret.add(pattern, task);
        return ret;
    }
    
}
