package app_ASD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

import Database.Database_Common;
import app_third.Mang_Stu;

public class Stu_Add implements ActionListener 
{
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rowptr; 
	
	JFrame frmid;
	JLabel lsname,lsage,lsclass,lsadd,lsadmin,lsbg,lphoto,schnm,pi;
	JPanel imgpnl,idpnl,schpnl,btnpnl;
	JTextField tsname,tsage,tsclass,tsadd,tsadmin,tsbg;
	JButton btninsert,btnclear,btnback;
	Font f,f1;
	ImageIcon i;
	public void IdGUI() {
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
		
		lsadmin = new JLabel("ADMISSION NUMBER: ");
		lsadmin.setBounds(50, 95, 250, 30);
		lsadmin.setFont(f1);
		pi.add(lsadmin);
		lsname = new JLabel("NAME: ");
		lsname.setBounds(490, 95, 110, 30);
		lsname.setFont(f1);
		pi.add(lsname);
		lsclass = new JLabel("CLASS: ");
		lsclass.setBounds(50, 195, 110, 30);
		lsclass.setFont(f1);
		pi.add(lsclass);
		lsage = new JLabel("AGE: ");
		lsage.setBounds(490, 195, 110, 30);
		lsage.setFont(f1);
		pi.add(lsage);
		lsadd = new JLabel("ADDRESS: ");
		lsadd.setBounds(50, 295, 110, 30);
		lsadd.setFont(f1);
		pi.add(lsadd);
		lsbg = new JLabel("BLOOD GROUP: ");
		lsbg.setBounds(490, 295, 180, 30);
		lsbg.setFont(f1);
		pi.add(lsbg);

		tsadmin = new JTextField(10);
		tsadmin.setBounds(300, 95, 110, 30);
		tsadmin.setFont(f1);
		pi.add(tsadmin);
		tsname =new  JTextField(20);
		tsname.setBounds(610, 95, 110, 30);
		tsname.setFont(f1);
		pi.add(tsname);
		tsclass = new JTextField(10);
		tsclass.setBounds(180, 195, 110, 30);
		tsclass.setFont(f1);
		pi.add(tsclass);
		tsage = new JTextField(10);
		tsage.setBounds(610, 195, 110, 30);
		tsage.setFont(f1);
		pi.add(tsage);
		tsadd = new JTextField(100);
		tsadd.setBounds(180, 295, 300, 30);
		tsadd.setFont(f1);
		pi.add(tsadd);
		tsbg = new JTextField(10);
		tsbg.setBounds(660, 295, 110, 30);
		tsbg.setFont(f1);
		pi.add(tsbg);
		
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
		
		frmid.setSize(860,520);
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
			tsname.setText(" ");
			tsclass.setText(" ");
			tsadd.setText(" ");
			tsage.setText(" ");
			tsbg.setText(" ");
		}
		else if(control.getActionCommand()== "BACK")
		{
			Mang_Stu a=new Mang_Stu();
			a.CreateGUI();
			frmid.dispose();
		}
	}
	private void stdInsert() {
		
		Database_Common db = new Database_Common();
		db.Stu_Database();
		try {
			rowptr = db.rpreturn(rowptr);
			rowptr.moveToInsertRow();

			int sid = Integer.parseInt(tsadmin.getText());
			String sn = tsname.getText();
			String sc = tsclass.getText();
			String sadd = tsadd.getText();
			int sage = Integer.parseInt(tsage.getText());
			String sbg = tsbg.getText();
			
			//giving the position in virtual table 
			rowptr.updateInt(1,sid);
			rowptr.updateString(2, sn);
			rowptr.updateString(3, sadd);
			rowptr.updateInt(4, sage);
			rowptr.updateString(5, sc);
			rowptr.updateString(6, sbg);
					
			rowptr.insertRow();
		
			JOptionPane.showMessageDialog(frmid,"Record Inserted Successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}


