package gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JTable;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import tanulmanyiRendszer.Felev;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.TanulmanyiOsztaly;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

/**
 * A konstruktorát meghívva megjelenít egy olyan panelt, ami lehetővé teszi,
 * hogy a {@link TanulmanyiOsztaly} beállítsa az aktuális félévet.
 * 
 * @author adam
 *
 */
public class AktualisFelevBellitasaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	private JTable aktuálisFélévTable;

	/**
	 * Create the panel.
	 */
	public AktualisFelevBellitasaPanel(final JPanel contentPane,
			final BorderLayout borderLayout) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",
				Kozpont.getLocale());
		final Object[][] data = new Object[Kozpont.getFélévLista().size()][5];
		for (int i = 0; i < Kozpont.getFélévLista().size(); i++) {
			Felev félév = Kozpont.getFélévLista().get(i);
			data[i][0] = sdf.format(félév.getSzorgalmiIdőszak().getEleje());
			data[i][1] = sdf.format(félév.getSzorgalmiIdőszak().getVége());
			data[i][2] = sdf.format(félév.getVizsgaIdőszak().getEleje());
			data[i][3] = sdf.format(félév.getVizsgaIdőszak().getVége());
			data[i][4] = félév;
		}
		final String[] oszlopNevek = new String[] { "Szorgalmi időszak eleje",
				"Szorgalmi időszak vége", "Vizsgaidőszak eleje",
				"Vizsgaidőszak vége" };
		final DefaultTableModel defaultTableModel = new DefaultTableModel(data,
				oszlopNevek);

		JLabel lblAktulisFlvBelltsa = new JLabel("Aktuális félév beállítása");
		lblAktulisFlvBelltsa.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblAktulisFlvBelltsa = new GridBagConstraints();
		gbc_lblAktulisFlvBelltsa.gridwidth = 10;
		gbc_lblAktulisFlvBelltsa.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktulisFlvBelltsa.gridx = 0;
		gbc_lblAktulisFlvBelltsa.gridy = 0;
		add(lblAktulisFlvBelltsa, gbc_lblAktulisFlvBelltsa);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setModel(defaultTableModel);
		scrollPane.setViewportView(table);

		final Object[][] aktuálisFélévData = new Object[1][4];
		final Felev aktuálisFélév = Kozpont.getAktuálisFélév();
		if (aktuálisFélév != null) {
			aktuálisFélévData[0][0] = sdf.format(aktuálisFélév
					.getSzorgalmiIdőszak().getEleje());
			aktuálisFélévData[0][1] = sdf.format(aktuálisFélév
					.getSzorgalmiIdőszak().getVége());
			aktuálisFélévData[0][2] = sdf.format(aktuálisFélév
					.getVizsgaIdőszak().getEleje());
			aktuálisFélévData[0][3] = sdf.format(aktuálisFélév
					.getVizsgaIdőszak().getVége());
		} else {
			for (int i = 0; i < aktuálisFélévData[0].length; i++) {
				aktuálisFélévData[0][i] = "";
			}
		}

		final DefaultTableModel defaultTableModelForAktuálisFélév = new DefaultTableModel(
				aktuálisFélévData, oszlopNevek);
				
						JLabel lblAktulisFlv = new JLabel("Aktuális félév:");
						GridBagConstraints gbc_lblAktulisFlv = new GridBagConstraints();
						gbc_lblAktulisFlv.anchor = GridBagConstraints.EAST;
						gbc_lblAktulisFlv.insets = new Insets(0, 0, 5, 5);
						gbc_lblAktulisFlv.gridx = 0;
						gbc_lblAktulisFlv.gridy = 7;
						add(lblAktulisFlv, gbc_lblAktulisFlv);
		
				JScrollPane scrollPane_1 = new JScrollPane();
				GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
				gbc_scrollPane_1.gridheight = 2;
				gbc_scrollPane_1.gridwidth = 9;
				gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_1.gridx = 1;
				gbc_scrollPane_1.gridy = 7;
				add(scrollPane_1, gbc_scrollPane_1);
				
						aktuálisFélévTable = new JTable();
						aktuálisFélévTable.setModel(defaultTableModelForAktuálisFélév);
						scrollPane_1.setViewportView(aktuálisFélévTable);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_panel.gridwidth = 7;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 9;
		add(panel, gbc_panel);

		JButton btnMgsem = new JButton("Mégsem");
		btnMgsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout
						.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new TanulmanyiOsztalyPanel(contentPane,
						borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		panel.add(btnMgsem);

		JButton btnNewButton = new JButton("Beállítás");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowCount = table.getSelectedRowCount();
				if (selectedRowCount > 1) {
					JOptionPane.showMessageDialog(contentPane,
							"Egyszerre csak egy félév lehet aktív!", "Hiba",
							JOptionPane.ERROR_MESSAGE);
				} else if (selectedRowCount == 1) {
					int index = table.getSelectedRow();
					TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont
							.getBejelentkezettFelhasználó();
					to.setAktuálisFélév((Felev) data[index][4]);
					Felev aktuálisFélév = Kozpont.getAktuálisFélév();
					defaultTableModelForAktuálisFélév.setValueAt(sdf
							.format(aktuálisFélév.getSzorgalmiIdőszak()
									.getEleje()), 0, 0);
					defaultTableModelForAktuálisFélév.setValueAt(sdf
							.format(aktuálisFélév.getSzorgalmiIdőszak()
									.getVége()), 0, 1);
					defaultTableModelForAktuálisFélév.setValueAt(
							sdf.format(aktuálisFélév.getVizsgaIdőszak()
									.getEleje()), 0, 2);
					defaultTableModelForAktuálisFélév.setValueAt(
							sdf.format(aktuálisFélév.getVizsgaIdőszak()
									.getVége()), 0, 3);
					contentPane.validate();
				} else if (selectedRowCount < 1) {
					JOptionPane.showMessageDialog(contentPane,
							"Nincs félév kiválasztva!", "Hiba",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton);

	}
}
