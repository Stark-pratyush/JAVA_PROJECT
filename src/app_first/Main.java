package app_first;

 import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import app_second.Lib_Front;
import app_second.Mang_Front;
import app_third.Teach_Stu;

public class Main  implements ActionListener {

	JFrame frmi;
	JButton btnstu,btnman,btnteach,btnlib;
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
		
		btnman = new JButton("MANAGEMENT");
		btnman.addActionListener(this);
		btnman.setBounds(430, 260, 120, 50);
		pi.add(btnman);
		
		btnteach = new JButton("TEACHER");
		btnteach.addActionListener(this);
		btnteach.setBounds(430, 370, 120, 50);
		pi.add(btnteach);
		
		btnlib = new JButton("LIBRARY");
		btnlib.addActionListener(this);
		btnlib.setBounds(430, 480, 120, 50);
		pi.add(btnlib);
		
		 frmi.add(pi);
		 
		frmi.setBackground(Color.GREEN);
		frmi.setSize(1000, 720);
		frmi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmi.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent controlref) {
		 if (controlref.getActionCommand() == "MANAGEMENT") {
			 	Mang_Front mf = new Mang_Front();
				mf.CreateGUI();
				frmi.dispose();
		}
		 else if (controlref.getActionCommand() == "TEACHER") {
			    Teach_Stu tf = new Teach_Stu();
			    tf.Search();
				frmi.dispose();
			}
		 else if (controlref.getActionCommand() == "LIBRARY") {
			 Lib_Front lf = new Lib_Front();
			 lf.CreateGUI();
			 frmi.dispose();
		}
	}
}