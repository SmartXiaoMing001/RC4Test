package com.tman.stream;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;

import javax.crypto.KeyGenerator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class KeyPanel extends JPanel {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	KeyPanel(String str) {  
        JLabel label = new JLabel(str);  
        JTextField fileText = new JTextField(36);  
        JButton chooseButton = new JButton("随机产生");  
        this.add(label);  
        this.add(fileText);  
        this.add(chooseButton);  
        clickAction ca = new clickAction(this);  
        chooseButton.addActionListener(ca);  

    }  
  
    // 返回生成的密码（48个字符长度）  
    public String getKey() {  
        JTextField jtf = (JTextField) this.getComponent(1);  
        return jtf.getText();  
    }  
  
    public class clickAction implements ActionListener {  
        clickAction(Component c) {  
            cmpt = c;  
        }  
  
        public void actionPerformed(ActionEvent event) {  
            try {  
                KeyGenerator kg = KeyGenerator.getInstance("DES");  
                kg.init(56);  
                Key ke = kg.generateKey();  
                byte[] bytK1 = ke.getEncoded();  
                ke = kg.generateKey();  
                byte[] bytK2 = ke.getEncoded();  
                ke = kg.generateKey();  
                byte[] bytK3 = ke.getEncoded();  
  
                JPanel jp = (JPanel) cmpt;  
                JTextField jtf = (JTextField) jp.getComponent(1);  
                jtf.setText(getByteStr(bytK1) + getByteStr(bytK2)  
                        + getByteStr(bytK3));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
  
        private String getByteStr(byte[] byt) {  
            String strRet = "";  
            for (int i = 0; i < byt.length; i++) {  
                // System.out.println(byt[i]);  
                strRet += getHexValue((byt[i] & 240) / 16);  
                strRet += getHexValue(byt[i] & 15);  
            }  
            return strRet;  
        }  
  
        private String getHexValue(int s) {  
            String sRet = null;  
            switch (s) {  
            case 0:  
                sRet = "0";  
                break;  
            case 1:  
                sRet = "1";  
                break;  
            case 2:  
                sRet = "2";  
                break;  
            case 3:  
                sRet = "3";  
                break;  
            case 4:  
                sRet = "4";  
                break;  
            case 5:  
                sRet = "5";  
                break;  
            case 6:  
                sRet = "6";  
                break;  
            case 7:  
                sRet = "7";  
                break;  
            case 8:  
                sRet = "8";  
                break;  
            case 9:  
                sRet = "9";  
                break;  
            case 10:  
                sRet = "A";  
                break;  
            case 11:  
                sRet = "B";  
                break;  
            case 12:  
                sRet = "C";  
                break;  
            case 13:  
                sRet = "D";  
                break;  
            case 14:  
                sRet = "E";  
                break;  
            case 15:  
                sRet = "F";  
            }  
            return sRet;  
        }  
        private Component cmpt;  
    }  
} 
