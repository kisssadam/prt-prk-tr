package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.FelvettVizsga;
import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

/**
 * Lehetővé teszi egy {@link Oktato}-nak, hogy beírjon egy érdemjegyet egy {@link Hallgato}
 * által felvett vizsgára ({@link FelvettVizsga}).
 */
public class ErdemjegyBeirasaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable felvettTantargyakTable;
	private List<FelvettVizsga> megjelenítettFelvettVizsgák = new ArrayList<>();
	private DefaultTableModel felvettTantargyakTableModel;
	private HallgatoListazoPanelGyakorlatiCsoportAlapjan hallgatoListazoPanel;
	private MeghirdetettTantargyPanel meghirdetettTantargyPanel;
	private SimpleDateFormat sdf = Kozpont.getVizsgaIdőpontFormat();
	
	/**
	 * Create the panel.
	 */
	public ErdemjegyBeirasaPanel(final JPanel contentPane, final BorderLayout borderLayout) {
		
		JLabel lblrdemjegyBersa = new JLabel("Érdemjegy beírása");
		lblrdemjegyBersa.setFont(new Font("Dialog", Font.BOLD, 14));
		
		meghirdetettTantargyPanel = new MeghirdetettTantargyPanel();
		
		GyakorlatiCsoportPanel gyakorlatiCsoportPanel = new GyakorlatiCsoportPanel(
				contentPane,
				meghirdetettTantargyPanel.getMeghirdetettTantargyTable(),
				meghirdetettTantargyPanel.getMeghirdetettTantargyData());
		
		hallgatoListazoPanel = new HallgatoListazoPanelGyakorlatiCsoportAlapjan(
				contentPane,
				gyakorlatiCsoportPanel.getGyakorlatiCsoportokTable(),
				gyakorlatiCsoportPanel.getGyakorlatiCsoportok());
		
		JScrollPane scrollPane = new JScrollPane();
		felvettTantargyakTable = new JTable();
		
		felvettTantargyakTableModel = new DefaultTableModel();
		felvettTantargyakTableModel.addColumn("Vizsga időpont");
		felvettTantargyakTableModel.addColumn("Vizsga terem");
		felvettTantargyakTableModel.addColumn("Érdemjegy");
		
		felvettTantargyakTable.setModel(felvettTantargyakTableModel);
		scrollPane.setViewportView(felvettTantargyakTable);
		
		meghirdetettTantargyPanel.getMeghirdetettTantargyTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				felvettTantargyakTableModel.setRowCount(0);
				megjelenítettFelvettVizsgák.clear();
			}
		});
		
		ListSelectionModel listSelectionModel = hallgatoListazoPanel.getHallgatoTable().getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateFelvettVizsgák();
			}
		});
		
		JLabel lblrdemjegy = new JLabel("Érdemjegy:");
		
		final JSpinner erdemjegySpinner = new JSpinner();
		erdemjegySpinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		
		JButton btnBeiras = new JButton("Beírás");
		btnBeiras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hallgato hallgato = hallgatoListazoPanel.getSelectedHallgato();
				if (hallgato == null) {
					JOptionPane.showMessageDialog(contentPane, "Ki kell választani egy hallgatót!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				FelvettVizsga selectedFelvettVizsga = getSelectedFelvettVizsga();
				if (selectedFelvettVizsga == null) {
					JOptionPane.showMessageDialog(contentPane, "Válassz ki egy vizsgát, ahova be szeretnéd írni a jegyet!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Oktato oktato = (Oktato) Kozpont.getBejelentkezettFelhasználó();
				try {
					oktato.érdemjegyBeírása(hallgato, selectedFelvettVizsga.getVizsga(), (Integer) erdemjegySpinner.getValue());
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				updateFelvettVizsgák();
				contentPane.validate();
			}
		});
		
		JButton btnMgsem = new JButton("Mégsem");
		btnMgsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new OktatoPanel(contentPane, borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(hallgatoListazoPanel, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblrdemjegyBersa)
							.addContainerGap(639, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblrdemjegy)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(erdemjegySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
							.addComponent(btnMgsem)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBeiras)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(meghirdetettTantargyPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
								.addComponent(gyakorlatiCsoportPanel, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblrdemjegyBersa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(meghirdetettTantargyPanel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gyakorlatiCsoportPanel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(hallgatoListazoPanel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBeiras)
						.addComponent(btnMgsem)
						.addComponent(lblrdemjegy)
						.addComponent(erdemjegySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		setLayout(groupLayout);
		
	}
	
	public FelvettVizsga getSelectedFelvettVizsga() {
		if (felvettTantargyakTable.getSelectedRowCount() != 1) {
			return null;
		}
		int index = felvettTantargyakTable.getSelectedRow();
		return megjelenítettFelvettVizsgák.get(index);
	}
	
	public void updateFelvettVizsgák() {
		megjelenítettFelvettVizsgák.clear();
		felvettTantargyakTableModel.setRowCount(0);
		
		for (FelvettVizsga felvettVizsga : hallgatoListazoPanel.getSelectedHallgato().getAktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák(
				meghirdetettTantargyPanel.getSelectedMeghirdetettTantargy())) {
			felvettTantargyakTableModel.addRow(new Object[] {sdf.format(felvettVizsga.getVizsga().getIdőpont()),
															 felvettVizsga.getVizsga().getTerem(),
															 felvettVizsga.getÉrdemjegy()});
			megjelenítettFelvettVizsgák.add(felvettVizsga);
		}
	}
}
