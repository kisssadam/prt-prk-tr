package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import tanulmanyiRendszer.Oktato;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * Amikor egy {@link Oktato} bejelentkezik, akkor ez a panel jelenik meg.
 * @author adam
 *
 */
public class OktatoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public OktatoPanel(final JPanel contentPane, final BorderLayout borderLayout) {

		JLabel lblOktatiPanel = new JLabel("Oktatói panel");
		lblOktatiPanel.setFont(new Font("Dialog", Font.BOLD, 14));

		final JRadioButton rdbtnAlairasBeirasa = new JRadioButton("Aláírás beírása");
		final JRadioButton rdbtnVizsgaKiirasa = new JRadioButton("Vizsga kiírása");
		final JRadioButton rdbtnErdemjegyBeirasa = new JRadioButton("Érdemjegy beírása");
		
		JButton btnTovbb = new JButton("Tovább");
		btnTovbb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
				if (rdbtnAlairasBeirasa.isSelected()) {
					contentPane.add(new AlairasBeirasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
				} else if (rdbtnVizsgaKiirasa.isSelected()) {
					contentPane.add(new VizsgaKiirasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
				} else if (rdbtnErdemjegyBeirasa.isSelected()) {
					contentPane.add(new ErdemjegyBeirasaPanel(contentPane, borderLayout), BorderLayout.CENTER);
				} else {
					contentPane.add(new OktatoPanel(contentPane, borderLayout), BorderLayout.CENTER);
					JOptionPane.showMessageDialog(contentPane, "Ki kell választani egy lehetőséget!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
				}
				contentPane.validate();
			}
		});
		
		
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(rdbtnAlairasBeirasa);
		radioButtonGroup.add(rdbtnVizsgaKiirasa);
		radioButtonGroup.add(rdbtnErdemjegyBeirasa);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblOktatiPanel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(88)
							.addComponent(btnTovbb))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnVizsgaKiirasa)
								.addComponent(rdbtnAlairasBeirasa)
								.addComponent(rdbtnErdemjegyBeirasa))))
					.addContainerGap(261, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOktatiPanel)
					.addGap(40)
					.addComponent(rdbtnAlairasBeirasa)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnVizsgaKiirasa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnErdemjegyBeirasa)
					.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
					.addComponent(btnTovbb)
					.addGap(53))
		);
		setLayout(groupLayout);

	}
}
