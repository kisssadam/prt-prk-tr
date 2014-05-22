package gui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.MeghirdetettTantargy;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Lehetővé teszi egy {@link Oktato} számára, hogy egy {@link Hallgato}-nak
 *  beírja az aláírást.
 * @author adam
 *
 */
public class AlairasBeirasaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AlairasBeirasaPanel(final JPanel contentPane, final BorderLayout borderLayout) {

		final MeghirdetettTantargyPanel meghirdetettTantargyPanel = new MeghirdetettTantargyPanel();

		GyakorlatiCsoportPanel gyakorlatiCsoportPanel = new GyakorlatiCsoportPanel(
				contentPane, meghirdetettTantargyPanel.getMeghirdetettTantargyTable(),
				meghirdetettTantargyPanel.getMeghirdetettTantargyData());

		final HallgatoListazoPanelGyakorlatiCsoportAlapjan hallgatoListazoPanel = new HallgatoListazoPanelGyakorlatiCsoportAlapjan(
				contentPane,
				gyakorlatiCsoportPanel.getGyakorlatiCsoportokTable(),
				gyakorlatiCsoportPanel.getGyakorlatiCsoportok());

		ButtonGroup radioButtonGroup = new ButtonGroup();

		final JRadioButton rdbtnAlrsMegadsa = new JRadioButton("aláírás megadása");
		rdbtnAlrsMegadsa.setSelected(true);
		JRadioButton rdbtnAlrsMegtagadsa = new JRadioButton("aláírás megtagadása");

		radioButtonGroup.add(rdbtnAlrsMegadsa);
		radioButtonGroup.add(rdbtnAlrsMegtagadsa);

		JButton btnTovabb = new JButton("Tovább");
		btnTovabb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Oktato oktato = (Oktato) Kozpont.getBejelentkezettFelhasználó();
				Hallgato hallgato = hallgatoListazoPanel.getSelectedHallgato();
				
				MeghirdetettTantargy meghirdetettTantargy = meghirdetettTantargyPanel.getSelectedMeghirdetettTantargy();
				if (meghirdetettTantargy == null) {
					JOptionPane.showMessageDialog(contentPane, "Ki kell választani egy tantárgyat!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					oktato.aláírásBeírása(hallgato, meghirdetettTantargy, (rdbtnAlrsMegadsa.isSelected() ? true : false));
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnMegsem = new JButton("Mégsem");
		btnMegsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new OktatoPanel(contentPane, borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addComponent(meghirdetettTantargyPanel,
								Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
								778, Short.MAX_VALUE)
						.addComponent(gyakorlatiCsoportPanel,
								GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(hallgatoListazoPanel,
												GroupLayout.DEFAULT_SIZE, 778,
												Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(23)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				rdbtnAlrsMegtagadsa)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				353,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnMegsem)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnTovabb))
														.addComponent(
																rdbtnAlrsMegadsa))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(
												meghirdetettTantargyPanel,
												GroupLayout.PREFERRED_SIZE,
												135, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(gyakorlatiCsoportPanel,
												GroupLayout.PREFERRED_SIZE,
												136, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(hallgatoListazoPanel,
												GroupLayout.PREFERRED_SIZE,
												146, GroupLayout.PREFERRED_SIZE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(18)
																		.addComponent(
																				rdbtnAlrsMegadsa)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				rdbtnAlrsMegtagadsa)
																		.addContainerGap(
																				30,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnTovabb)
																						.addComponent(
																								btnMegsem))
																		.addContainerGap()))));
		setLayout(groupLayout);

	}
}
