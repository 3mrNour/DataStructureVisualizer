import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinkedListVisualizer extends JFrame implements ActionListener {
    private JPanel boxesPanel;
    private JTextField textField;
    private JButton addButton, popButton, clearButton;
    private LinkedList linkedList;

    public LinkedListVisualizer() {
        linkedList = new LinkedList();
        
        textField = new JTextField(10);
        
        addButton = new JButton("Add");
        popButton = new JButton("Pop");
        clearButton = new JButton("Clear");
        
        addButton.addActionListener(this);
        popButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(textField);
        inputPanel.add(addButton);
        inputPanel.add(popButton);
        inputPanel.add(clearButton);
        
        boxesPanel = new JPanel();
        boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.X_AXIS));
        
        JScrollPane scrollPane = new JScrollPane(boxesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        setTitle("Linked List Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateBoxes() {
        boxesPanel.removeAll();
        Node temp = linkedList.getHead();
        while (temp != null) {
            JPanel box = createBox(temp.data);
            boxesPanel.add(box);
            if (temp.next != null) {
                JLabel underscore = new JLabel("==>");
                boxesPanel.add(underscore);
            }
            temp = temp.next;
        }
        boxesPanel.revalidate();
        boxesPanel.repaint();
    }

    private JPanel createBox(int value) {
        JPanel box = new JPanel();
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        box.setPreferredSize(new Dimension(50, 50));
        box.setMaximumSize(new Dimension(50, 50));
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(String.valueOf(value));
        label.setFont(new Font("Arial",Font.BOLD,25));
        box.add(label);
        return box;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String value = textField.getText();
            if (!value.isEmpty()) {
                int intValue = Integer.parseInt(value);
                linkedList.insert(intValue);
                textField.setText("");
                updateBoxes();
            }
        } else if (e.getSource() == popButton) {
            linkedList.pop();
            updateBoxes();
        } else if (e.getSource() == clearButton) {
            linkedList.clear();
            updateBoxes();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LinkedListVisualizer::new);
    }
}

