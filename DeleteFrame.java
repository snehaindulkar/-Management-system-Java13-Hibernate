import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteFrame extends JFrame{
	
	Container c;
	JLabel lblId;
	JTextField txtId;
	JButton btnDelete,btnBack;

	DeleteFrame(){
		c=getContentPane();
		c.setLayout(new FlowLayout());
				
		lblId =new JLabel("Enter Employee-id: ");
		c.add(lblId);
		
		txtId= new JTextField(20);
		c.add(txtId);
		
		btnDelete= new JButton("Delete");
		btnBack= new JButton("Back");
		
		c.add(btnDelete);
		c.add(btnBack);

		
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Configuration cfg =new Configuration();
				cfg.configure("hibernate.cfg.xml");

				SessionFactory sfact = cfg.buildSessionFactory();

				Session session =sfact.openSession();

				Transaction t =null ;
				try{
					System.out.println("Begin");
					t= session.beginTransaction();
					int eid =Integer.parseInt(txtId.getText());
					Employee e = (Employee)session.get(Employee.class,eid);
					if(e != null)
					{
						e.setEid(eid);
						session.delete(e);
						t.commit();
						JOptionPane.showMessageDialog(new JDialog(),"Record Deleted");
						txtId.setText("");
						txtId.requestFocus();
					}
					else	
					{
						JOptionPane.showMessageDialog(new JDialog(),"No data found");
					}	
			
				}
				catch(Exception e){
					t.rollback();
					JOptionPane.showMessageDialog(new JDialog(),"No data found");
				}
				finally{
					session.close();
				}
			}
		});

		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			MainFrame m = new MainFrame();
			dispose();
			}
		});

		
		setTitle("Add");
		setSize(400,400);
		//setLayout(null);
		//setResizable(false);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
public static void main(String args[]){
DeleteFrame df = new DeleteFrame();
}
}
