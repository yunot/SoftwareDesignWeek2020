package B;


import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

public class Main extends JFrame implements ActionListener{
    JButton B1, B2;
    int x_location, y_location;
    public static void main(String[] args) {

        InitGlobalFont(new Font("宋体", Font.PLAIN, 15));
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            //BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("set skin fail!");
        }


        new Main();
    }


    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    public Main() {
        setTitle("B17030331陶永昱");
        setSize(600,400);
        x_location = getCenterLocation(1);
        y_location = getCenterLocation(2);
        setLocation(x_location, y_location);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,2));



        B1 = new JButton("学生成绩核算系统");
        B1.addActionListener(this);
        add(B1);

        B2 = new JButton("电信计费系统");
        B2.addActionListener(this);
        add(B2);

        setVisible(true);
    }

    public int getCenterLocation(int type) {
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        if(type == 1) {
            return (int) (width - this.getWidth()) / 2;
        }else {
            return (int) (height - this.getHeight()) / 2;
        }
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == B1) {
            LoginB1 b1 = new LoginB1();
            b1.setVisible(true);
        }else if(event.getSource() == B2) {
            LoginB2 b2 = new LoginB2();
            b2.setVisible(true);
        }
    }
}


