package A;


import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class A1 extends JFrame{
    // 默认表格模型
    private DefaultTableModel model = null;
    private JTable table = null;

    private JButton addBtn = null, delBtn = null, sortBtn = null;

    String number = "0";

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


        new A1();
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

    public A1()
    {
        super("B17030331陶永昱");
        String[][] datas = {};
        String[] titles = { "序号", "姓名","分数" };
        model = new DefaultTableModel(datas, titles);
        table = new JTable(model);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1, 3,2,2));

        //标题
        JLabel label = new JLabel("分数统计",JLabel.CENTER);
        label.setFont(new Font("Serif",0,15));


        addBtn = new JButton("录入分数");
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                number = String.valueOf(Integer.valueOf(number)+1);
                model.addRow(new String[] { number, "", "" }); //初值
            }
        });

        delBtn = new JButton("删除分数");
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(table.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "请添加数据", "警告", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        model.removeRow(table.getSelectedRow());
                    }
                }catch(ArrayIndexOutOfBoundsException e1) {
                    JOptionPane.showMessageDialog(null, "未选择所要删除的栏目", "警告", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        sortBtn = new JButton("排序");
        sortBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean info = false;
                String[] name = new String[table.getRowCount()];
                float[] score = new float[table.getRowCount()];
                if(table.getRowCount() != 0) {
                    for(int i=0;i<table.getRowCount();i++) {
                        if(table.getModel().getValueAt(i,1).equals("")||table.getModel().getValueAt(i,2).toString().equals("")) {
                            JOptionPane.showMessageDialog(null, "请完善信息", "警告", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }else {
                            try {
                                name[i] = (String) table.getModel().getValueAt(i,1);
                                score[i] = Float.valueOf(table.getModel().getValueAt(i,2).toString());
                                info = true;
                            }catch(NumberFormatException except) { //由最初定义的name和score数据类型，若不符合则抛出异常
                                JOptionPane.showMessageDialog(null, "行序号为"+table.getModel().getValueAt(i,0)+"的成绩格式错误", "错误", JOptionPane.ERROR_MESSAGE);
                                info = false;
                                break;
                            }
                        }
                    }
                    if(info) {
                        for(int i=0;i<table.getRowCount();i++) {
                            float scoreTemp;
                            String nameTemp;
                            for(int j=0;j<table.getRowCount();j++) {
                                if(score[j]<score[i]) {
                                    //分数交换
                                    scoreTemp = score[i];  //分数较大的值放进暂存的scoreTemp
                                    score[i] = score[j];
                                    score[j] = scoreTemp;
                                    //姓名交换
                                    nameTemp = name[i];
                                    name[i] = name[j];
                                    name[j] = nameTemp;
                                }
                            }
                        }
                        //变换后需要重写数据
                        for(int i=0;i<table.getRowCount();i++) {
                            table.setValueAt(i+1, i, 0);
                            table.setValueAt(name[i], i, 1);
                            table.setValueAt(score[i], i, 2);
                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "请添加数据", "警告", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnPanel.add(addBtn);
        btnPanel.add(delBtn);
        btnPanel.add(sortBtn);

        add(label, BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);
        add(new JScrollPane(table));

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕尺寸
        int width = 320;//The application's width
        int height = 360;
        setBounds((d.width-width)/2, (d.height-height)/2, width, height);//窗口的坐标和尺寸，这种方式居中
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //table居中显示
        DefaultTableCellRenderer t = new DefaultTableCellRenderer();
        t.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, t);
        table.setFont(new Font("Serif",0,15));
        table.setRowHeight(30);
        //设置表头居中显示
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }


}


