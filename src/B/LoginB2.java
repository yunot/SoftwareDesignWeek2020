package B;



import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class LoginB2 extends JFrame implements ActionListener
{
    JPanel yunopanel,panel1,panel2,panel3,panel4;
    JLabel title,name,password;
    JButton login,exit;
    JTextField JName;
    JPasswordField JPassword;

    public LoginB2()
    {
        setTitle("B17030331陶永昱");
        yunopanel = new JPanel();
        yunopanel.setLayout(new GridLayout(4, 1));//网格式布局
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        //第一排
        title = new JLabel("用户登录");
        title.setFont(new Font("Serif",0,18)); //字体，样式
        panel1.add(title);

        //第二排
        name = new JLabel("用户名：");
        JName = new JTextField(10); //明文账号输入
        panel2.add(name);
        panel2.add(JName);

        //第三排
        password = new JLabel("密码： ");
        JPassword = new JPasswordField(10); // 非明文密码输入；
        panel3.add(password);
        panel3.add(JPassword);

        //第四排
        login = new JButton("登录");
        login.addActionListener(this); //登录增加事件监听
        exit = new JButton("退出");
        exit.addActionListener(this);	//退出增加事件监听
        panel4.add(login);
        panel4.add(exit);



        yunopanel.add(panel1);
        yunopanel.add(panel2);
        yunopanel.add(panel3);
        yunopanel.add(panel4);

        add(yunopanel);
        add(yunopanel,BorderLayout.CENTER);	//将整块面板定义在中间


        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕尺寸
        int width = 320;//The application's width
        int height = 360;
        setBounds((d.width-width)/2, (d.height-height)/2, width, height);//窗口的坐标和尺寸，这种方式居中
        //this.pack();  		//表示随着面板自动调整大小
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)  // 对时间进行处理
    {
        if(e.getSource() == exit)
        {
            int i = JOptionPane.showConfirmDialog(null,"确认要退出吗？", "确认", JOptionPane.YES_NO_OPTION);
            // 显示选择对话框
            if(i == JOptionPane.YES_OPTION);
            System.exit(0);
        }
        else
        {
            if(JName.getText().equals("a")&& String.valueOf(JPassword.getPassword()).equals("123"))
            {
                JOptionPane.showMessageDialog(null, "登录成功！");
                //显示信息提示框
                B2 frame = new B2();
                frame.setVisible(true);
                frame.setResizable(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "用户或密码错误！请重新输入！");
                //显示信息提示框
                JName.setText("");
                JPassword.setText("");
            }
        }
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


        new LoginB2();
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