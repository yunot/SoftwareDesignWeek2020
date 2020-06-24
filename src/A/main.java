package A;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

public class main extends JFrame implements ActionListener{
    JButton A1, A2, A3, A4, A5;
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


        new main();
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

    public main() {
        setTitle("B17030331陶永昱");
        setSize(600,400);
        x_location = getCenterLocation(1);
        y_location = getCenterLocation(2);
        setLocation(x_location, y_location);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,4));

        A1 = new JButton("分数统计");
        A1.addActionListener(this);
        add(A1);

        A2 = new JButton("打字程序");
        A2.addActionListener(this);
        add(A2);

        A3 = new JButton("文本编辑器");
        A3.addActionListener(this);
        add(A3);

        A4 = new JButton("加密解密程序");
        A4.addActionListener(this);
        add(A4);

        A5 = new JButton("进制转换器");
        A5.addActionListener(this);
        add(A5);



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
        if(event.getSource() == A1) {
            A1 a1 = new A1();
            a1.setVisible(true);
        }else if(event.getSource() == A2) {
            A2 a2 = new A2();
            a2.setVisible(true);
        }else if(event.getSource() == A3) {
            A3 a3 = new A3();
            a3.setVisible(true);
        }else if(event.getSource() == A4) {
            A4 a4 = new A4();
            a4.setVisible(true);
        }else if(event.getSource() == A5) {
            A5 a5 = new A5();
            a5.setVisible(true);
        }
    }
}

