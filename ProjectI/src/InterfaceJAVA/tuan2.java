/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceJAVA;

import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import InterfaceJAVA.tuan1;

/**
 *
 * @author Manh VU
 */
public class tuan2 extends javax.swing.JFrame {

    /**
     * Creates new form tuan2
     */
    public tuan2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        chithi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        result = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        tf = new javax.swing.JTextField();
        exCb = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        input = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cb2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        debai = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        result2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        input1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        chithi1 = new javax.swing.JTextArea();

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("Bạn đã chọn: ");

        jButton3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 255));
        jButton3.setText("ENTER");

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jTextArea3.setForeground(new java.awt.Color(0, 0, 255));
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        chithi.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        chithi.setForeground(new java.awt.Color(0, 204, 51));

        jLabel6.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Mô tả đầu vào");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setText("Kết quả :");

        result.setColumns(20);
        result.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        result.setForeground(new java.awt.Color(0, 0, 255));
        result.setRows(5);
        jScrollPane4.setViewportView(result);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setForeground(new java.awt.Color(0, 0, 255));
        jButton1.setText("OK");

        tf.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        exCb.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        exCb.setForeground(new java.awt.Color(0, 51, 255));
        exCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bai 1", "Bai 2", "Bai 3", "Bai 4", "Bai 5", "Bai 6", "Bai 7", "Bai 8", "Bai 9", "Bai 10", "Bai 11", "Bai 12", "Bai 13", "Bai 14", " ", " " }));

        jButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 255));
        jButton2.setText("Hiển thị đề bài");

        jLabel8.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Chọn bài tập: ");

        input.setColumns(20);
        input.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        input.setForeground(new java.awt.Color(0, 0, 255));
        input.setRows(5);
        jScrollPane5.setViewportView(input);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("BÀI TẬP JAVA TUẦN 1");

        jButton6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 255));
        jButton6.setText("ENTER");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("BÀI TẬP JAVA TUẦN 2");

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Chọn bài tập: ");

        cb2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        cb2.setForeground(new java.awt.Color(0, 51, 255));
        cb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bai 1", "Bai 2", "Bai 3", "Bai 4", "Bai 5", "Bai 6", "Bai 7", "Bai 8", "Bai 9", "Bai 10", "Bai 11", "Bai 12", " " }));
        cb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb2ActionPerformed(evt);
            }
        });

        debai.setBackground(new java.awt.Color(255, 255, 255));
        debai.setColumns(20);
        debai.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        debai.setForeground(new java.awt.Color(0, 0, 255));
        debai.setLineWrap(true);
        debai.setRows(5);
        debai.setWrapStyleWord(true);
        jScrollPane1.setViewportView(debai);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Kết quả :");

        result2.setBackground(new java.awt.Color(255, 255, 255));
        result2.setColumns(20);
        result2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        result2.setForeground(new java.awt.Color(0, 0, 255));
        result2.setRows(5);
        jScrollPane2.setViewportView(result2);

        jButton4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 255));
        jButton4.setText("OK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Mô tả đầu vào");

        jButton7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 255));
        jButton7.setText("ENTER");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Đề bài");

        input1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        input1.setForeground(new java.awt.Color(51, 0, 255));

        chithi1.setBackground(new java.awt.Color(255, 255, 255));
        chithi1.setColumns(20);
        chithi1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        chithi1.setForeground(new java.awt.Color(51, 51, 255));
        chithi1.setLineWrap(true);
        chithi1.setRows(5);
        chithi1.setWrapStyleWord(true);
        jScrollPane6.setViewportView(chithi1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(248, 248, 248))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(input1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(63, 63, 63))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jButton4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(137, 137, 137)
                                        .addComponent(jButton7)))
                                .addGap(174, 174, 174)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                                .addComponent(jScrollPane1))
                            .addComponent(jLabel3))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel3)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(input1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb2ActionPerformed
    private int index;
    public void hoTen1(){
        result2.append("\n-------------------------------------------------------");
	result2.append("\n20162679 - Vu Duy Manh - 676424");
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        result2.setText("");
        if (index == 1) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] intValues = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    intValues[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }

            }
            int n = intValues.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (intValues[i] < intValues[j]) {
                        int temp = intValues[i];
                        intValues[i] = intValues[j];
                        intValues[j] = temp;
                    }
                }
            }

            for (int i = 0; i < intValues.length; i++) {
                result2.append(intValues[i] + "   ");
            }
            hoTen1();
        } else if (index == 2) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }

            }
            int k, dem, x;
            int N = a.length;
            for (k = 0; k < N; k++) {
                dem = 0;
                for (x = 2; x <= a[k]; x++) {
                    if (a[k] % x == 0) {
                        dem++;
                    }
                }
                if (dem == 1) {
                    result2.append(a[k] + " ");
                }
            }
            hoTen1();
        } else if (index == 3) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length;
            int k, min;
            min = a[0];
            for (k = 0; k < N; k++) {
                if (min > a[k]) {
                    min = a[k];
                }
            }
            result2.append("Giá trị nhỏ nhất của dãy là: " + min);
            result2.append("\nVị trí của phần tử có giá trị min là: ");
            for (k = 0; k < N; k++) {
                if (min == a[k]) {
                    result2.append(k + " ");
                }
            }
            hoTen1();
        } else if (index == 4) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length, k, max;
            max = a[0];
            for (k = 0; k < N; k++) {
                if (max < a[k]) {
                    max = a[k];
                }
            }
            result2.append("Giá trị lớn nhất của dãy là: " + max);
            result2.append("\nVị trí của phần tử có giá trị max là: ");
            for (k = 0; k < N; k++) {
                if (max == a[k]) {
                    result2.append(k + " ");
                }
            }
            hoTen1();
        } else if (index == 5) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length, k;
            int nt = 0;
            int hs = 0;
            for (k = 0; k < N; k++) {
                int dem = 0;
                for (int x = 2; x <= a[k]; x++) {
                    if (a[k] % x == 0) {
                        dem++;
                    }
                }
                if (dem == 1) {
                    nt++;
                } else {
                    hs++;
                }
            }
            result2.append("Số các số nguyên tố là: " + nt);
            result2.append("\nSố các hợp số là: " + hs);
            hoTen1();
        } else if (index == 6) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length, k;
            for (k = 0; k < N; k++) {
                for (int j = 0; j < N; j++) {
                    if ((j == k) | (a[k] == 0)) {
                        continue;
                    }
                    if (a[j] % a[k] == 0) {
                        result2.append(a[k] + "    ");
                        break;
                    }
                }
            }
            hoTen1();
        } else if (index == 7) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length, k, in = 0;
            for (k = 0; k < N - 1; k++) {
                for (int j = k + 1; j < N; j++) {
                    int temp;
                    if (a[k] > a[j]) {
                        temp = a[j];
                        a[j] = a[k];
                        a[k] = temp;
                    }
                }
            }
            for (k = 0; k < N - 1; k++) {
                if (a[k] != a[k + 1]) {
                    if (k == 0) {
                        result2.append(a[k] + "");
                        break;
                    } else if (a[k - 1] != a[k]) {
                        result2.append(a[k] + "");
                        break;
                    }
                }
            }
            hoTen1();
        } else if (index == 8) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length, i = 0, j = 0, dem = 0;
            int[] b = new int[N];
            while (i < N) {
                if (a[i] == 0) {
                    i++;
                } else {
                    b[j] = a[i];
                    i++;
                    j++;
                    dem++;
                }
            }
            for (j = 0; j < dem; j++) {
                result2.append(b[j] + " ");
            }
            hoTen1();
        } else if (index == 9) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length, k, c = NhapC.c;
            int dem1 = 0, dem2 = 0, dem3 = 0;
            for (k = 0; k < N; k++) {
                if (a[k] < c) {
                    dem1++;
                }
                if (a[k] == c) {
                    dem2++;
                }
                if (a[k] > c) {
                    dem3++;
                }
            }
            result2.append("Số các số nhỏ hơn " + c + " là:" + dem1 + "\n");
            result2.append("Số các số bằng " + c + " là:" + dem2 + "\n");
            result2.append("Số các số lớn hơn " + c + " là:" + dem3 + "\n");
            hoTen1();
        } else if (index == 10) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length, k, demmax = 0, dem, x = 0;
            for (k = 0; k < N - 1; k++) {
                if (a[k] == a[k + 1]) {
                    dem = 0;
                    for (int j = k; j < N; j++) {
                        if (a[k] == a[j]) {
                            dem++;
                        }
                        if (demmax < dem) {
                            x = k;
                            demmax = dem;
                        }
                    }
                }
            }
            result2.append("Số các số thuộc dãy dài nhất là: " + demmax);
            result2.append("\nChỉ số của dãy dài nhất là:" + x);
            hoTen1();
        } else if (index == 11) {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int N = a.length, demmax = 0, dem, x = 0;
            int k;
            for (k = 0; k < N - 1; k++) {
                if (a[k] <= a[k + 1]) {
                    dem = 1;
                    for (int j = k; j < N - 1; j++) {
                        if (a[j] <= a[j + 1]) {
                            dem++;
                            if (demmax < dem) {
                                demmax = dem;
                                x = k;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            result2.append("Số các số thuộc dãy dài nhất là: " + demmax);
            result2.append("\nChỉ số của dãy dài nhất là: " + x);
            hoTen1();
        } else {
            String values = input1.getText().replaceAll("\\s", "");
            String[] strValues = values.split(",");
            int[] a = new int[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                try {
                    a[i] = Integer.parseInt(strValues[i]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Bạn nhập không chính xác");
                }
            }
            int M = NhapMangb.b.length, N = a.length, k, x = 0, j;
            int in = 0;
            for (j = 0; j < N; j++) {
                for (k = x; k < M; k++) {
                    {
                        if (a[j] == NhapMangb.b[k]) {
                            in++;
                            x = k + 1;
                            break;
                        }
                    }
                }
            }
            if (in == N) {
                result2.append("Dãy a là dãy con của dãy b");
            } else {
                result2.append("Dãy a không là con của dãy b");
            }
            hoTen1();
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        result2.setText("");
        input1.setText("");
        if (cb2.getSelectedItem().toString().equals("Bai 1")) {
            index = 1;
            debai.setText("Bài 01: Cho một dãy số tự nhiên, viết chương trình sắp xếp dãy này theo thứ tự giảm dần");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 2")) {
            index = 2;
            debai.setText("Bài 02: Cho một dãy số tự nhiên, in ra màn hình tất cả các số nguyên tố của dãy này");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 3")) {
            index = 3;
            debai.setText("Bài 03: Cho một dãy số tự nhiên, tìm và in ra một giá trị min của dãy này và tất cả các chỉ số ứng với giá trị min này");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 4")) {
            index = 4;
            debai.setText("Bài 04:Cho một dãy số tự nhiên, tìm và in ra một giá trị max của dãy này và tất cả các chỉ số ứng với giá trị max này");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 5")) {
            index = 5;
            debai.setText("Bài 05: Cho một dãy số nguyên tố, hãy đếm xem trong dãy có bao nhiêu số nguyên tố bao nhiêu hợp số");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 6")) {
            index = 6;
            debai.setText("Bài 06: Cho một dãy số tự nhiên, hãy in ra các số thỏa mãn số này là ước thực sự của một số hạng khác trong dãy");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 7")) {
            index = 7;
            debai.setText("Bài 07: Cho một dãy số tự nhiên, hãy tìm ra 1 số tự nhiên nhỏ nhất c không bằng bất cứ số nào trong dãy trên ");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 8")) {
            index = 8;
            debai.setText("Bài 08: Cho một dãy số tự nhiên, hãy xóa đi trong dãy này các số hạng bằng 0 và in ra các số còn lại");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 9")) {
            index = 9;
            debai.setText("Bài 09: Cho một dãy số tự nhiên, cho trước một số c, hãy đếm xem trong dãy này có bao nhiêu số >c,<c,=c");
            NhapC nhap = new NhapC();
            nhap.setVisible(true);
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");

        } else if (cb2.getSelectedItem().toString().equals("Bai 10")) {
            index = 10;
            debai.setText("Bài 10: Cho một dãy số tự nhiên, hãy tìm ra một dãy số liền nhau dài nhất bao gồm các số bằng nhau. Hãy in ra số lượng và các chỉ số đầu tiên của dãy con này");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 11")) {
            index = 11;
            debai.setText("Bài 11: Cho một dãy số nguyên bất kì. Hãy tìm một dãy con liên túc đơn điệu tăng dài nhất của dãy trên .");
            chithi1.setText("Nhập vào dãy số, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        } else if (cb2.getSelectedItem().toString().equals("Bai 12")) {
            index = 12;
            NhapMangb nhapb = new NhapMangb();
            nhapb.setVisible(true);
            debai.setText("Bài 12: Dãy số a[] được gọi là dãy con của b[] nếu từ b[] xóa đi 1 vài số sẽ thu được a[]. Cho trước 2 dãy số nguyên  a[],b[] . Hãy kiểm tra xem a[] có là con của b[] hay không? .");
            chithi1.setText("Nhập vào dãy a, các số cách nhau bởi dấu phẩy vào ô bên dưới");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tuan2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tuan2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tuan2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tuan2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tuan2 t2=new tuan2();
                t2.setVisible(true);
                t2.setTitle("Vũ Duy Mạnh - 20162679");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb2;
    private javax.swing.JTextField chithi;
    private javax.swing.JTextArea chithi1;
    private javax.swing.JTextArea debai;
    private javax.swing.JComboBox<String> exCb;
    private javax.swing.JTextArea input;
    public static javax.swing.JTextField input1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    public javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea3;
    public javax.swing.JTextArea result;
    public static javax.swing.JTextArea result2;
    private javax.swing.JTextField tf;
    // End of variables declaration//GEN-END:variables
}