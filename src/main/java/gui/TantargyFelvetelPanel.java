package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.FelvettTantargy;
import tanulmanyiRendszer.GyakorlatiCsoport;
import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.MeghirdetettTantargy;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

/**
 * Lehetővé teszi egy {@link Hallgato} számára, hogy felvegyen egy
 * {@link MeghirdetettTantargy}-at {@link GyakorlatiCsoport}-tal együtt.
 * @author adam
 *
 */
public class TantargyFelvetelPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable felvettTantargyakTable;

	/**
	 * Create the panel.
	 */
	public TantargyFelvetelPanel(final JPanel contentPane, final BorderLayout borderLayout) {

		final MeghirdetettTantargyPanel meghirdetettTantargyakPanel = new MeghirdetettTantargyPanel(true);

		JLabel lblTantrgyfelvtel = new JLabel("Tantárgyfelvétel");

		final GyakorlatiCsoportPanel gyakorlatiCsoportPanel = new GyakorlatiCsoportPanel(
				true,
				contentPane,
				meghirdetettTantargyakPanel.getMeghirdetettTantargyTable(),
				meghirdetettTantargyakPanel.getMeghirdetettTantargyData());

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblFevlettTantrgyak = new JLabel("Fevlett tantárgyak:");

		JButton btnFelvesz = new JButton("Felvesz");

		JButton btnMgsem = new JButton("Mégsem");
		btnMgsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new HallgatoPanel(contentPane, borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(lblTantrgyfelvtel)
								.addContainerGap(615, Short.MAX_VALUE))
				.addComponent(meghirdetettTantargyakPanel,
						GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
				.addComponent(gyakorlatiCsoportPanel, GroupLayout.DEFAULT_SIZE,
						744, Short.MAX_VALUE)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblFevlettTantrgyak)
								.addPreferredGap(ComponentPlacement.RELATED,
										403, Short.MAX_VALUE)
								.addComponent(btnMgsem)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnFelvesz).addGap(6))
				.addComponent(scrollPane, Alignment.LEADING,
						GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblTantrgyfelvtel)
								.addGap(22)
								.addComponent(meghirdetettTantargyakPanel,
										GroupLayout.PREFERRED_SIZE, 152,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(gyakorlatiCsoportPanel,
										GroupLayout.PREFERRED_SIZE, 142,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(
														lblFevlettTantrgyak)
												.addComponent(btnFelvesz)
												.addComponent(btnMgsem))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 146,
										Short.MAX_VALUE)));

		felvettTantargyakTable = new JTable();

		final DefaultTableModel felvettTantargyakTableModel = new DefaultTableModel();
		felvettTantargyakTableModel.addColumn("Meghirdetett tantárgy");
		felvettTantargyakTableModel.addColumn("Gyakorlati Csoport");

		final Hallgato hallgato = (Hallgato) Kozpont.getBejelentkezettFelhasználó();
		for (FelvettTantargy felvettTantargy : hallgato.getFelvettTantárgyak()) {
			if (felvettTantargy.getMeghirdetettTantárgy().getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
				felvettTantargyakTableModel.addRow(new Object[] {
						felvettTantargy.getMeghirdetettTantárgy().getTantárgy().getNév(),
						felvettTantargy.getFelvettGyakorlatiCsoport() });
			}
		}

		btnFelvesz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (meghirdetettTantargyakPanel.getMeghirdetettTantargyTable().getSelectedRowCount() != 1) {
					return;
				}
				if (gyakorlatiCsoportPanel.getGyakorlatiCsoportokTable().getSelectedRowCount() != 1) {
					return;
				}
				int mtIndex = meghirdetettTantargyakPanel.getMeghirdetettTantargyTable().getSelectedRow();
				int gyIndex = gyakorlatiCsoportPanel.getGyakorlatiCsoportokTable().getSelectedRow();
				try {
					MeghirdetettTantargy meghirdetettTantargy = (MeghirdetettTantargy) meghirdetettTantargyakPanel.getMeghirdetettTantargyData()[mtIndex][3];
					GyakorlatiCsoport gyakorlatiCsoport = gyakorlatiCsoportPanel.getGyakorlatiCsoportok().get(gyIndex);
					
					hallgato.felveszTantárgy(meghirdetettTantargy,gyakorlatiCsoport);
					felvettTantargyakTableModel.addRow(new Object[] { meghirdetettTantargy.getTantárgy().getNév(), gyakorlatiCsoport });
					contentPane.validate();
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		scrollPane.setViewportView(felvettTantargyakTable);
		felvettTantargyakTable.setModel(felvettTantargyakTableModel);
		setLayout(groupLayout);

	}
}
