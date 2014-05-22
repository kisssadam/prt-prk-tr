package gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.FelvettVizsga;
import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;

/**
 * Kilistázza egy adott {@link Hallgato} által felvett vizsgákat ({@link FelvettVizsga}).
 * 
 * @author adam
 *
 */
public class FelvettVizsgakPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable felvettVizsgakTable;
	private DefaultTableModel felvettVizsgakTableModel;
	private SimpleDateFormat sdf = Kozpont.getVizsgaIdőpontFormat();
	/**
	 * Create the panel.
	 */
	public FelvettVizsgakPanel() {
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
		
		felvettVizsgakTable = new JTable();
		felvettVizsgakTableModel = new DefaultTableModel();
		felvettVizsgakTableModel.addColumn("Tantárgy");
		felvettVizsgakTableModel.addColumn("Időpont");
		felvettVizsgakTableModel.addColumn("Terem");
		felvettVizsgakTableModel.addColumn("Érdemjegy");
		
		Hallgato hallgato = (Hallgato) Kozpont.getBejelentkezettFelhasználó();
		for (FelvettVizsga felvettVizsga : hallgato.getFelvettVizsgák()) {
			if (felvettVizsga.getVizsga().getMeghirdetettTantargy().getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
				felvettVizsgakTableModel.addRow(new Object[] {felvettVizsga.getVizsga().getMeghirdetettTantargy().getTantárgy().getNév(),
															  sdf.format(felvettVizsga.getVizsga().getIdőpont()),
															  felvettVizsga.getVizsga().getTerem(),
															  felvettVizsga.getÉrdemjegy()});
			}
		}
		felvettVizsgakTable.setModel(felvettVizsgakTableModel);
		scrollPane.setViewportView(felvettVizsgakTable);

	}
	
	public JTable getFelvettVizsgakTable() {
		return felvettVizsgakTable;
	}
	
	public DefaultTableModel getFelvettVizsgakTableModel() {
		return felvettVizsgakTableModel;
	}
}
