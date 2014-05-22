package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.FelvettTantargy;
import tanulmanyiRendszer.GyakorlatiCsoport;
import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;

/**
 * Kilistázza azokat a {@link Hallgato}-kat, akik felvettek az adott
 * {@link GyakorlatiCsoport}-ot.
 * @author adam
 *
 */
public class HallgatoListazoPanelGyakorlatiCsoportAlapjan extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable hallgatoTable;
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",
			Kozpont.getLocale());
	List<Hallgato> hallgatok = new ArrayList<>();

	private DefaultTableModel hallgatoTableModel;

	/**
	 * Create the panel.
	 */
	public HallgatoListazoPanelGyakorlatiCsoportAlapjan(
			final JPanel contentPane,
			final JTable gyakorlatiCsoportokTable,
			final List<GyakorlatiCsoport> gyakorlatiCsoportok) {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 383, 0 };
		gridBagLayout.rowHeights = new int[] { 3, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		hallgatoTable = new JTable();
		hallgatoTableModel = new DefaultTableModel();

		hallgatoTableModel.addColumn("Vezetéknév");
		hallgatoTableModel.addColumn("Keresztnév");
		hallgatoTableModel.addColumn("Felhasználónév");
		hallgatoTableModel.addColumn("Születésnap");
		hallgatoTableModel.addColumn("Szak");

		hallgatoTable.setModel(hallgatoTableModel);
		scrollPane.setViewportView(hallgatoTable);

		ListSelectionModel listSelectionModel = gyakorlatiCsoportokTable
				.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						hallgatoTableModel.setRowCount(0);
						hallgatok.clear();
						if (gyakorlatiCsoportokTable.getSelectedRowCount() == 1) {
							int index = gyakorlatiCsoportokTable.getSelectedRow();
							GyakorlatiCsoport gyakorlatiCsoport = gyakorlatiCsoportok.get(index);
							for (Hallgato hallgato : Kozpont.getHallgatóLista()) {
								for (FelvettTantargy felvettTantargy : hallgato.getFelvettTantárgyak()) {
									if (felvettTantargy.getFelvettGyakorlatiCsoport().equals(gyakorlatiCsoport)) {
										hallgatoTableModel.addRow(new Object[] {
												hallgato.getVezetéknév(),
												hallgato.getKeresztnév(),
												hallgato.getFelhasználónév(),
												sdf.format(hallgato.getSzületésnap()),
												hallgato.getSzak().toString() });
										hallgatok.add(hallgato);
									}
								}
							}
						}
						contentPane.validate();
					}
				});
	}

	public List<Hallgato> getHallgatok() {
		return hallgatok;
	}

	public DefaultTableModel getHallgatoTableModel() {
		return hallgatoTableModel;
	}

	public JTable getHallgatoTable() {
		return hallgatoTable;
	}
	
	public Hallgato getSelectedHallgato() {
		if (hallgatoTable.getSelectedRowCount() != 1) {
			return null;
		}
		return hallgatok.get(hallgatoTable.getSelectedRow());
	}
	
}
