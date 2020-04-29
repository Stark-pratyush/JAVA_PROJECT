package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import app_first.Main;

public class Front_page implements ActionListener {

	JFrame frmi;
	JButton btnnp;
	JLabel ws, pii;
	ImageIcon i;

	public void CreateGUI() {

		Font f = new Font("Serif",Font.BOLD,40);
		frmi = new JFrame();
		frmi.setTitle("SCHOOL APP");

		i = new ImageIcon("src\\school-hd.jpg");
		Image img = i.getImage();
		Image temp_img = img.getScaledInstance(1000, 720, Image.SCALE_SMOOTH) ;
		i = new ImageIcon(temp_img);
		
		pii = new JLabel(i);

		ws = new JLabel("WELCOME   TO   SCHOOL");
		 ws.setBounds(265, 30, 550, 50);
		 ws.setForeground(Color.blue);
		 ws.setFont(f);
		
		btnnp = new JButton(">>");
		btnnp.setSize(1000, 25);
		btnnp.setBounds(0, 657, 1000, 25);
		btnnp.addActionListener(this);
		pii.add(btnnp);		

		pii.add(ws);

		frmi.add(pii);

		frmi.setSize(1000, 720);
		frmi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmi.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent controlref) {
		if (controlref.getActionCommand() == ">>") {
			Next_Page();
		}
	}

	public void Next_Page() {
		Main m = new Main();
		m.CreateGUI();
		frmi.dispose();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Front_page lmsobj = new Front_page();
		lmsobj.CreateGUI();
	}
}