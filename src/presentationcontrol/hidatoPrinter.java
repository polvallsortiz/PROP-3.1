package presentationcontrol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class hidatoPrinter extends JFrame {
    private JLabel celltypelabel;
    private JComboBox celltypecombobox;
    private JComboBox adjacencytypecombobox;
    private JLabel adjacencytypelabel;
    private JLabel lineslabel;
    private JLabel columnslabel;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton printhidato;
    private JLabel hidato;
    private JPanel mainpanel;
    private Container contentPane;

    public hidatoPrinter() {
        super("Hidato Printer!");
        setContentPane(mainpanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initProperties();
        printhidato.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                hidato.setText(celltypecombobox.getSelectedItem().toString());
                hidato.setText(hidato.getText() + " \n " + adjacencytypecombobox.getSelectedItem().toString());
            }
        });
        setVisible(true);
    }

    public void initProperties() {
        //CELLTYPE
        celltypecombobox.addItem("Quadrat");
        celltypecombobox.addItem("Triangle");
        celltypecombobox.addItem("Hexàgon");

        //ADJACENCYTYPE
        adjacencytypecombobox.addItem("Costat");
        adjacencytypecombobox.addItem("Costat i Angle");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
