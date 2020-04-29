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
import app_third.Mang_Teach;

public class Teach_Add implements ActionListener 
{
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rowptr; 
		
	JFrame frmid;
	JLabel ltname,ltpost,ltsalary,ltdoj,ltid,schnm,pi;
	JPanel idpnl,schpnl,btnpnl;
	JTextField ttname,ttpost,ttsalary,ttid,ttdoj;
	JButton btninsert,btnclear,btnback;
	ImageIcon i;
	Font f,f1;
		
	public void IdGUI()
	{
			frmid =  new JFrame("Teacher Details");
			
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
			
			ltid = new JLabel("TEACHER ID: ");
			ltid.setBounds(50, 95, 250, 30);
			ltid.setFont(f1);
			pi.add(ltid);
			ltname = new JLabel("NAME: ");
			ltname.setBounds(490, 95, 110, 30);
			ltname.setFont(f1);
			pi.add(ltname);
			ltsalary = new JLabel("SALARY: ");
			ltsalary.setBounds(50, 195, 110, 30);
			ltsalary.setFont(f1);
			pi.add(ltsalary);
			ltdoj = new JLabel("DATE OF JOIN: ");
			ltdoj.setBounds(450, 195, 160, 30);
			ltdoj.setFont(f1);
			pi.add(ltdoj);
			ltpost = new JLabel("POST: ");
			ltpost.setBounds(50, 295, 110, 30);
			ltpost.setFont(f1);
			pi.add(ltpost);

			ttid = new JTextField(10);
			ttid.setBounds(300, 95, 110, 30);
			ttid.setFont(f1);
			pi.add(ttid);
			ttname =new  JTextField(20);
			ttname.setBounds(610, 95, 110, 30);
			ttname.setFont(f1);
			pi.add(ttname);
			ttsalary = new JTextField(10);
			ttsalary.setBounds(180, 195, 110, 30);
			ttsalary.setFont(f1);
			pi.add(ttsalary);
			ttdoj = new JTextField(10);
			ttdoj.setBounds(630, 195, 110, 30);
			ttdoj.setFont(f1);
			pi.add(ttdoj);
			ttpost = new JTextField(100);
			ttpost.setBounds(180, 295, 300, 30);
			ttpost.setFont(f1);
			pi.add(ttpost);
			
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
	public void actionPerformed(ActionEvent control) 
	{			
			if(control.getActionCommand()== "INSERT")
			{
				stdInsert();
			}else if(control.getActionCommand()== "CLEAR")
			{
				ttname.setText(" ");
				ttpost.setText(" ");
				ttdoj.setText(" ");
			}else if(control.getActionCommand()== "BACK")
			{
				Mang_Teach mt = new Mang_Teach();
				mt.CreateGUI();
				frmid.dispose();
			}
	}
	private void stdInsert() 
	{		
			Database_Common db = new Database_Common();
			db.Teach_Database();
			try {
				rowptr = db.rpreturn(rowptr);
				rowptr.moveToInsertRow();

				int tid = Integer.parseInt(ttid.getText());
				String tn = ttname.getText();
				String tp = ttpost.getText();
				double tsal = Double.parseDouble(ttsalary.getText());
				String doj = ttdoj.getText();

				//giving the position in virtual table 
				rowptr.updateInt(1,tid);
				rowptr.updateString(2, tn);
				rowptr.updateDouble(3, tsal);
				rowptr.updateString(4, doj);
				rowptr.updateString(5, tp);
					
				rowptr.insertRow();
				
				JOptionPane.showMessageDialog(frmid,"Record Inserted Successfully");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
}