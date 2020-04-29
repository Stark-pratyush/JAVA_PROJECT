package app_ASD;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app_third.*;

public class Teach_Search implements ActionListener{
	
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rowptr; 
	
	JFrame frsearch;
	JLabel ltid;
	JTextField ttid;
	JButton ok;
	JLabel lsid,pi;
	JTextField tsid;
	FlowLayout d;
	ImageIcon i;
	Font f,f1; 
	JFrame frmid;
	JLabel lsname,lsage,lsclass,lsadd,lsadmin;
	JLabel tsname,tsage,tsclass,tsadd,tsadmin;
	JLabel schnm;
	JPanel idpnl,schpnl,btnpnl;
	JButton btndelete,btnback;
	
	public void Search()
	{
		frsearch = new JFrame("Search");
		
		ltid = new JLabel("Teacher ID:-");
		
		ttid = new JTextField(10);
		
		ok = new JButton("Search");
		ok.addActionListener(this);
		
		d = new FlowLayout();
		
		frsearch.add(ltid);
		frsearch.add(ttid);
		frsearch.add(ok);
		frsearch.setLayout(d);;
		frsearch.setSize(300,300);
		frsearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frsearch.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent control) 
	{	
		if(control.getActionCommand()== "Search")
		{
			rcrSearch();
		}
		else if(control.getActionCommand()== "DELETE")
		{
			stdDelete();
		}
		else if(control.getActionCommand()== "BACK")
		{
			Mang_Teach mf = new Mang_Teach();
			mf.CreateGUI();
			frmid.dispose();
		}
		
	} 
	public void Teach_Database()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/schoolapp", "root", "8669");
		
			String msg = "select * from teacher";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rowptr = stmt.executeQuery(msg);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void rcrSearch() {
		Teach_Database();
		if (ttid.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(frsearch, "We need cid to search");
		} else {
			int id = Integer.parseInt(ttid.getText());

			String msg = "select * from teacher where tid = ?";

			try {
				pstmt = con.prepareStatement(msg);
				pstmt.setInt(1, id);
				rowptr = pstmt.executeQuery();
				if (rowptr.next()) {
					frsea(rowptr);
				} else {
					JOptionPane.showMessageDialog(frsearch, " is not in our database");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void stdDelete() {
		try {
				String id =ttid.getText();
				String q = "delete from teacher where tid = ' "+id+" ' ";
				int x=stmt.executeUpdate(q);
				//ptr1.deleteRow();
				if( x>0 )
					JOptionPane.showMessageDialog(frmid,"Record Deleted Successfully");
				else
					JOptionPane.showMessageDialog(frmid,"Error!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
private void frsea(ResultSet rp) {
		
		frsearch.dispose();
		
		frmid =  new JFrame("Student Details");
		
		f = new Font("Serif",Font.BOLD,40);
		f1= new Font("Serif",Font.BOLD,20);
		
		i = new ImageIcon("src\\Untitled.jpg");
		Image img = i.getImage();
		Image temp_img = img.getScaledInstance(860, 520, Image.SCALE_SMOOTH);
		i = new ImageIcon(temp_img);
		
		pi = new JLabel(i);
		
		schpnl = new JPanel();
		schpnl.setBounds(0, 0,845,90);
		schpnl.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
		
		schnm =new JLabel("SCHOOL NAME");
		schnm.setBounds(250, 20, 550, 50);
		schnm.setForeground(Color.BLUE);
		schnm.setFont(f);
		schpnl.add(schnm);
		pi.add(schpnl);
		
		idpnl = new JPanel();
		idpnl.setBounds(0, 80, 845, 350);
		idpnl.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
		
		lsadmin = new JLabel("TEACHER ID:  ");
		lsadmin.setBounds(50, 95, 250, 30);
		lsadmin.setFont(f1);
		pi.add(lsadmin);
		lsname = new JLabel("NAME: ");
		lsname.setBounds(490, 95, 110, 30);
		lsname.setFont(f1);
		pi.add(lsname);
		lsclass = new JLabel("SALARY: ");
		lsclass.setBounds(50, 195, 110, 30);
		lsclass.setFont(f1);
		pi.add(lsclass);
		lsage = new JLabel("DATE OF JOIN: ");
		lsage.setBounds(430, 195, 150, 30);
		lsage.setFont(f1);
		pi.add(lsage);
		lsadd = new JLabel("POST: ");
		lsadd.setBounds(50, 295, 110, 30);
		lsadd.setFont(f1);
		pi.add(lsadd);

		tsadmin = new JLabel();
		try {
			tsadmin.setText(Integer.toString(rp.getInt("tid")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsadmin.setBounds(300, 95, 110, 30);
		tsadmin.setFont(f1);
		pi.add(tsadmin);
		tsname = new JLabel();
		try {
			tsname.setText(rp.getString("tname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsname.setBounds(610, 95, 110, 30);
		tsname.setFont(f1);
		pi.add(tsname);
		tsclass = new JLabel();
		try {
			tsclass.setText(Integer.toString(rp.getInt("tsalary")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsclass.setBounds(180, 195, 110, 30);
		tsclass.setFont(f1);
		pi.add(tsclass);
		tsage = new JLabel();
		try {
				tsage.setText(rp.getString("tdjoin"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsage.setBounds(610, 195, 110, 30);
		tsage.setFont(f1);
		pi.add(tsage);
		tsadd = new JLabel();
		try {
			tsadd.setText(rp.getString("tpost"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsadd.setBounds(180, 295, 300, 30);
		tsadd.setFont(f1);
		pi.add(tsadd);
		
		btnpnl = new JPanel();
		btnpnl.setBounds(0, 400, 845, 80);
		btnpnl.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
		
		btndelete = new JButton("DELETE");
		btndelete.addActionListener(this);
		btndelete.setBounds(300, 415, 120, 50);
		pi.add(btndelete);
		btnback = new JButton("BACK");
		btnback.addActionListener(this);
		btnback.setBounds(440, 415, 120, 50);
		pi.add(btnback);
		pi.add(btnpnl);
		pi.add(idpnl);
		frmid.add(pi);
		
		frmid.setSize(860,520);
		frmid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmid.setVisible(true);
		}
}