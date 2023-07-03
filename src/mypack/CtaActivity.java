package mypack;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

class InvalidIAMarks extends Exception {
	public InvalidIAMarks(String message) {
		super(message);

	}
}

class CtaMarksException extends Exception {
	public CtaMarksException(String message) {
		super(message);

	}
}

class SeeMarksException extends Exception {
	public SeeMarksException(String message) {
		super(message);

	}
}

class CieMarksException extends Exception {
	public CieMarksException(String message) {
		super(message);

	}
}

class Form extends JFrame implements ActionListener {

	Container ContentPane;
	JButton b;
	JPanel p;
	JLabel l, l1, l2, l3, l4, l5, l6, l7, l8;
	JTextField t, t1, t2, t3, t4;

	public int IA1, IA2, IA3, CTA;
	public double SEE;

	void Student(int IA1, int IA2, int IA3, int SEE, int CTA) {
		this.IA1 = IA1;
		this.IA2 = IA2;
		this.IA3 = IA3;
		this.CTA = CTA;
		this.SEE = SEE;
	}

	Form(String title) {
		super(title);
		ContentPane = this.getContentPane();
		b = new JButton("Calculate");
		b.setSize(10, 5);
		l6 = new JLabel("Total Maks:");
		l7 = new JLabel("Grade:");
		p = new JPanel();
		p.setLayout(new GridLayout(7, 2, 10, 10));

		l = new JLabel("Grade Calculator");
		l1 = new JLabel("Enter IA1 marks:");
		t = new JTextField(4);
		l2 = new JLabel("Enter IA2 marks:");
		t1 = new JTextField(4);
		l3 = new JLabel("Enter IA3 marks:");
		t2 = new JTextField(4);
		l4 = new JLabel("Enter CTA marks:");
		t3 = new JTextField(4);
		l5 = new JLabel("Enter SEE marks:");
		t4 = new JTextField(4);

		b.addActionListener(this);
		p.add(l);
		p.add(l1);
		p.add(t);
		p.add(l2);
		p.add(t1);
		p.add(l3);
		p.add(t2);
		p.add(l4);
		p.add(t3);
		p.add(l5);
		p.add(t4);

		p.add(b);

		p.add(l6);
		p.add(l7);

		ContentPane.add(p, BorderLayout.SOUTH);
		ContentPane.add(l, BorderLayout.NORTH);
		// contentPane.add(l7,BorderLayout.EAST);
		// contentpane.add(l8,BorderLayout.WEST);

	}

	public void actionPerformed(ActionEvent e) {

		int IA1 = Integer.parseInt(t.getText());
		int IA2 = Integer.parseInt(t1.getText());
		int IA3 = Integer.parseInt(t2.getText());
		int CTA = Integer.parseInt(t3.getText());
		int SEE = Integer.parseInt(t4.getText());

		try {
			if (IA1 < 0 || IA2 > 20 || IA2 < 0 || IA2 > 20 || IA3 < 0 || IA3 > 20)
				throw new InvalidIAMarks("IA marks should be between 0 and 20");
		} catch (InvalidIAMarks iae) {
			JOptionPane.showMessageDialog(p, iae.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}

		try {
			if (CTA < 0 || CTA > 10)
				throw new CtaMarksException("CTA marks should be between 0 and 10");
		} catch (CtaMarksException cme) {
			JOptionPane.showMessageDialog(p, cme.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}

		try {
			if (SEE < 0 || SEE > 100)
				throw new SeeMarksException("SEE marks should be between 0 and 100");

		} catch (SeeMarksException sme) {
			JOptionPane.showMessageDialog(p, sme.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}

		int CIE;
		if (IA2 >= IA1 && IA3 >= IA1) {
			CIE = IA2 + IA3 + CTA;
		} else if (IA1 >= IA2 && IA3 >= IA2) {
			CIE = IA1 + IA3 + CTA;
		} else
			CIE = IA1 + IA2 + CTA;

		try {
			if (CIE < 20) {
				throw new CieMarksException("Student is detained");
			}
		} catch (CieMarksException cie) {
			JOptionPane.showMessageDialog(p, cie.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
			;
		}

		if (SEE == 38 || SEE == 39)
			SEE = 40;
		double totalmarks = CIE + Math.ceil(SEE / 2);
		l7.setText("Total Marks:" + totalmarks);

		String grade;
		if (totalmarks >= 90)
			grade = "S";
		else if (totalmarks >= 80 && totalmarks <= 89)
			grade = "A";
		else if (totalmarks >= 70 && totalmarks <= 79)
			grade = "B";
		else if (totalmarks >= 60 && totalmarks <= 69)
			grade = "C";
		else if (totalmarks >= 50 && totalmarks <= 59)
			grade = "D";
		else if (totalmarks >= 40 && totalmarks <= 49)
			grade = "E";
		else
			grade = "F";

		l8.setText("Grade:" + grade);

	}
}

public class CtaActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new Form("Student Grading System");

		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setBounds(200, 400, 300, 300);
		f.setVisible(true);
	}
}
