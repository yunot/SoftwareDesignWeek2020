package B;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class B1_Admin extends JFrame implements ActionListener{
    JMenuBar menu;
    JMenu  help;
    JMenuItem readme;
    JButton openfile, savefile,button3, button4, button5, button6,button7;
    boolean fileOpened = false, solved = false;
    JTable table;
    JScrollPane sc;
    //设置成最多20个数据
    String[][] stulist = new String[20][6];
    int total = 0;


    // 默认表格模型
    private DefaultTableModel model = null;
    public B1_Admin() {

        //标题
        setTitle("B17030331陶永昱");
        JLabel label = new JLabel("学生成绩核算系统",JLabel.CENTER);
        label.setFont(new Font("Serif",0,30));

        //菜单
        menu = new JMenuBar();
        help = new JMenu("帮助");
        menu.add(help);
        readme = new JMenuItem("帮助文档");
        readme.addActionListener(this);
        help.add(readme);


        String[][] datas = {};
        String[] titles = { "序号", "学号", "平时成绩", "期中考试成绩", "期末考试成绩"};
        model = new DefaultTableModel(datas, titles);
        table = new JTable(model);
        //渲染表格
        DefaultTableCellRenderer t = new DefaultTableCellRenderer();// 设置table内容居中
        t.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, t);
        table.setFont(new Font("Serif",0,16));
        table.setRowHeight(28);
        //设置表头居中显示
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //设置滚轮
        sc = new JScrollPane(table);
        add(sc);



        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1, 7,2,2));

        openfile = new JButton("打开文件");
        openfile.addActionListener(this);
        savefile = new JButton("保存文件");
        savefile.addActionListener(this);
        button3 = new JButton("等级和总评");
        button3.addActionListener(this);
        button4 = new JButton("按等级查询");
        button4.addActionListener(this);
        button5 = new JButton("按总评排序");
        button5.addActionListener(this);
        button6 = new JButton("按学号查询");
        button6.addActionListener(this);
        button7 = new JButton("等级分布");
        button7.addActionListener(this);



        setJMenuBar(menu);
        btnPanel.add(openfile);
        btnPanel.add(savefile);
        btnPanel.add(button3);
        btnPanel.add(button7);
        btnPanel.add(button5);
        btnPanel.add(button4);
        btnPanel.add(button6);


        add(label, BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);

        //居中显示
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕尺寸
        int width = 820;//The application's width
        int height = 600;
        setBounds((d.width-width)/2, (d.height-height)/2, width, height);//窗口的坐标和尺寸，这种方式居中
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    //各类监听事件
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == openfile) {
            openPro();
            //标志：可以保存文件
            fileOpened = true;
            //button3.setEnabled(true);
        }else if(event.getSource() == savefile) {
            savePro();
        }else if(event.getSource() == readme) {
            JOptionPane.showMessageDialog(B1_Admin.this," 使用指南：\n" +
                    " 1、打开学生成绩文件->显示各个学生的成绩\n" +
                    " 2、计算等级与总评\n" +
                    " 3、查看等级分布->计算出本班学生成绩的平均分\n" +
                    " 4、按总评排序\n" +
                    " 5、按等级查询成绩\n" +
                    " 3、按学号查询成绩\n","关于",JOptionPane.PLAIN_MESSAGE);
        }else  if(table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "请添加数据", "警告", JOptionPane.INFORMATION_MESSAGE);
        }else  if(event.getSource() == button3) {
            //还没有保存时，
            if(!solved) {
                solve_GradePro();
            }
        }else if(event.getSource() == button4) {
            select_gradePro();
        }else if(event.getSource() == button5) {
            rankPro();
        }else if(event.getSource() == button6) {
            select_stuNumPro();
        }else if(event.getSource() == button7) {
            Grade_layPro();
        }
    }

    //btn1
    public void openPro() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File filex=chooser.getSelectedFile();
            byte[] b = new byte[10];
            try{
                //设置最多数据数，默认为20个
                String[] tempstr = new String[20];
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(filex));
                String line = "";
                int i = 0;
                //读第一行，读到/n,/r后停止。这里设置的第一行是总行数。
                total = Integer.valueOf(br.readLine()); // Reads a line of text.  A line is considered to be terminated by any one of feed(\n ...)
                while((line=br.readLine())!=null){
                    tempstr[i] = line;
                    i++;
                }
                for(i=0; i< total; i++) {
                    handle(tempstr[i], i);
                }
                br.close();
            } catch (Exception e) {
            }
        }

        //将读到的数据写入JTable
        for(int i = 0; i< total; i++) {
            model.addRow(new String[] { "", "", "", "" });
            table.setValueAt(i+1, i, 0); //序号
            table.setValueAt(stulist[i][0], i, 1);
            table.setValueAt(stulist[i][1], i, 2);
            table.setValueAt(stulist[i][2], i, 3);
            table.setValueAt(stulist[i][3], i, 4);
        }
    }


    //btn2
    public void savePro() {
        if(fileOpened) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "txt");
            //设置文件类型
            chooser.setFileFilter(filter);
            //打开选择器面板
            int returnVal = chooser.showSaveDialog(new JPanel());
            //保存文件从这里入手，输出的是文件名
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getPath();
                System.out.println(path);
                if(path != null) {
                    File file = new File(path+".dat");
                    System.out.println(file.getAbsolutePath());
                    try {
                        file.createNewFile();
                        FileOutputStream fos = new FileOutputStream(file);
                        for(int i = 0; i< total; i++) {
                            String line = stulist[i][0]+"\t"+stulist[i][1]+"\t"+stulist[i][2]+"\t"+stulist[i][3]+"\t"+stulist[i][4]+"\t"+stulist[i][5]+"\n";
                            fos.write(line.getBytes());
                        }
                        fos.close();
                    } catch (Exception e) {

                    }
                }
            }
        }
    }


    //计算总评和等级
    public void solve_GradePro() {
        model.addColumn("总评");
        model.addColumn("等级");
        float overall_grade;

            for (int i = 0; i < total; i++) {
                overall_grade = (float) ((int) (((float) (Float.valueOf(stulist[i][1]) * 0.3) + (float) (Float.valueOf(stulist[i][2]) * 0.3) + (float) (Float.valueOf(stulist[i][3]) * 0.4)) * 100) / 100.0);
                if (overall_grade < 60) {
                    table.setValueAt(overall_grade, i, 5);
                    table.setValueAt("E", i, 6);
                    stulist[i][4] = String.valueOf(overall_grade);
                    stulist[i][5] = "E";
                } else if (overall_grade >= 60 && overall_grade < 70) {
                    table.setValueAt(overall_grade, i, 5);
                    table.setValueAt("D", i, 6);
                    stulist[i][4] = String.valueOf(overall_grade);
                    stulist[i][5] = "D";
                } else if (overall_grade >= 70 && overall_grade < 80) {
                    table.setValueAt(overall_grade, i, 5);
                    table.setValueAt("C", i, 6);
                    stulist[i][4] = String.valueOf(overall_grade);
                    stulist[i][5] = "C";
                } else if (overall_grade >= 80 && overall_grade < 90) {
                    table.setValueAt(overall_grade, i, 5);
                    table.setValueAt("B", i, 6);
                    stulist[i][4] = String.valueOf(overall_grade);
                    stulist[i][5] = "B";
                } else if (overall_grade >= 90) {
                    table.setValueAt(overall_grade, i, 5);
                    table.setValueAt("A", i, 6);
                    stulist[i][4] = String.valueOf(overall_grade);
                    stulist[i][5] = "A";
                }
            }
            solved = true;
    }


    ////按等级查询
    public void select_gradePro() {
        String selectStr;
        boolean yes = false;
        if(table.getColumnCount() == 5 ) {
            JOptionPane.showMessageDialog(null, "请先计算等级", "警告", JOptionPane.INFORMATION_MESSAGE);
        }else {
            selectStr = JOptionPane.showInputDialog("请输入您所要查询的等级：");
            if (selectStr.toCharArray()[0] >= 'a' && selectStr.toCharArray()[0] <= 'z')
                //如果小写了，转大写
                selectStr = selectStr.toUpperCase();
            String[] rank ={"A","B","C","D","E"};
            for (String s : rank) {
                if (selectStr.equals(s)) {
                    yes = true;
                    break;
                }
            }
            if (yes) {
                //将row删至无
                while (table.getRowCount() > 0) {
                    model.removeRow(0);
                }
                int order = 0;

                for (int j = 0; j < total; j++) {
                    if (stulist[j][5].equals(selectStr)) {
                        //增添新row；
                        model.addRow(new String[]{});
                        table.setValueAt(order + 1, order, 0);
                        table.setValueAt(stulist[j][0], order, 1);
                        table.setValueAt(stulist[j][1], order, 2);
                        table.setValueAt(stulist[j][2], order, 3);
                        table.setValueAt(stulist[j][3], order, 4);
                        table.setValueAt(stulist[j][4], order, 5);
                        table.setValueAt(stulist[j][5], order, 6);
                        order++;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "请输入正确的等级", "警告", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    //按总评排序
    public void rankPro() {
        String[][] tempStr = new String[total][6];
        String[] temps = new String[6];
        tempStr = stulist;
        if(table.getColumnCount() == 5 ) {
            JOptionPane.showMessageDialog(null, "请先计算总评", "警告", JOptionPane.INFORMATION_MESSAGE);
        }else {
            //冒泡排序，将[i][4]与[j][4]比较
            for (int i = 0; i < total; i++) {
                for (int j = 0; j < total; j++) {
                    if (Float.valueOf(tempStr[i][4]) > Float.valueOf(tempStr[j][4])) {
                        temps = tempStr[i];
                        tempStr[i] = tempStr[j]; //小的 大的互换
                        tempStr[j] = temps;
                    }
                }
            }
            while (table.getRowCount() > 0) {
                model.removeRow(0);
            }
            model.setColumnCount(0);
            model.addColumn("序号");
            model.addColumn("学号");
            model.addColumn("平时成绩");
            model.addColumn("期中考试成绩");
            model.addColumn("期末考试成绩");
            model.addColumn("总评");
            model.addColumn("等级");


            for (int i = 0; i < total; i++) {
                model.addRow(new String[]{});
                table.setValueAt(i + 1, i, 0);
                for (int j = 0; j < 6; j++) {
                    table.setValueAt(tempStr[i][j], i, j + 1);
                }
            }
        }
    }

   //按学号查询
    public void select_stuNumPro() {
        if(table.getColumnCount() == 5 ) {
            JOptionPane.showMessageDialog(null, "请先计算等级和总评", "警告", JOptionPane.INFORMATION_MESSAGE);
        }else {
            String selectStr;
            selectStr = JOptionPane.showInputDialog("请输入您所要查询的学号：");
            boolean yes = false;
            int flag = 0;
            if (selectStr.toCharArray()[0] >= 'a' && selectStr.toCharArray()[0] <= 'z')
                selectStr = selectStr.toUpperCase();
            for (int j = 0; j < total; j++) {
                if (stulist[j][0].equals(selectStr)) {
                    yes = true;
                    flag = j;
                }
            }


            if (yes) {
                while (table.getRowCount() > 0) {
                    model.removeRow(0);
                }
                int xuhao = 0;
                model.addRow(new String[]{});
                table.setValueAt(xuhao + 1, xuhao, 0);
                table.setValueAt(stulist[flag][0], xuhao, 1);
                table.setValueAt(stulist[flag][1], xuhao, 2);
                table.setValueAt(stulist[flag][2], xuhao, 3);
                table.setValueAt(stulist[flag][3], xuhao, 4);
                table.setValueAt(stulist[flag][4], xuhao, 5);
                table.setValueAt(stulist[flag][5], xuhao, 6);
                xuhao++;
            } else {
                JOptionPane.showMessageDialog(null, "没有查询到您所要的内容！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    //等级分布
    public void Grade_layPro() {
        if(table.getColumnCount() == 5 ) {
            JOptionPane.showMessageDialog(null, "请先计算等级", "警告", JOptionPane.INFORMATION_MESSAGE);
        }else {
        model.setColumnCount(0);
        model.addColumn("等级");
        model.addColumn("人数");
        model.addColumn("百分比");
        float[] Percent = new float[5];
        int[] Num = {0, 0, 0, 0, 0};

            for (int i = 0; i < total; i++) {
                switch (stulist[i][5]) {
                    case "A":
                        Num[0]++;
                        break;
                    case "B":
                        Num[1]++;
                        break;
                    case "C":
                        Num[2]++;
                        break;
                    case "D":
                        Num[3]++;
                        break;
                    case "E":
                        Num[4]++;
                        break;
                }
            }
            String[] grade = {"A", "B", "C", "D", "E"};
            model.setRowCount(0);
            //百分比
            for (int i = 0; i < 5; i++) {
                model.addRow(new String[]{});
                Percent[i] = (float) ((int) (((float) Num[i] / total) * 1000)) / 1000;
                //将i行j列的数设置；
                table.setValueAt(grade[i], i, 0);
                table.setValueAt(Num[i], i, 1);
                table.setValueAt(Percent[i], i, 2);
            }
            //平均数
            String average;
            float ave = 0;
            for (int i = 0; i < total; i++) {
                ave += Float.valueOf(stulist[i][4]);
            }
            ave /= total;
            average = "本班平均成绩" + (float) ((int) (ave * 100)) / 100;
            model.addRow(new String[]{});
            table.setValueAt(average, 5, 1);
        }
    }

    public void handle(String eString ,int i) {

        int j=0;
        //构造一个用来解析 str 的 StringTokenizer 对象，并提供一个指定的分隔符。将数据一一分别存储到stulist里
        StringTokenizer st = new StringTokenizer(eString," ");
        while(st.hasMoreElements()) {
            stulist[i][j] = st.nextElement().toString();
            j++;
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
        new B1_Admin();
    }

}
