package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ez tartalmazza a fejlesztő nevét és az fejlesztés időpontját.
 * 
 * @author adam
 *
 */
public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtKissSndordm;
	private JTextField txtTanv;

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setTitle("About");
		setBounds(100, 100, 296, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 184, 0 };
		gbl_contentPanel.rowHeights = new int[] { 34, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblAboutDialog = new JLabel("About dialog");
			lblAboutDialog.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblAboutDialog = new GridBagConstraints();
			gbc_lblAboutDialog.insets = new Insets(0, 0, 5, 5);
			gbc_lblAboutDialog.gridx = 0;
			gbc_lblAboutDialog.gridy = 0;
			contentPanel.add(lblAboutDialog, gbc_lblAboutDialog);
		}
		{
			JLabel lblProgramozta = new JLabel("Programozta:");
			GridBagConstraints gbc_lblProgramozta = new GridBagConstraints();
			gbc_lblProgramozta.insets = new Insets(0, 0, 5, 5);
			gbc_lblProgramozta.anchor = GridBagConstraints.EAST;
			gbc_lblProgramozta.gridx = 0;
			gbc_lblProgramozta.gridy = 1;
			contentPanel.add(lblProgramozta, gbc_lblProgramozta);
		}
		{
			txtKissSndordm = new JTextField();
			txtKissSndordm.setEditable(false);
			txtKissSndordm.setText("Kiss Sándor Ádám");
			GridBagConstraints gbc_txtKissSndordm = new GridBagConstraints();
			gbc_txtKissSndordm.insets = new Insets(0, 0, 5, 0);
			gbc_txtKissSndordm.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtKissSndordm.gridx = 1;
			gbc_txtKissSndordm.gridy = 1;
			contentPanel.add(txtKissSndordm, gbc_txtKissSndordm);
			txtKissSndordm.setColumns(10);
		}
		{
			JLabel lblIdpont = new JLabel("Időpont:");
			GridBagConstraints gbc_lblIdpont = new GridBagConstraints();
			gbc_lblIdpont.anchor = GridBagConstraints.EAST;
			gbc_lblIdpont.insets = new Insets(0, 0, 0, 5);
			gbc_lblIdpont.gridx = 0;
			gbc_lblIdpont.gridy = 2;
			contentPanel.add(lblIdpont, gbc_lblIdpont);
		}
		{
			txtTanv = new JTextField();
			txtTanv.setEditable(false);
			txtTanv.setText("2013/2014 tanév, 2. félév");
			GridBagConstraints gbc_txtTanv = new GridBagConstraints();
			gbc_txtTanv.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTanv.gridx = 1;
			gbc_txtTanv.gridy = 2;
			contentPanel.add(txtTanv, gbc_txtTanv);
			txtTanv.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Bezárás");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				closeButton.setActionCommand("OK");
				buttonPane.add(closeButton);
				getRootPane().setDefaultButton(closeButton);
			}
		}
	}

}
