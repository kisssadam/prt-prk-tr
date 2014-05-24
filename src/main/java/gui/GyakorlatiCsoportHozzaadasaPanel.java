package gui;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JScrollPane;

import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import tanulmanyiRendszer.GyakorlatiCsoport;
import tanulmanyiRendszer.Idopont;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.MeghirdetettTantargy;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.Tantargy;
import tanulmanyiRendszer.TanulmanyiOsztaly;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Lehetővé teszi a {@link TanulmanyiOsztaly} számára, hogy hozzáadjon egy
 * {@link GyakorlatiCsoport}-ot egy {@link MeghirdetettTantargy}-hoz.
 * @author adam
 *
 */
public class GyakorlatiCsoportHozzaadasaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tantargyTable;
	private JTable gyakorlatiCsoportTable;
	private JTextField teremField;
	private JTable gyakorlatVezetőTable;

	/**
	 * Create the panel.
	 */
	public GyakorlatiCsoportHozzaadasaPanel(final JPanel contentPane,
			final BorderLayout borderLayout) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 118, 77, 99, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblGyakorlatiCsoportHozzadsa = new JLabel(
				"Gyakorlati csoport hozzáadása");
		lblGyakorlatiCsoportHozzadsa.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblGyakorlatiCsoportHozzadsa = new GridBagConstraints();
		gbc_lblGyakorlatiCsoportHozzadsa.gridwidth = 2;
		gbc_lblGyakorlatiCsoportHozzadsa.insets = new Insets(0, 0, 5, 5);
		gbc_lblGyakorlatiCsoportHozzadsa.gridx = 0;
		gbc_lblGyakorlatiCsoportHozzadsa.gridy = 0;
		add(lblGyakorlatiCsoportHozzadsa, gbc_lblGyakorlatiCsoportHozzadsa);

		JLabel lblNewLabel = new JLabel("Meghirdetett tantárgyak:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		List<MeghirdetettTantargy> aktuálisanMeghirdetettTantárgyLista = Kozpont
				.getAktuálisanMeghirdetettTantárgyLista();
		final Object[][] aktuálisanMeghirdetettTantárgyData = new Object[aktuálisanMeghirdetettTantárgyLista
				.size()][8];
		for (int i = 0; i < aktuálisanMeghirdetettTantárgyData.length; i++) {
			MeghirdetettTantargy mt = aktuálisanMeghirdetettTantárgyLista
					.get(i);
			Tantargy tantárgy = mt.getTantárgy();
			aktuálisanMeghirdetettTantárgyData[i][0] = tantárgy
					.getTantárgykód();
			aktuálisanMeghirdetettTantárgyData[i][1] = tantárgy.getNév();
			aktuálisanMeghirdetettTantárgyData[i][2] = tantárgy.getKredit();
			aktuálisanMeghirdetettTantárgyData[i][3] = tantárgy.getSzak();
			Oktato előadó = mt.getElőadó();
			aktuálisanMeghirdetettTantárgyData[i][4] = előadó.getVezetéknév()
					+ " " + előadó.getKeresztnév();
			aktuálisanMeghirdetettTantárgyData[i][5] = mt.getElőadásIdőpont();
			aktuálisanMeghirdetettTantárgyData[i][6] = mt.getElőadásTerem();
			aktuálisanMeghirdetettTantárgyData[i][7] = mt;
		}

		String[] amtOszlopNevek = { "Tantárgykód", "Név", "Kredit", "Szak",
				"Előadó", "Előadás Időpont", "Előadás Terem" };

		DefaultTableModel amtDefaultTableModel = new DefaultTableModel(
				aktuálisanMeghirdetettTantárgyData, amtOszlopNevek);

		tantargyTable = new JTable();
		tantargyTable.setModel(amtDefaultTableModel);
		scrollPane.setViewportView(tantargyTable);

		JLabel lblGyakorlatvezet = new JLabel("Oktatók:");
		GridBagConstraints gbc_lblGyakorlatvezet = new GridBagConstraints();
		gbc_lblGyakorlatvezet.anchor = GridBagConstraints.EAST;
		gbc_lblGyakorlatvezet.insets = new Insets(0, 0, 5, 5);
		gbc_lblGyakorlatvezet.gridx = 0;
		gbc_lblGyakorlatvezet.gridy = 2;
		add(lblGyakorlatvezet, gbc_lblGyakorlatvezet);

		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 2;
		add(scrollPane_2, gbc_scrollPane_2);

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",
				Kozpont.getLocale());
		List<Oktato> oktatóLista = Kozpont.getOktatóLista();
		final Object[][] gyakorlatvezetők = new Object[oktatóLista.size()][6];
		for (int i = 0; i < gyakorlatvezetők.length; i++) {
			Oktato oktató = oktatóLista.get(i);
			gyakorlatvezetők[i][0] = oktató.getVezetéknév();
			gyakorlatvezetők[i][1] = oktató.getKeresztnév();
			gyakorlatvezetők[i][2] = oktató.getFelhasználónév();
			gyakorlatvezetők[i][3] = sdf.format(oktató.getSzületésnap());
			gyakorlatvezetők[i][4] = oktató.getFizetés();
			gyakorlatvezetők[i][5] = oktató;
		}
		String[] gyakorlatvezetőOszlopNevek = { "Vezetéknév", "Keresztnév",
				"Felhasználónév", "Születésnap", "Fizetés" };
		DefaultTableModel gyakorlatVezetőDefaultTableModel = new DefaultTableModel(
				gyakorlatvezetők, gyakorlatvezetőOszlopNevek);
		gyakorlatVezetőTable = new JTable();
		gyakorlatVezetőTable.setModel(gyakorlatVezetőDefaultTableModel);
		scrollPane_2.setViewportView(gyakorlatVezetőTable);

		JLabel lblKivlasztottTantrgyGyakorlati = new JLabel(
				"Gyakorlati csoportok:");
		GridBagConstraints gbc_lblKivlasztottTantrgyGyakorlati = new GridBagConstraints();
		gbc_lblKivlasztottTantrgyGyakorlati.anchor = GridBagConstraints.EAST;
		gbc_lblKivlasztottTantrgyGyakorlati.insets = new Insets(0, 0, 5, 5);
		gbc_lblKivlasztottTantrgyGyakorlati.gridx = 0;
		gbc_lblKivlasztottTantrgyGyakorlati.gridy = 3;
		add(lblKivlasztottTantrgyGyakorlati,
				gbc_lblKivlasztottTantrgyGyakorlati);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 3;
		add(scrollPane_1, gbc_scrollPane_1);

		final DefaultTableModel gyakorlatiCsoportDefaultTableModel = new DefaultTableModel();
		gyakorlatiCsoportDefaultTableModel.addColumn("Gyakorlatvezető");
		gyakorlatiCsoportDefaultTableModel.addColumn("Terem");
		gyakorlatiCsoportDefaultTableModel.addColumn("Időpont");
		gyakorlatiCsoportTable = new JTable();
		gyakorlatiCsoportTable.setModel(gyakorlatiCsoportDefaultTableModel);
		scrollPane_1.setViewportView(gyakorlatiCsoportTable);

		JLabel lblTerem = new JLabel("Terem:");
		GridBagConstraints gbc_lblTerem = new GridBagConstraints();
		gbc_lblTerem.anchor = GridBagConstraints.EAST;
		gbc_lblTerem.insets = new Insets(0, 0, 5, 5);
		gbc_lblTerem.gridx = 0;
		gbc_lblTerem.gridy = 4;
		add(lblTerem, gbc_lblTerem);

		teremField = new JTextField();
		GridBagConstraints gbc_teremField = new GridBagConstraints();
		gbc_teremField.insets = new Insets(0, 0, 5, 0);
		gbc_teremField.fill = GridBagConstraints.HORIZONTAL;
		gbc_teremField.gridx = 1;
		gbc_teremField.gridy = 4;
		add(teremField, gbc_teremField);
		teremField.setColumns(10);

		JLabel lblIdpont = new JLabel("Időpont:");
		GridBagConstraints gbc_lblIdpont = new GridBagConstraints();
		gbc_lblIdpont.anchor = GridBagConstraints.EAST;
		gbc_lblIdpont.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdpont.gridx = 0;
		gbc_lblIdpont.gridy = 5;
		add(lblIdpont, gbc_lblIdpont);

		final IdopontValasztoPanel idopontValasztoPanel = new IdopontValasztoPanel();
		GridBagConstraints gbc_idopontValasztoPanel = new GridBagConstraints();
		gbc_idopontValasztoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_idopontValasztoPanel.anchor = GridBagConstraints.WEST;
		gbc_idopontValasztoPanel.fill = GridBagConstraints.VERTICAL;
		gbc_idopontValasztoPanel.gridx = 1;
		gbc_idopontValasztoPanel.gridy = 5;
		add(idopontValasztoPanel, gbc_idopontValasztoPanel);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 6;
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
				int numOfSelectedMeghirdetettTantárgy = tantargyTable
						.getSelectedRowCount();
				if (numOfSelectedMeghirdetettTantárgy > 1) {
					JOptionPane
							.showMessageDialog(
									contentPane,
									"Csak egy meghirdetett tantárgyat lehet kiválasztani!",
									"Figyelmeztetés",
									JOptionPane.WARNING_MESSAGE);
					return;
				} else if (numOfSelectedMeghirdetettTantárgy < 1) {
					JOptionPane.showMessageDialog(contentPane,
							"Válassz ki egy meghirdetett tantárgyat!",
							"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					return;
				}
				MeghirdetettTantargy meghirdetettTantárgy = (MeghirdetettTantargy) aktuálisanMeghirdetettTantárgyData[tantargyTable
						.getSelectedRow()][7];

				Idopont gyakorlatIdőpont;
				try {
					gyakorlatIdőpont = new Idopont(idopontValasztoPanel
							.getSelectedNap(), idopontValasztoPanel.getÓra(),
							idopontValasztoPanel.getPerc());
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane,
							e1.getMessage(),
							"Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int numOfSelectedGyakorlatVezető = gyakorlatVezetőTable
						.getSelectedRowCount();
				if (numOfSelectedGyakorlatVezető > 1) {
					JOptionPane.showMessageDialog(contentPane,
							"Csak egy gyakorlatvezetőt lehet kiválasztani!",
							"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (numOfSelectedGyakorlatVezető < 1) {
					JOptionPane.showMessageDialog(contentPane,
							"A gyakorlatvezető kiválasztása kötelező!",
							"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String terem = teremField.getText();
				if (terem.equals("")) {
					JOptionPane.showMessageDialog(contentPane,
							"A terem megadása kötelező!", "Figyelmeztetés",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				GyakorlatiCsoport gyakorlatiCsoport = new GyakorlatiCsoport(
						(Oktato) gyakorlatvezetők[gyakorlatVezetőTable
								.getSelectedRow()][5], terem, gyakorlatIdőpont);

				TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont
						.getBejelentkezettFelhasználó();
				try {
					to.gyakorlatiCsoportHozzáadása(meghirdetettTantárgy,
							gyakorlatiCsoport);

					Oktato gyakorlatVezető = gyakorlatiCsoport
							.getGyakorlatvezető();

					StringBuilder sb = new StringBuilder();
					sb.append(gyakorlatVezető.getVezetéknév());
					sb.append(" ");
					sb.append(gyakorlatVezető.getKeresztnév());

					gyakorlatiCsoportDefaultTableModel.addRow(new Object[] {
							sb.toString(), gyakorlatiCsoport.getTerem(),
							gyakorlatiCsoport.getIdőpont() });
					contentPane.validate();
				} catch (TanulmanyiRendszerKivetel ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(),
							"Hiba", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnHozzads);

		ListSelectionModel listSelectionModel = tantargyTable
				.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (tantargyTable.getSelectedRowCount() == 1) {
							int index = tantargyTable.getSelectedRow();
							MeghirdetettTantargy mt = (MeghirdetettTantargy) aktuálisanMeghirdetettTantárgyData[index][7];
							List<GyakorlatiCsoport> gyakorlatiCsoportLista = mt
									.getGyakorlatiCsoportok();

							gyakorlatiCsoportDefaultTableModel.setRowCount(0);
							StringBuilder sb = new StringBuilder();
							for (GyakorlatiCsoport gycs : gyakorlatiCsoportLista) {
								sb.delete(0, sb.length());
								sb.append(gycs.getGyakorlatvezető()
										.getVezetéknév());
								sb.append(" ");
								sb.append(gycs.getGyakorlatvezető()
										.getKeresztnév());
								Object[] row = { sb.toString(),
										gycs.getTerem(), gycs.getIdőpont() };
								gyakorlatiCsoportDefaultTableModel.addRow(row);
							}

							contentPane.validate();
						}
					}
				});

	}
}
