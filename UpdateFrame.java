import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.hibernate.*;
import org.hibernate.cfg.*;


class UpdateFrame extends JFrame{
	Container c;
	JLabel lblId,lblName,lblSal;
	JTextField txtId,txtName,txtSal;
	JButton btnUpdate,btnBack;
	UpdateFrame(){
	
		c=getContentPane();
		c.setLayout(new FlowLayout());
				
		lblId =new JLabel("Enter Employee-id: ");
		c.add(lblId);
		
		txtId= new JTextField(20);
		c.add(txtId);
		
		lblName = new JLabel("Enter Emp-Name: ");
		c.add(lblName);
		
		txtName = new JTextField(20);
		c.add(txtName);
		
		lblSal = new JLabel("Enter Emp-salary: ");
		c.add(lblSal);
		
		txtSal = new JTextField(20);
		c.add(txtSal);
	
		btnUpdate= new JButton("Update");
		btnBack= new JButton("Back");
		
		c.add(btnUpdate);
		c.add(btnBack);
		
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Configuration cfg =new Configuration();
				cfg.configure("hibernate.cfg.xml");

				SessionFactory sfact =cfg.buildSessionFactory();

				Session session =sfact.openSession();
				Transaction t =null;

				try{
					System.out.println("Begin");
					t = session.beginTransaction();
					int eid =Integer.parseInt(txtId.getText());
					Employee e = (Employee)session.get(Employee.class,eid);

					if(e!=null){
						String name= txtName.getText(); 
						e.setEname(name);
						int sal = Integer.parseInt(txtSal.getText());
						e.setSal(sal);
						session.save(e);
						t.commit();
						JOptionPane.showMessageDialog(new JDialog(),"Record Updated");
						txtId.requestFocus();
					}
				}
				catch(Exception e){
					t.rollback();
					JOptionPane.showMessageDialog(new JDialog(),"Error: "+ e);
				}
				finally {
					session.close();
				}
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				MainFrame m=new MainFrame();
				dispose();
			}
		});

		setTitle("Update");
		setSize(400,400);
		//setLayout(null);
		//setResizable(false);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

public static void main(String args[]){
UpdateFrame uf = new UpdateFrame();
}
}

