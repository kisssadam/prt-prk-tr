package gui;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import tanulmanyiRendszer.Idopont;
import tanulmanyiRendszer.Idopont.Napok;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

/**
 * Segítségével egy {@link Idopont}-ot lehet példányosítani.
 * @author adam
 *
 */
public class IdopontValasztoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Napok> napokComboBox;
	private JSpinner óraSpinner;
	private JSpinner percSpinner;

	public Napok getSelectedNap() {
		return (Napok) napokComboBox.getSelectedItem();
	}

	public Integer getÓra() {
		return (Integer) óraSpinner.getValue();
	}

	public Integer getPerc() {
		return (Integer) percSpinner.getValue();
	}

	/**
	 * Create the panel.
	 */
	public IdopontValasztoPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 45, 98, 0 };
		gridBagLayout.rowHeights = new int[] { 24, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel napokLabel = new JLabel("Nap:");
		GridBagConstraints gbc_napokLabel = new GridBagConstraints();
		gbc_napokLabel.insets = new Insets(0, 0, 5, 5);
		gbc_napokLabel.anchor = GridBagConstraints.EAST;
		gbc_napokLabel.gridx = 0;
		gbc_napokLabel.gridy = 0;
		add(napokLabel, gbc_napokLabel);

		napokComboBox = new JComboBox<Napok>();
		napokComboBox.setModel(new DefaultComboBoxModel<Napok>(Napok.values()));
		GridBagConstraints gbc_napokComboBox = new GridBagConstraints();
		gbc_napokComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_napokComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_napokComboBox.anchor = GridBagConstraints.NORTH;
		gbc_napokComboBox.gridx = 1;
		gbc_napokComboBox.gridy = 0;
		add(napokComboBox, gbc_napokComboBox);

		JLabel óraLabel = new JLabel("Óra:");
		GridBagConstraints gbc_óraLabel = new GridBagConstraints();
		gbc_óraLabel.anchor = GridBagConstraints.EAST;
		gbc_óraLabel.insets = new Insets(0, 0, 5, 5);
		gbc_óraLabel.gridx = 0;
		gbc_óraLabel.gridy = 1;
		add(óraLabel, gbc_óraLabel);

		óraSpinner = new JSpinner();
		óraSpinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		GridBagConstraints gbc_óraSpinner = new GridBagConstraints();
		gbc_óraSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_óraSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_óraSpinner.gridx = 1;
		gbc_óraSpinner.gridy = 1;
		add(óraSpinner, gbc_óraSpinner);

		JLabel percLabel = new JLabel("Perc");
		GridBagConstraints gbc_percLabel = new GridBagConstraints();
		gbc_percLabel.anchor = GridBagConstraints.EAST;
		gbc_percLabel.insets = new Insets(0, 0, 0, 5);
		gbc_percLabel.gridx = 0;
		gbc_percLabel.gridy = 2;
		add(percLabel, gbc_percLabel);

		percSpinner = new JSpinner();
		percSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		GridBagConstraints gbc_percSpinner = new GridBagConstraints();
		gbc_percSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_percSpinner.gridx = 1;
		gbc_percSpinner.gridy = 2;
		add(percSpinner, gbc_percSpinner);
	}

}
