/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udacityproject1.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sahar
 */
public class InvoiceHeader {
    
    //Declare Data used in Invoice Herder.csv
    private int invoiceNumber;
    private String customerName;
    private Date invoiceDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date d;
    String invoiceDateFormatted;
    
    
    //Create array List from  Invoice Line class
    private ArrayList<InvoiceLine> lines;
    
    //class InvoiceHeader  constructor
    public InvoiceHeader(int invoiceNumber, Date invoiceDate, String customerName) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        
    }

    // <editor-fold defaultstate="collapsed" desc="Setter & Getter">
    // Get & Set for Invoice Number
    public int getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    // Get & Set for Cusromer Name
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Get & Set for Invoice Date
    public Date getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }// </editor-fold>

    public ArrayList<InvoiceLine> getLines() {
       if (lines == null)
        {
           lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    
    
    
    /*
    //fill the Array List with Invoice Lines 
    public void addInvoiceLine(InvoiceLine line){
        getLines().add(line);
    }*/
    
    //Get the whole Invoice total
    public double invoiceTotal(){        
        double total = 0.0;
        for (int i = 0 ; i< getLines().size(); i++)
        {
           total += lines.get(i).getLineTotal();
        }   
        return total;
    }  
 
    @Override
    public String toString() {
        invoiceDateFormatted = dateFormat.format(invoiceDate);
        return invoiceNumber +"," + customerName +"," +  invoiceDateFormatted ;
    }  

}
