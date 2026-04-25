package jdbcdemo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;


public class queryTable extends JFrame implements ActionListener{
	JPanel p1,p2,p3,mp,mp1,mp2;
	JTextField queryField;
	JLabel query,q1;
	JButton queryBtn;
	JScrollPane sp;
	JTable table;
	DefaultTableModel dtm;
	String colname[]= {"Rollno","Name","Address"};
	String rowsdata[][]= {{"101","abc","Nagpur"}};
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	ResultSetMetaData rsmd;
queryTable()
{
	dtm =new DefaultTableModel(rowsdata,colname);
	table =new JTable(dtm);
	sp =new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	queryField =new JTextField(20);
	query =new JLabel("Enter Query");
	q1 =new JLabel("Query Details are:");
	queryBtn =new JButton("Execute");
	p1 =new JPanel(new GridLayout(1,3,10,10));
	p2 =new JPanel(new GridLayout(1,1,10,10));
	p3 =new JPanel();
	mp =new JPanel(new GridLayout(3,1,10,10));
	mp1 =new JPanel(new GridLayout());
	mp2 =new JPanel(new BorderLayout());
	p1.add(query);
	p1.add(queryField);
	p1.add(queryBtn);
	p2.add(q1);
	p3.add(sp);
	mp.add(p1);
	mp.add(p2);
	mp1.add(p3);
	mp2.add(mp,BorderLayout.NORTH);
	mp2.add(mp1,BorderLayout.CENTER);
	add(mp2);
	setBounds(100,100,500,500);
	setLayout(new FlowLayout());
	setVisible(true);
	setTitle("query Table");
	queryBtn.addActionListener(this);
	makeConnection();
}
public void makeConnection()
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","swara");
		JOptionPane.showMessageDialog(mp, "Database connected");
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
	if(e.getSource()==queryBtn)
	{
		String b=queryField.getText();
		try {
			pst =con.prepareStatement(b,rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
			rs =pst.executeQuery();
			rsmd =rs.getMetaData();
			int cols =rsmd.getColumnCount();
			colname =new String[cols];
			int i;
			for(i=1;i<=cols;i++)
			{
				colname[i-1]=rsmd.getColumnName(i);
				
			}
			int rows =0;
			while(rs.next())
			{
				rows++;
			}
			rs.beforeFirst();
			rowsdata =new String[rows][cols];
			rows=0;
			while(rs.next())
			{
				for(i=1;i<=cols;i++)
				{
					rowsdata[rows][i-1]=rs.getString(i);	
					
				}
				rows++;
			}
			dtm =new DefaultTableModel(rowsdata,colname);
			table.setModel(dtm);
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
public static void main(String[] args)
{
	new queryTable();
}
}


