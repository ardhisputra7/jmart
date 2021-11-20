package com.ArdhiJmartBO.controller;
import com.ArdhiJmartBO.ObjectPoolThread;
import com.ArdhiJmartBO.Payment;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>
{
    public static long DELIVERED_LIMIT_MS = 100;
    public static long ON_DELIVERY_LIMIT_MS = 100;
    public static long ON_PROGRESS_LIMIT_MS = 150;
    public static long WAITING_CONF_LIMIT_MS = 150;
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    @PostMapping("/{id}/accept")
    boolean accept (int id) {
        return true;
    }
    @PostMapping("/{id}/cancel")
    boolean cancel (int id) {
        return false;
    }
    @PostMapping("/create")
    Payment create (int buyerId, int productId,
        String shipmentAddress, byte shipmentPlan){
        return null;
    }
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("submit")
    boolean submit (int id, String receipt){
        return true;
    }
    private static boolean timekeeper (Payment payment){
        return false;
    }
}
