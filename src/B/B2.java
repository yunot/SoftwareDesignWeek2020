package B;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class B2 extends JFrame implements ActionListener{
    //GUI 组件
    JMenuBar jmb;
    JMenu file, help, open, save;
    JMenuItem openTicket, openRate, openUser, openCost,saveCost,readme;
    JButton countCost, checkCostByMainCall, checkCostByUsername, checkTicketByMainCall,checkTicketByUsername;
    boolean ticketOpened = false;
    static boolean rateOpened = false;
    boolean userlistOpened = false;
    boolean costSolved = false;
    boolean costFileSaved = false;
    JTable table;
    JScrollPane sc;
    // 默认表格模型
    private DefaultTableModel model = null;

    //数据
    ArrayList<Ticket> ticket = new ArrayList<Ticket>();
    Cost[] cost;
    Map<String, String> rate = new HashMap<>();
    Map<String, String> userList = new HashMap<>();

    public B2() {
        jmb = new JMenuBar();
        //一级菜单
        file = new JMenu("文件");
        help = new JMenu("帮助");
        jmb.add(file);
        jmb.add(help);
        //二级菜单
        //文件操作
        open = new JMenu("打开");
        save = new JMenu("保存");
        file.add(open);
        file.add(save);

        //标题
        JLabel label = new JLabel("电信计费系统",JLabel.CENTER);
        label.setFont(new Font("Serif",0,30));

        readme = new JMenuItem("帮助文档");
        readme.addActionListener(this);

        help.add(readme);

        //功能选择
//        checkTicketByMaincall = new JMenuItem("按主叫号码查询话单");
//        checkTicketByMaincall.addActionListener(this);
//        solveCost = new JMenuItem("计算话费");
//        solveCost.addActionListener(this);
//        checkCostBymaincall= new JMenuItem("按主叫号码查询话费");
//        checkCostBymaincall.addActionListener(this);
//        checkTicketByUsername = new JMenuItem("按用户名查询话单");
//        checkTicketByUsername.addActionListener(this);
//        checkCostByUsername = new JMenuItem("按用户名查询话费");
//        checkCostByUsername.addActionListener(this);
//        edit.add(checkTicketByMaincall);
//        edit.add(solveCost);
//        edit.add(checkCostBymaincall);
//        edit.add(checkTicketByUsername);
//        edit.add(checkCostByUsername);


        //三级菜单
        openTicket = new JMenuItem("打开话单文件");
        openTicket.addActionListener(this);
        openRate = new JMenuItem("打开费率文件");
        openRate.addActionListener(this);
        openUser = new JMenuItem("打开用户文件");
        openUser.addActionListener(this);
        openCost = new JMenuItem("打开话费文件");
        openCost.addActionListener(this);
        open.add(openTicket);
        open.add(openRate);
        open.add(openUser);
        open.add(openCost);

        saveCost = new JMenuItem("保存话费文件");
        saveCost.addActionListener(this);
        save.add(saveCost);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1, 5,2,2));


        countCost = new JButton("计算话费");
        countCost.addActionListener(this);
        checkCostByMainCall = new JButton("按主叫号码查询话费");
        checkCostByMainCall.addActionListener(this);
        checkCostByUsername = new JButton("按用户名查询话费");
        checkCostByUsername.addActionListener(this);
        checkTicketByMainCall = new JButton("按主叫号码查询话单");
        checkTicketByMainCall.addActionListener(this);
        checkTicketByUsername = new JButton("按用户名查询话单");
        checkTicketByUsername.addActionListener(this);




        btnPanel.add(countCost);
        btnPanel.add(checkTicketByUsername);
        btnPanel.add(checkCostByUsername);
        btnPanel.add(checkCostByMainCall);
        btnPanel.add(checkTicketByMainCall);

        add(label, BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);


        String[][] datas = {};
        String[] titles = {};
        model = new DefaultTableModel(datas, titles);
        table = new JTable(model);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        table.setFont(new Font("Serif",0,16));
        table.setRowHeight(28);
        //设置表头居中显示
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        sc = new JScrollPane(table);

        add(sc);
        SetMeunUable();
        setJMenuBar(jmb);
        setTitle("B17030331陶永昱");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕尺寸
        int width = 820;//The application's width
        int height = 600;
        setBounds((d.width-width)/2, (d.height-height)/2, width, height);//窗口的坐标和尺寸，这种方式居中
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



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
        new B2();
    }


    private void SetMeunUable() {
        openCost.setEnabled(false);
        openRate.setEnabled(false);
        openUser.setEnabled(false);
        saveCost.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == openTicket) {
            OpenTicket();
        }else if(event.getSource() == openRate) {
            OpenRate();
        }else if(event.getSource() == openUser) {
            OpenUser();
        }else if(event.getSource() == openCost) {
            OpenCost();
        }else if(event.getSource() == saveCost) {
            SaveCost();
        }else if(event.getSource() == readme) {
            JOptionPane.showMessageDialog(B2.this," 使用指南：\n" +
                    " 1、打开话单文件->按主叫号码查询话单\n" +
                    " 2、打开费率文件->计算话费->保存话费->打开话费文件、按主叫号码查询话费\n" +
                    " 3、打开用户文件->按用户名查询话单->按用户名查询话费\n","关于",JOptionPane.PLAIN_MESSAGE);
        }
        else  if(table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "请打开文件", "警告", JOptionPane.INFORMATION_MESSAGE);
        } else if(event.getSource() == countCost) {
            CountCost();
        }
        else if(event.getSource() == checkCostByMainCall) {
            CheckCostBymaincall();
        }else if(event.getSource() == checkCostByUsername) {
            CheckCostByUsername();
        }else if(event.getSource() == checkTicketByMainCall) {
            checkTicketByMaincall();
        }else if(event.getSource() == checkTicketByUsername) {
            CheckTicketByUsername();
        }
    }


    private void OpenTicket() {
        DoTicket doticket = new DoTicket();

        ticket = doticket.readTicketFromFile();
        if(ticket != null) {
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("主叫区号");
            model.addColumn("主叫号码");
            model.addColumn("被叫区号");
            model.addColumn("被叫号码");
            model.addColumn("通话时长(s)");

            for(int i=0;i<ticket.size();i++) {
                model.addRow(new String[] { "", "", "", "", ""});
                table.setValueAt(ticket.get(i).getMainCallAC(), i, 0);
                table.setValueAt(ticket.get(i).getMainCallNum(), i, 1);
                table.setValueAt(ticket.get(i).getCalledAC(), i, 2);
                table.setValueAt(ticket.get(i).getCalledNum(), i, 3);
                table.setValueAt(ticket.get(i).getCallTime(), i, 4);
            }

            openRate.setEnabled(true);
        }
    }

    private void OpenRate() {
        DoTicket doticket = new DoTicket();
        rate = doticket.readRate();
        try {
            //使用迭代器，获取key;
            Iterator<String> iter = rate.keySet().iterator();
            int i = 0;

            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("区号");
            model.addColumn("费率");

            while(iter.hasNext()){
                String key=iter.next();
                String value = rate.get(key);
                model.addRow(new String[] { "", "", "", "", ""});
                table.setValueAt(key, i, 0);
                table.setValueAt(value, i, 1);
                i++;
            }
            openUser.setEnabled(true);
        } catch(Exception e) {}

    }

    private void OpenUser() {
        DoUser douser = new DoUser();
        userList = douser.readUser();

        try {
            //使用迭代器，获取key;
            Iterator<String> iter = userList.keySet().iterator();
            int i = 0;

            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("电话号码");
            model.addColumn("用户名");

            while(iter.hasNext()){
                String key=iter.next();
                String value = userList.get(key);
                model.addRow(new String[] { "", "", "", "", ""});
                table.setValueAt(key, i, 0);
                table.setValueAt(value, i, 1);
                i++;
            }
        } catch(Exception e) {}
    }

    private void OpenCost() {
        DoCost docost = new DoCost();
        cost = docost.readCostFromFile();
        if(cost != null) {
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("主叫号码");
            model.addColumn("通话类型");
            model.addColumn("话费金额");

            for(int i=0;i<cost.length;i++) {
                model.addRow(new String[] {});
                table.setValueAt(cost[i].getMainCallNum(), i, 0);
                table.setValueAt(cost[i].getCallType(), i, 1);
                table.setValueAt(cost[i].getCallCost(), i, 2);
            }

            if(userList.size() != 0)
                checkCostByUsername.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(null, "文件打开错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void SaveCost() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "txt");
        //设置文件类型
        chooser.setFileFilter(filter);
        //打开选择器面板
        int returnVal = chooser.showSaveDialog(new JPanel());
        //保存文件从这里入手，输出的是文件名
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getPath();
            if(path != null) {
                File file = new File(path+".dat");
                try {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    for(int i=0;i<cost.length;i++) {
                        String line = cost[i].getMainCallNum()+"\t"+cost[i].getCallType()+"\t"+cost[i].getCallCost()+"\n";
                        fos.write(line.getBytes());
                    }
                    fos.close();
                    openCost.setEnabled(true);
                } catch (Exception e) {}
            }
        }
        openCost.setEnabled(true);
    }

    private void checkTicketByMaincall() {
        String mainCallNum;
        ArrayList<Ticket> checkTicketbyMC = new ArrayList<Ticket>();
        mainCallNum = JOptionPane.showInputDialog("请输入您所要查询的主叫号码：");
        if(mainCallNum != null) {
            while(table.getRowCount() > 0) {
                model.removeRow(0);
            }
            for(int i=0;i<ticket.size();i++) {
                if(ticket.get(i).getMainCallNum().equals(mainCallNum)) {
                    checkTicketbyMC.add(ticket.get(i));
                }
            }
            if(checkTicketbyMC.size() > 0) {
                model.setColumnCount(0);
                model.setRowCount(0);

                model.addColumn("主叫号码");
                model.addColumn("用户名");
                model.addColumn("被叫号码");
                model.addColumn("通话时长(s)");
                for(int j=0;j<checkTicketbyMC.size();j++) {
                    model.addRow(new String[] {});
                    table.setValueAt(checkTicketbyMC.get(j).getMainCallNum(), j, 0);
                    table.setValueAt(userList.get(checkTicketbyMC.get(j).getMainCallNum()), j, 1);
                    table.setValueAt(checkTicketbyMC.get(j).getCalledNum(), j, 2);
                    table.setValueAt(checkTicketbyMC.get(j).getCallTime(), j, 3);
                }

            }else
                JOptionPane.showMessageDialog(null, "没找到对应的话单！", "提示", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private void CountCost() {
        if (table.getColumnName(1) != "费率" ) {
            JOptionPane.showMessageDialog(null, "请先打开费率文件", "警告", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DoTicket doticket = new DoTicket();
            cost = doticket.solveCost(ticket, rate);
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("主叫号码");
            model.addColumn("通话类型");
            model.addColumn("话费金额");

            for (int i = 0; i < cost.length; i++) {
                model.addRow(new String[]{});
                table.setValueAt(cost[i].getMainCallNum(), i, 0);
                table.setValueAt(cost[i].getCallType(), i, 1);
                table.setValueAt(cost[i].getCallCost(), i, 2);
            }
            saveCost.setEnabled(true);
        }
    }

    private void CheckCostBymaincall() {
        String mainCallNum;
        ArrayList<Cost> checkCostbyMC = new ArrayList<Cost>();
        if (table.getColumnName(1) != "费率" && table.getColumnName(2) != "话费金额") {
            JOptionPane.showMessageDialog(null, "请先打开费率文件", "警告", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mainCallNum = JOptionPane.showInputDialog("请输入您所要查询的主叫号码：");
            if (mainCallNum != null) {
                while (table.getRowCount() > 0) {
                    model.removeRow(0);
                }
                for (int i = 0; i < cost.length; i++) {
                    if (cost[i].getMainCallNum().equals(mainCallNum)) {
                        //先把有主叫号码的找到
                        checkCostbyMC.add(cost[i]);
                    }
                }
                if (userList.size() == 0) {
                    JOptionPane.showMessageDialog(null, "请打开用户文件再查！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else if (checkCostbyMC.size() > 0) {
                    float localCost = 0, longCost = 0, overall = 0;
                    model.setColumnCount(0);
                    model.setRowCount(0);

                    model.addColumn("主叫号码");
                    model.addColumn("用户名");
                    model.addColumn("通话类型");
                    model.addColumn("通话金额");
                    int j = 0;
                    for (; j < checkCostbyMC.size(); j++) {
                        model.addRow(new String[]{});
                        table.setValueAt(checkCostbyMC.get(j).getMainCallNum(), j, 0);
                        table.setValueAt(userList.get(checkCostbyMC.get(j).getMainCallNum()), j, 1);
                        table.setValueAt(checkCostbyMC.get(j).getCallType(), j, 2);
                        table.setValueAt(checkCostbyMC.get(j).getCallCost(), j, 3);
                        if (checkCostbyMC.get(j).getCallType().equals("本地通话")) {
                            localCost += checkCostbyMC.get(j).getCallCost();
                        } else {
                            longCost += checkCostbyMC.get(j).getCallCost();
                        }
                    }
                    localCost = (float) ((int) (localCost * 100)) / 100;
                    longCost = (float) ((int) (longCost * 100)) / 100;
                    overall += localCost + longCost;
                    model.addRow(new String[]{});
                    table.setValueAt("本地话费：" + localCost, j, 0);
                    table.setValueAt("长途话费：" + longCost, j, 1);
                    table.setValueAt("总计话费：" + overall, j, 2);
                } else
                    JOptionPane.showMessageDialog(null, "没找到对应的话费！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void CheckTicketByUsername() {
        String userName, mainCallNum = null;
        ArrayList<Ticket> checkTicketbyUN = new ArrayList<Ticket>();
        if (table.getColumnName(1) != "用户名" ) {
            JOptionPane.showMessageDialog(null, "请先打开用户文件", "警告", JOptionPane.INFORMATION_MESSAGE);
        } else {
            userName = JOptionPane.showInputDialog("请输入您所要查询的用户名：");
            if (userName != null) {
                while (table.getRowCount() > 0) {
                    model.removeRow(0);
                }
                //使用迭代器，获取key;
                Iterator<String> iter = userList.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    String value = userList.get(key);
                    if (value.equals(userName)) {
                        mainCallNum = key;
                        break;
                    }
                }
                if (mainCallNum != null) {
                    for (int i = 0; i < ticket.size(); i++) {
                        if (ticket.get(i).getMainCallNum().equals(mainCallNum)) {
                            checkTicketbyUN.add(ticket.get(i));
                        }
                    }
                    if (checkTicketbyUN.size() > 0) {
                        model.setColumnCount(0);
                        model.setRowCount(0);
                        model.addColumn("用户名");
                        model.addColumn("主叫号码");
                        model.addColumn("被叫号码");
                        model.addColumn("通话时长(s)");
                        for (int j = 0; j < checkTicketbyUN.size(); j++) {
                            model.addRow(new String[]{});
                            table.setValueAt(userName, j, 0);
                            table.setValueAt(checkTicketbyUN.get(j).getMainCallNum(), j, 1);
                            table.setValueAt(checkTicketbyUN.get(j).getCalledNum(), j, 2);
                            table.setValueAt(checkTicketbyUN.get(j).getCallTime(), j, 3);
                        }

                    } else
                        JOptionPane.showMessageDialog(null, "没找到此用户对应的话单！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "没找到此用户！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
    }

    private void CheckCostByUsername() {
        String userName, mainCallNum = null;
        ArrayList<Cost> checkCostbyUN = new ArrayList<Cost>();
            if (table.getColumnName(1) != "用户名" ) {
                JOptionPane.showMessageDialog(null, "请先打开用户文件", "警告", JOptionPane.INFORMATION_MESSAGE);
            } else {
                userName = JOptionPane.showInputDialog("请输入您所要查询的用户名：");
                if (userName != null) {
                    while (table.getRowCount() > 0) {
                        model.removeRow(0);
                    }
                    //使用迭代器，获取key;
                    Iterator<String> iter = userList.keySet().iterator();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        String value = userList.get(key);
                        if (value.equals(userName)) {
                            mainCallNum = key;
                            break;
                        }
                    }
                    if (mainCallNum != null) {
                        for (int i = 0; i < cost.length; i++) {
                            if (cost[i].getMainCallNum().equals(mainCallNum)) {
                                checkCostbyUN.add(cost[i]);
                            }
                        }
                        if (checkCostbyUN.size() > 0) {
                            float localCost = 0, longCost = 0, overall = 0;
                            model.setColumnCount(0);
                            model.setRowCount(0);
                            model.addColumn("用户名");
                            model.addColumn("主叫号码");
                            model.addColumn("通话类型");
                            model.addColumn("通话金额");
                            int j = 0;
                            for (; j < checkCostbyUN.size(); j++) {
                                model.addRow(new String[]{});
                                table.setValueAt(userName, j, 0);
                                table.setValueAt(mainCallNum, j, 1);
                                table.setValueAt(checkCostbyUN.get(j).getCallType(), j, 2);
                                table.setValueAt(checkCostbyUN.get(j).getCallCost(), j, 3);
                                if (checkCostbyUN.get(j).getCallType().equals("本地通话")) {
                                    localCost += checkCostbyUN.get(j).getCallCost();
                                } else {
                                    longCost += checkCostbyUN.get(j).getCallCost();
                                }
                            }
                            localCost = (float) ((int) (localCost * 100)) / 100;
                            longCost = (float) ((int) (longCost * 100)) / 100;
                            overall += localCost + longCost;
                            model.addRow(new String[]{});
                            table.setValueAt("本地话费：" + localCost, j, 0);
                            table.setValueAt("长途话费：" + longCost, j, 1);
                            table.setValueAt("总计话费：" + overall, j, 2);

                        } else
                            JOptionPane.showMessageDialog(null, "没找到此用户对应的话费！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "没找到此用户！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            }
        }
    }



//1、readTick 2、先读取费率 3、计算cost 4、输出费用至文件  5、读取费用文件
class DoTicket{
    //读取费率文件	(被叫区号	费率)
    public Map<String, String> readRate() {
        Map<String,String> rate = new HashMap<String, String>();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
        chooser.setFileFilter(filter);
        String[] tempstr = new String[4];
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File filex=chooser.getSelectedFile();
            byte[] b = new byte[10];
            try{
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(filex));
                String line = "";
                int i = 0;
                while((line=br.readLine())!=null){
                    tempstr[i] = line;
                    i++;
                }
                br.close();
                String[] tempst = new String[2];
                int max = i;
                for(i=0;i<max;i++) {
                    int j = 0;
                    StringTokenizer st = new StringTokenizer(tempstr[i],",!' ';");
                    while(st.hasMoreElements()) {
                        tempst[j] = st.nextElement().toString();
                        j++;
                    }
                    rate.put(tempst[0], tempst[1]);
                }
                return rate;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "文件选择错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    //读取通话文件	(主叫区号	主叫号码	被叫区号	被叫号码	通话时长)
    public ArrayList<Ticket> readTicketFromFile() {
        int j=0;
        Ticket ticket;
        JFileChooser chooser = new JFileChooser();
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File filex=chooser.getSelectedFile();
            byte[] b = new byte[10];
            try{
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(filex));
                String line = "";
                int i = 0;
                ArrayList a = new ArrayList<String>();
                while((line=br.readLine())!=null){
                    a.add(line);
                }

                for(i=0;i<a.size();i++) {
                    j = 0;
                    ticket = new Ticket();
                    StringTokenizer st = new StringTokenizer(a.get(i).toString(),",!' ';");
                    while(st.hasMoreElements()) {
                        switch(j) {
                            case 0:
                                ticket.setMainCallAC(st.nextElement().toString());
                                break;
                            case 1:
                                ticket.setMainCallNum(st.nextElement().toString());break;
                            case 2:
                                ticket.setCalledAC(st.nextElement().toString());break;

                            case 3:
                                ticket.setCalledNum(st.nextElement().toString());break;
                            case 4:
                                ticket.setCallTime(Integer.valueOf(st.nextElement().toString()));break;
                        }
                        j++;
                    }
                    tickets.add(ticket);
                }
                br.close();

            } catch (IOException e) {}
        }
        if(j == 5) {
            return tickets;
        }else {
            JOptionPane.showMessageDialog(null, "文件选择错误！", "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    //计算话费(主叫号码	通话类型	通话费用)
    public Cost[] solveCost(ArrayList<Ticket> ticket, Map<String, String> rate) {
        Cost[] cost = null;//maincallNum,calltype,callcost
        String mainCallAC, mainCallNum, calledAC, calledNum;
        int callTime;
        if(ticket != null) {
            cost = new Cost[ticket.size()];
            for(int i=0;i<ticket.size();i++) {
                float result;
                cost[i] = new Cost();
                mainCallAC = ticket.get(i).getMainCallAC();
                mainCallNum = ticket.get(i).getMainCallNum();
                calledAC = ticket.get(i).getCalledAC();
                calledNum = ticket.get(i).getCalledNum();
                callTime = ticket.get(i).getCallTime();
                callTime = callTime%60 == 0 ? callTime/60 : (callTime/60+1);
                cost[i].setMainCallNum(mainCallNum);
                if(mainCallAC.equals(calledAC)) {
                    cost[i].setCallType("本地通话");
                    result = callTime > 3 ? (float)((callTime-3)*0.2+0.3) : (float)0.3;
                    cost[i].setCallCost(result);
                }else {
                    cost[i].setCallType("长途通话");
                    result = (float)(callTime*Float.valueOf(rate.get(calledAC)));
                    result = (float)((int)(result*100))/100;
                    cost[i].setCallCost(result);
                }
            }
        }
        return cost;
    }

}

class DoCost{

    //读取话费文件	(主叫号码	通话类型	通话费用)
    public Cost[] readCostFromFile() {
        int j = 0;
        Cost[] cost = null;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File filex=chooser.getSelectedFile();
            byte[] b = new byte[10];
            try{
                ArrayList<String> array = new ArrayList<String>();
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(filex));
                String line = "";
                while((line=br.readLine())!=null){
                    array.add(line);
                }
                cost = new Cost[array.size()];
                for(int i=0;i<array.size();i++) {
                    StringTokenizer st = new StringTokenizer(array.get(i).toString(),",!' ' \t ;");
                    cost[i] = new Cost();
                    j = 0;
                    while(st.hasMoreElements()) {
                        switch(j) {
                            case 0:
                                cost[i].setMainCallNum(st.nextElement().toString());break;
                            case 1:
                                cost[i].setCallType(st.nextElement().toString());break;
                            case 2:
                                cost[i].setCallCost(Float.valueOf(st.nextElement().toString()));break;
                        }
                        j++;
                    }
                }
                br.close();

            } catch (IOException e) {
            }
        }
        if(j == 3) {
            return cost;
        }else {
            JOptionPane.showMessageDialog(null, "文件选择错误！", "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    //保存话费文件
    public void writeCostToFile(Cost[] cost) {
        if(cost != null) {
            String filename;
            String inputstr = JOptionPane.showInputDialog("请输入您所要保存的文件名：","output.dat");
            if(inputstr != null) {
                filename = inputstr;
                try {
                    FileOutputStream fos = new FileOutputStream(filename);
                    for(int i=0;i<cost.length;i++) {
                        String line = cost[i].getMainCallNum()+"\t"+cost[i].getCallType()+"\t"+cost[i].getCallCost()+"\n";
                        fos.write(line.getBytes());
                    }
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
    }

}

class DoUser{
    //读取用户文件 (电话号码	用户名)
    public Map<String, String> readUser() {
        Map<String, String> userlist = new HashMap<String, String>();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
        chooser.setFileFilter(filter);
        ArrayList<String> array = new ArrayList<String>();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File filex=chooser.getSelectedFile();
            byte[] b = new byte[10];
            try{
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(filex));
                String line = "";
                while((line=br.readLine())!=null){
                    array.add(line);
                }
                br.close();
            } catch (Exception e) {
            }
        }
        String[] tempst = new String[2];
        try {
            for(int i=0;i<array.size();i++) {
                int j = 0;
                StringTokenizer st = new StringTokenizer(array.get(i),",!' ';");
                while(st.hasMoreElements()) {
                    tempst[j] = st.nextElement().toString();
                    j++;
                }
                userlist.put(tempst[0], tempst[1]);
            }
            return userlist;
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "文件选择错误！", "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}

class Ticket{
    private String mainCallAC, mainCallNum, calledAC, calledNum;
    private int callTime;

    public String getMainCallAC() {
        return mainCallAC;
    }
    public void setMainCallAC(String mainCallAC) {
        this.mainCallAC = mainCallAC;
    }
    public String getMainCallNum() {
        return mainCallNum;
    }
    public void setMainCallNum(String mainCallNum) {
        this.mainCallNum = mainCallNum;
    }
    public String getCalledAC() {
        return calledAC;
    }
    public void setCalledAC(String calledAC) {
        this.calledAC = calledAC;
    }
    public String getCalledNum() {
        return calledNum;
    }
    public void setCalledNum(String calledNum) {
        this.calledNum = calledNum;
    }
    public int getCallTime() {
        return callTime;
    }
    public void setCallTime(int callTime) {
        this.callTime = callTime;
    }
    public String toString() {
        return "mainCallAC:"+ mainCallAC+",mainCallNum:"+mainCallNum+",calledAC:"+
                calledAC+",calledNum:" +calledNum+",callTime:"+callTime;
    }
}
class Cost extends Ticket{
    private String callType;
    private float callCost;
    public String getCallType() {
        return callType;
    }
    public void setCallType(String callType) {
        this.callType = callType;
    }
    public float getCallCost() {
        return callCost;
    }
    public void setCallCost(float callCost) {
        this.callCost = callCost;
    }
}

class User{
    private String userName, userNum;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserNum() {
        return userNum;
    }
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
}

