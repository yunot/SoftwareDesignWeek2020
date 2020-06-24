package A;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class A4 extends JFrame implements ActionListener{
    public static String[] mingwen = new String[200];//规定明文最大长度
    public static String[] miwen = new String[200];//规定密文最大长度
    JPanel mypanel0, mypanel1, mypanel2, mypanel21, mypanel3, mypanel4, mypanel41, mypanel5;
    JLabel label1, label2;
    JButton EncryptBtn,DecryptBtn;
    static JTextArea log;
    static JTextArea log2;
    JScrollPane sc, sc2;

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


        new A4();
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

    public A4() {
        mypanel0 = new JPanel();
        mypanel0.setLayout(new GridLayout(3, 1));
        mypanel1 = new JPanel();
        mypanel2 = new JPanel();
        //mypanel2.setLayout(new GridLayout(1, 1));
        mypanel21 = new JPanel();
        mypanel3 = new JPanel();
        mypanel3.setLayout(new GridLayout(1, 1));
        mypanel4 = new JPanel();
        mypanel41 = new JPanel();
        mypanel5 = new JPanel(new GridLayout(2,1));

        label1 = new JLabel("加密解密程序");
        label1.setFont(new Font("Serif",0,18));
        mypanel1.add(label1);

        EncryptBtn  = new JButton("加密");
        EncryptBtn.setSize(10,5);
        EncryptBtn.addActionListener(this);
        DecryptBtn  = new JButton("解密");
        DecryptBtn.setSize(10,5);
        DecryptBtn.addActionListener(this);

        //mypanel2.setLayout(new GridLayout(1, 1));
        mypanel2.add(EncryptBtn);
        mypanel2.add(DecryptBtn);

        label2 = new JLabel("操作记录：");
        label2.setFont(new Font("Serif",0,18));
        mypanel21.add(label2);

        log = new JTextArea(6,14);
        log.setEditable(false);
        sc = new JScrollPane(log);
        log2 = new JTextArea(6,20);
        log2.setEditable(false);
        sc2 = new JScrollPane(log2);
        mypanel4.add(sc);
        mypanel41.add(sc2);
        //mypanel3.add(mypanel4);
        mypanel3.add(mypanel41);

        mypanel0.add(mypanel1);
        mypanel0.add(mypanel2);
        mypanel0.add(mypanel21);

        mypanel5.add(mypanel0);
        mypanel5.add(mypanel3);

        add(mypanel5);

        setTitle("B17030331陶永昱");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕尺寸
        int width = 320;//The application's width
        int height = 360;
        setBounds((d.width-width)/2, (d.height-height)/2, width, height);//窗口的坐标和尺寸，这种方式居中
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == EncryptBtn) {
            new childFrame("encrypt");
        }else if(event.getSource() == DecryptBtn){
            new childFrame("decrypt");
        }
    }

}

class childFrame extends JFrame implements ActionListener{

    JLabel title1, title2;
    JTextField  input2;
    JPasswordField input1;
    JButton okbtn, canclebtn;
    JPanel mypanel0, mypanel1, mypanel2, mypanel3;
    //问题分析：加到二位数之后开始不能表示，得自己设计。关键点，自己设计的密钥。  0+10=a, 0+36=A
    String mystr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String method;
    public childFrame(String type) {
        method = type;

        mypanel0 = new JPanel();
        mypanel0.setSize(3,1);

        mypanel1 = new JPanel();
        mypanel2 = new JPanel();
        mypanel3 = new JPanel();

        title1 = new JLabel("请输入密钥：");
        input1 = new JPasswordField(8);
        mypanel1.add(title1);
        mypanel1.add(input1);


        if(method.equals("encrypt")){
            title2 = new JLabel("请输入明文：");
            input2 = new JTextField(8);
            mypanel2.add(title2);
            mypanel2.add(input2);
        }else {
            title2 = new JLabel("请输入密文：");
            input2 = new JTextField(8);
            mypanel2.add(title2);
            mypanel2.add(input2);
        }

        okbtn = new JButton("确认");
        okbtn.addActionListener(this);
        canclebtn = new JButton("取消");
        canclebtn.addActionListener(this);
        mypanel3.add(okbtn);
        mypanel3.add(canclebtn);

        mypanel0.add(mypanel1);
        mypanel0.add(mypanel2);
        mypanel0.add(mypanel3);

        add(mypanel0);
        setTitle("信息输入");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕尺寸
        int width = 240;//The application's width
        int height = 180;
        setBounds((d.width-width)/2, (d.height-height)/2, width, height);//窗口的坐标和尺寸，这种方式居中
        setVisible(true);
    }

    //加密解密的过程，监听事件
    @Override
    public void actionPerformed(ActionEvent event1) {
        if(method.equals("encrypt")) {
            if (event1.getSource() == okbtn) {
                encrypt();
            } else if (event1.getSource() == canclebtn) {
                setVisible(false);
            }
        }else{
            if (event1.getSource() == okbtn) {
                 decrypt();
            } else if (event1.getSource() == canclebtn) {
                setVisible(false);
            }

        }

    }

    //加密方法
    private void encrypt() {
        String input[] = new String[2]; //数组 {密钥，明文}
        int key = 0;
        input[0] = input1.getText();
        input[1] = input2.getText();
        try {
            key = Integer.parseInt(input[0]);//Integer.parseInt()是把()里的内容转换成整数。Integer.parseInt(String)遇到一些不能被转换为整型的字符时，会抛出异常。
            if(A4.miwen[key]!=null) { //每一个密文对应一个miwen[key]空间，如果miwen[key]已经存好了值
                JOptionPane.showMessageDialog(null, "该条密钥已被加密！请换条密钥！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }else {
                //offsetStr(1, input[1], key);
                A4.mingwen[key] = input[1]; //明文贴上
                A4.miwen[key] = offsetStr(1,input[1],key); //密文贴上
                JOptionPane.showMessageDialog(null, "明文加密成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                A4.log2.append("密文：" + A4.miwen[key] + "\n");
            }

        }catch(Exception e) {//java.lang.IndexOutOfBoundsException : Invalid array range: 100 to 100
            JOptionPane.showMessageDialog(null, "密钥不能为空或字母！", "错误", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }

    //解密
    private void decrypt() {
        String input[] = new String[2]; //数组 {密钥，密文}
        int key = 0;
        input[0] = input1.getText();
        input[1] = input2.getText();
        try {
            key = Integer.parseInt(input[0]);//Integer.parseInt()是把()里的内容转换成整数。Integer.parseInt(String)遇到一些不能被转换为整型的字符时，会抛出异常。
            if(A4.miwen[key]!=null) { //每一个密文对应一个miwen[key]空间，如果miwen[key]已经存好了值
                if(counterOffsetStr(0, input[1], key).equals(A4.mingwen[key])) {
                    JOptionPane.showMessageDialog(null, "密文解密成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    A4.log2.append("解密明文：" + A4.mingwen[key] + "\n");
                }else {
                    JOptionPane.showMessageDialog(null, "解密失败！", "错误", JOptionPane.ERROR_MESSAGE);
                    A4.log2.append("密文解密失败！\n");
                    A4.log2.append("\n");
                }
            }else {
                JOptionPane.showMessageDialog(null, "解密明文不存在！", "错误", JOptionPane.ERROR_MESSAGE);
            }

        }catch(Exception e) {//java.lang.IndexOutOfBoundsException : Invalid array range: 100 to 100
            JOptionPane.showMessageDialog(null, "解密明文不存在！", "错误", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }




    //偏移
    private String offsetStr(int chagetype, String input, int key) {
        String result = "";
        char offset = '0';//字符‘0’ ascii码为48

        //确定有几位明文，循环几次
        for(int i=0;i<input.length();i++) {
            int j = 0;//每次循环j置0；
            //当明文值与密钥库的值不一致时，把j叠加到相等的那个位
            // input[i] + key =  output[i], 要决定超过一位数后的值是什么，所以设计一个mystr库
            while(mystr.toCharArray()[j] != input.toCharArray()[i]) {
                j++;
            }
            if(chagetype == 1) {
                j = j + key;// j为偏移指标，即差值+密钥值；其作用都是指示要加到的位数。
                //String mystr = "0123456789a10bcdefghijklmnopqrstuvwxyz35A36BCDEFGHIJKLMNOPQRSTUVWXYZ61";
                if(j>61) {
                    j = j - 62; //58
                }
            }else {
                if(j<key) {
                    j = 62 - key + j;
                }
            }
            offset = mystr.toCharArray()[j]; // 规定偏移量，比如说j=10，则偏移到mystr的第十位，即a。
            result = result + offset; //加上偏移量
        }
        return result;
    }

    //反偏
    private String counterOffsetStr(int chagetype, String input, int key) {
        String result = "";
        char offset = '0';//字符‘0’ ascii码为48

        //确定有几位密文，循环几次
        for(int i=0;i<input.length();i++) {
            int j = 0;//每次循环j置0；
            //当明文值与密钥库的值不一致时，把j叠加到相等的那个位
            // input[i] + key =  output[i], 要决定超过一位数后的值是什么，所以设计一个mystr库
            while(mystr.toCharArray()[j] != input.toCharArray()[i]) {
                j++;
            }
            if(key>61) {
                key = key - 62;
            }
            if(chagetype == 0) {
                j = j - key;// j为偏移指标，即差值-密钥值；j指的是最后要指向的那位
            }
            offset = mystr.toCharArray()[j]; // 规定偏移量，比如说j=10，则偏移到mystr的第十位，即a。
            result = result + offset; //减去偏移量
        }
        return result;
    }


}
