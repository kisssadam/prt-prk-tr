package gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Szak;

/**
 * Megjeleníti a már korábban hozzáadott {@link Szak}-okat.
 * @author adam
 *
 */
public class SzakListazoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable szakListazoTable;
	private DefaultTableModel szakListazoTableModel;
	private List<Szak> megjelenitettSzakok;
	/**
	 * Create the panel.
	 */
	public SzakListazoPanel() {
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
		
		szakListazoTableModel = new DefaultTableModel();
		szakListazoTableModel.addColumn("Szak neve");
		szakListazoTableModel.addColumn("Szak szintje");
		
		megjelenitettSzakok = new ArrayList<>();
		for (Szak szak : Kozpont.getSzakLista()) {
			szakListazoTableModel.addRow(new Object[] {szak.getNév(), szak.getSzint()});
			megjelenitettSzakok.add(szak);
		}
		
		szakListazoTable = new JTable();
		szakListazoTable.setModel(szakListazoTableModel);
		scrollPane.setViewportView(szakListazoTable);

	}
	
	public JTable getSzakListazoTable() {
		return szakListazoTable;
	}
	
	public DefaultTableModel getSzakListazoTableModel() {
		return szakListazoTableModel;
	}
	
	public List<Szak> getMegjelenitettSzakok() {
		return megjelenitettSzakok;
	}
	
	/**
	 * Visszaadja a kiválasztott szakot.
	 * @return null ha 0 vagy 1-nél több szak van kiválasztva, egyébként pedig a kiválasztott szakot adja vissza.
	 */
	public Szak getSelectedSzak() {
		if (szakListazoTable.getSelectedRowCount() != 1) {
			return null;
		}
		return megjelenitettSzakok.get(szakListazoTable.getSelectedRow());
	}
	
}
