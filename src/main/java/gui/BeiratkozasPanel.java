package gui;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JButton;

import tanulmanyiRendszer.Felev;
import tanulmanyiRendszer.Hallgato;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

/**
 * Lehetővé teszi egy {@link Hallgato}-nak, hogy beiratkozzon az aktuális félévre.
 * 
 * @author adam
 *
 */
public class BeiratkozasPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField szorgalmiIdoszakElejeField;
	private JTextField szorgalmiIdoszakVegeField;
	private JTextField vizsgaidoszakElejeField;
	private JTextField vizsgaidoszakVegeField;
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",
			Kozpont.getLocale());

	/**
	 * Create the panel.
	 */
	public BeiratkozasPanel(final JPanel contentPane,
			final BorderLayout borderLayout) {

		JLabel lblBeiratkozsAKvetkez = new JLabel(
				"Beiratkozás a következő félévre:");
		lblBeiratkozsAKvetkez.setFont(new Font("Dialog", Font.BOLD, 14));

		JButton btnMgsem = new JButton("Mégsem");
		btnMgsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout
						.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new HallgatoPanel(contentPane, borderLayout),
						BorderLayout.CENTER);
				contentPane.validate();
			}
		});

		JButton btnBeiratkozs = new JButton("Beiratkozás");
		btnBeiratkozs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hallgato hallgato = (Hallgato) Kozpont
						.getBejelentkezettFelhasználó();
				try {
					hallgato.beiratkozás();
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(),
							"Hiba", JOptionPane.ERROR_MESSAGE);
				}
				contentPane.remove(borderLayout
						.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new HallgatoPanel(contentPane, borderLayout),
						BorderLayout.CENTER);
				contentPane.validate();
			}
		});

		JLabel lblSzorgalmiIdszakEleje = new JLabel("szorgalmi időszak eleje:");

		JLabel lblSzorgalmiIdszakVge = new JLabel("szorgalmi időszak vége:");

		JLabel lblVizsgaidszakEleje = new JLabel("vizsgaidőszak eleje:");

		JLabel lblVizsgaidszakVge = new JLabel("vizsgaidőszak vége:");

		Felev aktualisFelev = Kozpont.getAktuálisFélév();

		szorgalmiIdoszakElejeField = new JTextField();
		szorgalmiIdoszakElejeField.setEditable(false);
		szorgalmiIdoszakElejeField.setColumns(10);

		szorgalmiIdoszakVegeField = new JTextField();
		szorgalmiIdoszakVegeField.setEditable(false);
		szorgalmiIdoszakVegeField.setColumns(10);

		vizsgaidoszakElejeField = new JTextField();
		vizsgaidoszakElejeField.setEditable(false);
		vizsgaidoszakElejeField.setColumns(10);

		vizsgaidoszakVegeField = new JTextField();
		vizsgaidoszakVegeField.setEditable(false);
		vizsgaidoszakVegeField.setColumns(10);

		if (aktualisFelev == null) {
			String nincsAktuálisFélév = "Nincs meghirdetett félév!";
			szorgalmiIdoszakElejeField.setText(nincsAktuálisFélév);
			szorgalmiIdoszakVegeField.setText(nincsAktuálisFélév);
			vizsgaidoszakElejeField.setText(nincsAktuálisFélév);
			vizsgaidoszakVegeField.setText(nincsAktuálisFélév);
		} else {
			szorgalmiIdoszakElejeField.setText(sdf.format(aktualisFelev
					.getSzorgalmiIdőszak().getEleje()));
			szorgalmiIdoszakVegeField.setText(sdf.format(aktualisFelev
					.getSzorgalmiIdőszak().getVége()));
			vizsgaidoszakElejeField.setText(sdf.format(aktualisFelev
					.getVizsgaIdőszak().getEleje()));
			vizsgaidoszakVegeField.setText(sdf.format(aktualisFelev
					.getVizsgaIdőszak().getVége()));
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblBeiratkozsAKvetkez)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(28)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblSzorgalmiIdszakVge)
																						.addComponent(
																								lblVizsgaidszakEleje)
																						.addComponent(
																								lblVizsgaidszakVge)
																						.addComponent(
																								lblSzorgalmiIdszakEleje))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								szorgalmiIdoszakElejeField,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								341,
																								Short.MAX_VALUE)
																						.addComponent(
																								vizsgaidoszakVegeField,
																								Alignment.LEADING,
																								341,
																								341,
																								Short.MAX_VALUE)
																						.addComponent(
																								vizsgaidoszakElejeField,
																								Alignment.LEADING,
																								341,
																								341,
																								Short.MAX_VALUE)
																						.addComponent(
																								szorgalmiIdoszakVegeField,
																								Alignment.LEADING,
																								341,
																								341,
																								Short.MAX_VALUE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												btnMgsem)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnBeiratkozs)))
																		.addGap(150)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblBeiratkozsAKvetkez)
										.addGap(37)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblSzorgalmiIdszakEleje)
														.addComponent(
																szorgalmiIdoszakElejeField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblSzorgalmiIdszakVge)
														.addComponent(
																szorgalmiIdoszakVegeField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblVizsgaidszakEleje)
														.addComponent(
																vizsgaidoszakElejeField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblVizsgaidszakVge)
														.addComponent(
																vizsgaidoszakVegeField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED,
												144, Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnBeiratkozs)
														.addComponent(btnMgsem))
										.addGap(137)));
		setLayout(groupLayout);

	}
}
