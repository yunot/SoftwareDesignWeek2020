package A;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class A2 extends JFrame implements ActionListener{
    JPanel yunopanel, panel1, panelx,panel2, panel3, panel4, panel5, panel6;
    JLabel chooseCount,label1, label2, label3, label4, label5;
    JTextField randomString, counter,input, output; //文字域
    JButton btn1, btn2,addCount,minCount;
    Integer count = 1 ;

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
        new A2();
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

    public A2() {
        super("B17030331陶永昱");
        yunopanel = new JPanel();
        yunopanel.setLayout(new GridLayout(7, 1));//网格式布局

        panel1 = new JPanel();
        panelx = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();



        label1 = new JLabel("打字练习");
        label1.setFont(new Font("Serif",0,18)); //字体，样式
        panel1.add(label1);


        chooseCount = new JLabel("  选择位数:");
        counter = new JTextField(5);
        addCount = new JButton("+");
        addCount.addActionListener(this);
        minCount = new JButton("-");
        minCount.addActionListener(this);
        counter.setText(count.toString());
        counter.setHorizontalAlignment(JTextField.CENTER);
        counter.setEditable(false);
        panelx.add(chooseCount);
        panelx.add(addCount);
        panelx.add(counter);
        panelx.add(minCount);


        label2 = new JLabel("随机字符串：");
        randomString = new JTextField(10);
        randomString.setText(getRandomString());
        randomString.setEditable(false);
        panel2.add(label2);
        panel2.add(randomString);

        label3 = new JLabel("输入字符串：");
        input = new JTextField(10);
        panel3.add(label3);
        panel3.add(input);


        label4 = new JLabel("  正确率：  ");
        output = new JTextField(10);
        output.setEditable(false);
        panel4.add(label4);
        panel4.add(output);


        label5 = new JLabel("");
        label5.setFont(new Font("Serif",0,18)); //字体，样式
        panel5.add(label5);

        btn1 = new JButton("生成字符串");
        btn1.addActionListener(this);
        btn2 = new JButton("计算正确率");
        btn2.addActionListener(this);
        panel6.add(btn1);
        panel6.add(btn2);

        yunopanel.add(panel1);
        yunopanel.add(panelx);
        yunopanel.add(panel2);
        yunopanel.add(panel3);
        yunopanel.add(panel4);
        yunopanel.add(panel5);
        yunopanel.add(panel6);


        add(yunopanel);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕尺寸
        int width = 320;//The application's width
        int height = 360;
        setBounds((d.width-width)/2, (d.height-height)/2, width, height);//窗口的坐标和尺寸，这种方式居中
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 获得随机字符串,该方法仅用于获得随机字符串，可以忽略
     *
     * @return
     */
    private String getRandomString(){
        String myString = "";
        String ss = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int x = count; //4+(int)(Math.random()*7);//x为位数，生成4到11位的随机数
        for(int i=0;i<x;i++) {
            myString = myString + ss.toCharArray()[(int)(Math.random()*62)]; //toCharArray() 方法将字符串转换为字符数组。
        }
        return myString;
    }
    //今日收获：知道string+char型  变为 向右逐位增加！
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == btn1) {
            randomString.setText(getRandomString()); //文本框呈现生产的随机字符串
            label5.setText("");//清空label5.
        }else if(event.getSource() == btn2) {
            String originalString = randomString.getText();
            String inputString = input.getText();
            int temp = 0;
            double a = 0;
            try{
                for(int i=0;i<originalString.length();i++) {
                     if(originalString.toCharArray()[i] == inputString.toCharArray()[i]) {
                        temp++;
                    }
                }
                if(originalString.length()!=inputString.length()) {
                    JOptionPane.showMessageDialog(null, "输入位数不对", "警告", JOptionPane.INFORMATION_MESSAGE);
                    label5.setText("");//清空label5.
                }
                else { a=  (int)(((float)temp/originalString.length())*10000)/100.0;
                    output.setText(String.valueOf(a+"%"));
                    String str;
                    if(a < 50 || a == 50){
                         label5.setText("差的有点儿多，请继续努力！");

                    }else if (a > 50 && a < 100){
                        label5.setText("快接近正确了，再试一次吧！");

                    }else  {
                        label5.setText("输入正确,再接再厉！");

                    }

                }
            }catch(ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "输入位数不对", "警告", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(event.getSource() == addCount){
           if(count<10) {
               count++;
               counter.setText(count.toString());
           }else{
               JOptionPane.showMessageDialog(null, "最大位数为10", "警告", JOptionPane.INFORMATION_MESSAGE);
           }
        }else if(event.getSource() == minCount){
            if(count>1) {
                count--;
                counter.setText(count.toString());
            }else{
                JOptionPane.showMessageDialog(null, "最小位数为1", "警告", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}