package com.ArdhiJmartBO.controller;
import com.ArdhiJmartBO.Account;
import com.ArdhiJmartBO.Invoice;
import com.ArdhiJmartBO.ObjectPoolThread;
import com.ArdhiJmartBO.Payment;
import com.ArdhiJmartBO.dbjson.JsonAutowired;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>
{
    public static final long DELIVERED_LIMIT_MS = 100;
    public static final long ON_DELIVERY_LIMIT_MS = 100;
    public static final long ON_PROGRESS_LIMIT_MS = 150;
    public static final long WAITING_CONF_LIMIT_MS = 150;

    @JsonAutowired(filepath = "C:/Java/Jmart/resource", value = Payment.class)
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    @PostMapping("/{id}/accept")
    boolean accept (@PathVariable int id) {
        return true;
    }

    @PostMapping("/{id}/cancel")
    boolean cancel (@PathVariable int id) {
        return false;
    }

    @PostMapping("/create")
    Payment create (int buyerId, int productId, String shipmentAddress, byte shipmentPlan){
        return null;
    }

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    boolean submit ( @PathVariable int id, String receipt){
        return true;
    }

    public static boolean timekeeper (Payment payment) {

        Date currentDate = new Date();
        int lastRecord = payment.history.size() - 1;
        long elapsedTime = currentDate.getTime() - payment.history.get(lastRecord).date.getTime();

        if (payment.history.get(lastRecord).status == Invoice.Status.WAITING_CONFIRMATION
                && elapsedTime > WAITING_CONF_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
        }
        if (payment.history.get(lastRecord).status == Invoice.Status.ON_PROGRESS
                && elapsedTime > ON_PROGRESS_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
        }
        if (payment.history.get(lastRecord).status == Invoice.Status.ON_DELIVERY
                && elapsedTime > ON_DELIVERY_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Terkirim"));
        }
        if (payment.history.get(lastRecord).status == Invoice.Status.DELIVERED
                && elapsedTime > DELIVERED_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Selesai"));
        }

        for(Payment.Record element: payment.history) {
            if(element.status == Invoice.Status.FINISHED || element.status == Invoice.Status.FAILED)
                payment.history.remove(element);
        }

        if(payment.history.isEmpty()) {
            return true;
        }
        else return false;

    }
}
