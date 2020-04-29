package app_second;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import app_first.Main;
import app_third.*;

public class Mang_Front implements ActionListener{	
	
	JFrame frmi;
	JButton btnstu,btnteach,btnlib,btnback;
	JLabel ws,sn,pi;
	JPanel manstu,tsm;
	ImageIcon i;
	Font f,f1;
	
	public void CreateGUI() {
	
		frmi = new JFrame();
		frmi.setTitle("SCHOOL APP");
		f = new Font("Serif",Font.BOLD,40);
		f1= new Font("Serif",Font.BOLD,25);
		
		i = new ImageIcon("src\\school-hd.jpg");
		Image img = i.getImage();
		Image temp_img = img.getScaledInstance(1000, 720, Image.SCALE_SMOOTH);
		i = new ImageIcon(temp_img);
		
		pi = new JLabel(i);
		
		manstu = new JPanel();
		manstu.setSize(1000,100);
		manstu.setBackground(new Color(0,0,0,0));
		manstu.setBounds(0, 0, 1000, 100);
		pi.add(manstu);
		
		ws = new JLabel("WELCOME   TO   SCHOOL");
		ws.setBounds(270, 0, 550, 100);
		ws.setForeground(Color.BLUE);
		ws.setFont(f);
		manstu.add(ws);
		 
		sn = new JLabel("SCHOOL NAME");
		sn.setBounds(395, 35, 550, 100);
		sn.setForeground(Color.BLUE);
		sn.setFont(f1);
		pi.add(sn); 
		
		tsm = new JPanel();
		tsm.setBounds(250, 200, 500, 400);
		tsm.setBackground(new Color(0,0,0,0));
		pi.add(tsm);
		
		btnstu = new JButton("STUDENT");
		btnstu.addActionListener(this);
		btnstu.setBounds(430, 370, 120, 50);
		pi.add(btnstu);
		
		btnteach = new JButton("TEACHER");
		btnteach.addActionListener(this);
		btnteach.setBounds(430, 260, 120, 50);
		pi.add(btnteach);
		
		btnlib = new JButton("LIBRARY");
		btnlib.addActionListener(this);
		btnlib.setBounds(430, 480, 120, 50);
		pi.add(btnlib);
		
		btnback = new JButton("BACK");
		btnback.addActionListener(this);
		btnback.setBounds(0, 657, 1000, 25);
		pi.add(btnback);
		 
		 frmi.add(pi);
		
		frmi.setSize(1000, 720);
		frmi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmi.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent controlref) {
		 if (controlref.getActionCommand() == "TEACHER") {
			 Next_Page();
		}
		else if (controlref.getActionCommand() == "STUDENT") {
				 NextPage();
		}
		else if (controlref.getActionCommand() == "LIBRARY") {
			 nextPage();
		}
		else if (controlref.getActionCommand() == "BACK") {
			 Prev_Page();
		}
	}
	public void Prev_Page() {
		// TODO Auto-generated method stub
		Main mf = new Main();
		mf.CreateGUI();
		frmi.dispose();		
	}

	public void nextPage() {
		// TODO Auto-generated method stub
		Mang_Lib mf = new Mang_Lib();
		mf.CreateGUI();
		frmi.dispose();
	}

	public void Next_Page()
	{
		Mang_Teach mf = new Mang_Teach();
		mf.CreateGUI();
		frmi.dispose();
	}
	public void NextPage()
	{
		Mang_Stu mf = new Mang_Stu();
		mf.CreateGUI();
		frmi.dispose();
	}
}
