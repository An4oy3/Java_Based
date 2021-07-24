import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    String fullName = "";
    JPanel mainPanel;
    private JButton collapse;
    private JTextField secondNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;


    public MainForm(){
        collapse.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(secondNameField.getText().equals("") || firstNameField.getText().equals("")){
                    JOptionPane.showMessageDialog(mainPanel, "Введите имя и фамилию", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                } else {
                    fullName = secondNameField.getText() + " " + firstNameField.getText() + " " + middleNameField.getText();
                    mainPanel.removeAll();
                    mainPanel.revalidate();
                    mainPanel.add(new expendForm(fullName).getExpendPanel());
            }
        }});
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
