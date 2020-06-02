import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame{
	JPanel c;
	JButton btnAdd,btnView,btnUpdate,btnDelete;
	MainFrame(){
		c =new JPanel(new GridLayout(2,2,10,10));
		c.setBounds(100,80,200,200);
		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		c.add(btnAdd);	c.add(btnView);	c.add(btnUpdate);	c.add(btnDelete);
		add(c);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				AddFrame a=new AddFrame();
				dispose();
			}
		});
		
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				ViewFrame v=new ViewFrame();
				dispose();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				UpdateFrame u=new UpdateFrame();
				dispose();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				DeleteFrame d=new DeleteFrame();
				dispose();
			}
		});

		setTitle("EMSystem");
		setSize(400,400);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String args[]){
		MainFrame m =new MainFrame();
	}
}