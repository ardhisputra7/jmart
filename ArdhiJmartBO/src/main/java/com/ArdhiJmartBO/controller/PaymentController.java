package com.ArdhiJmartBO.controller;
import com.ArdhiJmartBO.*;
import com.ArdhiJmartBO.Payment;
import com.ArdhiJmartBO.dbjson.JsonAutowired;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.ArdhiJmartBO.controller.AccountController.accountTable;
import static com.ArdhiJmartBO.controller.ProductController.productTable;

/**
 * Controller class untuk payment
 * Menggunakan Spring framework
 * Mengimplementasikan BasicGetController
 * @author Ardhi Putra
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>
{

    public static final long DELIVERED_LIMIT_MS = 100;
    public static final long ON_DELIVERY_LIMIT_MS = 100;
    public static final long ON_PROGRESS_LIMIT_MS = 150;
    public static final long WAITING_CONF_LIMIT_MS = 150;

    @JsonAutowired(filepath = "C:/Java/Jmart/resource/payment.json", value = Payment.class)

    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<>(PaymentController::timekeeper);

    /**
     * Method untuk melakukan penerimaan payment
     * @param id merupakan id dari payment
     * @return mengembalikan boolean yang menunjukkan apakah payment berhasil
     */
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

    /**
     * Method untuk melakukan pembatalan payment
     * @param id merupakan id dari payment
     * @return mengembalikan boolean yang menunjukkan apakah pembatalan berhasil
     */
    @PostMapping("/{id}/cancel")
    boolean cancel (@PathVariable int id) {
        Predicate<Payment> findPayment = paymentFound -> paymentFound.id == id;
        Algorithm.find(paymentTable, findPayment);
        Payment payment = Algorithm.find(paymentTable, findPayment);
        Product prod = Algorithm.<Product>find(ProductController.productTable, product -> product.id == payment.productId);
		Account acc = Algorithm.<Account>find(AccountController.accountTable, account -> account.id == payment.buyerId);

        if (Algorithm.exists(paymentTable, findPayment) &&
                payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION){
            payment.history.add(new Payment.Record(Invoice.Status.CANCELLED, "Pesanan dibatalkan."));
			acc.balance += payment.getTotalPay(prod) * payment.productCount;
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Method untuk melakukan pembuatan payment
     * @param buyerId id dari pembeli
     * @param productId id dari product
     * @param productCount jumlah product yang ingin dibeli
     * @param shipmentAddress merupakan alamat dari pembelu
     * @param shipmentPlan shipment plan yang dipilih
     * @return mengembalikan payment yang dibuat
     */
    @PostMapping("/create")
    Payment create (
            @RequestParam int buyerId,
            @RequestParam int productId,
            @RequestParam int productCount,
            @RequestParam String shipmentAddress,
            @RequestParam byte shipmentPlan
    ){
        Payment pay = new Payment(buyerId, productId, productCount, new Shipment(shipmentAddress, 0, shipmentPlan, null));
        Account acc = Algorithm.<Account>find(AccountController.accountTable,
                account -> account.id == buyerId);
        Product prod = Algorithm.<Product>find(ProductController.productTable,
                product -> product.id == productId);
        if(Algorithm.<Account>exists(AccountController.accountTable, acc)
                && Algorithm.<Product>exists(ProductController.productTable, prod)
                && pay.getTotalPay(prod) <= acc.balance
                && pay.shipment.cost == 0
                && pay.shipment.receipt == null) {
            acc.balance -= pay.getTotalPay(prod) * productCount;
            pay.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Menunggu konfirmasi penjual."));
            getJsonTable().add(pay);
            poolThread.add(pay);
            return pay;
        }
        return null;
    }

    /**
     * method untuk mengembalikan jsonTable dari payment
     * @return paymentTable
     */
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    /**
     * Method untuk melakukan submit terhadap payment yang dilakykan
     * @param id id dari payment
     * @param receipt string yang berisi receipt
     * @return mengembalikan boolean yang menentukan apakah submit berhasil
     */
    @PostMapping("/{id}/submit")
    boolean submit ( @PathVariable int id, @RequestParam String receipt){
        Predicate<Payment> findPayment = paymentFound -> paymentFound.id == id;
        Algorithm.find(paymentTable, findPayment);
        Payment payment = Algorithm.find(paymentTable, findPayment);
        Product prod = Algorithm.<Product>find(ProductController.productTable, product -> product.id == payment.productId);
        Account storeAcc = Algorithm.<Account>find(AccountController.accountTable, account -> account.id == prod.accountId);

        if (Algorithm.exists(paymentTable, findPayment)
                && payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_PROGRESS
                && !receipt.isBlank()){
            payment.shipment.receipt = receipt;
            storeAcc.balance += payment.getTotalPay(prod) * payment.productCount;
            payment.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "Pesanan sedang dikirim."));
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method untuk keep track waktu yang sudah berjalan pada payment
     * @param payment payment yang akan di timekeep
     * @return mengembalikan payment boolean apakah sudah selesai atau belum
     */
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
