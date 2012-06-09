/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import eb.Payments;
import eb.PaymentsFacade;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TimerService;

/**
 *
 * @author DuMaster
 */
@Singleton
@Startup
public class CheckUserPay {

    @EJB
    public PaymentsFacade paymentsFacade;
    
    @Resource
    TimerService timerService;

    @Schedule(persistent = true, minute = "*", second = "*/5", hour = "*")
    public void excheckpayment() {
        try {
            List<Payments> pm = paymentsFacade.findAll();
            System.out.print("process and size :"+pm.size());
            Calendar cal = Calendar.getInstance();
            Date d = cal.getTime(); 
            for (Payments payments : pm) {
                
                if (payments.getPaydate().before(d)) {
                    Payments p = paymentsFacade.find(payments.getPaymentID());
                    p.setStatus(0);
                    paymentsFacade.edit(p);
                    System.out.println("check date , Update payment : payID : " 
                            + payments.getPaymentID()+",time update :" + d +",End date " + payments.getPaydate());
                    System.out.println("-------------------------------");
                } else {
                    System.out.println("not update false : " + d+",Day End :" + payments.getPaydate());
                    System.out.println("-------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
