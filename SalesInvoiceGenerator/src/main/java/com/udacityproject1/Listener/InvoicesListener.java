/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udacityproject1.Listener;
import com.udacityproject1.InvoiceDesign.CreateNewInvoiceDialog;
import com.udacityproject1.InvoiceDesign.CreateNewLineDialog;
import com.udacityproject1.InvoiceDesign.InvoiceFrame;
import com.udacityproject1.Model.InvoiceHeader;
import com.udacityproject1.Model.InvoiceLine;
import com.udacityproject1.Model.InvoicesTableModel;
import com.udacityproject1.Model.ItemsTableModel;
//*****************
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//*************

/**
 *
 * @author sahar
 */
public class InvoicesListener implements ActionListener, ListSelectionListener  {
   //Variable Declaration
    private InvoiceFrame frame;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
   // private CreateNewInvoiceDialog invHeaderDialog;
    //private CreateNewLineDialog itemLineDialog;
    //private List<InvoiceHeader> invoiceHeaderList = new ArrayList<>();
    //private InvoicesTableModel invTableModel;

    
    public InvoicesListener(InvoiceFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         switch (e.getActionCommand()) {           
            case "LoadFile":
                loadFileMenuItem();
                break;
            case "SaveFile":
                saveFileMenuItem();
                break;
            case "CreateNewInvoice":
                displayNewInvoiceDialog();
                break;
            case "cancelCreateNewInvoice":
                cancelCreateNewInvoice();
                break;
            case "createNewInvoice":
                createNewInvoice();
                break;
            case "DeleteInvoice":
                deleteInvoice();
                break;
            case "CreateNewLine":
                displayNewLineDialog();
                break;
            case "DeleteLine":
                deleteItemLine();
                break;                           
            case "cancelCreateNewLine":
                cancelCreateNewLine();
                break;
            case "addNewLine":
                addNewLine();
                break;
        }        
    }
       
    // <editor-fold defaultstate="collapsed" desc="Selection from invoice Table Methods">
    //Method to handle Selection from invoice Table and Show the Item
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int rowIndex = frame.getInvoicesTable().getSelectedRow();
        System.out.println("Invoice Selected!" + rowIndex);
        invoicesTableRowSelected(rowIndex);
        
    }
    
    
     private void invoicesTableRowSelected(int rowIndex) {    
        if (rowIndex != -1) {
            InvoiceHeader rowSelected = frame.getInvoicesList().get(rowIndex);
            ArrayList<InvoiceLine> lines = rowSelected.getLines();
            ItemsTableModel itemsData = new ItemsTableModel(lines);
            frame.setItemLineList(lines);
            frame.getItemsTable().setModel(itemsData);
            frame.getNameTextField().setText(rowSelected.getCustomerName());
            frame.getDateTextField().setText(dateFormat.format(rowSelected.getInvoiceDate()));
            frame.getInvoiceNoValue().setText("" + rowSelected.getInvoiceNumber());
            frame.getTotalValue().setText("" + rowSelected.invoiceTotal());
         
        }
      
    }// </editor-fold> 
    
    
    // <editor-fold defaultstate="collapsed" desc="Load File Menu Item">
    private void loadFileMenuItem() {
        System.out.println("Load File");
        // Wraning msg for the the user to choose the invoice header file
        JOptionPane.showMessageDialog(frame, "Please, Select InvoiceHeader.Csv file!", "Attension", JOptionPane.WARNING_MESSAGE);
        
        //Choose File Invoices Header and Open it 
        JFileChooser openFile = new JFileChooser();
            try {
                 int result = openFile.showOpenDialog(frame);
                 //Check that the user choose the file and did not click on cancel
                 if (result == JFileChooser.APPROVE_OPTION) {
                     File invoiceHeaderFile = openFile.getSelectedFile();
                     //Read Line by Line
                     Path headerPath = Paths.get(invoiceHeaderFile.getAbsolutePath());
                     List<String> invHeaderLines = Files.readAllLines(headerPath);
                     ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
                     for(String invHeaderLine : invHeaderLines){
                         String[] data = invHeaderLine.split(",");
                         String strinvnum = data[0];
                         String strdate = data[1];
                         String strname = data[2];
                         int invNum = Integer.parseInt(strinvnum);
                         Date invoiceDate = dateFormat.parse(strdate);
                         
                         InvoiceHeader header = new InvoiceHeader(invNum, invoiceDate, strname);
                         invoiceHeaders.add(header);
                        }
                     frame.setInvoicesList(invoiceHeaders);
                     
                     
                     // Wraning msg for the the user to choose the invoice Line file        
                     JOptionPane.showMessageDialog(frame, "Please, Select Invoice line.csv file!", "Attension", JOptionPane.WARNING_MESSAGE);
                     //Choose File Invoices Line and Open it 
                     result = openFile.showOpenDialog(frame);
                     if (result == JFileChooser.APPROVE_OPTION) {
                         File itemsFile = openFile.getSelectedFile();
                         //Read Line by Line
                         Path itemPath = Paths.get(itemsFile.getAbsolutePath());
                         List<String> itemsLines = Files.readAllLines(itemPath);
                         ArrayList<InvoiceLine> invoiceItems = new ArrayList<>();
                         for(String itemLine : itemsLines){
                             String[] itemData = itemLine.split(",");
                             String item1 = itemData[0];
                             int invoiceNum = Integer.parseInt(item1);  // convert invoice number from Sting to int
                             String itemName = itemData[1];
                             String item2 = itemData[2];
                             double itemPrice = Double.parseDouble(item2); // convert Price  from Sting to Double
                             String item3 = itemData[3];
                             int itemCount = Integer.parseInt(item3);  // convert item count from Sting to int
                        
                             InvoiceHeader header = frame.findInvoiceByInvNum(invoiceNum);
                             InvoiceLine invoiceLine = new InvoiceLine(itemName, itemPrice, itemCount, header);
                             header.getLines().add(invoiceLine);
                        }
                     }
                     
                    InvoicesTableModel invheadertab = new InvoicesTableModel(invoiceHeaders);
                    frame.setInvoiceHeaderTableModel(invheadertab);
                    frame.getInvoicesTable().setModel(invheadertab); 
                 }
                System.out.println("Check");
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Number Format Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "File Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Read Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Date parse Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }          
    }// </editor-fold> 

    
    // <editor-fold defaultstate="collapsed" desc="Save File Menu Item">
    private void saveFileMenuItem() {
        System.out.println("Save File");
        JOptionPane.showMessageDialog(frame, "Please, Select to Save  InvoiceHeader.Csv file! then Choose InvoiceLine.Csv ", "Attension", JOptionPane.WARNING_MESSAGE);
         ArrayList<InvoiceHeader> invoicesList = frame.getInvoicesList();
        JFileChooser fileChoosed = new JFileChooser();
        try {
            int result = fileChoosed.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChoosed.getSelectedFile();
                FileWriter headerWriter = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for (InvoiceHeader invoice : invoicesList) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (InvoiceLine line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fileChoosed.showSaveDialog(frame);
                File lineFile = fileChoosed.getSelectedFile();
                FileWriter lineWriter = new FileWriter(lineFile);
                headerWriter.write(headers);
                lineWriter.write(lines);
                headerWriter.close();
                lineWriter.close();
                
                JOptionPane.showMessageDialog(frame, "Files Saved Successfully  ", "Attension", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// </editor-fold> 

    
    // <editor-fold defaultstate="collapsed" desc="Display New Invoice Dialog">
    private void displayNewInvoiceDialog() {
        System.out.println("New Invoice Dialog Active");
        frame.setInvoiceHeaderDialog(new CreateNewInvoiceDialog(frame));
        frame.getInvoiceHeaderDialog().setVisible(true);
    }// </editor-fold> 
    
    
    // <editor-fold defaultstate="collapsed" desc="Create New Invoice">
    private void createNewInvoice() {
        System.out.println("Create New Invoice");
        //Close the Dialog
        frame.getInvoiceHeaderDialog().setVisible(false);
        
        
        //get Data Written in the text Fields
        String customerName = frame.getInvoiceHeaderDialog().getCustomerNameField().getText();
        String invDatetxtField = frame.getInvoiceHeaderDialog().getInvoiceDateField().getText();
        //String d = dateFormat.(invDatetxtField);
        Date invoiceDate = new Date();
        try {
             invoiceDate = dateFormat.parse(invDatetxtField);      
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        /*
        int invoiceIndex = frame.getInvoicesTable().getSelectedRow();
        //InvoiceHeader header = frame.getInvoiceHeaderTableModel().getInvoicesHeaderList().get(invoiceIndex);
        frame.getInvoiceHeaderTableModel().getInvoicesHeaderList().remove(invoiceIndex);
        */
       //Save the data in the table
        int invoiceNum = 0;
        invoiceNum = getNextInvoiceIndex();
        InvoiceHeader invoiceHeader = new InvoiceHeader(invoiceNum, invoiceDate, customerName);
        frame.getInvoicesList().add(invoiceHeader);
        //Draw the Table Again After changes
        frame.getInvoiceHeaderTableModel().fireTableDataChanged();
        frame.getInvoiceHeaderDialog().dispose();
        frame.setInvoiceHeaderDialog(null);
    }

    //This function will get the Latest invoice number and add it to 1 
    private int getNextInvoiceIndex() {
        int max = 0;
        for (InvoiceHeader header : frame.getInvoicesList()) {
            if (header.getInvoiceNumber() > max) {
                max = header.getInvoiceNumber();
            }
        }
        return max + 1;
    }// </editor-fold> 
   
    
    // <editor-fold defaultstate="collapsed" desc="Cancel Create New Invoice">
    private void cancelCreateNewInvoice() {
        System.out.println("New Invoice Dialog Canceled");
        frame.getInvoiceHeaderDialog().setVisible(false);
        frame.getInvoiceHeaderDialog().dispose();
        frame.setInvoiceHeaderDialog(null);
    }// </editor-fold> 

    
    // <editor-fold defaultstate="collapsed" desc="Delete Invoice">
    private void deleteInvoice() {
        System.out.println("Invoice Deleted");
        int invoiceIndex = frame.getInvoicesTable().getSelectedRow();
        InvoiceHeader header = frame.getInvoiceHeaderTableModel().getInvoicesHeaderList().get(invoiceIndex);
        frame.getInvoiceHeaderTableModel().getInvoicesHeaderList().remove(invoiceIndex);
        frame.getInvoiceHeaderTableModel().fireTableDataChanged();
        frame.setInvoiceLinesTableModel(new ItemsTableModel(new ArrayList<InvoiceLine>()));
        frame.getItemsTable().setModel(frame.getInvoiceLinesTableModel());
        frame.getInvoiceLinesTableModel().fireTableDataChanged();
        frame.getNameTextField().setText("");
        frame.getDateTextField().setText("");
        frame.getInvoiceNoValue().setText("");
        frame.getTotalValue().setText("");
    }// </editor-fold> 

    
    // <editor-fold defaultstate="collapsed" desc="Display New Line Dialog">
    private void displayNewLineDialog() {
        System.out.println("New Line Dialog Active");
        frame.setLinenewDialog(new CreateNewLineDialog(frame));
        frame.getLineNewDialog().setVisible(true); 
    }// </editor-fold> 
    
    
    // <editor-fold defaultstate="collapsed" desc="Add New Line">
    private void addNewLine() {
       System.out.println("Add New Line");
       // get Data from the Dialog
       String itemName = frame.getLineNewDialog().getItemNameField().getText();
       String itemc = frame.getLineNewDialog().getItemCountField().getText();
       String itemprc = frame.getLineNewDialog().getItemPriceField().getText();
       
       //Close the Dialog
       frame.getLineNewDialog().setVisible(false);
       
       
       
       int itemCount = Integer.parseInt(itemc);
       double itemPrice = Double.parseDouble(itemprc);
       
       int invoiceIndex = frame.getInvoicesTable().getSelectedRow();
       if(invoiceIndex != -1){
           InvoiceHeader header = frame.getInvoicesList().get(invoiceIndex);
           InvoiceLine invoiceLine = new InvoiceLine(itemName, itemPrice, itemCount, header);
           header.getLines().add(invoiceLine);
           //frame.getItemLineList().add(invoiceLine);
           ItemsTableModel lineTableModel = (ItemsTableModel) frame.getItemsTable().getModel();
           lineTableModel.fireTableDataChanged();
           
           frame.getInvoiceHeaderTableModel().fireTableDataChanged();
       }
       frame.getInvoicesTable().setRowSelectionInterval(invoiceIndex, invoiceIndex);

       frame.getLineNewDialog().dispose();
       frame.setLinenewDialog(null);
       
       
       
       
    }// </editor-fold> 

    
    // <editor-fold defaultstate="collapsed" desc="Cancel Create New Line">
    private void cancelCreateNewLine() {
        System.out.println("New Line Dialog Canceled");
       frame.getLineNewDialog().setVisible(false);
       frame.getLineNewDialog().dispose();
       frame.setLinenewDialog(null);
    }// </editor-fold>  
    
    
    // <editor-fold defaultstate="collapsed" desc="Delete Item Line">
    private void deleteItemLine() {
        System.out.println("Line Deleted");
         
        int itemLineIndex = frame.getItemsTable().getSelectedRow();
        int invoiceIndex = frame.getInvoicesTable().getSelectedRow();
        if (itemLineIndex != -1) {
            frame.getItemLineList().remove(itemLineIndex);
            ItemsTableModel lineTableModel = (ItemsTableModel) frame.getItemsTable().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getInvoiceNoValue().setText("" + frame.getInvoicesList().get(invoiceIndex).invoiceTotal());
            frame.getInvoiceHeaderTableModel().fireTableDataChanged();
            frame.getInvoicesTable().setRowSelectionInterval(invoiceIndex, invoiceIndex);
        }
    }// </editor-fold> 

}
