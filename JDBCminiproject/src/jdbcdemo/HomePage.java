package jdbcdemo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HomePage extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton insertBtn, deleteBtn, searchBtn, displayBtn;
    JPanel p1,p2,p3,p4,mp;

    HomePage()
    {
        insertBtn = new JButton("Insert");
        deleteBtn = new JButton("Delete");
        searchBtn = new JButton("Search");
        displayBtn = new JButton("Display");
        p1 =new JPanel(new GridLayout(1,1,10,10));
        p2 =new JPanel(new GridLayout(1,1,10,10));
        p3 =new JPanel(new GridLayout(1,1,10,10));
        p4 =new JPanel(new GridLayout(1,1,10,10));
        mp =new JPanel(new GridLayout(4,1,10,10));
        setLayout(new FlowLayout());
        p1.add(insertBtn);
        p2.add(deleteBtn);
        p3.add(searchBtn);
        p4.add(displayBtn);
        mp.add(p1);
        mp.add(p2);
        mp.add(p3);
        mp.add(p4);
        add(mp);
        setTitle("HOME Page");
        setBounds(100,100,300,300);
        setVisible(true);
        searchBtn.addActionListener(this);
		insertBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        displayBtn.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource()==insertBtn)
	{
		this.setVisible(false);
		new InsertPage();
	}
	if(e.getSource()==deleteBtn)
	{
		this.setVisible(false);
		new DeletePage();
	}
	if(e.getSource()==searchBtn)
	{
		this.setVisible(false);
		new SearchPage();
	}
	if(e.getSource()==displayBtn)
		{
			this.setVisible(false);
			new loginpage();
	}
}
public static void main(String[] args)
{
	new HomePage();
}
}
