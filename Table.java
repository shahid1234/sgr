
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.awt.Color;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author Shahid
 */
public class Table extends javax.swing.JPanel {

    int row1=0;
    int col1=0;
   int d =0;
    String result = "";
    
    public Table() {
        initComponents();
        jTextArea2.setEditable(false);
        jTextArea3.setEditable(false);
        this.setBackground(Color.WHITE);
    }
        public void SymbolTable(String s) {

        int j = 0;
        StringTokenizer st = new StringTokenizer(s);
        String[] temp = new String[st.countTokens()];
        for (j = 0; st.hasMoreTokens(); j++) {
            temp[j] = st.nextToken();
        }


        for (int i = 0; i < temp.length; i++) {
         
            // KeyWord Checking
            if (IsKeyword(temp[i])) {
                if (IsDataType(temp[i])) {
                    result = "DataType";
                } else if (IsFunction(temp[i])) {
                    result = "Function Keyword";
                } else if (temp[i].equals("repeat")) {
                    result = "Loop keyword";
                } else {
                    result = "Keyword";
                }

            } else if (("var".equals(temp[i]))) {
                    result = "Variable Declaration";
               } 
            
            // Operator Checking
            else if (IsOperator(temp[i])) {
                 if (temp[i].equals("|+")) {
                    result = "Summation Operator";
                    if(temp[i].equals("|+") && IsIdentifier(temp[i+1]) && IsIdentifier(temp[i-1]) ){
                          jTextArea2.append("\n"+"add ax , bx");
                       }
                } else if (temp[i].equals("|-")) {
                    result = "Subtraction Operator";
                    if(temp[i].equals("|-") && IsIdentifier(temp[i+1]) && IsIdentifier(temp[i-1]) ){
                        
                         
                          jTextArea2.append("\n"+"sub ax , bx");
                          
                       }
                }  else if (temp[i].equals("|/")) {
                    result = "Division Operator";
                     if(temp[i].equals("|/") && IsIdentifier(temp[i+1]) && IsIdentifier(temp[i-1]) ){
                          jTextArea2.append("\n"+"div ax , bx");
                       }
                } else if (temp[i].equals("|*")) {
                    result = "Multiplication Operator";
                    if(temp[i].equals("|*") && IsIdentifier(temp[i+1]) && IsIdentifier(temp[i-1]) ){
                          jTextArea2.append("\n"+"mul ax , bx");
                       }
                } else if (temp[i].equals("!")) {
                  result = "NOT Operator";
                  if(temp[i].equals("!") && IsIdentifier(temp[i+1])  ){
                          jTextArea2.setText("not "+ temp[i+1]);
                       }
                }  else if (temp[i].equals("<>")) {
                    result = "Concatenation Operator";
                } else if (temp[i].equals("|&")) {
                    result = "AND Operator";
                    if(temp[i].equals("|&") && IsIdentifier(temp[i+1]) && IsIdentifier(temp[i-1]) ){
                         jTextArea2.append("\n"+"AND ax , bx");
                       }
                } else if (temp[i].equals("|#")) {
                    result = "OR Operator";
                    if(temp[i].equals("|#") && IsIdentifier(temp[i+1]) && IsIdentifier(temp[i-1]) ){
                          jTextArea2.append("\n"+"OR ax , bx");
                          
                       }
                } else if (temp[i].equals("[")) {
                    result = "Start Function";
                } else if (temp[i].equals("]")) {
                    result = "Termination of Function";
                } else if (temp[i].equals(".")) {
                    result = "Start Of Statement";
                }else if (temp[i].equals(":")) {
                    result = "Assignment Operater";
                }else if (temp[i].equals(",")) {
                    result = "Step Operator";
                }else if (temp[i].equals("(")) {
                    result = "Starting Parenthesis";
                } else if (temp[i].equals(")")) {
                    result = "Ending Parenthesis";
                    
                }
               else {
                    result = "Not Defined in Eighteen++";
                }
} 

            // Number Checking 
            else if (IsNumber(temp[i])) {
                result = "(DataType)Number";
            } 
            else if (IsRational(temp[i])) {
                result = "(DataType)Rational Literal";
            }  else if ((";".equals(temp[i]))) {
                result = "End Of Statement";
            }
            else if (IsIdentifier(temp[i])) {
                if (!("var".equals(temp[i]))) {
                result = "Identifier";
                  }
            } 
            else if (NotIdentifier(temp[i])) {
                result = "Not Identifier";
                }
         else  {
                    result="Not Defined in Eighteen++";
                }
                          jTable1.setValueAt(temp[i], row1, col1++);
                          jTable1.setValueAt(result, row1, col1);
                          col1 = 0;
                          row1++;
          }
    }
        
    public static boolean IsKeyword(String s) {
        String[] keyword = {"number", "rational",  "acquire", "display", "if", "elif", "el", "fi", "repeat", "whole"};
        for (int i = 0; i < keyword.length; i++) {
            if (keyword[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsFunction(String s) {
        String[] keyword = {"acquire", "display"};
        for (int i = 0; i < keyword.length; i++) {
            if (keyword[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsDataType(String s) {
        String[] Dtype = {"number", "rational","whole"};
        for (int i = 0; i < Dtype.length; i++) {
            if (Dtype[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsNumber(String i) {
        try {
            int temp = Integer.parseInt(i);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean IsRational(String i) {
        try {
            if (i.contains(".")) {
                float temp = Float.parseFloat(i);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
public static boolean IsIdentifier(String i) {
        if (i.matches("[_a-zA-Z][_a-z_A-Z0-9]*") && !(IsKeyword(i)) && !(NotIdentifier(i)) ) {

            return true;

        }else if(i.matches("[a-z_a-z][a-zA-Z0-9]*") && !(IsKeyword(i)) && !(NotIdentifier(i))){
        
            return true;
        }else
    
        return false;
    }
    
public static boolean IsOperator(String s) {
        String[] oper = {"|+","|-","|*","|/","=","!","<>","|&","|#","[","]","|%",".",":","(",")",","};
        
        for (int i = 0; i < oper.length; i++) {
            if (oper[i].equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean NotIdentifier(String s){
        String[] notid={"int","char","float","double","String","Array","public","static"};
         for (int i = 0; i < notid.length; i++) {
            if (notid[i].equals(s)) {
                return true;
            }
        }
        return false;
    }


   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1360, 720));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("Enter Code Here :");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Word", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setText("Symbol Table");

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jButton1.setText("Create Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setText("Assembly Code");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel4.setText("Statement Checking");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane4))
                            .addComponent(jLabel4))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jButton1)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(230, 230, 230))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButton1)
                .addContainerGap(115, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] input = jTextArea1.getText().split("\\n");

        for (int i = 0; i < input.length; i++) {

            SymbolTable(input[i]);
            Statement_Checking(input[i]);
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed
static String [] temp; 
static Stack brakets = new Stack();
public  void Statement_Checking(String s)  
 { 
     
int j =0;
StringTokenizer st = new StringTokenizer(s);
temp =new String [st.countTokens()] ;
     for( j=0;st.hasMoreTokens();j++)
            {
                temp[j]=st.nextToken();
            }
   if(s.contains("(")|| s.contains(")")|| s.contains("{")|| s.contains("}")){
            chkbraces();
        }
    
 //Check Start Statment 
    if(!temp[0].equals(".")) {
        jTextArea3.append("\n"+". (dot) missing");
//        System.out.println(". (dot) missing");
        return;
    }
 if(j==2){    
     
 if (((temp[0].equals("."))&&(temp[1].equals("[")))) 
            {
                jTextArea3.append("\n"+"Start of the code block statement correct");
               // System.out.println("Start of the code block statement correct"); 
            }
 else if ((j==2)&&(temp[0].equals("."))&&(temp[1].equals("]"))) 
            {
                jTextArea3.append("\n"+"End of the code block statement correct");
               // System.out.println("End of the code block statement correct"); 
            }
  else if ((j==2)&&(temp[0].equals("."))&&(temp[1].equals("fi")) )  
            {
                System.out.println("(end of if elif) statement correct"); //get byte and display byte functions
 jTextArea3.append("\n"+"(end of if elif) statement correct");
            }
     
       else{ 
      jTextArea3.append("\n"+"InComplete Statement ");
      System.out.println("InComplete Statement "); 
 }
 }            
 else{
     
    
     
     if(j==3){
         if((temp[0].equals(".")) &&(temp[1].equals("acquire")) && (temp[2].equals(";"))){
            jTextArea3.append("\n"+"Acquire Statement error");
             System.out.println("Acquire Statement error");  
         }
         else if((temp[0].equals(".")) &&(temp[1].equals("display")) && (temp[2].equals(";"))){
            jTextArea3.append("\n"+"Display Statement error");
             System.out.println("Display Statement error");  
         }
         else if((temp[0].equals(".")) &&(IsIdentifier(temp[1])) && (temp[2].equals(";"))){
            jTextArea3.append("\n"+"DataType missing for the identifier");
             System.out.println("DataType missing for the identifier");  
         }
     }
     if(j==5){
         if (d==0&& (temp[0].equals(".")) && (IsIdentifier(temp[1])) && (temp[2].equals(":")) && (IsNumber(temp[3])) && ((temp[4]).equals(";")))   
            {
              d++; 
jTextArea3.append("\n"+"Declarative Statement correct");
jTextArea2.append("\n"+"mov ax ,"+ temp[3]);

            }
        else if (d==1&& (temp[0].equals(".")) && (IsIdentifier(temp[1])) && (temp[2].equals(":")) && (IsNumber(temp[3])) && ((temp[4]).equals(";")))   
            {
               
jTextArea3.append("\n"+"Declarative Statement correct");
jTextArea2.append("\n"+"mov bx , "+ temp[3]);
            }
     }
     
     
     else{        //Declarartion  Checking   
              
    if(j==6){         
            
          if ((temp[0].equals(".")) &&(temp[1].equals("var")) && (IsIdentifier(temp[2])) && (temp[3].equals(":")) && ((IsDataType(temp[4]))) && ((temp[5]).equals(";")))   
            {
                System.out.println("Declarative Statement correct"); 
jTextArea3.append("\n"+"Declarative Statement correct");
jTextArea2.append("\n"+ temp[2]+ " db 0");
            }
          else  if ((temp[0].equals(".")) &&(temp[1].equals("if"))&&(temp[2].equals("(")) &&(IsIdentifier(temp[3])) &&((IsConditional(temp[4])))&&(IsIdentifier(temp[5])))  
            {
             jTextArea3.append("\n"+"If statement; ) missing");
                System.out.println("If statement; ) missing"); //get byte and display byte functions

            }
       else  if ((temp[0].equals(".")) &&(temp[1].equals("if")) &&(IsIdentifier(temp[2])) &&((IsConditional(temp[3])))&&(IsIdentifier(temp[3]))&& (temp[5].equals(")")))  
            {
                jTextArea3.append("\n"+"If statement; ( missing ");
                System.out.println("If statement; ( missing "); //get byte and display byte functions

            }
       else  if ((temp[0].equals(".")) &&(temp[1].equals("if"))&&(temp[2].equals("(")) &&((IsConditional(temp[3])))&&(IsIdentifier(temp[3]))&& (temp[5].equals(")")))  
            {
                jTextArea3.append("\n"+"If statement; comparision statement is invalid");
                System.out.println("If statement; comparision statement is invalid"); //get byte and display byte functions

            }
       else  if ((temp[0].equals(".")) &&(temp[1].equals("if"))&&(temp[2].equals("(")) &&(IsIdentifier(temp[3])) &&((IsConditional(temp[4])))&& (temp[5].equals(")")))  
            {
                jTextArea3.append("\n"+"If statement; comparision statement is invalid");
                System.out.println("If statement; comparision statement is invalid"); //get byte and display byte functions

            }
       else  if ((temp[0].equals(".")) &&(temp[1].equals("if"))&&(temp[2].equals("(")) &&(IsIdentifier(temp[3])) &&(IsIdentifier(temp[5]))&& (temp[6].equals(")")))  
            {
                jTextArea3.append("\n"+"If statement; comparision statement is invalid");
                System.out.println("If statement; comparision statement is invalid"); //get byte and display byte functions

            }
     
       } 
    else {
    
        if ((temp[0].equals(".")) &&(temp[1].equals("var")) && (IsIdentifier(temp[2])))
            {
                jTextArea3.append("\n"+"InCorrect Declaration");
                System.out.println("InCorrect Declaration"); 
            }
    if ((temp[0].equals(".")) &&(temp[1].equals("var")) && (IsIdentifier(temp[2]))&&(temp[3].equals(";")))
            {
                jTextArea3.append("\n"+"DataType Missing");
                System.out.println("DataType Missing"); 
            }
    
    if ((temp[0].equals(".")) && (IsFunction(temp[1])) && (temp[2].equals("(")) &&(IsDataType(temp[3])) && (temp[4].equals(","))&& (IsIdentifier(temp[5]) && (temp[6].equals(")")) && (temp[7].equals(";")))||((j==8)&&(temp[0].equals(".")) && (IsIdentifier(temp[1]))&& (temp[2].equals("=")) &&(IsFunction(temp[3]))  && (temp[4].equals("(")) && (IsDataType(temp[5])) &&  (temp[6].equals(")")) &&  (temp[7].equals(";")) ))   
            {
                jTextArea3.append("\n"+"Correct "+temp[3]+" Function");
                if(temp[1].equals("display")){
                    jTextArea2.append("\n"+"mov ah, 2h \n int 21h");
                }
                if(temp[1].equals("acquire")){
                    jTextArea2.append("\n"+"mov ah, 1h \n int 21h");
                }
                if(temp[3].equals("acquire")){
                    jTextArea2.append("\n"+"mov ah, 1h \n int 21h");
                }
                else if(temp[3].equals("display")){
                    jTextArea2.append("\n"+"mov ah, 2h \n int 21h");
                }
                System.out.println("Correct Function D"); //get byte and display byte functions

            
               }
    else{
          if ((temp[0].equals(".")) &&(temp[1].equals("repeat")) && (IsIdentifier(temp[2])) && (temp[3].equals(":"))&&((IsNumber(temp[4])))&&(temp[5].equals(","))&&((IsNumber(temp[6]))))   
            {
                jTextArea3.append("\n"+"statement loop correct");
                System.out.println("statement loop correct"); //get byte and display byte functions
                
            jTextArea2.append("\n"+"loop " + temp[2]);
            }
       else  if ((temp[0].equals(".")) &&(temp[1].equals("if"))&&(temp[2].equals("(")) &&(IsIdentifier(temp[3])) &&((IsConditional(temp[4])))&&(IsIdentifier(temp[5]))&& (temp[6].equals(")")))  
            {
                jTextArea3.append("\n"+"If statement correct");
                jTextArea2.append("\n"+"cmp "+ temp[3]+","+temp[5]);
               
                System.out.println("If statement correct"); //get byte and display byte functions

            }else if(temp[0].equals(".") && temp[1].equals("acquire") && IsIdentifier(temp[2]) && temp[3].equals(";")){
            System.out.println("acquire statement correct");
            jTextArea3.append("\n"+"acquire statement correct");
            jTextArea2.append("\n"+"mov ah, 1h \n int 21h");
            //           . my_int = acquire ( number ) ;
           //. my_float = acquire ( rational ) ;

        }
            else if(temp[0].equals(".") && IsIdentifier(temp[1])&& temp[2].equals("=") && temp[3].equals("acquire")&& temp[4].equals("(")&& IsDataType(temp[5]) && temp[6].equals(")")){
            System.out.println("acquire statement correct");
            jTextArea3.append("\n"+"acquire statement correct");
            jTextArea2.append("\n"+"mov ah, 1h \n int 21h");
            //           . my_int = acquire ( number ) ;
           //. my_float = acquire ( rational ) ;

        }
             else if(temp[0].equals(".") && IsIdentifier(temp[1]) && temp[2].equals("=") && temp[3].equals("display")&& temp[4].equals("(")&& IsDataType(temp[5]) && temp[6].equals(")")){
            System.out.println("acquire statement correct");
            jTextArea3.append("\n"+"acquire statement correct");
            jTextArea2.append("\n"+"mov ah, 1h \n int 21h");
            //           . my_int = acquire ( number ) ;
           //. my_float = acquire ( rational ) ;

        }
       else if(temp[0].equals(".") && temp[1].equals("display") && IsIdentifier(temp[2]) && temp[3].equals(";")){
            System.out.println("display statement correct");
            jTextArea3.append("\n"+"display statement correct");
            jTextArea2.append("\n"+"mov ah, 09h \n int 21h");
        }
        else if ((temp[0].equals(".")) &&(temp[1].equals("elif"))&&(temp[2].equals("(")) &&(IsIdentifier(temp[3])) &&(IsConditional(temp[4]))&&(IsIdentifier(temp[5]))&& (temp[6].equals(")")))  
            {
                jTextArea3.append("\n"+"elif statement correct");
                jTextArea2.append("\n"+"cmp "+ temp[3]+","+temp[5]);

            }
       else if(temp[0].equals(".") && IsIdentifier(temp[1]) && IsOperator(temp[2]) && IsIdentifier(temp[3])){
           jTextArea3.append("\n"+temp[2]+" operational equation");
           System.out.println(temp[2]+" operational equation");
            String s11 = null;
           for(int i=0; i<temp.length;i++){
          s11=s11+temp[i];
           }
       
        
       }
       
        
      else{
          jTextArea3.append("\n"+"Incorrect function");
           
      }
    }
    }
     
          
}

 }}public static boolean IsEBraces(String s){
        String [] q = {"}","]",")"};
       for(int i=0;i<q.length;i++)
        {
            if(q[i].equals(s))
            {return true;}
        }
        return false;
    }
 public static boolean IsSBraces(String s){
        String [] q = {"(","[","{"};
       for(int i=0;i<q.length;i++)
        {
            if(q[i].equals(s))
            {return true;}
        }
        return false;
    }
 private static void chkbraces() {
        int count=0;
        for(int f =0 ; f <temp.length; f++){
            
            if(IsSBraces(temp[f])){
                brakets.push(temp[f]);
                count++;
            }
            else if(IsEBraces(temp[f]) && !brakets.isEmpty()){
                brakets.pop();
                count--;
            }
            else if(IsEBraces(temp[f]) && brakets.isEmpty()){
                System.out.println("braces mismatch");
            }
            else {
                
            }
                
        }
        if(!brakets.isEmpty() || count!=0) {
            System.out.println("braces mismatch");
        }
    }
  
public static boolean IsConditional(String s)
    {
        String []oper={"==","!=",">",">=","<","<=","|&","|#"};
        for(int i=0;i<oper.length;i++)
        {
            if(oper[i].equals(s))
            {return true;}
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables
}
