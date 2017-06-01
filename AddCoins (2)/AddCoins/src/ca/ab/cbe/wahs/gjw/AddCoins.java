package ca.ab.cbe.wahs.gjw;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.text.DecimalFormat;
import java.util.Locale;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddCoins extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuarters;
	private JTextField txtDimes;
	private JTextField txtNickels;
	private JTextField txtPennies;
	private JButton btnCalculate;
	private JButton btnSettings;
	private JLabel lblQuarters;
	private JLabel lblDimes;
	private JLabel lblNickels;
	private JLabel lblPennies;	
	private JLabel lblTotal;
	private JTextField txtTotal;
	DecimalFormat df = new DecimalFormat("0.00");
	private Locale locale = Locale.ENGLISH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCoins frame = new AddCoins();
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
	public AddCoins() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 242, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtQuarters = new JTextField();
		txtQuarters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtQuarters_keyTyped(arg0);
			}
		});
		txtQuarters.setBounds(106, 11, 86, 20);
		contentPane.add(txtQuarters);
		txtQuarters.setColumns(10);
		
		txtDimes = new JTextField();
		txtDimes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtDimes_keyTyped(e);
			}
		});
		txtDimes.setBounds(106, 42, 86, 20);
		contentPane.add(txtDimes);
		txtDimes.setColumns(10);
		
		txtNickels = new JTextField();
		txtNickels.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtNickels_keyTyped(e);
			}
		});
		txtNickels.setBounds(106, 73, 86, 20);
		contentPane.add(txtNickels);
		txtNickels.setColumns(10);
		
		txtPennies = new JTextField();
		txtPennies.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtPennies_keyTyped(e);
			}
		});
		txtPennies.setBounds(106, 104, 86, 20);
		contentPane.add(txtPennies);
		txtPennies.setColumns(10);
		
		lblQuarters = new JLabel("Quarters");
		lblQuarters.setBounds(10, 14, 86, 14);
		contentPane.add(lblQuarters);

		lblDimes = new JLabel("Dimes");
		lblDimes.setBounds(10, 45, 86, 14);
		contentPane.add(lblDimes);
		
		lblNickels = new JLabel("Nickels");
		lblNickels.setBounds(10, 76, 86, 14);
		contentPane.add(lblNickels);
		
		lblPennies = new JLabel("Pennies");
		lblPennies.setBounds(10, 107, 86, 14);
		contentPane.add(lblPennies);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnCalculate_mouseClicked(e);
			}
		});
		btnCalculate.setBounds(106, 135, 89, 23);
		contentPane.add(btnCalculate);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 172, 46, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		
		txtTotal.setBounds(106, 169, 86, 20);
		contentPane.add(txtTotal);
		
		btnSettings = new JButton("");
		btnSettings.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				btnSettings_mouseClicked(arg0);
			}
		});
		btnSettings.setBounds(202, 10, 24, 23);
		btnSettings.setIcon(new ImageIcon("res/" + "settings.png"));
		
		contentPane.add(btnSettings);
		setControls();
		
	}

	protected void btnCalculate_mouseClicked(MouseEvent e) {

		//INPUT
		int quarters = TryParse(this.txtQuarters.getText());
		int dimes = TryParse(this.txtDimes.getText());
		int nickels = TryParse(this.txtNickels.getText());
		int pennies = TryParse(this.txtPennies.getText());

		//PROCESSING
		double total = CurrencyUtilities.calculateTotalDollars(quarters, dimes, nickels, pennies);
		String output ="$" + df.format(total);	
		
		//OUTPUT
		this.txtTotal.setText(output);
	}
	
	private int TryParse(String input){

		int output;
		
		try {
			output = Integer.parseInt(input, 10);
		} catch (NumberFormatException e1) {
			output = 0;
		}
		
		return output;
	}
	protected void btnSettings_mouseClicked(MouseEvent arg0) {
		Settings settings = new Settings();
		settings.setLocationRelativeTo(this);
		settings.setModalityType(ModalityType.APPLICATION_MODAL);
		settings.setLocale(locale);
		settings.setVisible(true);
		
		if (settings.isOk()){
			locale = settings.getLocale();
			setControls();			
		}
		
		this.repaint();
	}
		
		
	private void setControls(){

		boolean valuesEntered = false;
		valuesEntered |= (this.txtQuarters.getText().length() > 0);
		valuesEntered |= (this.txtDimes.getText().length() > 0);
		valuesEntered |= (this.txtNickels.getText().length() > 0);
		valuesEntered |= (this.txtPennies.getText().length() > 0);
		
		this.btnCalculate.setEnabled(valuesEntered);
		
		if (locale == Locale.FRENCH)
		{			
			this.lblQuarters.setText("25 sous");
			this.lblDimes.setText("10 sous");
			this.lblNickels.setText("5 sous");
			this.lblPennies.setText("un sous");
			this.btnCalculate.setText("CALCULER");
			this.lblTotal.setText("TOTAL");
		}
		else
		{
			this.lblQuarters.setText("Quarters");
			this.lblDimes.setText("Dimes");
			this.lblNickels.setText("Nickels");
			this.lblPennies.setText("Pennies");
			this.btnCalculate.setText("CALCULATE");
			this.lblTotal.setText("TOTAL");
		}

			
	}
	protected void txtQuarters_keyTyped(KeyEvent arg0) {
		setControls();		
	}
	protected void txtDimes_keyTyped(KeyEvent e) {
		setControls();		
	}
	protected void txtNickels_keyTyped(KeyEvent e) {
		setControls();		
	}
	protected void txtPennies_keyTyped(KeyEvent e) {
		setControls();		
	}
}
