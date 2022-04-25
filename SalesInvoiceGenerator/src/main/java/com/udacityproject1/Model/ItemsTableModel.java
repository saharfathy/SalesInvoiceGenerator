/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udacityproject1.Model;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author sahar
 */
public class ItemsTableModel extends AbstractTableModel{

    private ArrayList<InvoiceLine> itemsLinesList;
    private int Colnumber = 4 ; 
    
    public ItemsTableModel(ArrayList<InvoiceLine> InvoiceLineList) {
        
        this.itemsLinesList = InvoiceLineList;
    }
    
    /*public ArrayList<InvoiceLine> getInvoiceLines() {
        return itemsLinesList;
    }*/

    @Override
    public int getRowCount() { 
        
        return itemsLinesList == null ? 0 : itemsLinesList.size();
        
    }

    @Override
    public int getColumnCount() {
        return Colnumber;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Item Name";
            case 1:
                return "Item Price";
            case 2:
                return "Count";
            case 3:
                return "Item Total";
            default:
                return "";
        }       
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
     switch (columnIndex) {
           case 0:
                return String.class;
            case 1:
                return Double.class;
            case 2:
                return Integer.class;
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
        if (itemsLinesList == null) {
            return "";
        } else {
            InvoiceLine line = itemsLinesList.get(rowIndex);
            switch (columnIndex) {
                 case 0:
                      return line.getItemName();
                 case 1:
                      return line.getItemPrice();
                 case 2:
                       return line.getItemCount();
                 case 3:
                       return line.getLineTotal();
                 default:
                       return "";
            } 
        }
    }  
}
