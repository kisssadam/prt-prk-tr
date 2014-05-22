package gui;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import tanulmanyiRendszer.GyakorlatiCsoport;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.MeghirdetettTantargy;

/**
 * Megjelenít egy olyan panelt, ahol egy {@link MeghirdetettTantargy}
 * {@link GyakorlatiCsoport}-jai vannak kilistázva.
 * 
 * @author adam
 *
 */
public class GyakorlatiCsoportPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable gyakorlatiCsoportokTable;
	private List<GyakorlatiCsoport> gyakorlatiCsoportok;

	private DefaultTableModel gyakorlatiCsoportTableModel;

	public GyakorlatiCsoportPanel(final JPanel contentPane,
			final JTable meghirdetettTantárgyTable,
			final Object[][] meghirdetettTantargyData) {
		this(false, contentPane, meghirdetettTantárgyTable,
				meghirdetettTantargyData);
	}

	/**
	 * Create the panel.
	 */
	public GyakorlatiCsoportPanel(final boolean hallgatoPanel,
			final JPanel contentPane, final JTable meghirdetettTantárgyTable,
			final Object[][] meghirdetettTantargyData) {
		gyakorlatiCsoportok = new ArrayList<>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		gyakorlatiCsoportokTable = new JTable();
		gyakorlatiCsoportTableModel = new DefaultTableModel();
		gyakorlatiCsoportTableModel.addColumn("Gyakorlatvezető");
		gyakorlatiCsoportTableModel.addColumn("Terem");
		gyakorlatiCsoportTableModel.addColumn("Időpont");
		gyakorlatiCsoportokTable.setModel(gyakorlatiCsoportTableModel);

		ListSelectionModel listSelectionModel = meghirdetettTantárgyTable
				.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						gyakorlatiCsoportTableModel.setRowCount(0);
						gyakorlatiCsoportok.clear();
						
						if (hallgatoPanel) {
							if (meghirdetettTantárgyTable.getSelectedRowCount() == 1) {
								int index = meghirdetettTantárgyTable.getSelectedRow();
								MeghirdetettTantargy mt = (MeghirdetettTantargy) meghirdetettTantargyData[index][3];

								for (GyakorlatiCsoport gycs : mt.getGyakorlatiCsoportok()) {
									gyakorlatiCsoportTableModel
											.addRow(new Object[] {
													gycs.getGyakorlatvezető(),
													gycs.getTerem(),
													gycs.getIdőpont(),
													gycs });
									gyakorlatiCsoportok.add(gycs);
								}
							}
						} else {
							if (meghirdetettTantárgyTable.getSelectedRowCount() == 1) {
								int index = meghirdetettTantárgyTable.getSelectedRow();
								MeghirdetettTantargy mt = (MeghirdetettTantargy) meghirdetettTantargyData[index][3];

								for (GyakorlatiCsoport gycs : mt.getGyakorlatiCsoportok()) {
									if (gycs.getGyakorlatvezető().equals(Kozpont.getBejelentkezettFelhasználó())) {
										gyakorlatiCsoportTableModel.addRow(new Object[] {
												gycs.getGyakorlatvezető(),
												gycs.getTerem(),
												gycs.getIdőpont(),
												gycs });
										gyakorlatiCsoportok.add(gycs);
									}
								}
							}
						}
						contentPane.validate();
					}
				});

		scrollPane.setViewportView(gyakorlatiCsoportokTable);

	}

	public JTable getGyakorlatiCsoportokTable() {
		return gyakorlatiCsoportokTable;
	}

	public DefaultTableModel getGyakorlatiCsoportTableModel() {
		return gyakorlatiCsoportTableModel;
	}

	public List<GyakorlatiCsoport> getGyakorlatiCsoportok() {
		return gyakorlatiCsoportok;
	}
}
