package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import org.jdesktop.swingx.JXDatePicker;

import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Szak;
import tanulmanyiRendszer.TanulmanyiOsztaly;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.util.Date;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

/**
 * Ezen panel segítségével a {@link TanulmanyiOsztaly} hozzá tud adni
 * újabb {@link Hallgato}-kat.
 * 
 * @author adam
 *
 */
public class HallgatoHozzaadasaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField vezeteknevField;
	private JTextField keresztnevField;
	private JTextField felhasznalonevField;
	private JPasswordField jelszoField;
	private JComboBox<Szak> szakBox;

	/**
	 * Create the panel.
	 */
	public HallgatoHozzaadasaPanel(final JPanel contentPane,
			final BorderLayout borderLayout) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{180, 180, 0};
		gridBagLayout.rowHeights = new int[]{0, 31, 26, 31, 31, 31, 31, 39, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
										
										JLabel lblHallgatHozzadsa = new JLabel("Hallgató hozzáadása");
										lblHallgatHozzadsa.setFont(new Font("Dialog", Font.BOLD, 14));
										GridBagConstraints gbc_lblHallgatHozzadsa = new GridBagConstraints();
										gbc_lblHallgatHozzadsa.gridwidth = 2;
										gbc_lblHallgatHozzadsa.insets = new Insets(0, 0, 5, 5);
										gbc_lblHallgatHozzadsa.gridx = 0;
										gbc_lblHallgatHozzadsa.gridy = 0;
										add(lblHallgatHozzadsa, gbc_lblHallgatHozzadsa);
								
										JLabel lblVezetknv = new JLabel("Vezetéknév:");
										GridBagConstraints gbc_lblVezetknv = new GridBagConstraints();
										gbc_lblVezetknv.anchor = GridBagConstraints.EAST;
										gbc_lblVezetknv.fill = GridBagConstraints.VERTICAL;
										gbc_lblVezetknv.insets = new Insets(0, 0, 5, 5);
										gbc_lblVezetknv.gridx = 0;
										gbc_lblVezetknv.gridy = 1;
										add(lblVezetknv, gbc_lblVezetknv);
						
								vezeteknevField = new JTextField();
								GridBagConstraints gbc_vezeteknevField = new GridBagConstraints();
								gbc_vezeteknevField.fill = GridBagConstraints.HORIZONTAL;
								gbc_vezeteknevField.insets = new Insets(0, 0, 5, 0);
								gbc_vezeteknevField.gridx = 1;
								gbc_vezeteknevField.gridy = 1;
								add(vezeteknevField, gbc_vezeteknevField);
								vezeteknevField.setColumns(10);
								
										JLabel lblKeresztnv = new JLabel("Keresztnév:");
										GridBagConstraints gbc_lblKeresztnv = new GridBagConstraints();
										gbc_lblKeresztnv.anchor = GridBagConstraints.EAST;
										gbc_lblKeresztnv.fill = GridBagConstraints.VERTICAL;
										gbc_lblKeresztnv.insets = new Insets(0, 0, 5, 5);
										gbc_lblKeresztnv.gridx = 0;
										gbc_lblKeresztnv.gridy = 2;
										add(lblKeresztnv, gbc_lblKeresztnv);
						
								keresztnevField = new JTextField();
								GridBagConstraints gbc_keresztnevField = new GridBagConstraints();
								gbc_keresztnevField.fill = GridBagConstraints.HORIZONTAL;
								gbc_keresztnevField.insets = new Insets(0, 0, 5, 0);
								gbc_keresztnevField.gridx = 1;
								gbc_keresztnevField.gridy = 2;
								add(keresztnevField, gbc_keresztnevField);
								keresztnevField.setColumns(10);
				
						JLabel lblFelhasznlnv = new JLabel("Felhasználónév:");
						GridBagConstraints gbc_lblFelhasznlnv = new GridBagConstraints();
						gbc_lblFelhasznlnv.anchor = GridBagConstraints.EAST;
						gbc_lblFelhasznlnv.fill = GridBagConstraints.VERTICAL;
						gbc_lblFelhasznlnv.insets = new Insets(0, 0, 5, 5);
						gbc_lblFelhasznlnv.gridx = 0;
						gbc_lblFelhasznlnv.gridy = 3;
						add(lblFelhasznlnv, gbc_lblFelhasznlnv);
				
						felhasznalonevField = new JTextField();
						GridBagConstraints gbc_felhasznalonevField = new GridBagConstraints();
						gbc_felhasznalonevField.fill = GridBagConstraints.HORIZONTAL;
						gbc_felhasznalonevField.insets = new Insets(0, 0, 5, 0);
						gbc_felhasznalonevField.gridx = 1;
						gbc_felhasznalonevField.gridy = 3;
						add(felhasznalonevField, gbc_felhasznalonevField);
						felhasznalonevField.setColumns(10);
		
				JLabel lblJelsz = new JLabel("Jelszó:");
				GridBagConstraints gbc_lblJelsz = new GridBagConstraints();
				gbc_lblJelsz.anchor = GridBagConstraints.EAST;
				gbc_lblJelsz.fill = GridBagConstraints.VERTICAL;
				gbc_lblJelsz.insets = new Insets(0, 0, 5, 5);
				gbc_lblJelsz.gridx = 0;
				gbc_lblJelsz.gridy = 4;
				add(lblJelsz, gbc_lblJelsz);
				
						jelszoField = new JPasswordField();
						GridBagConstraints gbc_jelszoField = new GridBagConstraints();
						gbc_jelszoField.fill = GridBagConstraints.HORIZONTAL;
						gbc_jelszoField.insets = new Insets(0, 0, 5, 0);
						gbc_jelszoField.gridx = 1;
						gbc_jelszoField.gridy = 4;
						add(jelszoField, gbc_jelszoField);
		
				JLabel lblSzletsnap = new JLabel("Születésnap:");
				GridBagConstraints gbc_lblSzletsnap = new GridBagConstraints();
				gbc_lblSzletsnap.anchor = GridBagConstraints.EAST;
				gbc_lblSzletsnap.fill = GridBagConstraints.VERTICAL;
				gbc_lblSzletsnap.insets = new Insets(0, 0, 5, 5);
				gbc_lblSzletsnap.gridx = 0;
				gbc_lblSzletsnap.gridy = 5;
				add(lblSzletsnap, gbc_lblSzletsnap);
				
						final JXDatePicker datePicker = new JXDatePicker(Kozpont.getLocale());
						datePicker.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.println(datePicker.getDate().toString());
							}
						});
						GridBagConstraints gbc_datePicker = new GridBagConstraints();
						gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
						gbc_datePicker.insets = new Insets(0, 0, 5, 0);
						gbc_datePicker.gridx = 1;
						gbc_datePicker.gridy = 5;
						add(datePicker, gbc_datePicker);
				
						JLabel lblSzak = new JLabel("Szak:");
						GridBagConstraints gbc_lblSzak = new GridBagConstraints();
						gbc_lblSzak.anchor = GridBagConstraints.EAST;
						gbc_lblSzak.fill = GridBagConstraints.VERTICAL;
						gbc_lblSzak.insets = new Insets(0, 0, 5, 5);
						gbc_lblSzak.gridx = 0;
						gbc_lblSzak.gridy = 6;
						add(lblSzak, gbc_lblSzak);
		
				szakBox = new JComboBox<Szak>();
				GridBagConstraints gbc_szakBox = new GridBagConstraints();
				gbc_szakBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_szakBox.insets = new Insets(0, 0, 5, 0);
				gbc_szakBox.gridx = 1;
				gbc_szakBox.gridy = 6;
				add(szakBox, gbc_szakBox);
						
						JPanel panel = new JPanel();
						GridBagConstraints gbc_panel = new GridBagConstraints();
						gbc_panel.anchor = GridBagConstraints.SOUTHEAST;
						gbc_panel.gridx = 1;
						gbc_panel.gridy = 7;
						add(panel, gbc_panel);
								
										JButton btnNewButton_1 = new JButton("Mégsem");
										panel.add(btnNewButton_1);
										btnNewButton_1.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												contentPane.remove(borderLayout
														.getLayoutComponent(BorderLayout.CENTER));
												contentPane.add(new TanulmanyiOsztalyPanel(contentPane,
														borderLayout), BorderLayout.CENTER);
												contentPane.validate();
											}
										});
						
								JButton btnNewButton = new JButton("Hozzáadás");
								panel.add(btnNewButton);
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if (vezeteknevField.getText().isEmpty()
												|| keresztnevField.getText().isEmpty()
												|| felhasznalonevField.getText().isEmpty()
												|| jelszoField.getPassword().length == 0
												|| datePicker.getDate() == null) {
											JOptionPane.showMessageDialog(contentPane,
													"Az összes mező kitöltése kötelező!",
													"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
										} else {
											Szak szak = (Szak) szakBox.getSelectedItem();
											TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont
													.getBejelentkezettFelhasználó();
											try {
												to.hallgatóHozzáadása(vezeteknevField.getText(),
														keresztnevField.getText(), felhasznalonevField
														.getText(), String.valueOf(jelszoField
														.getPassword()), datePicker.getDate(), szak);
												vezeteknevField.setText("");
												keresztnevField.setText("");
												felhasznalonevField.setText("");
												jelszoField.setText("");
												datePicker.setDate(new Date());
											} catch (TanulmanyiRendszerKivetel ex) {
												JOptionPane.showMessageDialog(contentPane,
														ex.getMessage(), "Figyelmeztetés",
														JOptionPane.WARNING_MESSAGE);
											}
										}
									}
								});
		for (Szak szak : Kozpont.getSzakLista()) {
			szakBox.addItem(szak);
		}

	}
}
