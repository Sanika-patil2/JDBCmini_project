package jdbcdemo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class SearchPage extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel rollLabel,nameLabel, addressLabel;
    JPanel p1,p2,p3,p4,mp;
    JButton searchBtn, updateBtn, homeBtn;
    JTextField rollField, nameField, addressField;
    Connection con;
    PreparedStatement pst,pst1;
    ResultSet rs;
SearchPage()
{
        rollLabel = new JLabel("Roll:");
        rollField = new JTextField(20);
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        searchBtn = new JButton("Search");
        updateBtn = new JButton("Update");
        homeBtn = new JButton("Home");
        p1 =new JPanel(new GridLayout(1,2,10,10));
        p2 =new JPanel(new GridLayout(1,2,10,10));
        p3 =new JPanel(new GridLayout(1,2,10,10));
        p4 =new JPanel(new GridLayout(1,2,10,10));
        mp =new JPanel(new GridLayout(4,1,10,10));
        setLayout(new FlowLayout());
        p1.add(rollLabel);
        p1.add(rollField );
        p2.add(nameLabel);
        p2.add(nameField);
        p3.add(addressLabel);
        p3.add(addressField);
        p4.add(searchBtn);
        p4.add(updateBtn);
        p4.add(homeBtn);
        mp.add(p1);
        mp.add(p2);
        mp.add(p3);
        mp.add(p4);
        add(mp);
        setTitle("SEARCH Page");
        setBounds(100,100,500,500);
        setVisible(true);
        searchBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        homeBtn.addActionListener(this);
        makeConnection();
    }
public void makeConnection()
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","swara");
		JOptionPane.showMessageDialog(mp, "Database connected");
		pst=con.prepareStatement("Select * from Studentdetails where roll=?");
		pst1=con.prepareStatement("update Studentdetails set name =?,address=? where roll=?");
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
	if(e.getSource()==searchBtn)
	{
		int a=Integer.parseInt(rollField.getText());
		String b=nameField.getText();
		String c=addressField.getText();
		if(a>0)
		{
		try {
			pst.setInt(1, a);
			//pst.setString(2, b);
			//pst.setString(3,c);
			rs=pst.executeQuery();
			if(rs.next())
			{
				JOptionPane.showMessageDialog(mp, " Search Successfull");
				nameField.setText(""+rs.getString(2));
				addressField.setText(""+rs.getString(3));
			}
			else
			{
				JOptionPane.showMessageDialog(mp, "Either rollno , name or address is incrorrect");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	}
	if(e.getSource()==updateBtn)
	{
	   int a=Integer.parseInt(rollField.getText());
       String b=nameField.getText();
	   String c=addressField.getText();
	   try {
		pst1.setString(1, b);
		   pst1.setString(2, c);
		   pst1.setInt(3, a);
		   int res=pst1.executeUpdate();
			if(res>=1)
			{
				JOptionPane.showMessageDialog(mp,res+"  record is updated successfully");
			}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(mp," Error in  record  updation");
		e1.printStackTrace();
	}
	}
	if(e.getSource()==homeBtn)
	{
		this.setVisible(false);
		new HomePage();
	}
}
public static void main(String[] args)
{
	new SearchPage();
}
}
