package com.tman.stream;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class FilePanel extends JPanel {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FilePanel(String str) {  
        JLabel label = new JLabel(str);  
        JTextField fileText = new JTextField(35);  
        JButton chooseButton = new JButton("浏览...");  
        this.add(label);  
        this.add(fileText);  
        this.add(chooseButton);  
        clickAction ca = new clickAction(this);  
        chooseButton.addActionListener(ca);  
  
    }  
  
	/**路径*/
    public String getFileName() {  
        JTextField jtf = (JTextField) this.getComponent(1); 
        
        return jtf.getText();  
    }  
  
    public class clickAction implements ActionListener {  
        clickAction(Component c) {  
            cmpt = c;  
        }  
  
        public void actionPerformed(ActionEvent event) {  
            JFileChooser chooser = new JFileChooser();  
            chooser.setCurrentDirectory(new File("."));  
            int ret = chooser.showOpenDialog(cmpt);  
            if (ret == JFileChooser.APPROVE_OPTION) {  
                JPanel jp = (JPanel) cmpt;  
                JTextField jtf = (JTextField) jp.getComponent(1);  
                jtf.setText(chooser.getSelectedFile().getPath());  
            }  
        }  
  
        private Component cmpt;  
    }  
} 
