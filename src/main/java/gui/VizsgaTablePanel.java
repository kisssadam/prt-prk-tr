package gui;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.MeghirdetettTantargy;
import tanulmanyiRendszer.Vizsga;

/**
 * Kilistázza egy {@link MeghirdetettTantargy} {@link Vizsga}-it.
 * @author adam
 *
 */
public class VizsgaTablePanel extends JPanel {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable vizsgaTable;
	private static SimpleDateFormat sdf = Kozpont.getSimpleDateFormat();
	private List<Vizsga> meghirdetettTantargyVizsgai = new ArrayList<>();
	private DefaultTableModel vizsgaTableModel;
	
	/**
	 * Create the panel.
	 * Paraméterként a MeghirdetettTantargyPanel data-ját kapja és az ott kiválasztott
	 * MeghirdetettTantargy-hoz tartozó vizsgákat fogja kilistázni.
	 */
	public VizsgaTablePanel(final JTable meghirdetettTantargyTable, final Object[][] meghirdetettTantargyData) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		vizsgaTable = new JTable();
		
		vizsgaTableModel = new DefaultTableModel();
		vizsgaTableModel.addColumn("Időpont");
		vizsgaTableModel.addColumn("Terem");
		
		ListSelectionModel listSelectionModel = meghirdetettTantargyTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				vizsgaTableModel.setRowCount(0);
				meghirdetettTantargyVizsgai.clear();
				
				if (meghirdetettTantargyTable.getSelectedRowCount() != 1) {
					return;
				}
				int index = meghirdetettTantargyTable.getSelectedRow();
				MeghirdetettTantargy meghirdetettTantargy = (MeghirdetettTantargy) meghirdetettTantargyData[index][3];
				
				for (Vizsga vizsga : Kozpont.getMeghirdetettVizsgák()) {
					if (vizsga.getMeghirdetettTantargy().equals(meghirdetettTantargy)) {
						vizsgaTableModel.addRow(new Object[] {sdf.format(vizsga.getIdőpont()), vizsga.getTerem()});
						meghirdetettTantargyVizsgai.add(vizsga);	
					}
				}
			}
		});
		
		vizsgaTable.setModel(vizsgaTableModel);
		scrollPane.setViewportView(vizsgaTable);
		
	}
	
	public List<Vizsga> getMeghirdetettTantargyVizsgai() {
		return meghirdetettTantargyVizsgai;
	}
	
	public JTable getVizsgaTable() {
		return vizsgaTable;
	}
	public DefaultTableModel getVizsgaTableModel() {
		return vizsgaTableModel;
	}
	
	public Vizsga getSelectedVizsga() {
		if (vizsgaTable.getSelectedRowCount() != 1) {
			return null;
		}
		int index = vizsgaTable.getSelectedRow();
		return meghirdetettTantargyVizsgai.get(index);
	}
}
