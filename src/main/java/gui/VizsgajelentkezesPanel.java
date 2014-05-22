package gui;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;
import tanulmanyiRendszer.Vizsga;

import java.awt.Font;

/**
 * Lehetővé teszi egy {@link Hallgato} számára, hogy vizsgára jelentkezzen.
 * @author adam
 *
 */
public class VizsgajelentkezesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public VizsgajelentkezesPanel(final JPanel contentPane, final BorderLayout borderLayout) {
		
		MeghirdetettTantargyPanel meghirdetettTantargyPanel = new MeghirdetettTantargyPanel(true);
		
		final FelvettVizsgakPanel felvettVizsgakPanel = new FelvettVizsgakPanel();
		
		final VizsgaTablePanel vizsgaTablePanel = new VizsgaTablePanel(meghirdetettTantargyPanel.getMeghirdetettTantargyTable(),
																	   meghirdetettTantargyPanel.getMeghirdetettTantargyData());
		
		JButton btnJelentkezs = new JButton("Jelentkezés");
		btnJelentkezs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vizsga vizsga = vizsgaTablePanel.getSelectedVizsga();
				if (vizsga == null) {
					JOptionPane.showMessageDialog(contentPane, "Nincs kiválasztott vizsga!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Hallgato hallgato = (Hallgato) Kozpont.getBejelentkezettFelhasználó();
				try {
					hallgato.vizsgajelentkezés(vizsga);
					
					felvettVizsgakPanel.getFelvettVizsgakTableModel().addRow(new Object[] {vizsga.getMeghirdetettTantargy(),
																						   vizsga.getIdőpont(),
																						   vizsga.getTerem(),
																						   -1});
					contentPane.validate();
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnMgsem = new JButton("Mégsem");
		btnMgsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout .getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new HallgatoPanel(contentPane, borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		
		JLabel lblVizsgajelentkezs = new JLabel("Vizsgajelentkezés");
		lblVizsgajelentkezs.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblEbbenAFlvben = new JLabel("Ebben a félévben ezekre a vizsgákra jelentkeztél:");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(lblVizsgajelentkezs)
					.addContainerGap(607, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(meghirdetettTantargyPanel, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(felvettVizsgakPanel, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEbbenAFlvben)
					.addPreferredGap(ComponentPlacement.RELATED, 443, Short.MAX_VALUE)
					.addComponent(btnMgsem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnJelentkezs)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(vizsgaTablePanel, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
					.addGap(11))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVizsgajelentkezs)
					.addGap(18)
					.addComponent(meghirdetettTantargyPanel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(vizsgaTablePanel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnJelentkezs)
							.addComponent(btnMgsem))
						.addComponent(lblEbbenAFlvben))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(felvettVizsgakPanel, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}
}
