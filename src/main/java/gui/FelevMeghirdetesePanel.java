package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import org.jdesktop.swingx.JXDatePicker;

import tanulmanyiRendszer.Felev;
import tanulmanyiRendszer.Idoszak;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.TanulmanyiOsztaly;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

/**
 * Lehetővé teszi egy {@link TanulmanyiOsztaly} számára, hogy új félévet hirdessen meg.
 * 
 * @author adam
 *
 */
public class FelevMeghirdetesePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public FelevMeghirdetesePanel(final JPanel contentPane,
			final BorderLayout borderLayout) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Félév meghirdetése");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblEddigiFlvek = new JLabel("Eddigi félévek:");
		GridBagConstraints gbc_lblEddigiFlvek = new GridBagConstraints();
		gbc_lblEddigiFlvek.anchor = GridBagConstraints.EAST;
		gbc_lblEddigiFlvek.insets = new Insets(0, 0, 5, 5);
		gbc_lblEddigiFlvek.gridx = 1;
		gbc_lblEddigiFlvek.gridy = 1;
		add(lblEddigiFlvek, gbc_lblEddigiFlvek);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 8;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",
				Kozpont.getLocale());
		Object[][] data = new Object[Kozpont.getFélévLista().size()][4];
		for (int i = 0; i < Kozpont.getFélévLista().size(); i++) {
			Felev félév = Kozpont.getFélévLista().get(i);
			data[i][0] = sdf.format(félév.getSzorgalmiIdőszak().getEleje());
			data[i][1] = sdf.format(félév.getSzorgalmiIdőszak().getVége());
			data[i][2] = sdf.format(félév.getVizsgaIdőszak().getEleje());
			data[i][3] = sdf.format(félév.getVizsgaIdőszak().getVége());
		}
		String[] oszlopNevek = new String[] { "Szorgalmi időszak eleje",
				"Szorgalmi időszak vége", "Vizsgaidőszak eleje",
				"Vizsgaidőszak vége" };
		table = new JTable();

		final DefaultTableModel defaultTableModel = new DefaultTableModel(data,
				oszlopNevek);
		table.setModel(defaultTableModel);

		scrollPane.setViewportView(table);

		JLabel lblSzorgalmiIdszakEleje = new JLabel("Szorgalmi időszak eleje:");
		GridBagConstraints gbc_lblSzorgalmiIdszakEleje = new GridBagConstraints();
		gbc_lblSzorgalmiIdszakEleje.anchor = GridBagConstraints.EAST;
		gbc_lblSzorgalmiIdszakEleje.insets = new Insets(0, 0, 5, 5);
		gbc_lblSzorgalmiIdszakEleje.gridx = 1;
		gbc_lblSzorgalmiIdszakEleje.gridy = 10;
		add(lblSzorgalmiIdszakEleje, gbc_lblSzorgalmiIdszakEleje);

		final JXDatePicker szorgalmiIdőszakElejePicker = new JXDatePicker(
				Kozpont.getLocale());
		GridBagConstraints gbc_szorgalmiIdőszakElejePicker = new GridBagConstraints();
		gbc_szorgalmiIdőszakElejePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_szorgalmiIdőszakElejePicker.insets = new Insets(0, 0, 5, 5);
		gbc_szorgalmiIdőszakElejePicker.gridx = 2;
		gbc_szorgalmiIdőszakElejePicker.gridy = 10;
		add(szorgalmiIdőszakElejePicker, gbc_szorgalmiIdőszakElejePicker);

		JLabel lblSzorgalmiIdszakVge = new JLabel("Szorgalmi Időszak vége:");
		GridBagConstraints gbc_lblSzorgalmiIdszakVge = new GridBagConstraints();
		gbc_lblSzorgalmiIdszakVge.anchor = GridBagConstraints.EAST;
		gbc_lblSzorgalmiIdszakVge.insets = new Insets(0, 0, 5, 5);
		gbc_lblSzorgalmiIdszakVge.gridx = 1;
		gbc_lblSzorgalmiIdszakVge.gridy = 11;
		add(lblSzorgalmiIdszakVge, gbc_lblSzorgalmiIdszakVge);

		final JXDatePicker szorgalmiIdőszakVégePicker = new JXDatePicker(
				Kozpont.getLocale());
		GridBagConstraints gbc_szorgalmiIdőszakVégePicker = new GridBagConstraints();
		gbc_szorgalmiIdőszakVégePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_szorgalmiIdőszakVégePicker.insets = new Insets(0, 0, 5, 5);
		gbc_szorgalmiIdőszakVégePicker.gridx = 2;
		gbc_szorgalmiIdőszakVégePicker.gridy = 11;
		add(szorgalmiIdőszakVégePicker, gbc_szorgalmiIdőszakVégePicker);

		JLabel lblVizsgaidszakEleje = new JLabel("Vizsgaidőszak eleje:");
		GridBagConstraints gbc_lblVizsgaidszakEleje = new GridBagConstraints();
		gbc_lblVizsgaidszakEleje.anchor = GridBagConstraints.EAST;
		gbc_lblVizsgaidszakEleje.insets = new Insets(0, 0, 5, 5);
		gbc_lblVizsgaidszakEleje.gridx = 1;
		gbc_lblVizsgaidszakEleje.gridy = 12;
		add(lblVizsgaidszakEleje, gbc_lblVizsgaidszakEleje);

		final JXDatePicker vizsgaIdőszakElejePicker = new JXDatePicker(
				Kozpont.getLocale());
		GridBagConstraints gbc_vizsgaIdőszakElejePicker = new GridBagConstraints();
		gbc_vizsgaIdőszakElejePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_vizsgaIdőszakElejePicker.insets = new Insets(0, 0, 5, 5);
		gbc_vizsgaIdőszakElejePicker.gridx = 2;
		gbc_vizsgaIdőszakElejePicker.gridy = 12;
		add(vizsgaIdőszakElejePicker, gbc_vizsgaIdőszakElejePicker);

		JLabel lblVizsgaidszakVge = new JLabel("Vizsgaidőszak vége:");
		GridBagConstraints gbc_lblVizsgaidszakVge = new GridBagConstraints();
		gbc_lblVizsgaidszakVge.anchor = GridBagConstraints.EAST;
		gbc_lblVizsgaidszakVge.insets = new Insets(0, 0, 5, 5);
		gbc_lblVizsgaidszakVge.gridx = 1;
		gbc_lblVizsgaidszakVge.gridy = 13;
		add(lblVizsgaidszakVge, gbc_lblVizsgaidszakVge);

		final JXDatePicker vizsgaIdőszakVégePicker = new JXDatePicker(
				Kozpont.getLocale());
		GridBagConstraints gbc_vizsgaIdőszakVégePicker = new GridBagConstraints();
		gbc_vizsgaIdőszakVégePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_vizsgaIdőszakVégePicker.insets = new Insets(0, 0, 5, 5);
		gbc_vizsgaIdőszakVégePicker.gridx = 2;
		gbc_vizsgaIdőszakVégePicker.gridy = 13;
		add(vizsgaIdőszakVégePicker, gbc_vizsgaIdőszakVégePicker);

		final JCheckBox chckbxLegyenEzAz = new JCheckBox(
				"Legyen ez az aktuális félév");
		chckbxLegyenEzAz.setSelected(true);
		GridBagConstraints gbc_chckbxLegyenEzAz = new GridBagConstraints();
		gbc_chckbxLegyenEzAz.gridwidth = 2;
		gbc_chckbxLegyenEzAz.anchor = GridBagConstraints.WEST;
		gbc_chckbxLegyenEzAz.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxLegyenEzAz.gridx = 2;
		gbc_chckbxLegyenEzAz.gridy = 14;
		add(chckbxLegyenEzAz, gbc_chckbxLegyenEzAz);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 15;
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

		JButton btnHozzads = new JButton("Hozzáadás");
		btnHozzads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date szorgEleje = szorgalmiIdőszakElejePicker.getDate();
				Date szorgVége = szorgalmiIdőszakVégePicker.getDate();
				Date vizsgEleje = vizsgaIdőszakElejePicker.getDate();
				Date vizsgVége = vizsgaIdőszakVégePicker.getDate();

				if (szorgEleje == null || szorgVége == null
						|| vizsgEleje == null || vizsgVége == null) {
					JOptionPane.showMessageDialog(contentPane,
							"Az összes mező kitöltése kötelező!",
							"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
				} else {
					Felev félév = new Felev(new Idoszak(szorgEleje, szorgVége),
							new Idoszak(vizsgEleje, vizsgVége));
					TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont
							.getBejelentkezettFelhasználó();
					try {
						to.újFélév(félév);
						defaultTableModel.addRow(new String[] {
								sdf.format(szorgEleje), sdf.format(szorgVége),
								sdf.format(vizsgEleje), sdf.format(vizsgVége) });
						if (chckbxLegyenEzAz.isSelected()) {
							to.setAktuálisFélév(félév);
						}
					} catch (TanulmanyiRendszerKivetel ex) {
						JOptionPane.showMessageDialog(contentPane,
								ex.getMessage(), "Figyelmeztetés",
								JOptionPane.WARNING_MESSAGE);
						contentPane.validate();
					}
				}
			}
		});
		panel.add(btnHozzads);

	}
}
