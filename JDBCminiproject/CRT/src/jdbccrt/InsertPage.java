package jdbccrt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InsertPage extends JFrame implements ActionListener
{
	JLabel rollLabel,nameLabel, addressLabel;
    JPanel p1,p2,p3,p4,mp;
    JButton insertBtn, clearBtn, homeBtn;
    JTextField rollField, nameField, addressField;
InsertPage()
{
        rollLabel = new JLabel("Roll:");
        rollField = new JTextField(20);
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        insertBtn = new JButton("Insert");
        clearBtn = new JButton("Clear");
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
        p4.add(insertBtn);
        p4.add(clearBtn);
        p4.add(homeBtn);
        mp.add(p1);
        mp.add(p2);
        mp.add(p3);
        mp.add(p4);
        add(mp);
        setTitle("INSERT Page");
        setBounds(100,100,500,500);
        setVisible(true);
        insertBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        homeBtn.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
	    {
		if(e.getSource()==insertBtn)
		{
		String a=rollField.getText();
		String b=nameField.getText();
		String c=addressField.getText();
		}
		if(e.getSource()==clearBtn)
		{
			rollField.setText(" ");
			nameField.setText(" ");
			addressField.setText(" ");
		}
	  if(e.getSource()==homeBtn)
			{
				this.setVisible(false);
				new HomePage();
	    }
	}
public static void main(String[] args)
{
	new InsertPage();
}
}
