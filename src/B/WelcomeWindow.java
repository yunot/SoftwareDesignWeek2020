package B;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class WelcomeWindow extends JWindow {
    public WelcomeWindow() {
        JLabel label_pic = new JLabel(new ImageIcon("src\\B\\images\\home.gif"));
        getContentPane().add(label_pic, BorderLayout.CENTER);
        setVisible(true);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

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

        WelcomeWindow window1 = new WelcomeWindow();
        try {
            Thread.sleep(2700);
            Main frame = new Main();
            frame.setVisible(true);
            frame.setResizable(false);
            window1.dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
