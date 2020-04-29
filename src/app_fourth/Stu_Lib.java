package app_fourth;

import java.awt.Color;
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
import app_second.Lib_Front;

public class Stu_Lib implements ActionListener {
	
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rowptr; 
	
	JFrame frmid;
	JLabel lbname,lbid,lbauth,lbprice,pi,lbdoi,lbe;
	JLabel schnm;
	JPanel idpnl,schpnl,btnpnl;
	JTextField tbname,tbid,tbauth,tbprice,tbdoi,tbe;
	JButton btninsert,btnclear,btnback;
	Font f,f1;
	ImageIcon i;
	public void IdGUI() {
		frmid =  new JFrame("Book Issue Details");
		
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
		
		lbe = new JLabel("ENTRY NO: ");
		lbe.setBounds(250, 95, 210, 30);
		lbe.setFont(f1);
		pi.add(lbe);
		lbid = new JLabel("BOOK ID: ");
		lbid.setBounds(300, 145, 250, 30);
		lbid.setFont(f1);
		pi.add(lbid);
		lbname = new JLabel("ADMISSION NO: ");
		lbname.setBounds(250, 195, 200, 30);
		lbname.setFont(f1);
		pi.add(lbname);
		lbauth = new JLabel("BOOK NAME: ");
		lbauth.setBounds(280, 245, 150, 30);
		lbauth.setFont(f1);
		pi.add(lbauth);
		lbprice = new JLabel("AUTHOR: ");
		lbprice.setBounds(300, 295, 110, 30);
		lbprice.setFont(f1);
		pi.add(lbprice);
		lbdoi = new JLabel("DATE OF ISSUE: ");
		lbdoi.setBounds(250, 345, 210, 30);
		lbdoi.setFont(f1);
		pi.add(lbdoi);
		
		tbe = new JTextField(10);
		tbe.setBounds(450, 95, 110, 30);
		tbe.setFont(f1);
		pi.add(tbe);
		tbid = new JTextField(10);
		tbid.setBounds(450, 145, 110, 30);
		tbid.setFont(f1);
		pi.add(tbid);
		tbname =new  JTextField(20);
		tbname.setBounds(450, 195, 250, 30);
		tbname.setFont(f1);
		pi.add(tbname);
		tbauth = new JTextField(10);
		tbauth.setBounds(450, 245, 210, 30);
		tbauth.setFont(f1);
		pi.add(tbauth);
		tbprice = new JTextField(10);
		tbprice.setBounds(450, 295, 110, 30);
		tbprice.setFont(f1);
		pi.add(tbprice);
		tbdoi = new JTextField(10);
		tbdoi.setBounds(450, 345, 110, 30);
		tbdoi.setFont(f1);
		pi.add(tbdoi);
		
		btnpnl = new JPanel();
		btnpnl.setBounds(0, 400, 845, 80);
		btnpnl.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
		
		btninsert = new JButton("INSERT");
		btninsert.addActionListener(this);
		btninsert.setBounds(250, 415, 120, 50);
		pi.add(btninsert);
		btnclear = new JButton("CLEAR");
		btnclear.addActionListener(this);
		btnclear.setBounds(380, 415, 120, 50);
		pi.add(btnclear);
		btnback = new JButton("BACK");
		btnback.addActionListener(this);
		btnback.setBounds(510, 415, 120, 50);
		pi.add(btnback);
		pi.add(btnpnl);
		pi.add(idpnl);
		frmid.add(pi);
		
		frmid.setSize(860,518);
		frmid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmid.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent control) {
		// TODO Auto-generated method stub
		
		if(control.getActionCommand()== "INSERT")
		{
			stdInsert();
		}else if(control.getActionCommand()== "CLEAR")
		{
			tbid.setText(" ");
			tbname.setText(" ");
			tbauth.setText(" ");
			tbprice.setText(" ");
		}else if(control.getActionCommand()== "BACK")
		{
			Lib_Front ml = new Lib_Front();
			ml.CreateGUI();
			frmid.dispose();
		}
	}
	
	private void stdInsert() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/schoolapp", "root", "root");
			String msg = "select * from sissued";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rowptr = stmt.executeQuery(msg);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rowptr.moveToInsertRow();
			
			
			int bid = Integer.parseInt(tbid.getText());
			int bn =Integer.parseInt( tbname.getText());
			String ba = tbauth.getText();
			String bprice = tbprice.getText();
			String doi = tbdoi.getText();
			int e =Integer.parseInt(tbe.getText());

			rowptr.updateInt(1,bid);
			rowptr.updateInt(2, bn);
			rowptr.updateString(3, ba);
			rowptr.updateString(4, bprice);
			rowptr.updateString(5, doi);
			rowptr.updateInt(6, e);
			
			rowptr.insertRow();
			
			JOptionPane.showMessageDialog(frmid,"Record Inserted Successfully");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
