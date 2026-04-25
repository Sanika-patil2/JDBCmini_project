package jdbcdemo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class loginpage extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel userLabel, passLabel;
	JButton loginBtn, exitBtn;
	JTextField userField, passField;
	JPanel p1,p2,p3,mp;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
loginpage() {
        userLabel = new JLabel("User ID:");
        userField = new JTextField(20);
        passLabel = new JLabel("Password:");
        passField = new JTextField(20);
        loginBtn = new JButton("Login");
        exitBtn = new JButton("Exit");
        p1 =new JPanel(new GridLayout(1,2,10,10));
	    p2 =new JPanel(new GridLayout(1,2,10,10));
		p3 =new JPanel(new GridLayout(1,2,10,10));
        mp =new JPanel(new GridLayout(3,1,10,10));
        setLayout(new FlowLayout());
        p1.add(userLabel);
        p1.add(userField);
        p2.add(passLabel);
        p2.add(passField);
        p3.add(loginBtn);
        p3.add(exitBtn);
        mp.add(p1);
        mp.add(p2);
        mp.add(p3);
        add(mp);
        setTitle("LOGIN Page");
        setBounds(100,100,500,500);
        setVisible(true);
        loginBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        makeConnection();
    }
public void makeConnection()
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","swara");
		JOptionPane.showMessageDialog(mp, "Database connected");
		pst=con.prepareStatement("Select * from Userdetails where username=? and password=?");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
     public void actionPerformed(ActionEvent e)
		    {
			if(e.getSource()==loginBtn)
			{
				String a=userField.getText();
				String b=passField.getText();
				if(a.length()>0 && b.length()>0)
				{
					
					try {
						pst.setString(1, a);
						pst.setString(2, b);
						rs=pst.executeQuery();
						if(rs.next())
						{
							JOptionPane.showMessageDialog(mp, " Login Successfull");
							this.setVisible(false);
							new HomePage();
						
						}
						else
						{
							JOptionPane.showMessageDialog(mp, "Either user name or password in incrorrect");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(mp,"Error is database connection");
						e1.printStackTrace();
					}
				}
					
			}
			if(e.getSource()==exitBtn)
			{
		      this.setVisible(false);
		    }
}
    public static void main(String[] args) {
        new loginpage();
    }
}
