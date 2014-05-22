package gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import tanulmanyiRendszer.TanulmanyiOsztaly;

/**
 * Ha egy {@link TanulmanyiOsztaly} bejelentkezik, akkor ez a panel
 * fog megjelenni.
 * @author adam
 *
 */
public class TanulmanyiOsztalyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TanulmanyiOsztalyPanel(final JPanel contentPane, final BorderLayout borderLayout) {

		final JRadioButton rdbtnSzakHozzadsa = new JRadioButton("Szak hozzáadása");
		final JRadioButton rdbtnHallgatHozzadsa = new JRadioButton("Hallgató hozzáadása");
		final JRadioButton rdbtnOktatHozzadsa = new JRadioButton("Oktató hozzáadása");
		final JRadioButton rdbtnFlvMeghirdetse = new JRadioButton("Félév meghirdetése");
		final JRadioButton rdbtnAktulisFlvBelltsa = new JRadioButton("Aktuális félév beállítása");
		final JRadioButton rdbtnTantrgyHozzadsa = new JRadioButton("Tantárgy hozzáadása");
		final JRadioButton rdbtnTantrgyakMeghirdetse = new JRadioButton("Tantárgyak meghirdetése");
		final JRadioButton rdbtnGyakorlatiCsoportFelvtele = new JRadioButton("Gyakorlati csoport felvétele");

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnSzakHozzadsa);
		buttonGroup.add(rdbtnHallgatHozzadsa);
		buttonGroup.add(rdbtnOktatHozzadsa);
		buttonGroup.add(rdbtnFlvMeghirdetse);
		buttonGroup.add(rdbtnAktulisFlvBelltsa);
		buttonGroup.add(rdbtnTantrgyakMeghirdetse);
		buttonGroup.add(rdbtnGyakorlatiCsoportFelvtele);
		buttonGroup.add(rdbtnTantrgyHozzadsa);
		
		JButton btnTovbb = new JButton("Tovább");
		btnTovbb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnSzakHozzadsa.isSelected()) {

					contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
					contentPane.add(new SzakHozzadasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
					contentPane.validate();

				} else if (rdbtnHallgatHozzadsa.isSelected()) {

					contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
					contentPane.add(new HallgatoHozzaadasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
					contentPane.validate();

				} else if (rdbtnOktatHozzadsa.isSelected()) {

					contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
					contentPane.add(new OktatoHozzaadasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
					contentPane.validate();

				} else if (rdbtnFlvMeghirdetse.isSelected()) {

					contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
					contentPane.add(new FelevMeghirdetesePanel(contentPane, borderLayout), BorderLayout.CENTER);
					contentPane.validate();

				} else if (rdbtnAktulisFlvBelltsa.isSelected()) {

					contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
					contentPane.add(new AktualisFelevBellitasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
					contentPane.validate();

				} else if (rdbtnTantrgyHozzadsa.isSelected()) {
					
					contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
					contentPane.add(new TantargyHozzaadasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
					contentPane.validate();
					
				} else if (rdbtnTantrgyakMeghirdetse.isSelected()) {

					contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
					contentPane.add(new TantargyakMeghirdetesePanel(contentPane, borderLayout), BorderLayout.CENTER);
					contentPane.validate();

				} else if (rdbtnGyakorlatiCsoportFelvtele.isSelected()) {

					contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
					contentPane.add(new GyakorlatiCsoportHozzaadasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
					contentPane.validate();

				} else {

					JOptionPane.showMessageDialog(contentPane,
							"Kérlek válassz egy lehetőséget!",
							"Figyelmeztetés", JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(163, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnGyakorlatiCsoportFelvtele)
						.addComponent(rdbtnTantrgyakMeghirdetse)
						.addComponent(rdbtnTantrgyHozzadsa)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(rdbtnHallgatHozzadsa)
									.addComponent(rdbtnSzakHozzadsa)
									.addComponent(rdbtnOktatHozzadsa)
									.addComponent(rdbtnFlvMeghirdetse)
									.addComponent(rdbtnAktulisFlvBelltsa))
								.addGap(27))
							.addComponent(btnTovbb, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)))
					.addGap(162))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(111)
					.addComponent(rdbtnSzakHozzadsa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnHallgatHozzadsa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnOktatHozzadsa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnFlvMeghirdetse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnAktulisFlvBelltsa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnTantrgyHozzadsa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnTantrgyakMeghirdetse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnGyakorlatiCsoportFelvtele)
					.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
					.addComponent(btnTovbb)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
}
