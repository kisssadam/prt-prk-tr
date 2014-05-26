package gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;

import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;

public class HallgatoListazoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable hallgatoTable;
	private DefaultTableModel hallgatoTableModel;

	/**
	 * Create the panel.
	 */
	public HallgatoListazoPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		hallgatoTableModel = new DefaultTableModel();
		hallgatoTableModel.addColumn("Vezetéknév");
		hallgatoTableModel.addColumn("Keresztnév");
		hallgatoTableModel.addColumn("Felhasználónév");
		hallgatoTableModel.addColumn("Szak");
		hallgatoTableModel.addColumn("Születésnap");
		
		for (Hallgato hallgato : Kozpont.getHallgatóLista()) {
			hallgatoTableModel.addRow(new Object[] {
					hallgato.getVezetéknév(),
					hallgato.getKeresztnév(),
					hallgato.getFelhasználónév(),
					hallgato.getSzak(),
					hallgato.getSzületésnap()
			});
		}
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		hallgatoTable = new JTable();
		hallgatoTable.setModel(hallgatoTableModel);
		scrollPane.setViewportView(hallgatoTable);
		
		
	}
	
	public JTable getHallgatoTable() {
		return hallgatoTable;
	}
	
	public DefaultTableModel getHallgatoTableModel() {
		return hallgatoTableModel;
	}
	
}
