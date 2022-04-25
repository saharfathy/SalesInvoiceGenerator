/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udacityproject1.Model;

/**
 *
 * @author sahar
 */
public class InvoiceLine {
   //int invoiceNumber;
   //Declare Data used in Invoice Line.csv
   private String itemName;
   private double itemPrice;
   private int itemCount;
   private double LineTotal;  
   //Create array List from  Invoice Header class
   private InvoiceHeader header;
   
   
   //class InvoiceLine  constructor
   public InvoiceLine(String itemName, double itemPrice, int itemCount, InvoiceHeader header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
    }

   
   // <editor-fold defaultstate="collapsed" desc="Setter & Getter">
   // Get & Set for item Name
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
   // Get & Set for item Count
    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    // Get & Set for item Ptice
    public double getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    // Get & Set for Invoice Header
    public InvoiceHeader getHeader() {
        return header;
    }
    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }// </editor-fold>

    // Get & Set for Line Total
    public double getLineTotal() {
        LineTotal = itemPrice * itemCount; 
        return LineTotal;
    }  
      
    @Override
    public String toString() {
        return "InvoiceLine{" + "itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCount=" + itemCount + '}';
    }   

    
   
}
