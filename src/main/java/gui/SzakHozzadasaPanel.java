package gui;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.Font;

import javax.swing.JButton;

import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Szak;
import tanulmanyiRendszer.TanulmanyiOsztaly;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;
import tanulmanyiRendszer.Szak.Szint;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Lehetővé teszi a {@link TanulmanyiOsztaly} számára, hogy új {@link Szak}-ot vegyen fel.
 * @author adam
 *
 */
public class SzakHozzadasaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nevField;

	/**
	 * Create the panel.
	 */
	public SzakHozzadasaPanel(final JPanel contentPane, final BorderLayout borderLayout) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblSzakHozzadsa = new JLabel("Szak hozzáadása");
		lblSzakHozzadsa.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblSzakHozzadsa = new GridBagConstraints();
		gbc_lblSzakHozzadsa.gridwidth = 2;
		gbc_lblSzakHozzadsa.insets = new Insets(0, 0, 5, 0);
		gbc_lblSzakHozzadsa.gridx = 0;
		gbc_lblSzakHozzadsa.gridy = 0;
		add(lblSzakHozzadsa, gbc_lblSzakHozzadsa);

		ButtonGroup buttonGroup = new ButtonGroup();

		final SzakListazoPanel szakListazoPanel = new SzakListazoPanel();
		GridBagConstraints gbc_szakListazoPanel = new GridBagConstraints();
		gbc_szakListazoPanel.gridheight = 4;
		gbc_szakListazoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_szakListazoPanel.fill = GridBagConstraints.BOTH;
		gbc_szakListazoPanel.gridx = 1;
		gbc_szakListazoPanel.gridy = 1;
		add(szakListazoPanel, gbc_szakListazoPanel);

		JLabel lblNv = new JLabel("Név:");
		GridBagConstraints gbc_lblNv = new GridBagConstraints();
		gbc_lblNv.insets = new Insets(0, 0, 5, 5);
		gbc_lblNv.anchor = GridBagConstraints.EAST;
		gbc_lblNv.gridx = 0;
		gbc_lblNv.gridy = 5;
		add(lblNv, gbc_lblNv);

		nevField = new JTextField();
		GridBagConstraints gbc_nevField = new GridBagConstraints();
		gbc_nevField.insets = new Insets(0, 0, 5, 0);
		gbc_nevField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nevField.gridx = 1;
		gbc_nevField.gridy = 5;
		add(nevField, gbc_nevField);
		nevField.setColumns(10);

		JLabel lblKpzsSzintje = new JLabel("Képzés szintje:");
		GridBagConstraints gbc_lblKpzsSzintje = new GridBagConstraints();
		gbc_lblKpzsSzintje.insets = new Insets(0, 0, 5, 5);
		gbc_lblKpzsSzintje.anchor = GridBagConstraints.EAST;
		gbc_lblKpzsSzintje.gridx = 0;
		gbc_lblKpzsSzintje.gridy = 6;
		add(lblKpzsSzintje, gbc_lblKpzsSzintje);

		final JRadioButton rdbtnAlapkpzsbsc = new JRadioButton("Alapképzés (BSc)");
		rdbtnAlapkpzsbsc.setSelected(true);
		GridBagConstraints gbc_rdbtnAlapkpzsbsc = new GridBagConstraints();
		gbc_rdbtnAlapkpzsbsc.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAlapkpzsbsc.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnAlapkpzsbsc.gridx = 1;
		gbc_rdbtnAlapkpzsbsc.gridy = 6;
		add(rdbtnAlapkpzsbsc, gbc_rdbtnAlapkpzsbsc);
		buttonGroup.add(rdbtnAlapkpzsbsc);

		JRadioButton rdbtnMesterkpzsmsc = new JRadioButton("Mesterképzés (MSc)");
		GridBagConstraints gbc_rdbtnMesterkpzsmsc = new GridBagConstraints();
		gbc_rdbtnMesterkpzsmsc.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnMesterkpzsmsc.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMesterkpzsmsc.gridx = 1;
		gbc_rdbtnMesterkpzsmsc.gridy = 7;
		add(rdbtnMesterkpzsmsc, gbc_rdbtnMesterkpzsmsc);
		buttonGroup.add(rdbtnMesterkpzsmsc);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 9;
		add(panel, gbc_panel);

		JButton btnMgsem = new JButton("Mégsem");
		btnMgsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new TanulmanyiOsztalyPanel(contentPane, borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		panel.add(btnMgsem);

		JButton btnHozzaadás = new JButton("Hozzáadás");
		panel.add(btnHozzaadás);
		btnHozzaadás.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String szakNév = nevField.getText();
				if (!szakNév.equals("")) {
					Szak szak = new Szak(szakNév, rdbtnAlapkpzsbsc.isSelected() ? Szint.BSc : Szint.MSc);

					TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont.getBejelentkezettFelhasználó();
					try {
						to.szakHozzáadása(szak);
						nevField.setText("");
						szakListazoPanel.getSzakListazoTableModel().addRow(new Object[] {szak.getNév(), szak.getSzint()});
						szakListazoPanel.getMegjelenitettSzakok().add(szak);
						contentPane.validate();
					} catch (TanulmanyiRendszerKivetel ex) {
						JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
						nevField.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Meg kell adnod egy szaknak a nevét!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					nevField.setText("");
				}
			}
		});

	}
}
