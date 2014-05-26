package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractButton;

import tanulmanyiRendszer.Felev;
import tanulmanyiRendszer.Idopont;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.MeghirdetettTantargy;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.Tantargy;
import tanulmanyiRendszer.TanulmanyiOsztaly;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

/**
 * Lehetővé teszi a {@link TanulmanyiOsztaly} számára, hogy meghirdessen
 * egy {@link Tantargy}-at.
 * @author adam
 *
 */
public class TantargyakMeghirdetesePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField eloadasTeremField;
	private JTable oktatoTable;

	/**
	 * Create the panel.
	 */
	public TantargyakMeghirdetesePanel(final JPanel contentPane, final BorderLayout borderLayout) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 144, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 94, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblTantrgyakMeghirdetse = new JLabel("Tantárgyak meghirdetése");
		lblTantrgyakMeghirdetse.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblTantrgyakMeghirdetse = new GridBagConstraints();
		gbc_lblTantrgyakMeghirdetse.gridwidth = 3;
		gbc_lblTantrgyakMeghirdetse.insets = new Insets(0, 0, 5, 0);
		gbc_lblTantrgyakMeghirdetse.gridx = 0;
		gbc_lblTantrgyakMeghirdetse.gridy = 0;
		add(lblTantrgyakMeghirdetse, gbc_lblTantrgyakMeghirdetse);

		JLabel lblTantrgy = new JLabel("Tantárgy:");
		GridBagConstraints gbc_lblTantrgy = new GridBagConstraints();
		gbc_lblTantrgy.anchor = GridBagConstraints.EAST;
		gbc_lblTantrgy.insets = new Insets(0, 0, 5, 5);
		gbc_lblTantrgy.gridx = 0;
		gbc_lblTantrgy.gridy = 1;
		add(lblTantrgy, gbc_lblTantrgy);

		JScrollPane tantargyScrollPane = new JScrollPane();
		GridBagConstraints gbc_tantargyScrollPane = new GridBagConstraints();
		gbc_tantargyScrollPane.gridheight = 3;
		gbc_tantargyScrollPane.gridwidth = 2;
		gbc_tantargyScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_tantargyScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tantargyScrollPane.gridx = 1;
		gbc_tantargyScrollPane.gridy = 1;
		add(tantargyScrollPane, gbc_tantargyScrollPane);

		final TantargyListazoPanel tantargyTable = new TantargyListazoPanel();
		tantargyScrollPane.setViewportView(tantargyTable);

		JLabel lblOktat = new JLabel("Oktató:");
		GridBagConstraints gbc_lblOktat = new GridBagConstraints();
		gbc_lblOktat.anchor = GridBagConstraints.EAST;
		gbc_lblOktat.insets = new Insets(0, 0, 5, 5);
		gbc_lblOktat.gridx = 0;
		gbc_lblOktat.gridy = 4;
		add(lblOktat, gbc_lblOktat);

		JScrollPane oktatoScrollPane = new JScrollPane();
		GridBagConstraints gbc_oktatoScrollPane = new GridBagConstraints();
		gbc_oktatoScrollPane.gridwidth = 2;
		gbc_oktatoScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_oktatoScrollPane.fill = GridBagConstraints.BOTH;
		gbc_oktatoScrollPane.gridx = 1;
		gbc_oktatoScrollPane.gridy = 4;
		add(oktatoScrollPane, gbc_oktatoScrollPane);

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Kozpont.getLocale());
		List<Oktato> oktatóLista = Kozpont.getOktatóLista();
		final Object[][] oktatóData = new Object[oktatóLista.size()][6];
		for (int i = 0; i < oktatóData.length; i++) {
			Oktato oktató = oktatóLista.get(i);
			oktatóData[i][0] = oktató.getVezetéknév();
			oktatóData[i][1] = oktató.getKeresztnév();
			oktatóData[i][2] = oktató.getFelhasználónév();
			oktatóData[i][3] = sdf.format(oktató.getSzületésnap());
			oktatóData[i][4] = oktató.getFizetés();
			oktatóData[i][5] = oktató;
		}
		String[] oktatóOszlopNevek = { "Vezetéknév", "Keresztnév",
				"Felhasználónév", "Születésnap", "Fizetés" };
		DefaultTableModel oktatóDefaultTableModel = new DefaultTableModel(
				oktatóData, oktatóOszlopNevek);
		oktatoTable = new JTable();
		oktatoTable.setModel(oktatóDefaultTableModel);
		oktatoScrollPane.setViewportView(oktatoTable);
		
		JLabel lblMeghirdetettTantrgyak = new JLabel("Meghirdetett Tantárgyak:");
		GridBagConstraints gbc_lblMeghirdetettTantrgyak = new GridBagConstraints();
		gbc_lblMeghirdetettTantrgyak.anchor = GridBagConstraints.EAST;
		gbc_lblMeghirdetettTantrgyak.insets = new Insets(0, 0, 5, 5);
		gbc_lblMeghirdetettTantrgyak.gridx = 0;
		gbc_lblMeghirdetettTantrgyak.gridy = 5;
		add(lblMeghirdetettTantrgyak, gbc_lblMeghirdetettTantrgyak);
		
		final MeghirdetettTantargyPanel meghirdetettTantargyListazoPanel = new MeghirdetettTantargyPanel();
		for (MeghirdetettTantargy mt : Kozpont.getAktuálisanMeghirdetettTantárgyLista()) {
			meghirdetettTantargyListazoPanel.getMeghirdetettTantargyTableModel().addRow(
					new Object[] {mt.getTantárgy().getNév(), mt.getElőadásIdőpont(), mt.getElőadásTerem()});
		}
		GridBagConstraints gbc_meghirdetettTantargyListazoPanel = new GridBagConstraints();
		gbc_meghirdetettTantargyListazoPanel.gridwidth = 2;
		gbc_meghirdetettTantargyListazoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_meghirdetettTantargyListazoPanel.fill = GridBagConstraints.BOTH;
		gbc_meghirdetettTantargyListazoPanel.gridx = 1;
		gbc_meghirdetettTantargyListazoPanel.gridy = 5;
		add(meghirdetettTantargyListazoPanel, gbc_meghirdetettTantargyListazoPanel);

		JLabel lblEladsIdpont = new JLabel("Előadás időpont:");
		GridBagConstraints gbc_lblEladsIdpont = new GridBagConstraints();
		gbc_lblEladsIdpont.anchor = GridBagConstraints.EAST;
		gbc_lblEladsIdpont.insets = new Insets(0, 0, 5, 5);
		gbc_lblEladsIdpont.gridx = 0;
		gbc_lblEladsIdpont.gridy = 6;
		add(lblEladsIdpont, gbc_lblEladsIdpont);

		final IdopontValasztoPanel időpontVálasztóPanel = new IdopontValasztoPanel();
		GridBagConstraints gbc_meghirdetettTantargyListazoPanel1 = new GridBagConstraints();
		gbc_meghirdetettTantargyListazoPanel1.anchor = GridBagConstraints.WEST;
		gbc_meghirdetettTantargyListazoPanel1.insets = new Insets(0, 0, 5, 5);
		gbc_meghirdetettTantargyListazoPanel1.fill = GridBagConstraints.VERTICAL;
		gbc_meghirdetettTantargyListazoPanel1.gridx = 1;
		gbc_meghirdetettTantargyListazoPanel1.gridy = 6;
		add(időpontVálasztóPanel, gbc_meghirdetettTantargyListazoPanel1);

		JLabel lblEladsTerem = new JLabel("Előadás terem:");
		GridBagConstraints gbc_lblEladsTerem = new GridBagConstraints();
		gbc_lblEladsTerem.anchor = GridBagConstraints.EAST;
		gbc_lblEladsTerem.insets = new Insets(0, 0, 5, 5);
		gbc_lblEladsTerem.gridx = 0;
		gbc_lblEladsTerem.gridy = 7;
		add(lblEladsTerem, gbc_lblEladsTerem);

		eloadasTeremField = new JTextField();
		GridBagConstraints gbc_eloadasTeremField = new GridBagConstraints();
		gbc_eloadasTeremField.gridwidth = 2;
		gbc_eloadasTeremField.insets = new Insets(0, 0, 5, 0);
		gbc_eloadasTeremField.fill = GridBagConstraints.HORIZONTAL;
		gbc_eloadasTeremField.gridx = 1;
		gbc_eloadasTeremField.gridy = 7;
		add(eloadasTeremField, gbc_eloadasTeremField);
		eloadasTeremField.setColumns(10);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_panel.gridwidth = 2;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 8;
		add(panel, gbc_panel);

		JButton btnMgsem = new JButton("Mégsem");
		btnMgsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new TanulmanyiOsztalyPanel(contentPane, borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		panel.add(btnMgsem);

		JButton btnMeghirdets = new JButton("Meghirdetés");
		btnMeghirdets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tantargy tantargy = tantargyTable.getSelectedTantargy();
				if (tantargy == null) {
					JOptionPane.showMessageDialog(contentPane, "Ki kell választani egy tantárgyat!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int kiválasztottOktatókSzáma = oktatoTable.getSelectedRowCount();
				if (kiválasztottOktatókSzáma > 1) {
					JOptionPane.showMessageDialog(contentPane, "Csak egy oktatót lehet kiválasztani!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (kiválasztottOktatókSzáma < 1) {
					JOptionPane.showMessageDialog(contentPane, "Ki kell választani egy oktatót!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Oktato oktató = (Oktato) oktatóData[oktatoTable.getSelectedRow()][5];

				Idopont előadásIdőpont;
				try {
					előadásIdőpont = new Idopont(
							időpontVálasztóPanel.getSelectedNap(),
							időpontVálasztóPanel.getÓra(),
							időpontVálasztóPanel.getPerc());
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane,
							e1.getMessage(),
							"Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String előadásTerem = eloadasTeremField.getText();
				if (előadásTerem.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "A terem megadása kötelező!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Felev aktuálisFélév = Kozpont.getAktuálisFélév();
				if (aktuálisFélév == null) {
					JOptionPane.showMessageDialog(contentPane, "Nincs aktuális félév!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}

				TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont.getBejelentkezettFelhasználó();
				try {
					MeghirdetettTantargy mt = to.tantárgyMeghirdetése(tantargy, oktató, aktuálisFélév, előadásIdőpont, előadásTerem);
					meghirdetettTantargyListazoPanel.getMeghirdetettTantargyTableModel().addRow(
							new Object[] {tantargy.getNév(), mt.getElőadásIdőpont(), mt.getElőadásTerem()});
				} catch (TanulmanyiRendszerKivetel ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		panel.add(btnMeghirdets);

	}

	/**
	 * http://stackoverflow.com/questions/201287/how-do-i-get-which-jradiobutton-is-selected-from-a-buttongroup
	 * 
	 * @param buttonGroup
	 * @return a kiválasztott nap sztring reprezentációját adja vissza.
	 */
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			
			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}
}
