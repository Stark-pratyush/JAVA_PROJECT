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
import app_third.Mang_Lib;

public class Lib_Add implements ActionListener 
	{
		Connection con;
		Statement stmt;
		PreparedStatement pstmt;
		ResultSet rowptr; 
		
		JFrame frmid;
		JLabel lbname,lbid,lbauth,lbprice,pi;
		JLabel schnm;
		JPanel idpnl,schpnl,btnpnl;
		JTextField tbname,tbid,tbauth,tbprice;
		JButton btninsert,btnclear,btnback;
		Font f,f1;
		ImageIcon i;
		public void IdGUI() {
			frmid =  new JFrame("Book Details");
			
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
			
			lbid = new JLabel("BOOK ID: ");
			lbid.setBounds(300, 95, 250, 30);
			lbid.setFont(f1);
			pi.add(lbid);
			lbname = new JLabel("BOOK NAME: ");
			lbname.setBounds(300, 145, 150, 30);
			lbname.setFont(f1);
			pi.add(lbname);
			lbauth = new JLabel("AUTHOR: ");
			lbauth.setBounds(300, 195, 110, 30);
			lbauth.setFont(f1);
			pi.add(lbauth);
			lbprice = new JLabel("PRICE: ");
			lbprice.setBounds(300, 245, 110, 30);
			lbprice.setFont(f1);
			pi.add(lbprice);

			tbid = new JTextField(10);
			tbid.setBounds(450, 95, 110, 30);
			tbid.setFont(f1);
			pi.add(tbid);
			tbname =new  JTextField(20);
			tbname.setBounds(450, 145, 250, 30);
			tbname.setFont(f1);
			pi.add(tbname);
			tbauth = new JTextField(10);
			tbauth.setBounds(450, 195, 210, 30);
			tbauth.setFont(f1);
			pi.add(tbauth);
			tbprice = new JTextField(10);
			tbprice.setBounds(450, 245, 110, 30);
			tbprice.setFont(f1);
			pi.add(tbprice);
			
			btnpnl = new JPanel();
			btnpnl.setBounds(0, 300, 845, 80);
			btnpnl.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
			
			btninsert = new JButton("INSERT");
			btninsert.addActionListener(this);
			btninsert.setBounds(250, 315, 120, 50);
			pi.add(btninsert);
			btnclear = new JButton("CLEAR");
			btnclear.addActionListener(this);
			btnclear.setBounds(380, 315, 120, 50);
			pi.add(btnclear);
			btnback = new JButton("BACK");
			btnback.addActionListener(this);
			btnback.setBounds(510, 315, 120, 50);
			pi.add(btnback);
			pi.add(btnpnl);
			pi.add(idpnl);
			frmid.add(pi);
			
			frmid.setSize(860,418);
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
				tbname.setText(" ");
				tbname.setText(" ");
				tbprice.setText(" ");
			}else if(control.getActionCommand()== "BACK")
			{
				Mang_Lib ml = new Mang_Lib();
				ml.CreateGUI();
				frmid.dispose();
			}
		}
		private void stdInsert() {
			
			Database_Common db = new Database_Common();
			db.Lib_Database();
			try {
				rowptr = db.rpreturn(rowptr);
				rowptr.moveToInsertRow();

				int bid = Integer.parseInt(tbid.getText());
				String bn = tbname.getText();
				String ba = tbauth.getText();
				double bprice = Double.parseDouble(tbprice.getText());
				
				//giving the position in virtual table 
				rowptr.updateInt(1,bid);
				rowptr.updateString(2, bn);
				rowptr.updateString(3, ba);
				rowptr.updateDouble(4, bprice);
				
				rowptr.insertRow();
				
				JOptionPane.showMessageDialog(frmid,"Record Inserted Successfully");	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
}
