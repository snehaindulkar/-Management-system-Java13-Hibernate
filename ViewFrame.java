import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.*;
import org.hibernate.cfg.*;

class ViewFrame extends JFrame{
	Container c;
	JTextArea ta;
	JButton btnBack;

	ViewFrame(){
		c=getContentPane();
		c.setLayout(new FlowLayout());
		ta= new JTextArea(3,2);
		btnBack =new JButton("Back");

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact = cfg.buildSessionFactory();
		Session session =sfact.openSession();
		StringBuffer sb =new StringBuffer();
		try{
			List<Employee> emp = new ArrayList<>();
			emp = session.createQuery("from Employee").list();

			for(Employee e: emp)
				sb.append("Emp ID: "+ e.getEid() + "\tEmp Name: "+ e.getEname()+"\n");
			System.out.println("End");		
		}				
		catch(Exception e){
			JOptionPane.showMessageDialog(new JDialog(),"Error : "+e);
		}
		finally{
			ta.setText(sb.toString());
			session.close();
		}
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			MainFrame m = new MainFrame();
			dispose();
			}
		});
		c.add(ta);
		c.add(btnBack);
		setTitle("View");
		setSize(400,400);
		//setLayout(null);
		//setResizable(false);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
public static void main(String args[]){
ViewFrame vf= new ViewFrame();
}
}