package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Szak;
import tanulmanyiRendszer.Tantargy;
import tanulmanyiRendszer.TanulmanyiOsztaly;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;
import javax.swing.JCheckBox;

/**
 * Lehetővé teszi a {@link TanulmanyiOsztaly} számára, hogy új tantárgyat
 * adjon hozzá.
 * @author adam
 *
 */
public class TantargyHozzaadasaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tantargykodField;
	private JTextField nevField;
	private SzakListazoPanel szakListazoPanel;

	/**
	 * Create the panel.
	 */
	public TantargyHozzaadasaPanel(final JPanel contentPane, final BorderLayout borderLayout) {
		
		JLabel lblTantrgyHozzadsa = new JLabel("Tantárgy hozzáadása");
		lblTantrgyHozzadsa.setFont(new Font("Dialog", Font.BOLD, 14));
		
		final TantargyListazoPanel tantargyListazoPanel = new TantargyListazoPanel();
		
		JLabel lblEddigiTantrgyak = new JLabel("Eddigi tantárgyak: (előfeltételt is itt lehet választani)");

		JLabel lblTantrgykd = new JLabel("Tantárgykód:");
		
		JLabel lblNv = new JLabel("Név:");
		
		JLabel lblKredit = new JLabel("Kredit:");
		
		JLabel lblSzak = new JLabel("Szak:");
		
		tantargykodField = new JTextField();
		tantargykodField.setColumns(10);
		
		nevField = new JTextField();
		nevField.setColumns(10);
		
		final JSpinner kreditSpinner = new JSpinner();
		kreditSpinner.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		
		final JCheckBox chckbxLegyenElfelttele = new JCheckBox("Legyen előfeltétele");
		
		JButton btnHozzads = new JButton("Hozzáadás");
		btnHozzads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tantárgykód = tantargykodField.getText();
				if (tantárgykód.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Meg kell adni a tantárgykódot!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String tantárgynév = nevField.getText();
				if (tantárgynév.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Meg kell adni a tantárgy nevét!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int kredit = (int) kreditSpinner.getValue();
				
				Szak szak = szakListazoPanel.getSelectedSzak();
				if (szak == null) {
					JOptionPane.showMessageDialog(contentPane, "Ki kell választani egy szakot!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Tantargy[] előfeltételek;
				if (chckbxLegyenElfelttele.isSelected()) {
					előfeltételek = tantargyListazoPanel.getSelectedTantargyak();
				} else {
					előfeltételek = new Tantargy[0];
				}
				 
				
				TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont.getBejelentkezettFelhasználó();
				try {
					Tantargy tantargy = new Tantargy(tantárgykód, tantárgynév, kredit, szak, előfeltételek);
					to.tantárgyHozzáadása(tantargy);
					
					StringBuilder előfeltételBuilder = new StringBuilder(100);
					for (int i = 0; i < előfeltételek.length; i++) {
						Tantargy t = előfeltételek[i];
						előfeltételBuilder.append(t.getTantárgykód());
						előfeltételBuilder.append(i+1 == előfeltételek.length ? "" : " ");
					}
					
					tantargyListazoPanel.getTantargyListazoTableModel().addRow(new Object[] {tantargy.getTantárgykód(),
																							 tantargy.getNév(),
																							 tantargy.getKredit(),
																							 tantargy.getSzak(),
																							 előfeltételBuilder.toString()});
					tantargyListazoPanel.getMegjelenitettTantargyak().add(tantargy);
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnMgsem = new JButton("Mégsem");
		btnMgsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new TanulmanyiOsztalyPanel(contentPane, borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		
		szakListazoPanel = new SzakListazoPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTantrgyHozzadsa))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblSzak)
										.addComponent(lblNv)
										.addComponent(lblTantrgykd)
										.addComponent(lblKredit))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(kreditSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(tantargykodField, Alignment.LEADING)
											.addComponent(nevField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
										.addComponent(szakListazoPanel, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblEddigiTantrgyak)
								.addComponent(chckbxLegyenElfelttele))
							.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)))
					.addGap(0))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(tantargyListazoPanel, GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(447, Short.MAX_VALUE)
					.addComponent(btnMgsem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnHozzads)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTantrgyHozzadsa)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTantrgykd)
						.addComponent(tantargykodField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNv)
						.addComponent(nevField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKredit)
						.addComponent(kreditSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(szakListazoPanel, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(lblSzak))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnMgsem)
								.addComponent(btnHozzads))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chckbxLegyenElfelttele)
							.addGap(17)))
					.addComponent(lblEddigiTantrgyak)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tantargyListazoPanel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
