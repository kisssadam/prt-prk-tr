package gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Oktato;

public class OktatoListazoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable oktatoTable;
	private DefaultTableModel oktatoTableModel;

	/**
	 * Create the panel.
	 */
	public OktatoListazoPanel() {
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
		
		oktatoTableModel = new DefaultTableModel();
		oktatoTableModel.addColumn("Vezetéknév");
		oktatoTableModel.addColumn("Keresztnév");
		oktatoTableModel.addColumn("Felhasználónév");
		oktatoTableModel.addColumn("Születésnap");
		oktatoTableModel.addColumn("Fizetés");
		
		for (Oktato oktato : Kozpont.getOktatóLista()) {
			oktatoTableModel.addRow(new Object[] {
					oktato.getVezetéknév(),
					oktato.getKeresztnév(),
					oktato.getFelhasználónév(),
					Kozpont.getSimpleDateFormat().format(oktato.getSzületésnap()),
					oktato.getFizetés()
			});
		}
		
		oktatoTable = new JTable();
		oktatoTable.setModel(oktatoTableModel);
		scrollPane.setViewportView(oktatoTable);

	}
	
	public JTable getOktatoTable() {
		return oktatoTable;
	}
	
	public DefaultTableModel getOktatoTableModel() {
		return oktatoTableModel;
	}
	
}
