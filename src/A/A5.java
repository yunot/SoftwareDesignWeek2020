package A;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

public class A5 extends JFrame implements ActionListener{

    JPanel mypanel0, mypanel1, mypanel2, mypanel3, mypanel4, mypanel5;
    JLabel title0, title1, title2, title3,title4;
    JTextField number;
    JButton convertBtn;
    JTextArea log;
    JScrollPane scroll;
    JComboBox<String> inChoose, outChoose;
    String[] SystemStr = {"二进制", "八进制", "十进制", "十六进制"};
    public A5() {
        mypanel0 = new JPanel();
        mypanel1 = new JPanel();
        mypanel2 = new JPanel();
        mypanel2.setLayout(new GridLayout(2, 1));
        mypanel3 = new JPanel();
        mypanel4 = new JPanel();
        mypanel5 = new JPanel();

        title0 = new JLabel("进制转换程序");
        title0.setFont(new Font("Serif",0,18));
        title1 = new JLabel("输入进制：");
        title2 = new JLabel("输入：");
        title2.setBorder(new EmptyBorder(0, 10, 0, 0));
        title3 = new JLabel("转换进制：");
        //title3.setBorder(new EmptyBorder(0, 0, 0, 0));
        inChoose = new JComboBox<>(SystemStr);
        number = new JTextField(5);

        outChoose = new JComboBox<>(SystemStr);

        title4 = new JLabel("     ");
        title4.setBorder(new EmptyBorder(0, 10, 0, 0));
        convertBtn = new JButton("转换");
        convertBtn.addActionListener(this);


        log = new JTextArea(7,30);
        log.append("转换记录：\n");
        scroll = new JScrollPane(log);

        mypanel1.add(title0);
        mypanel3.add(title1);
        mypanel3.add(inChoose);
        mypanel3.add(title2);
        mypanel3.add(number);
        mypanel4.add(title3);
        mypanel4.add(outChoose);
        mypanel4.add(title4);
        mypanel4.add(convertBtn);


        mypanel2.add(mypanel3);
        mypanel2.add(mypanel4);

        mypanel5.add(scroll);

        mypanel0.add(mypanel1);
        mypanel0.add(mypanel2);
        mypanel0.add(mypanel5);

        add(mypanel0);
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
        try {
            String inputNumber = number.getText();
            if(inputNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "输入不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
            }else {
                int inputSystem = 2, outputSystem = 2;
                //inChoose.getSelectedItem().toString())为 选中item的string型
                switch (inChoose.getSelectedItem().toString()) {
                        case "二进制":
                        inputSystem = 2;
                        break;
                    case "八进制":
                        inputSystem = 8;
                        break;
                    case "十进制":
                        inputSystem = 10;
                        break;
                    case "十六进制":
                        inputSystem = 16;
                        break;
                    default:
                        break;
                }
                switch (outChoose.getSelectedItem().toString()) {
                    case "二进制":
                        outputSystem = 2;
                        break;
                    case "八进制":
                        outputSystem = 8;
                        break;
                    case "十进制":
                        outputSystem = 10;
                        break;
                    case "十六进制":
                        outputSystem = 16;
                        break;
                    default:
                        break;
                }
                log.append(inChoose.getSelectedItem().toString()+"转"+outChoose.getSelectedItem().toString()+":"+ ConvertNum(inputSystem, outputSystem, inputNumber)+"\n");
            }
        }catch(NumberFormatException e) { //格式报错
            JOptionPane.showMessageDialog(null, "输入格式错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    //实际的转换过程
    private String ConvertNum(int inputSystem, int outputSystem, String inputNumber) {
        String result = "";
        //输入不为十进制时，先转10，再转n
        if(inputSystem != 10) {
            result = _NTo10(inputSystem,inputNumber);
            result = _10ToN(outputSystem,result);
        }else {
            result = _10ToN(outputSystem,inputNumber);
        }
        return result;
    }

    //10进制转n进制
    private String _10ToN(int outputSystem, String inputNumber) {
        String number = "";
        if(outputSystem == 2){
            number = Integer.toBinaryString(Integer.parseInt(inputNumber));
        }else if(outputSystem == 8){
            number = Integer.toOctalString(Integer.parseInt(inputNumber));
        }else if(outputSystem == 16){
            number = Integer.toHexString(Integer.parseInt(inputNumber));
        }else if(outputSystem == 10) {
            number = inputNumber;
        }
        return number;
    }

    //n进制转十进制 输入数字转为十进制
    private String _NTo10(int inputSystem, String inputNumber) {
        String number = "";
        number = Integer.valueOf(inputNumber,inputSystem).toString();
        return number;
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


        new A5();
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


