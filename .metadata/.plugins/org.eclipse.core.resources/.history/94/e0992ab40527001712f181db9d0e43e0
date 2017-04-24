package com.tman.stream;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.tman.stream.FilePanel;
import com.tman.stream.KeyPanel;

public class Stream extends JFrame {  
    /**
	 * FilePanel:第一行内容
	 * KeyPanel:第二行内容
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;  
    public static final int HEIGHT = 200;  
  
    @SuppressWarnings("deprecation")
	public static void main(String args[]) {  
    	Stream fe = new Stream();  
        fe.show();  
    }  
    //D0F45EC25729F16E86E51A85BF40C70EABDC8054F80DC289
    Stream() {  
        this.setSize(WIDTH, HEIGHT);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable(false);  
        Toolkit tk = Toolkit.getDefaultToolkit();  
        Dimension screenSize = tk.getScreenSize();  
        this.setLocation((screenSize.width - WIDTH) / 2,  
                (screenSize.height - HEIGHT) / 2);  
        this.setTitle("文件加密器(TriDES)");  
        
        Container c = this.getContentPane();  
        c.setLayout(new FlowLayout());  
  
        final FilePanel fp = new FilePanel("文件选择");  
        c.add(fp);  
  
        final KeyPanel pp = new KeyPanel("密码");  
        c.add(pp);  
  
        JButton jbE = new JButton("加密");  
        c.add(jbE);  
        jbE.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent event) {  
 
                File file = new File(fp.getFileName()); 
                /**文件的路径在这里！！！*/
                if (file.exists())  
                    encrypt(file.getAbsoluteFile(), pp.getKey());  
                else  
                    JOptionPane.showMessageDialog(null, "请选择文件！", "提示",  
                            JOptionPane.OK_OPTION);  
            }  
        });  
        JButton jbD = new JButton("解密");  
        c.add(jbD);  
        jbD.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent event) {  
                File file = new File(fp.getFileName());  
                if (file.exists())  
                    decrypt(file.getAbsoluteFile(), pp.getKey());  
                else  
                    JOptionPane.showMessageDialog(null, "请选择文件！", "提示",  
                            JOptionPane.OK_OPTION);  
            }  
        });  
    }  
  
    /** 
     * 加密函数 输入： 要加密的文件，密码（由0-F组成，共48个字符，表示3个8位的密码）如： 
     * AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746 其中： AD67EA2F3BE6E5AD 
     * DES密码一 D368DFE03120B5DF DES密码二 92A8FD8FEC2F0746 DES密码三 输出： 
     * 对输入的文件加密后，保存到同一文件夹下增加了".tdes"扩展名的文件中。 
     */  
    private void encrypt(File fileIn, String sKey) {  
        try {  
            if (sKey.length() == 48) {  
                byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));  
                
                /**
                 * 暂时变成两次
                byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));  
                byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));  
                */
                FileInputStream fis = new FileInputStream(fileIn);  
                byte[] bytIn = new byte[(int) fileIn.length()];  
                for (int i = 0; i < fileIn.length(); i++) {  
                    bytIn[i] = (byte) fis.read();  
                }  
                // 加密  
                /**
                 * byte[] bytOut = encryptByDES(  
                        encryptByDES(encryptByDES(bytIn, bytK1), bytK2), bytK3);
                */
                byte[] bytOut = encryptByDES(bytIn, bytK1);
                String fileOut =  fileIn.getPath() +".test";  
                FileOutputStream fos = new FileOutputStream(fileOut);  
                for (int i = 0; i < bytOut.length; i++) {  
                    fos.write((int) bytOut[i]);  
                }  
                fos.close();  
                JOptionPane.showMessageDialog(this, "加密成功！", "提示",  
                        JOptionPane.OK_OPTION);  
            } else  
                JOptionPane.showMessageDialog(this, "密码长度必须等于48！", "错误信息",  
                        JOptionPane.ERROR_MESSAGE);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 
     * 解密函数 输入： 要解密的文件，密码（由0-F组成，共48个字符，表示3个8位的密码）如： 
     * AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746 其中： AD67EA2F3BE6E5AD 
     * DES密码一 D368DFE03120B5DF DES密码二 92A8FD8FEC2F0746 DES密码三 输出： 
     * 对输入的文件解密后，保存到用户指定的文件中。 
     * 
     */  
    private void decrypt(File fileIn, String sKey) {  
        try {  
            if (sKey.length() == 48) {  
                String strPath = fileIn.getPath();  
                if (strPath.substring(strPath.length() - 5).toLowerCase()  
                        .equals(".test"))  
                    strPath = strPath.substring(0, strPath.length() - 5);  
                else {  
                    JOptionPane.showMessageDialog(this, "不是合法的加密文件！", "提示",  
                            JOptionPane.OK_OPTION);  
                    return;  
                }  
                JFileChooser chooser = new JFileChooser();  
                chooser.setCurrentDirectory(new File("."));  
                chooser.setSelectedFile(new File(strPath));  
                // 用户指定要保存的文件  
                int ret = chooser.showSaveDialog(this);  
                if (ret == JFileChooser.APPROVE_OPTION) {  
  
                    byte[] bytK1 = getKeyByStr(sKey.substring(0, 16)); 
                    
                    /**
                    byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));  
                    byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));  
                    */
                    FileInputStream fis = new FileInputStream(fileIn);  
                    byte[] bytIn = new byte[(int) fileIn.length()];  
                    for (int i = 0; i < fileIn.length(); i++) {  
                        bytIn[i] = (byte) fis.read();  
                    }  
                    // 解密  
                   /** byte[] bytOut = decryptByDES(  
                            decryptByDES(decryptByDES(bytIn, bytK3), bytK2),  
                            bytK1);  
                            */
                    byte[] bytOut = decryptByDES( bytIn,bytK1);
                    File fileOut = chooser.getSelectedFile();  
                    fileOut.createNewFile();  
                    FileOutputStream fos = new FileOutputStream(fileOut);  
                    for (int i = 0; i < bytOut.length; i++) {  
                        fos.write((int) bytOut[i]);  
                    }  
                    fos.close();  
                    JOptionPane.showMessageDialog(this, "解密成功！", "提示",  
                            JOptionPane.OK_OPTION);  
                }  
            } else  
                JOptionPane.showMessageDialog(this, "密码长度必须等于48！", "错误信息",  
                        JOptionPane.ERROR_MESSAGE);  
        } catch (Exception e) {  
            JOptionPane.showMessageDialog(this, "解密失败，请核对密码！", "提示",  
                    JOptionPane.OK_OPTION);  
        }  
    }  
  
    /** 
     * 用DES方法加密输入的字节 bytKey需为8字节长，是加密的密码 
     */  
    private byte[] encryptByDES(byte[] bytP, byte[] bytKey) throws Exception {  
        DESKeySpec desKS = new DESKeySpec(bytKey);  
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");  
        SecretKey sk = skf.generateSecret(desKS);  
        Cipher cip = Cipher.getInstance("DES");  
        cip.init(Cipher.ENCRYPT_MODE, sk);  
        return cip.doFinal(bytP);  
    }  
  
    /** 
     * 用DES方法解密输入的字节 bytKey需为8字节长，是解密的密码 
     */  
    private byte[] decryptByDES(byte[] bytE, byte[] bytKey) throws Exception {  
        DESKeySpec desKS = new DESKeySpec(bytKey);  
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");  
        SecretKey sk = skf.generateSecret(desKS);  
        Cipher cip = Cipher.getInstance("DES");  
        cip.init(Cipher.DECRYPT_MODE, sk);  
        return cip.doFinal(bytE);  
    }  
  
    /** 
     * 输入密码的字符形式，返回字节数组形式。 如输入字符串：AD67EA2F3BE6E5AD 返回字节数组：{ 
     * 173,103,234,47,59,230,229,173 } 
     */  
    private byte[] getKeyByStr(String str) {  
        byte[] bRet = new byte[str.length() / 2];  
        for (int i = 0; i < str.length() / 2; i++) {  
            Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i))  
                    + getChrInt(str.charAt(2 * i + 1)));  
            bRet[i] = itg.byteValue();  
        }  
        return bRet;  
    }  
  
    /** 
     * 计算一个16进制字符的10进制值 输入：0-F 
     */  
    private int getChrInt(char chr) {  
        int iRet = 0;  
        if (chr == "0".charAt(0))  
            iRet = 0;  
        if (chr == "1".charAt(0))  
            iRet = 1;  
        if (chr == "2".charAt(0))  
            iRet = 2;  
        if (chr == "3".charAt(0))  
            iRet = 3;  
        if (chr == "4".charAt(0))  
            iRet = 4;  
        if (chr == "5".charAt(0))  
            iRet = 5;  
        if (chr == "6".charAt(0))  
            iRet = 6;  
        if (chr == "7".charAt(0))  
            iRet = 7;  
        if (chr == "8".charAt(0))  
            iRet = 8;  
        if (chr == "9".charAt(0))  
            iRet = 9;  
        if (chr == "A".charAt(0))  
            iRet = 10;  
        if (chr == "B".charAt(0))  
            iRet = 11;  
        if (chr == "C".charAt(0))  
            iRet = 12;  
        if (chr == "D".charAt(0))  
            iRet = 13;  
        if (chr == "E".charAt(0))  
            iRet = 14;  
        if (chr == "F".charAt(0))  
            iRet = 15;  
        return iRet;  
    }  
}  
