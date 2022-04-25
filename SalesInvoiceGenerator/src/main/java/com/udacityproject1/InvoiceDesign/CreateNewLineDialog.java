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
public class CreateNewLineDialog extends JDialog{
    
    //Variables declaration
    private JTextField itemNameFieldtxt;
    private JTextField itemCountFieldtxt;
    private JTextField itemPriceFieldtxt;
    private JLabel nameLabel;
    private JLabel itemCountLabel;
    private JLabel itemPriceLabel;
    private JButton addLineBtn;
    private JButton cancelBtn;
    
    public CreateNewLineDialog(InvoiceFrame frame) {
        itemNameFieldtxt = new JTextField(20);
        nameLabel = new JLabel("Item Name");
        
        itemCountFieldtxt = new JTextField(20);
        itemCountLabel = new JLabel("Item Count");
        
        itemPriceFieldtxt = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price");
        
        addLineBtn = new JButton("Add Line");
        cancelBtn = new JButton("Cancel");
        
        //Set Action Commands and Listeners
        addLineBtn.setActionCommand("addNewLine");
        cancelBtn.setActionCommand("cancelCreateNewLine");        
        addLineBtn.addActionListener(frame.getListener());
        cancelBtn.addActionListener(frame.getListener());
        
        setLayout(new GridLayout(4, 2));       
        setTitle("Create New Line");
        
        // add Items and text boxs into the Dialog
        add(nameLabel);
        add(itemNameFieldtxt);
        add(itemCountLabel);
        add(itemCountFieldtxt);
        add(itemPriceLabel);
        add(itemPriceFieldtxt);
        add(addLineBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameFieldtxt;
    }

    public JTextField getItemCountField() {
        return itemCountFieldtxt;
    }

    public JTextField getItemPriceField() {
        return itemPriceFieldtxt;
    }
}
