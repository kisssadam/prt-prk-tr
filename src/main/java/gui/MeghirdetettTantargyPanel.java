package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.GyakorlatiCsoport;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.MeghirdetettTantargy;

/**
 * Kilistázza az aktuális félévben {@link MeghirdetettTantargy}-okat.
 * 
 * @author adam
 *
 */
public class MeghirdetettTantargyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable meghirdetettTantargyTable;
	private DefaultTableModel meghirdetettTantargyTableModel;
	private Object[][] meghirdetettTantargyData;

	public MeghirdetettTantargyPanel() {
		this(false);
	}

	/**
	 * Create the panel. Ha a hallgatói panel példányosítja, akkor true-t kell
	 * átadni.
	 */
	public MeghirdetettTantargyPanel(boolean hallgato) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 393, 0 };
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

		meghirdetettTantargyTable = new JTable();
		if (hallgato) {
			int sorokSzáma = 0;
			for (MeghirdetettTantargy mt : Kozpont.getMeghirdetettTantárgyLista()) {
				if (!mt.getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
					continue;
				}
				++sorokSzáma;
			}
			meghirdetettTantargyData = new Object[sorokSzáma][4];
			int i = 0;
			for (MeghirdetettTantargy mt : Kozpont.getMeghirdetettTantárgyLista()) {
				if (mt.getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
					meghirdetettTantargyData[i][0] = mt.getTantárgy().getNév();
					meghirdetettTantargyData[i][1] = mt.getElőadásIdőpont();
					meghirdetettTantargyData[i][2] = mt.getElőadásTerem();
					meghirdetettTantargyData[i][3] = mt;
					++i;
				}
			}
		} else {
			int sorokSzáma = 0;
			for (MeghirdetettTantargy mt : Kozpont.getMeghirdetettTantárgyLista()) {
				if (!mt.getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
					continue;
				}
				if (mt.getElőadó().equals(Kozpont.getBejelentkezettFelhasználó())) {
					++sorokSzáma;
					continue;
				}
				for (GyakorlatiCsoport gycs : mt.getGyakorlatiCsoportok()) {
					if (gycs.getGyakorlatvezető().equals(Kozpont.getBejelentkezettFelhasználó())) {
						++sorokSzáma;
						continue;
					}
				}
			}
			meghirdetettTantargyData = new Object[sorokSzáma][4];
			int j = 0;
			for (int i = 0; i < Kozpont.getMeghirdetettTantárgyLista().size(); i++) {
				MeghirdetettTantargy mt = Kozpont.getMeghirdetettTantárgyLista().get(i);
				if (!mt.getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
					continue;
				}
				if (mt.getElőadó().equals(Kozpont.getBejelentkezettFelhasználó())) {
					meghirdetettTantargyData[j][0] = mt.getTantárgy().getNév();
					meghirdetettTantargyData[j][1] = mt.getElőadásIdőpont();
					meghirdetettTantargyData[j][2] = mt.getElőadásTerem();
					meghirdetettTantargyData[j][3] = mt;
					j++;
					continue;
				}
				for (GyakorlatiCsoport gycs : mt.getGyakorlatiCsoportok()) {
					if (gycs.getGyakorlatvezető().equals(Kozpont.getBejelentkezettFelhasználó())) {
						meghirdetettTantargyData[j][0] = mt.getTantárgy().getNév();
						meghirdetettTantargyData[j][1] = mt.getElőadásIdőpont();
						meghirdetettTantargyData[j][2] = mt.getElőadásTerem();
						meghirdetettTantargyData[j][3] = mt;
						j++;
						continue;
					}
				}
			}
		}
		String[] oszlopNevek = new String[] { "Tantárgy", "Előadás Időpont",
				"Előadás terem" };
		meghirdetettTantargyTableModel = new DefaultTableModel(meghirdetettTantargyData,
				oszlopNevek);
		meghirdetettTantargyTable.setModel(meghirdetettTantargyTableModel);
		scrollPane.setViewportView(meghirdetettTantargyTable);
	}

	public DefaultTableModel getMeghirdetettTantargyTableModel() {
		return meghirdetettTantargyTableModel;
	}

	public JTable getMeghirdetettTantargyTable() {
		return meghirdetettTantargyTable;
	}

	public Object[][] getMeghirdetettTantargyData() {
		return meghirdetettTantargyData;
	}
	
	public MeghirdetettTantargy getSelectedMeghirdetettTantargy() {
		if (meghirdetettTantargyTable.getSelectedRowCount() != 1) {
			return null;
		}
		int index = meghirdetettTantargyTable.getSelectedRow();
		return (MeghirdetettTantargy) meghirdetettTantargyData[index][3];
	}
}
