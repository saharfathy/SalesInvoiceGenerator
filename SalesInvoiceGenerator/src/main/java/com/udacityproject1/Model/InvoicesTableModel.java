/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udacityproject1.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sahar
 */
public class InvoicesTableModel extends AbstractTableModel {

    private ArrayList<InvoiceHeader> invoiceHeaderList;
    private int Colnumber = 4 ; 
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public InvoicesTableModel(ArrayList<InvoiceHeader> invoiceHeaderList) {
        
        this.invoiceHeaderList = invoiceHeaderList;
    }

    public ArrayList<InvoiceHeader> getInvoicesHeaderList() {
        return invoiceHeaderList;
    }
    
    

    @Override
    public int getRowCount() { 
        
        return invoiceHeaderList.size();
        
    }

    @Override
    public int getColumnCount() {
        return Colnumber;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Invoice No.";
            case 1:
                return "Invoice Date";
            case 2:
                return "Customer Name";
            case 3:
                return "Invoice Total";
            default:
                return "";
        }       
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
     switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }    
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader row = invoiceHeaderList.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return row.getInvoiceNumber();
            case 1:
                return dateFormat.format(row.getInvoiceDate());
            case 2:
                return row.getCustomerName();
            case 3:
                return row.invoiceTotal();
            default:
                return "";
        }     
        
    }
    
}
