package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.TanulmanyiOsztaly;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Component;

import javax.swing.Box;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

/**
 * Lehetővé teszi a {@link TanulmanyiOsztaly} számára, hogy új {@link Oktato}-t adjon hozzá.
 * @author adam
 *
 */
public class OktatoHozzaadasaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField vezeteknevField;
	private JTextField keresztnevField;
	private JTextField felhasznalonevField;
	private JTextField fizetesField;
	private JPasswordField jelszoField;

	/**
	 * Create the panel.
	 */
	public OktatoHozzaadasaPanel(final JPanel contentPane,
			final BorderLayout borderLayout) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
				JLabel lblOktatHozzadsa = new JLabel("Oktató hozzáadása");
				lblOktatHozzadsa.setFont(new Font("Dialog", Font.BOLD, 14));
				GridBagConstraints gbc_lblOktatHozzadsa = new GridBagConstraints();
				gbc_lblOktatHozzadsa.gridwidth = 2;
				gbc_lblOktatHozzadsa.insets = new Insets(0, 0, 5, 5);
				gbc_lblOktatHozzadsa.gridx = 0;
				gbc_lblOktatHozzadsa.gridy = 0;
				add(lblOktatHozzadsa, gbc_lblOktatHozzadsa);

		JLabel lblVezetknv = new JLabel("Vezetéknév:");
		GridBagConstraints gbc_lblVezetknv = new GridBagConstraints();
		gbc_lblVezetknv.anchor = GridBagConstraints.EAST;
		gbc_lblVezetknv.insets = new Insets(0, 0, 5, 5);
		gbc_lblVezetknv.gridx = 0;
		gbc_lblVezetknv.gridy = 3;
		add(lblVezetknv, gbc_lblVezetknv);

		vezeteknevField = new JTextField();
		GridBagConstraints gbc_vezeteknevField = new GridBagConstraints();
		gbc_vezeteknevField.insets = new Insets(0, 0, 5, 0);
		gbc_vezeteknevField.fill = GridBagConstraints.HORIZONTAL;
		gbc_vezeteknevField.gridx = 1;
		gbc_vezeteknevField.gridy = 3;
		add(vezeteknevField, gbc_vezeteknevField);
		vezeteknevField.setColumns(10);

		JLabel lblKeresztnv = new JLabel("Keresztnév:");
		GridBagConstraints gbc_lblKeresztnv = new GridBagConstraints();
		gbc_lblKeresztnv.anchor = GridBagConstraints.EAST;
		gbc_lblKeresztnv.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeresztnv.gridx = 0;
		gbc_lblKeresztnv.gridy = 4;
		add(lblKeresztnv, gbc_lblKeresztnv);

		keresztnevField = new JTextField();
		GridBagConstraints gbc_keresztnevField = new GridBagConstraints();
		gbc_keresztnevField.insets = new Insets(0, 0, 5, 0);
		gbc_keresztnevField.fill = GridBagConstraints.HORIZONTAL;
		gbc_keresztnevField.gridx = 1;
		gbc_keresztnevField.gridy = 4;
		add(keresztnevField, gbc_keresztnevField);
		keresztnevField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Felhasználónév:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);

		felhasznalonevField = new JTextField();
		GridBagConstraints gbc_felhasznalonevField = new GridBagConstraints();
		gbc_felhasznalonevField.insets = new Insets(0, 0, 5, 0);
		gbc_felhasznalonevField.fill = GridBagConstraints.HORIZONTAL;
		gbc_felhasznalonevField.gridx = 1;
		gbc_felhasznalonevField.gridy = 5;
		add(felhasznalonevField, gbc_felhasznalonevField);
		felhasznalonevField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Jelszó:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 6;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		jelszoField = new JPasswordField();
		GridBagConstraints gbc_jelszoField = new GridBagConstraints();
		gbc_jelszoField.insets = new Insets(0, 0, 5, 0);
		gbc_jelszoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jelszoField.gridx = 1;
		gbc_jelszoField.gridy = 6;
		add(jelszoField, gbc_jelszoField);

		JLabel lblNewLabel_2 = new JLabel("Születésnap:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 7;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		final JXDatePicker datePicker = new JXDatePicker(Kozpont.getLocale());
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.insets = new Insets(0, 0, 5, 0);
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.gridx = 1;
		gbc_datePicker.gridy = 7;
		add(datePicker, gbc_datePicker);

		JLabel lblFizets = new JLabel("Fizetés:");
		GridBagConstraints gbc_lblFizets = new GridBagConstraints();
		gbc_lblFizets.insets = new Insets(0, 0, 5, 5);
		gbc_lblFizets.anchor = GridBagConstraints.EAST;
		gbc_lblFizets.gridx = 0;
		gbc_lblFizets.gridy = 8;
		add(lblFizets, gbc_lblFizets);

		fizetesField = new JTextField();
		GridBagConstraints gbc_fizetesField = new GridBagConstraints();
		gbc_fizetesField.insets = new Insets(0, 0, 5, 0);
		gbc_fizetesField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fizetesField.gridx = 1;
		gbc_fizetesField.gridy = 8;
		add(fizetesField, gbc_fizetesField);
		fizetesField.setColumns(10);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 10;
		add(panel, gbc_panel);

		JButton btnNewButton = new JButton("Mégsem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout
						.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new TanulmanyiOsztalyPanel(contentPane,
						borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		panel.add(btnNewButton);

		Component glue = Box.createGlue();
		panel.add(glue);

		JButton btnNewButton_1 = new JButton("Hozzáadás");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vezeteknevField.getText().isEmpty()
						|| keresztnevField.getText().isEmpty()
						|| felhasznalonevField.getText().isEmpty()
						|| jelszoField.getPassword().length == 0
						|| datePicker.getDate() == null
						|| fizetesField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
							"Az összes mező kitöltése kötelező!",
							"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Oktato oktató = new Oktato(vezeteknevField.getText(),
								keresztnevField.getText(), felhasznalonevField
										.getText(), String.valueOf(jelszoField
										.getPassword()), datePicker.getDate(),
								Integer.parseInt(fizetesField.getText()));
						TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont
								.getBejelentkezettFelhasználó();
						to.oktatóHozzáadása(oktató);
						vezeteknevField.setText("");
						keresztnevField.setText("");
						felhasznalonevField.setText("");
						jelszoField.setText("");
						datePicker.setDate(new Date());
						fizetesField.setText("");
					} catch (TanulmanyiRendszerKivetel ex) {
						JOptionPane.showMessageDialog(contentPane,
								ex.getMessage(), "Figyelmeztetés",
								JOptionPane.WARNING_MESSAGE);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(contentPane,
								"Érvénytelen fizetés lett megadva!",
								"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panel.add(btnNewButton_1);

	}
}
