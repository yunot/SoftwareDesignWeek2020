package drafts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class TextEditor extends JFrame {
    // 添加属性
    private JComboBox combox_name, combox_size;// 字体、字号组合框
    private JButton button_larger,button_smaller,button_color;//字体变大变小和颜色选择器
    private JCheckBox checkb_bold, checkb_italic;// 粗体、斜体复选框
    private JPopupMenu popupmenu;
    private JTextArea ta = new JTextArea();
    private JScrollPane sp = new JScrollPane(ta);

    public TextEditor(String str) {
        super(str);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = getToolkit().getScreenSize(); // 获得屏幕分辨率
        this.setBounds(dim.width / 4, dim.height / 4, 700, 480);
        JToolBar toolbar = new JToolBar(); // 创建工具栏
        this.add(toolbar, BorderLayout.NORTH); // 工具栏添加到窗格北部
        this.add(sp);
        ta.setLineWrap(true);// 换行
        //////////////////字体
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontsName = ge.getAvailableFontFamilyNames(); // 获得系统字体
        combox_name = new JComboBox(fontsName);
        toolbar.add(combox_name);
        combox_name.addActionListener(new ActionListener() {// 字号
            public void actionPerformed(ActionEvent e) {
                String fontname = (String)combox_name.getSelectedItem();//获得字体名
                Font font = ta.getFont();     //获得文本区的当前字体对象
                int style = font.getStyle();      //获得字形
                int size = font.getSize();
                ta.setFont(new Font(fontname, style, size));
            }
        });
        /////////////////字号
        String sizestr[] = { "20", "30", "40", "50", "60", "70" ,"80","90","100"};
        combox_size = new JComboBox(sizestr);
        combox_size.setEditable(true);
        toolbar.add(combox_size);
        combox_size.addActionListener(new ActionListener() {// 字号
            public void actionPerformed(ActionEvent e) {
                String fontname = (String)combox_name.getSelectedItem();//获得字体名
                int size = Integer.parseInt((String)combox_size.getSelectedItem());
                Font font = ta.getFont();     //获得文本区的当前字体对象
                int style = font.getStyle();      //获得字形
                ta.setFont(new Font(fontname, style, size));
            }
        });
        ////////////////////字号加减按钮
        button_larger=new JButton("A+");
        toolbar.add(button_larger);
        button_larger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fontname = (String)combox_name.getSelectedItem();//获得字体名
                Font font = ta.getFont();     //获得文本区的当前字体对象
                int style = font.getStyle();      //获得字形
                int size = font.getSize()+5;
                ta.setFont(new Font(fontname, style, size));
            }
        });
        button_smaller=new JButton("A-");
        toolbar.add(button_smaller);
        button_smaller.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fontname = (String)combox_name.getSelectedItem();//获得字体名
                Font font = ta.getFont();     //获得文本区的当前字体对象
                int style = font.getStyle();      //获得字形
                int size = font.getSize()-5;
                ta.setFont(new Font(fontname, style, size));
            }
        });
        /////////////////
        /////////////////粗体和斜体
        checkb_bold = new JCheckBox("粗体"); //字形复选框
        toolbar.add(checkb_bold);
        checkb_bold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fontname = (String)combox_name.getSelectedItem();//获得字体名
                Font font = ta.getFont();     //获得文本区的当前字体对象
                int style = font.getStyle();      //获得字形
                int size = font.getSize();
                style = style ^ 1;
                ta.setFont(new Font(fontname, style, size));
            }
        });
        checkb_italic = new JCheckBox("斜体");
        toolbar.add(checkb_italic);
        checkb_italic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fontname = (String)combox_name.getSelectedItem();//获得字体名
                Font font = ta.getFont();     //获得文本区的当前字体对象
                int style = font.getStyle();      //获得字形
                int size = font.getSize();
                style = style ^ 2;
                ta.setFont(new Font(fontname, style, size));
            }
        });
        ////////////////
        JRadioButton radiob_color[];
        String colorstr[]={"红","绿","蓝"};
        ButtonGroup bgroup_color = new ButtonGroup();      //按钮组
        radiob_color = new JRadioButton[colorstr.length];  //颜色单选按钮数组
        for (int i=0; i<radiob_color.length; i++){
            radiob_color[i]=new JRadioButton(colorstr[i]);
            bgroup_color.add(radiob_color[i]); //添加到按钮组
            toolbar.add(radiob_color[i]);     //添加到工具栏
        }
        radiob_color[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.setForeground(Color.red);// 设置颜色
            }
        });
        radiob_color[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.setForeground(Color.green);
            }
        });
        radiob_color[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.setForeground(Color.blue);
            }
        });
        ///////////////颜色选择器
        button_color=new JButton("其他");
        toolbar.add(button_color);
        button_color.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color;
                color=JColorChooser.showDialog(TextEditor.this,"颜色选择", Color.black);
                ta.setForeground(color);// 设置颜色
            }
        });
        ////////////////鼠标事件
        ta.addMouseListener(new MouseAdapter() {// 鼠标事件处理方法，右击弹出菜单
            public void mouseClicked(MouseEvent e) {
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK) // 单击的是鼠标右键
                    popupmenu.show(ta, e.getX(), e.getY()); // 在鼠标单击处显示快捷菜单
            }
        });
        ////////////////
        this.addmyMenu();       //调用自定义方法，添加菜单
        this.setVisible(true);
    }

    private void addmyMenu() {// 添加主菜单、快捷菜单、对话框
        JMenuBar menubar = new JMenuBar(); // 菜单栏
        this.setJMenuBar(menubar); // 添加菜单栏
        String menustr[] = { "文件", "编辑", "工具", "帮助" };
        JMenu menu[] = new JMenu[menustr.length];
        for (int i = 0; i < menustr.length; i++) {
            menu[i] = new JMenu(menustr[i]); // 菜单
            menubar.add(menu[i]); // 菜单栏中加入菜单
        }
        ////////////////////////////////
        JMenuItem menuitem_open = new JMenuItem("打开");
        menu[0].add(menuitem_open);
        menuitem_open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.showSaveDialog(TextEditor.this);
            }
        });
        JMenuItem menuitem_save = new JMenuItem("保存");
        menu[0].add(menuitem_save);
        menuitem_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.showSaveDialog(TextEditor.this);
            }
        });
        menu[0].addSeparator(); // 加分隔线
        JMenuItem menuitem_exit = new JMenuItem("退出");
        menu[0].add(menuitem_exit);
        menuitem_exit.addActionListener(new ActionListener() {// 退出
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /////////////////////////////
        JMenu menu_style = new JMenu("字形");
        JCheckBoxMenuItem checkboxmenuitem_bold = new JCheckBoxMenuItem("粗体");
        menu_style.add(checkboxmenuitem_bold);
        JCheckBoxMenuItem checkboxmenuitem_italic = new JCheckBoxMenuItem("斜体");
        menu_style.add(checkboxmenuitem_italic);
        menu[1].add(menu_style); // 菜单加入到菜单中成为二级菜单
        checkboxmenuitem_bold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fontname = (String)combox_name.getSelectedItem();//获得字体名
                Font font = ta.getFont();     //获得文本区的当前字体对象
                int style = font.getStyle();      //获得字形
                int size = font.getSize();
                style = style ^ 1;
                ta.setFont(new Font(fontname, style, size));
            }
        });

        checkboxmenuitem_italic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fontname = (String)combox_name.getSelectedItem();//获得字体名
                Font font = ta.getFont();     //获得文本区的当前字体对象
                int style = font.getStyle();      //获得字形
                int size = font.getSize();
                style = style ^ 2;
                ta.setFont(new Font(fontname, style, size));
            }
        });
        ////////////////////////////
        JMenu menu_color = new JMenu("颜色");
        menu[1].add(menu_color);
        ButtonGroup buttongroup = new ButtonGroup();
        String colorstr[] = { "红", "绿", "蓝" };
        JRadioButtonMenuItem rbmi_color[] = new JRadioButtonMenuItem[colorstr.length];
        for (int i = 0; i < rbmi_color.length; i++) {
            rbmi_color[i] = new JRadioButtonMenuItem(colorstr[i]); // 单选菜单项
            buttongroup.add(rbmi_color[i]);
            menu_color.add(rbmi_color[i]);
        }
        rbmi_color[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.setForeground(Color.red);
            }
        });
        rbmi_color[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.setForeground(Color.green);
            }
        });
        rbmi_color[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.setForeground(Color.blue);
            }
        });
        /////////////////////////////////
        JMenuItem menuitem_countwordsnum = new JMenuItem("字数统计");
        menu[2].add(menuitem_countwordsnum);
        menuitem_countwordsnum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int count=0;
                for(int i=0;i<ta.getText().length();i++){
                    count++;
                }
                JOptionPane.showMessageDialog(TextEditor.this, "文本框中一共有"+count+"个字符！");
            }
        });
        menu[2].addSeparator(); // 加分隔线
        JMenuItem menuitem_search = new JMenuItem("查找");
        menu[2].add(menuitem_search);
        menuitem_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str_search = JOptionPane.showInputDialog(
                        TextEditor.this, "请输入你要查找的字符串:");
                int len = str_search.length();
                int count = 0;
                int key=0;
                while (key<ta.getText().length()-len + 1) {
                    for (int i = key; i < ta.getText().length() - len + 1; i++) {
                        String str_record = ta.getText().substring(i, i + len);
                        if (str_record.equals(str_search)) {
                            count++;
                            key=i+1;
                            JOptionPane.showMessageDialog(TextEditor.this,"已经找到，文本框中文本“"+
                                    str_search + "”的第"+count+"次出现的位置：" + i+ "到" + (i + len));
                        }
                    }
                }
            }
        });
        JMenuItem menuitem_replace = new JMenuItem("替换");
        menu[2].add(menuitem_replace);
        menuitem_replace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str_replace=JOptionPane.showInputDialog(TextEditor.this,
                        "请输入你要替换的字符串:" );
                String str_replacelater=JOptionPane.showInputDialog(TextEditor.this,
                        "请输入你要用来替换的内容:" );
                int len=str_replace.length();
                for(int i=0;i<ta.getText().length()-len+1;i++){
                    String str_record=ta.getText().substring(i, i+len);
                    if(str_record.equals(str_replace)){
                        ta.replaceRange(str_replacelater,i, i+len);
                    }
                }
            }
        });
        /////////////////////////////////
        JMenuItem menuitem_about = new JMenuItem("关于");
        menu[3].add(menuitem_about);
        menuitem_about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TextEditor.this,"文本编辑器v1.0   开发者：hhtx");
            }
        });
        ////////////////////////////////////////////////// 快捷菜单对象
        popupmenu = new JPopupMenu();
        String menuitemstr[] = { "剪切", "复制", "粘贴" };
        JMenuItem popmenuitem[] = new JMenuItem[menuitemstr.length];
        for (int i = 0; i < popmenuitem.length; i++) {
            popmenuitem[i] = new JMenuItem(menuitemstr[i]);// 菜单项
            popupmenu.add(popmenuitem[i]);// 快捷菜单加入菜单项
        }
        popmenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                InputEvent.CTRL_MASK));// 设置快捷键Ctrl+X
        popmenuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                InputEvent.CTRL_MASK));// 设置快捷键Ctrl+C
        popmenuitem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                InputEvent.CTRL_MASK));// 设置快捷键Ctrl+V

        popmenuitem[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.cut(); //将选中文本剪切送系统剪贴板
            }
        });
        popmenuitem[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.copy(); //将选中文本复制送系统剪贴板
            }
        });
        popmenuitem[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.paste();//剪贴板的文本粘贴在当前位置
            }
        });
        ta.add(popupmenu); // 文本区添加快捷菜单
    }

    public static void main(String args[]) {
        new TextEditor("文本编辑器v1.0");
    }
}
