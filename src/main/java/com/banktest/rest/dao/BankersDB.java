package com.banktest.rest.dao;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.banktest.banker.api.Banker;
import com.banktest.banker.api.DealParameters;
import com.ritaja.xchangerate.util.Currency;
/**
 * A sample or an example in memory DB created to replace the DAO functionality
 * was be used as the DAO functionality for the sample Database. 
 * @author Abdullah
 *
 */
public class BankersDB {
     
    public static HashMap<Integer, Banker> employees = new HashMap<>();
    static{		
        employees.put(1, new Banker(1, "Loke", "Guitar", "UK",new DealParameters(5000.0, 5.0, 0.05, 0.0, Currency.EUR) ));
        employees.put(2, new Banker(2, "John", "Gruber", "USA",new DealParameters(5000, 5, .05, 0.0, Currency.EUR)));
        employees.put(3, new Banker(3, "Melcum", "Marshal", "AUS", new DealParameters(5000, 5, .05, 0.0, Currency.EUR)));
    }
     
    public static List<Banker> getEmployees(){
        return new ArrayList<Banker>(employees.values());
    }
     
    public static Banker getEmployee(Integer id){
        return employees.get(id);
    }
     
    public static void updateEmployee(Integer id, Banker employee){
        employees.put(id, employee);
    }
     
    public static void removeEmployee(Integer id){
        employees.remove(id);
    }
}