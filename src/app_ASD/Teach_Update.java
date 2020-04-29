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

public class Teach_Update implements ActionListener {
	
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rowptr; 
	JFrame frsearch;
	JLabel lsid,pi;
	JTextField tsid;
	JButton ok;
	FlowLayout d;
	ImageIcon i;
	Font f,f1; 
	JFrame frmid;
	JLabel lsname,lsage,lsclass,lsadd,lsadmin,lsbg;
	JTextField tsname,tsage,tsclass,tsadd,tsadmin,tsbg;
	JLabel schnm;
	JPanel idpnl,schpnl,btnpnl;
	JButton btnupdate,btnback;
	
	public void Search()
	{
		frsearch = new JFrame("Search");
		
		lsid = new JLabel("Teacher Id.:-");
		
		tsid = new JTextField(10);
		
		ok = new JButton("Search");
		ok.addActionListener(this);
		
		d = new FlowLayout();
		
		frsearch.add(lsid);
		frsearch.add(tsid);
		frsearch.add(ok);
		frsearch.setLayout(d);;
		frsearch.setSize(300,300);
		frsearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frsearch.setVisible(true);	
	}

	public void Stu_Database()
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
			//rowptr.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void rcrSearch() {
		Stu_Database();
		if (tsid.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(frsearch, "We need Adminssion Number  to search");
		} else {
			int id = Integer.parseInt(tsid.getText());

			String msg = "select * from teacher where tid = ?";

			try {
				pstmt = con.prepareStatement(msg);
				pstmt.setInt(1, id);
				rowptr = pstmt.executeQuery();
				if (rowptr.next()) {
					IdGUI(rowptr);
				} else {
					JOptionPane.showMessageDialog(frsearch,"Result not found!! \n Try another.");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent control) 
	{	
		if(control.getActionCommand()== "Search")
		{
			rcrSearch();
		}
		else if(control.getActionCommand()== "UPDATE")
		{
			Update();
		}
		else if(control.getActionCommand()== "BACK")
		{
			Mang_Teach mf = new Mang_Teach();
			mf.CreateGUI();
			frmid.dispose();
		}
	} 
	public void IdGUI(ResultSet rp) {
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
		
		lsadmin = new JLabel("TEACHER ID: ");
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

		tsadmin = new JTextField(10);
		try {
			tsadmin.setText(Integer.toString(rp.getInt("tid")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsadmin.setBounds(300, 95, 110, 30);
		tsadmin.setFont(f1);
		pi.add(tsadmin);
		tsname =new  JTextField(20);
		try {
			tsname.setText(rp.getString("tname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsname.setBounds(610, 95, 110, 30);
		tsname.setFont(f1);
		pi.add(tsname);
		tsclass = new JTextField(10);
		try {
			tsclass.setText(Double.toString(rp.getDouble("tsalary")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsclass.setBounds(180, 195, 110, 30);
		tsclass.setFont(f1);
		pi.add(tsclass);
		tsage = new JTextField(10);
		try {
			tsage.setText(rp.getString("tdjoin"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tsage.setBounds(610, 195, 110, 30);
		tsage.setFont(f1);
		pi.add(tsage);
		tsadd = new JTextField(10);
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
		
		btnupdate = new JButton("UPDATE");
		btnupdate.addActionListener(this);
		btnupdate.setBounds(300, 415, 120, 50);
		pi.add(btnupdate);
		btnback = new JButton("BACK");
		btnback.addActionListener(this);
		btnback.setBounds(450, 415, 120, 50);
		pi.add(btnback);
		pi.add(btnpnl);
		pi.add(idpnl);
		frmid.add(pi);
		
		frmid.setSize(860,520);
		frmid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmid.setVisible(true);	
	}
	
	private void Update() {
		try {
				String id =tsadmin.getText();
				String n =tsname.getText();
				String c =tsclass.getText();
				String a =tsage.getText();
				String add =tsadd.getText();
				String q = "update teacher set tname = ' "+n+ " ', tsalary = "+c+" ,tdjoin = ' "+a+" ' ,tpost = ' "+add+" ' where tid = ' "+id+" ' ";
				int x=stmt.executeUpdate(q);
				//ptr1.deleteRow();
				if( x>0 )
					JOptionPane.showMessageDialog(frmid,"Record Updated Successfully");
				else
					JOptionPane.showMessageDialog(frmid,"Error!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
