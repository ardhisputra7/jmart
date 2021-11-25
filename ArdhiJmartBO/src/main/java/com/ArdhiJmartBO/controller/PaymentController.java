package com.ArdhiJmartBO.controller;
import com.ArdhiJmartBO.*;
import com.ArdhiJmartBO.Payment;
import com.ArdhiJmartBO.dbjson.JsonAutowired;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.ArdhiJmartBO.controller.AccountController.accountTable;
import static com.ArdhiJmartBO.controller.ProductController.productTable;

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
        Predicate<Payment> findPayment = paymentFound -> paymentFound.id == id;
        Algorithm.find(paymentTable, findPayment);
        Payment payment = Algorithm.find(paymentTable, findPayment);

        if (Algorithm.exists(paymentTable, findPayment) &&
                payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION){
            payment.history.add(new Payment.Record(Invoice.Status.ON_PROGRESS, "Pesanan diproses."));
            return true;
        }
        else {
            return false;
        }
    }

    @PostMapping("/{id}/cancel")
    boolean cancel (@PathVariable int id) {
        Predicate<Payment> findPayment = paymentFound -> paymentFound.id == id;
        Algorithm.find(paymentTable, findPayment);
        Payment payment = Algorithm.find(paymentTable, findPayment);

        if (Algorithm.exists(paymentTable, findPayment) &&
                payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION){
            payment.history.add(new Payment.Record(Invoice.Status.CANCELLED, "Pesanan dibatalkan."));
            return true;
        }
        else {
            return false;
        }
    }

    @PostMapping("/create")
    Payment create (@RequestParam int buyerId, @RequestParam int productId,
                    @RequestParam int productCount, @RequestParam String shipmentAddress,
                    @RequestParam byte shipmentPlan){
        Predicate<Account> findAccount = accFind -> accFind.id == buyerId;
        Predicate<Product> findProduct = prodFind -> prodFind.id == productId;

        Product product = Algorithm.find(productTable, findProduct);
        Account account = Algorithm.find(accountTable, findAccount);

        Payment payment = Algorithm.<Payment>find(paymentTable, pred -> product.id == productId && account.id == buyerId);
        Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);

        payment.shipment = shipment;

        if(Algorithm.exists(accountTable, findAccount) && Algorithm.exists(productTable, findProduct)
           && account.balance >= product.price * productCount) {
            account.balance -= payment.getTotalPay(product)* productCount;
            payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Menunggu konfirmasi."));
            paymentTable.add(payment);
            poolThread.add(payment);
       }
        else{
            return null;
        }
        return payment;
    }

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    boolean submit ( @PathVariable int id, String receipt){
        Predicate<Payment> findPayment = paymentFound -> paymentFound.id == id;
        Algorithm.find(paymentTable, findPayment);
        Payment payment = Algorithm.find(paymentTable, findPayment);

        if (Algorithm.exists(paymentTable, findPayment)
                && payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_PROGRESS
                && !payment.shipment.receipt.isBlank()){
            payment.shipment.receipt = receipt;
            payment.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "Pesanan sedang dikirim."));
            return true;
        }
        else {
            return false;
        }
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

        return payment.history.isEmpty();

    }
}
