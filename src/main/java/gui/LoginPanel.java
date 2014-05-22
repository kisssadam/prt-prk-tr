package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tanulmanyiRendszer.Felhasznalo;
import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.TanulmanyiOsztaly;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Amikor a program elindul, akkor ez a panel fog megjelenni a {@link MainFrame}-en belül.
 * Ezeb a panelel keresztül tudnak bejelentkezni a {@link Hallgato}-ók, {@link Oktato}-ók, és
 * a {@link TanulmanyiOsztaly}-ok.
 * @author adam
 *
 */
public class LoginPanel extends JPanel {

	private JPasswordField jelszoField;
	private JTextField felhasznalonevField;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public LoginPanel(final JPanel contentPane, final BorderLayout borderLayout) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 100, 116, 130, 0 };
		gridBagLayout.rowHeights = new int[] { 65, 19, 19, 45, 1, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblFelhasznlnv = new JLabel("Felhasználónév:");
		GridBagConstraints gbc_lblFelhasznlnv = new GridBagConstraints();
		gbc_lblFelhasznlnv.anchor = GridBagConstraints.WEST;
		gbc_lblFelhasznlnv.insets = new Insets(0, 0, 5, 5);
		gbc_lblFelhasznlnv.gridx = 1;
		gbc_lblFelhasznlnv.gridy = 1;
		add(lblFelhasznlnv, gbc_lblFelhasznlnv);

		felhasznalonevField = new JTextField();
		GridBagConstraints gbc_felhasznalonevField = new GridBagConstraints();
		gbc_felhasznalonevField.anchor = GridBagConstraints.NORTH;
		gbc_felhasznalonevField.fill = GridBagConstraints.HORIZONTAL;
		gbc_felhasznalonevField.insets = new Insets(0, 0, 5, 0);
		gbc_felhasznalonevField.gridx = 2;
		gbc_felhasznalonevField.gridy = 1;
		this.add(felhasznalonevField, gbc_felhasznalonevField);
		felhasznalonevField.setColumns(10);

		JLabel lblJelsz = new JLabel("Jelszó:");
		GridBagConstraints gbc_lblJelsz = new GridBagConstraints();
		gbc_lblJelsz.anchor = GridBagConstraints.EAST;
		gbc_lblJelsz.insets = new Insets(0, 0, 5, 5);
		gbc_lblJelsz.gridx = 1;
		gbc_lblJelsz.gridy = 2;
		add(lblJelsz, gbc_lblJelsz);

		jelszoField = new JPasswordField();
		jelszoField.setColumns(50);
		GridBagConstraints gbc_jelszoField = new GridBagConstraints();
		gbc_jelszoField.anchor = GridBagConstraints.NORTH;
		gbc_jelszoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jelszoField.insets = new Insets(0, 0, 5, 0);
		gbc_jelszoField.gridx = 2;
		gbc_jelszoField.gridy = 2;
		this.add(jelszoField, gbc_jelszoField);

		JButton btnBejelentkezs = new JButton("Bejelentkezés");

		btnBejelentkezs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String felhasználónév = felhasznalonevField.getText();
				String jelszó = String.valueOf(jelszoField.getPassword());

				if (felhasználónév.isEmpty() || jelszó.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
							"Az összes mező kitöltése kötelező!",
							"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
				} else {
					Felhasznalo felhasználó = Kozpont.bejelentkezés(felhasználónév, jelszó);

					if (felhasználó == null) {
						JOptionPane.showMessageDialog(contentPane,
								"Sikertelen bejelentkezés!", "Hiba",
								JOptionPane.ERROR_MESSAGE);
					} else {
						contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
						if (felhasználó instanceof TanulmanyiOsztaly) {
							contentPane.add(new TanulmanyiOsztalyPanel(contentPane, borderLayout), BorderLayout.CENTER);
						} else if (felhasználó instanceof Oktato) {
							contentPane.add(new OktatoPanel(contentPane, borderLayout), BorderLayout.CENTER);
						} else if (felhasználó instanceof Hallgato) {
							contentPane.add(new HallgatoPanel(contentPane, borderLayout), BorderLayout.CENTER);
						}
						contentPane.validate();
					}
				}
			}
		});
		GridBagConstraints gbc_btnBejelentkezs = new GridBagConstraints();
		gbc_btnBejelentkezs.insets = new Insets(0, 0, 5, 0);
		gbc_btnBejelentkezs.fill = GridBagConstraints.BOTH;
		gbc_btnBejelentkezs.gridx = 2;
		gbc_btnBejelentkezs.gridy = 3;
		add(btnBejelentkezs, gbc_btnBejelentkezs);
	}
}
