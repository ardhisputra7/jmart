package com.ArdhiJmartBO.controller;

import com.ArdhiJmartBO.Account;
import com.ArdhiJmartBO.Algorithm;
import com.ArdhiJmartBO.Predicate;
import com.ArdhiJmartBO.Store;
import com.ArdhiJmartBO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import com.ArdhiJmartBO.dbjson.JsonAutowired;
import java.util.regex.Pattern;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Controller class untuk account
 * Menggunakan Spring framework
 * Mengimplementasikan BasicGetController
 * @author Ardhi Putra
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=\\S+$)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    @JsonAutowired(filepath = "C:/Java/Jmart/resource/account.json", value = Account.class)
    public static JsonTable<Account> accountTable;

    /**
     * method untuk mengembalikan jsonTable dari account
     * @return accountTable
     */
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    /**
     * Method untuk memproses login
     * @param email email yang ingin di cek
     * @param password password yang ingin di cek
     * @return mereturn account bila berhasil dan null bila gagal
     */
    @PostMapping("/login")
    Account login
            (
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String passhash = no.toString(16);
            while (passhash.length() < 32) {
                passhash = "0" + passhash;
            }
            password = passhash;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        for (Account account : accountTable){
            if(account.email.toLowerCase().equals(email) && account.password.equals(password)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Method untuk melakukan register account baru
     * @param name nama dari account baru
     * @param email email dari account baru
     * @param password password dari account baru
     * @return Account baru yang perlu di buat dan null bila gagal
     */
    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        if(!name.isBlank() && REGEX_PATTERN_EMAIL.matcher(email).find() &&
                REGEX_PATTERN_PASSWORD.matcher(password).find() && !Algorithm.exists(accountTable.toArray(), email))
        {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(password.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                String passhash = no.toString(16);
                while (passhash.length() < 32) {
                    passhash = "0" + passhash;
                }
                password = passhash;
            }
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            getJsonTable().add(new Account(name, email.toLowerCase(), password, 0));
            return new Account(name, email, password, 0);
        }
        else return null;
    }

    /**
     * Method untuk melakukan register store pada account
     * @param id id dari store yang dibuat
     * @param name nama dari store yang dibuat
     * @param address address dari store yang dibuat
     * @param phoneNumber nomor telpon dari store yang dibuat
     * @return mengembalikan store baru bila berhasil dan null ketika gagal
     */
    @PostMapping("/{id}/registerStore")
    Store registerStore
            (
                    @PathVariable int id,
                    @RequestParam String name,
                    @RequestParam String address,
                    @RequestParam String phoneNumber
            )
    {
        if(Algorithm.exists(getJsonTable().toArray(), id) || !Algorithm.exists(getJsonTable().toArray(), name)) {
			Account acc = Algorithm.<Account>find(getJsonTable(), (account -> account.id == id && account.store == null));
			acc.store = new Store(name, address, phoneNumber, 0);
			return acc.store;
        }

        return null;
    }

    /**
     * Method untuk melakukan top up pada account
     * @param id id dari account
     * @param balance balance yang ditambahkan dari account
     * @return mengembalikan true bila berhasil dan false ketika gagal
     */
    @PostMapping("/{id}/topUp")
    boolean topUp(@PathVariable int id, @RequestParam double balance) {
        Predicate<Account> findAccount = matchAccount -> matchAccount.id == id;
        Account account = Algorithm.find(getJsonTable(), findAccount);
        if(account != null) {
            account.balance += balance;
            return true;
        }
        return false;
    }


}
