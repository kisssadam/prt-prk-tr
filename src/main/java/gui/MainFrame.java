package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import db.DataLoader;
import db.DataSaver;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

/**
 * A program elindulása során ez lesz a fő keret, ami tartalmazni fogja a többi panelt.
 * @author adam
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private static MainFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DataLoader.init();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TanulmanyiRendszerKivetel e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					DataSaver.save();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Progtech / Progkörny Tanulmányi Rendszer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 575);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final BorderLayout borderLayout = new BorderLayout(0, 0);
		contentPane.setLayout(borderLayout);

		JPanel statuszbarPanel = new JPanel();
		contentPane.add(statuszbarPanel, BorderLayout.SOUTH);

		contentPane.add(new LoginPanel(contentPane, borderLayout),
				BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("Fájl");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Kilépés");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: az adatok szerializálását itt kell elvégezni
				System.exit(0);
			}
		});

		JMenuItem mntmKijelentkezs = new JMenuItem("Kijelentkezés");
		mntmKijelentkezs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Kozpont.kijelentkezés();
				contentPane.remove(borderLayout
						.getLayoutComponent(BorderLayout.CENTER));
				contentPane.add(new LoginPanel(contentPane, borderLayout),
						BorderLayout.CENTER);
				contentPane.validate();
			}
		});
		mnFile.add(mntmKijelentkezs);
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AboutDialog dialog = new AboutDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		mnHelp.add(mntmAbout);
	}
}
