package gui;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.MeghirdetettTantargy;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;
import tanulmanyiRendszer.Vizsga;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Lehetővé teszi egy {@link Oktato} számára, hogy új {@link Vizsga}-t írjon ki.
 * @author adam
 *
 */
public class VizsgaKiirasaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField teremField;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm", Kozpont.getLocale());

	/**
	 * Create the panel.
	 */
	public VizsgaKiirasaPanel(final JPanel contentPane, final BorderLayout borderLayout) {
		
		JButton btnMegsem = new JButton("Mégsem");
		btnMegsem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(borderLayout.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new OktatoPanel(contentPane, borderLayout), BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		
		JButton btnHozzads = new JButton("Hozzáadás");
		
		JLabel lblVizsgaHozzadsa = new JLabel("Vizsga hozzáadása");
		lblVizsgaHozzadsa.setFont(new Font("Dialog", Font.BOLD, 14));
		
		final MeghirdetettTantargyPanel meghirdetettTantargyPanel = new MeghirdetettTantargyPanel();
		
		final VizsgaTablePanel vizsgaTablePanel = new VizsgaTablePanel(meghirdetettTantargyPanel.getMeghirdetettTantargyTable(),
																	   meghirdetettTantargyPanel.getMeghirdetettTantargyData());
		
		JLabel lblTerem = new JLabel("Terem:");
		
		teremField = new JTextField();
		teremField.setColumns(10);
		
		final JXDatePicker idopontDatePicker = new JXDatePicker(Kozpont.getLocale());
		idopontDatePicker.setDate(new Date());
		
		JLabel lblIdopont = new JLabel("Időpont:");
		
		JLabel lblra = new JLabel("Óra:");
		
		final JSpinner oraSpinner = new JSpinner();
		oraSpinner.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		
		JLabel lblPerc = new JLabel("Perc:");
		
		final JSpinner percSpinner = new JSpinner();
		percSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(525, Short.MAX_VALUE)
					.addComponent(btnMegsem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnHozzads)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVizsgaHozzadsa)
					.addContainerGap(587, Short.MAX_VALUE))
				.addComponent(meghirdetettTantargyPanel, GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
				.addComponent(vizsgaTablePanel, GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPerc)
						.addComponent(lblra)
						.addComponent(lblTerem)
						.addComponent(lblIdopont))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(percSpinner)
						.addComponent(oraSpinner)
						.addComponent(teremField)
						.addComponent(idopontDatePicker, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
					.addGap(478))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVizsgaHozzadsa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(meghirdetettTantargyPanel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(vizsgaTablePanel, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(teremField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTerem))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblra)
								.addComponent(oraSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(idopontDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIdopont))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPerc)
						.addComponent(percSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMegsem)
						.addComponent(btnHozzads))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		
		btnHozzads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (teremField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Az összes mező kitöltése kötelező!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MeghirdetettTantargy meghirdetettTantargy = meghirdetettTantargyPanel.getSelectedMeghirdetettTantargy();
				if (meghirdetettTantargy == null) {
					JOptionPane.showMessageDialog(contentPane, "Ki kell választani egy tantárgyat!", "Hiba", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Oktato oktato = (Oktato) Kozpont.getBejelentkezettFelhasználó();
				
				long ora = 3600000 * (Integer) oraSpinner.getValue();
				long perc = 60_000 * (Integer) percSpinner.getValue();
				long time = idopontDatePicker.getDate().getTime() + ora + perc;
				Vizsga vizsga = new Vizsga(meghirdetettTantargy, new Date(time), teremField.getText());
				
				try {
					oktato.vizsgahirdetés(meghirdetettTantargy, vizsga);
					vizsgaTablePanel.getVizsgaTableModel().addRow(new Object[] {sdf.format(vizsga.getIdőpont()), vizsga.getTerem()});
					vizsgaTablePanel.getMeghirdetettTantargyVizsgai().add(vizsga);
					contentPane.validate();
				} catch (TanulmanyiRendszerKivetel e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
