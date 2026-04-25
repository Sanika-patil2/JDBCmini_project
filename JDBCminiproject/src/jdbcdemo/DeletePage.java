package jdbcdemo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class DeletePage extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel rollLabel;
	JTextField rollField;
	JButton deleteBtn,homeBtn;
	JPanel p1,p2,mp;
	Connection con;
	PreparedStatement pst;

	DeletePage()
	{
        rollLabel = new JLabel("Roll:");
        rollField = new JTextField(20);
        deleteBtn = new JButton("Delete");
        homeBtn = new JButton("Home");
        p1 =new JPanel(new GridLayout(1,2,10,10));
        p2 =new JPanel(new GridLayout(1,2,10,10));
        mp =new JPanel(new GridLayout(2,1,10,10));
        setLayout(new FlowLayout());
        p1.add(rollLabel);
        p1.add(rollField);
        p2.add(deleteBtn);
        p2.add(homeBtn);
        mp.add(p1);
        mp.add(p2);
        add(mp);
        setTitle("DELETE Page");
        setBounds(100,100,500,500);
        setVisible(true);
        deleteBtn.addActionListener(this);
        homeBtn.addActionListener(this);
        makeConnection();
    }
	public void makeConnection()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","swara");
			JOptionPane.showMessageDialog(mp, "Database connected");
			pst=con.prepareStatement("Delete from Studentdetails where roll=?");
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
		if(e.getSource()==deleteBtn)
		{
			int a =Integer.parseInt(rollField.getText());
			try {
				pst.setInt(1, a);
				int res=pst.executeUpdate();
				if (res>=1)
				{
					JOptionPane.showMessageDialog(mp,res+"  record is Deleted successfully");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(mp," Error in  record  Deletion");
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
			new DeletePage();
		}
}
