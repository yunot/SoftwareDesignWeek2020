package A;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.*;

//简单的文本编辑器

public class A3 extends JFrame {
    public JTextPane textEditor = new JTextPane(); //文本窗格d，编辑窗口
    public JFileChooser filechooser = new JFileChooser(); //文件选择器

    public A3()
    {
        super("B17030331陶永昱");
        //菜单项的各种功能
        Action[] actions=
                {
                        new NewAction(),
                        new OpenAction(),
                        new SaveAction(),   
                        new CutAction(),
                        new CopyAction(),
                        new PasteAction(),
                        new AboutAction(),
                        new ExitAction(),
                        //new HelpAction()
                };
        setJMenuBar(createJMenuBar(actions));		//根据actions创建菜单栏
        Container container=getContentPane();
        container.add(textEditor, BorderLayout.CENTER);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕尺寸
        int width = 320;//The application's width
        int height = 360;
        setBounds((d.width-width)/2, (d.height-height)/2, width, height);//窗口的坐标和尺寸，这种方式居中
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JMenuBar createJMenuBar(Action[] actions)	//创建菜单栏的函数
    {
        JMenuBar menubar=new JMenuBar();
        JMenu menuFile=new JMenu("文件(F)");
        JMenu menuEdit=new JMenu("编辑(E)");
        JMenu menuAbout=new JMenu("帮助(H)");
        menuFile.add(new JMenuItem(actions[0]));
        menuFile.add(new JMenuItem(actions[1]));
        menuFile.add(new JMenuItem(actions[2]));
        menuFile.add(new JMenuItem(actions[7]));
        menuEdit.add(new JMenuItem(actions[3]));
        menuEdit.add(new JMenuItem(actions[4]));
        menuEdit.add(new JMenuItem(actions[5]));
        menuAbout.add(new JMenuItem(actions[6]));
        //menuAbout.add(new JMenuItem(actions[8]));
        menubar.add(menuFile);
        menubar.add(menuEdit);
        menubar.add(menuAbout);
        return menubar;
    }

    class NewAction extends AbstractAction		//新建
    {
        public NewAction()
        {
            super("新建(New)     ");
        }
        public void actionPerformed(ActionEvent e)
        {
            textEditor.setDocument(new DefaultStyledDocument());
        }
    }

    class OpenAction extends AbstractAction		//打开
    {
        public OpenAction()
        {
            super("打开(Open)     ");
        }
        public void actionPerformed(ActionEvent e)
        {
            int i=filechooser.showOpenDialog(A3.this);			//显示打开文件对话框
            if(i==JFileChooser.APPROVE_OPTION)			//点击对话框打开选项
            {
                File f=filechooser.getSelectedFile();	//得到选择的文件
                try
                {
                    InputStream is=new FileInputStream(f);
                    textEditor.read(is, "d");
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    class SaveAction extends AbstractAction		//保存
    {
        public SaveAction()
        {
            super("保存(Save)     ");
        }
        public void actionPerformed(ActionEvent e)
        {
            int i=filechooser.showSaveDialog(A3.this);
            if(i==JFileChooser.APPROVE_OPTION)
            {
                File f=filechooser.getSelectedFile();
                try
                {
                    FileOutputStream out=new FileOutputStream(f);
                    out.write(textEditor.getText().getBytes());
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    class ExitAction extends AbstractAction		//退出
    {
        public ExitAction()
        {
            super("退出(Exit)");
        }
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }

    class CutAction extends AbstractAction		//剪切
    {
        public CutAction()
        {
            super("剪切(Cut)       Ctrl+X");
        }
        public void actionPerformed(ActionEvent e)
        {
            textEditor.cut();
        }
    }

    class CopyAction extends AbstractAction		//复制
    {
        public CopyAction()
        {
            super("复制(Copy)      Ctrl+C");
        }
        public void actionPerformed(ActionEvent e)
        {
            textEditor.copy();
        }
    }

    class PasteAction extends AbstractAction		//粘贴
    {
        public PasteAction()
        {
            super("粘贴(Paste)     Ctrl+V");
        }
        public void actionPerformed(ActionEvent e)
        {
            textEditor.paste();
        }
    }


    class AboutAction extends AbstractAction
    {
        public AboutAction()
        {
            super("关于(About)");
        }
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(A3.this,"实现了文本编辑器的一些基本功能","关于",JOptionPane.PLAIN_MESSAGE);
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


        new A3();
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