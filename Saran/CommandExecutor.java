import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandExecutor {
    public JFrame frame;
    public JTextField commandField;

    public CommandExecutor() {
        frame = new JFrame("Command Executor");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        commandField = new JTextField();
        commandField.setBounds(25, 50, 300, 25);
        panel.add(commandField);

        JButton executeButton = new JButton("Execute");
        executeButton.setBounds(100, 120, 100, 20);
        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeCommand();
            }
        });
        panel.add(executeButton);

        frame.setVisible(true);
    }

    public void executeCommand() {
        String command = commandField.getText();
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CommandExecutor();
    }
}
