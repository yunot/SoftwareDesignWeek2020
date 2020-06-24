package drafts;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class LoginFrm2 extends JFrame implements ActionListener{
    public static void main(String[] args) {
        new LoginFrm2();
    }

    JLabel lblname = new JLabel ("用户名：");
    JLabel lblpwd  = new JLabel ("密码    ：");
    JComboBox txtname = new JComboBox(new String[] {"10000","10020"});
    JPasswordField txtpwd = new JPasswordField(10);
    JButton btnOK = new JButton("确定");
    JButton btnCancel = new JButton("取消");
    JRadioButton rbtnpro =new JRadioButton("专家登陆");
    JRadioButton rbtnadmin =new JRadioButton("管理员登陆");
    ButtonGroup bg1=new ButtonGroup();

    public LoginFrm2() {
        JPanel jp1 = (JPanel) this.getContentPane();
        jp1.setLayout(new GridLayout(4,2,10,10));

        btnOK.addActionListener(this);
        btnCancel.addActionListener(this);

        txtname.setEditable(true);

        bg1.add(rbtnpro);bg1.add(rbtnadmin);

        jp1.add(lblname);jp1.add(txtname);
        jp1.add(lblpwd);jp1.add(txtpwd);
        jp1.add(rbtnpro);jp1.add(rbtnadmin);
        jp1.add(btnOK);jp1.add(btnCancel);

        this.setSize(300,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnOK) {
            if(rbtnpro.isSelected()) {
                if(txtname.getSelectedItem().equals("10000") && txtpwd.getText().equals("1"))
                    JOptionPane.showMessageDialog(this,"欢迎您，专家"+txtname.getSelectedItem()+"登陆成功");
                else
                    JOptionPane.showMessageDialog(this, "用户名或密码错误");
            }
            else if(rbtnadmin.isSelected()) {
                if(txtname.getSelectedItem().equals("10020") && txtpwd.getText().equals("1"))
                    JOptionPane.showMessageDialog(this,"欢迎您，管理员"+txtname.getSelectedItem()+"登陆成功");
                else
                    JOptionPane.showMessageDialog(this, "用户名或密码错误");
            }
        }
        if(e.getSource()==btnCancel) {
            if(JOptionPane.showConfirmDialog(this, "是否确定退出","关闭窗口",
                    JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                this.dispose();
            }
        }
    }
}

