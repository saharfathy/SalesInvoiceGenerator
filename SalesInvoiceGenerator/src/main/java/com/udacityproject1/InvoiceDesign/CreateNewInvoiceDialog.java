/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udacityproject1.InvoiceDesign;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author sahar
 */
public class CreateNewInvoiceDialog  extends JDialog {
    //Variables declaration
    private JTextField customerNameField;
    private JTextField invoiceDateField;
    private JLabel customerNameLabel;
    private JLabel invoiceDateLabel;
    private JButton createBtn;
    private JButton cancelBtn;

    public CreateNewInvoiceDialog(InvoiceFrame frame) {
        customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        
        invoiceDateLabel = new JLabel("Invoice Date:");
        invoiceDateField = new JTextField(20);
        
        createBtn = new JButton("Create");
        cancelBtn = new JButton("Cancel");
        
        //Set Action Commands and Listeners
        createBtn.setActionCommand("createNewInvoice");
        cancelBtn.setActionCommand("cancelCreateNewInvoice");
        createBtn.addActionListener(frame.getListener());
        cancelBtn.addActionListener(frame.getListener());
        
        setLayout(new GridLayout(3, 2));       
        setTitle("Create New Invoice");
        
        // add Items and text boxs into the Dialog
        add(invoiceDateLabel);
        add(invoiceDateField);
        add(customerNameLabel);
        add(customerNameField);
        add(createBtn);
        add(cancelBtn);
        
        pack();
        
    }


    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }
    
    
}
