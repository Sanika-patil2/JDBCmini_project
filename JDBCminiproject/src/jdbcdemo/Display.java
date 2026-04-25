package jdbcdemo;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Display extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p1,p2,p3,p4,mp;
	JButton prevBtn,nextBtn,firstBtn,lastBtn,homeBtn;
	JTextField rollField,nameField,addrField;
	JLabel rollLabel,nameLabel,addrLabel;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	Display()
	{
		prevBtn =new JButton("Previous");
		firstBtn =new JButton("First");
		lastBtn =new JButton("Last");
		homeBtn =new JButton("Home");
		nextBtn =new JButton("Next");
		rollField =new JTextField(20);
		nameField =new JTextField(20);
		addrField =new JTextField(20);
		rollLabel =new JLabel("Rollno");
		nameLabel =new JLabel("Name");
		addrLabel =new JLabel("Address");
		p1 =new JPanel(new GridLayout(1,2,10,10));
		p2 =new JPanel(new GridLayout(1,2,10,10));
		p3 =new JPanel(new GridLayout(1,2,10,10));
		p4 =new JPanel(new GridLayout(1,5,10,10));
		mp =new JPanel(new GridLayout(4,1,10,10));
		p1.add(rollLabel);
		p1.add(rollField);
		p2.add(nameLabel);
		p2.add(nameField);
		p3.add(addrLabel);
		p3.add(addrField);
		p4.add(prevBtn);
		p4.add(firstBtn);
		p4.add(lastBtn);
		p4.add(homeBtn);
		p4.add(nextBtn);
		mp.add(p1);
		mp.add(p2);
		mp.add(p3);
		mp.add(p4);
		add(mp);
		setVisible(true);
		setLayout(new FlowLayout());
        setTitle("Display Page");
        setBounds(100,100,500,500);
		prevBtn.addActionListener(this);
		homeBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		firstBtn.addActionListener(this);
		lastBtn.addActionListener(this);
        
		makeConnection();
		
	}
	public void makeConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","swara");
			JOptionPane.showMessageDialog(mp, "Database connected");
			pst=con.prepareStatement("Select * from Studentdetails",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=pst.executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	void DisplayPage()
	{
		try {
			rollField.setText(""+rs.getInt(1));
			nameField.setText(""+rs.getString(2));
			addrField.setText(""+rs.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==prevBtn) {
			try {
				if(rs.previous()) {
					DisplayPage();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==nextBtn) {
			try {
				if(rs.next()) {
					DisplayPage();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==firstBtn) {
			try {
				if(rs.first()) {
					DisplayPage();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource()==lastBtn) {
			try {
				if(rs.last()) {
					DisplayPage();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==homeBtn) {
			this.setVisible(false);
			new HomePage();
		}
	}
	public static void main(String arg[]) {
		new Display();
	}

}
