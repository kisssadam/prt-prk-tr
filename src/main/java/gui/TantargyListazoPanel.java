package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Tantargy;

/**
 * Kilistázza az ismert {@link Tantargy}-akat.
 * @author adam
 *
 */
public class TantargyListazoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tantargyListazoTable;
	private DefaultTableModel tantargyListazoTableModel;
	private List<Tantargy> megjelenitettTantargyak;

	/**
	 * Create the panel.
	 */
	public TantargyListazoPanel() {
		megjelenitettTantargyak = new ArrayList<>();
		
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
		
		tantargyListazoTableModel = new DefaultTableModel();
		tantargyListazoTableModel.addColumn("Tantárgykód");
		tantargyListazoTableModel.addColumn("Név");
		tantargyListazoTableModel.addColumn("Kredit");
		tantargyListazoTableModel.addColumn("Szak");
		tantargyListazoTableModel.addColumn("Előfeltétel(ek)");
		
		megjelenitettTantargyak.clear();
		StringBuilder előfeltételBuilder = new StringBuilder(100);
		for (Tantargy tantargy : Kozpont.getTantárgyLista()) {
			előfeltételBuilder.delete(0, előfeltételBuilder.length());
			for (int i = 0; i < tantargy.getElőfeltételek().size(); i++) {
				Tantargy t = tantargy.getElőfeltételek().get(i);
				előfeltételBuilder.append(t.getTantárgykód());
				előfeltételBuilder.append(i+1 == tantargy.getElőfeltételek().size() ? "" : " ");
			}
			tantargyListazoTableModel.addRow(new Object[] {tantargy.getTantárgykód(),
														   tantargy.getNév(),
														   tantargy.getKredit(),
														   tantargy.getSzak(),
														   előfeltételBuilder.toString()});
			megjelenitettTantargyak.add(tantargy);
		}
		
		tantargyListazoTable = new JTable();
		tantargyListazoTable.setModel(tantargyListazoTableModel);
		scrollPane.setViewportView(tantargyListazoTable);
	}
	
	public JTable getTantargyListazoTable() {
		return tantargyListazoTable;
	}
	
	public DefaultTableModel getTantargyListazoTableModel() {
		return tantargyListazoTableModel;
	}
	
	public List<Tantargy> getMegjelenitettTantargyak() {
		return megjelenitettTantargyak;
	}
	
	public Tantargy getSelectedTantargy() {
		if (tantargyListazoTable.getSelectedRowCount() != 1) {
			return null;
		}
		return megjelenitettTantargyak.get(tantargyListazoTable.getSelectedRow());
	}
	
	public Tantargy[] getSelectedTantargyak() {
		List<Tantargy> kiválasztottTantárgyak = new ArrayList<>();
		
		for (Integer i : tantargyListazoTable.getSelectedRows()) {
			kiválasztottTantárgyak.add(megjelenitettTantargyak.get(i));
		
		}
		return kiválasztottTantárgyak.toArray(new Tantargy[kiválasztottTantárgyak.size()]);
	}
	
}
