import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

//import java.util.regex.*;

class AddFrame extends JFrame{

	Container c;
	JLabel lblId,lblName;
	JTextField txtId,txtName;
	JButton btnSave,btnBack;
	AddFrame(){
	
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
		
		btnSave= new JButton("Save");
		btnBack= new JButton("Back");
		
		c.add(btnSave);
		c.add(btnBack);

		btnSave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae){
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();
			Session session =sfact.openSession();
			Transaction t=null;
			try{
				System.out.println("begin");
				t = session.beginTransaction();
				Employee s= new Employee();
				int eid         = Integer.parseInt(txtId.getText());
				s.setEid(eid);	

				String name   = txtName.getText();
				s.setEname(name);

				session.save(s);
				t.commit();
				JOptionPane.showMessageDialog(new JDialog(), "+  records inserted");
				System.out.println("end");
			}
			catch(Exception e){
				 if (t != null) 
				{
            					t.rollback();
       			 	}
				JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
			}
			finally{
				//if (t != null){this.releaseSession(t);}
				session.close();
				}
		MainFrame m=new MainFrame();
				dispose();
			}		
		});
		
		

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				MainFrame m=new MainFrame();
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
AddFrame af = new AddFrame();
}
}

