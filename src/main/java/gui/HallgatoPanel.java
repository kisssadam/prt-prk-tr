package gui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import tanulmanyiRendszer.Hallgato;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Amikor egy {@link Hallgato} bejelentkezik, akkor ez a panel fog megjelenni.
 * @author adam
 *
 */
public class HallgatoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HallgatoPanel(final JPanel contentPane,
			final BorderLayout borderLayout) {

		JLabel lblHallgatiPanel = new JLabel("Hallgatói panel");
		lblHallgatiPanel.setFont(new Font("Dialog", Font.BOLD, 14));

		ButtonGroup radioButtonGroup = new ButtonGroup();
		final JRadioButton rdbtnTantrgyFelvtele = new JRadioButton("Tantárgy felvétele");
		radioButtonGroup.add(rdbtnTantrgyFelvtele);
		
		final JRadioButton rdbtnBeiratkozs = new JRadioButton("Beiratkozás");
		rdbtnBeiratkozs.setSelected(true);
		radioButtonGroup.add(rdbtnBeiratkozs);

		final JRadioButton rdbtnTantrgyLeadsa = new JRadioButton("Tantárgy leadása");
		radioButtonGroup.add(rdbtnTantrgyLeadsa);
		rdbtnTantrgyLeadsa.setEnabled(false);

		final JRadioButton rdbtnVizsgajelentkezes = new JRadioButton("Vizsgajelentkezés");
		radioButtonGroup.add(rdbtnVizsgajelentkezes);

		JButton btnTovbb = new JButton("Tovább");
		btnTovbb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout .getLayoutComponent(BorderLayout.CENTER));
				if (rdbtnBeiratkozs.isSelected()) {
					contentPane.add(new BeiratkozasPanel(contentPane, borderLayout), BorderLayout.CENTER);
				} else if (rdbtnTantrgyFelvtele.isSelected()) {
					contentPane.add(new TantargyFelvetelPanel(contentPane, borderLayout), BorderLayout.CENTER);
				} else if (rdbtnVizsgajelentkezes.isSelected()) {
					contentPane.add(new VizsgajelentkezesPanel(contentPane, borderLayout), BorderLayout.CENTER);
				}
				contentPane.validate();
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblHallgatiPanel))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(140)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								rdbtnBeiratkozs)
																						.addComponent(
																								rdbtnTantrgyLeadsa)
																						.addComponent(
																								rdbtnVizsgajelentkezes)
																						.addComponent(
																								rdbtnTantrgyFelvtele))))
										.addContainerGap(413, Short.MAX_VALUE))
						.addGroup(
								groupLayout.createSequentialGroup()
										.addContainerGap(611, Short.MAX_VALUE)
										.addComponent(btnTovbb)
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblHallgatiPanel)
						.addGap(86)
						.addComponent(rdbtnBeiratkozs)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnTantrgyFelvtele)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnTantrgyLeadsa)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnVizsgajelentkezes)
						.addPreferredGap(ComponentPlacement.RELATED, 270,
								Short.MAX_VALUE).addComponent(btnTovbb)
						.addContainerGap()));
		setLayout(groupLayout);

	}
}
