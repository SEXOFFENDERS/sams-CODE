package ca.ab.cbe.wahs.gjw;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Settings extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JRadioButton optEnglish;
	JRadioButton optFrench;
	private ButtonGroup languageGroup;
	private Locale locale = Locale.ENGLISH;
	private boolean ok = false;

	public boolean isOk() {
		return ok;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Settings() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				this_componentShown(arg0);
			}
		});
		setBounds(100, 100, 148, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Language", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 121, 72);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		optEnglish = new JRadioButton("English");
		optEnglish.setBounds(6, 16, 109, 23);
		panel.add(optEnglish);
		
		optFrench = new JRadioButton("French");
		optFrench.setBounds(6, 42, 97, 23);
		panel.add(optFrench);
		
		languageGroup = new ButtonGroup();
		languageGroup.add(optEnglish);
		languageGroup.add(optFrench);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						okButton_mouseClicked(arg0);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String getLanguage() {
		if (this.optEnglish.isSelected()){
			return "english";
		}
		else {
			return "french";
		}
	}
	
	protected void okButton_mouseClicked(MouseEvent arg0) {
		ok = true;
		readControls();
		this.setVisible(false);
	}

	private void setControls(){
		this.optEnglish.setSelected(locale == Locale.ENGLISH);
		this.optFrench.setSelected(locale == Locale.FRENCH);
		
	}
	
	private void readControls(){
		if (this.optEnglish.isSelected()){
			locale = Locale.ENGLISH;
		}
		else {
			locale = Locale.FRENCH;
		}
		
	}
	
	protected void this_componentShown(ComponentEvent arg0) {
		setControls();
	}
}
