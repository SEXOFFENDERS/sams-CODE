import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CounterFrame extends JFrame {

	private Counter counter;
	private JPanel contentPane;
	private JTextField txtCount;
	JButton btnIncrement;
	JButton btnDecrement;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CounterFrame frame = new CounterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CounterFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				this_windowClosing(arg0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 168, 116);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCount = new JLabel("Count:");
		lblCount.setBounds(10, 11, 46, 14);
		contentPane.add(lblCount);
		
		txtCount = new JTextField();
		txtCount.setBounds(50, 8, 86, 20);
		contentPane.add(txtCount);
		txtCount.setColumns(10);
		
		btnIncrement = new JButton("++");
		btnIncrement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIncrement_actionPerformed(e);
			}
		});
		btnIncrement.setBounds(78, 36, 58, 23);
		contentPane.add(btnIncrement);
		
		btnDecrement = new JButton("--");
		btnDecrement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDecrement_actionPerformed(e);
			}
		});
		btnDecrement.setBounds(10, 36, 58, 23);
		contentPane.add(btnDecrement);
		
		counter = new Counter();
		setControls();
	}
		
	protected void btnDecrement_actionPerformed(ActionEvent e) {
		counter.decrement();
		setControls();
	}
	protected void btnIncrement_actionPerformed(ActionEvent e) {
		counter.increment();
		setControls();
	}
	
	private void setControls(){
		
		//values
		this.txtCount.setText(Integer.toString(counter.getCount()));
		
		//state
		boolean isPositive = (counter.getCount() > 0);
		this.btnDecrement.setEnabled(isPositive);
		this.btnIncrement.setEnabled(true);
		this.txtCount.setEditable(false);
	}
	
	protected void this_windowClosing(WindowEvent arg0) {
		counter.saveSettings();
	}
}
