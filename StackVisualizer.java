import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class StackVisualizer extends JFrame implements ActionListener {
    private JPanel boxesPanel;
    private JTextField textField;
    private JButton pushButton, popButton, clearButton;
    private Stack<Integer> stack;
    private boolean isFirstElementMarked;

    public StackVisualizer() {
        stack = new Stack<>();
        isFirstElementMarked = false;

        textField = new JTextField(10);

        pushButton = new JButton("Push");
        popButton = new JButton("Pop");
        clearButton = new JButton("Clear");

        pushButton.addActionListener(this);
        popButton.addActionListener(this);
        clearButton.addActionListener(this);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(textField);
        inputPanel.add(pushButton);
        inputPanel.add(popButton);
        inputPanel.add(clearButton);

        boxesPanel = new JPanel();
        boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(boxesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setTitle("Stack Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateBoxes() {
        boxesPanel.removeAll();
        for (int i = stack.size() - 1; i >= 0; i--) {
            JPanel box = createBox(stack.get(i));
            if (!isFirstElementMarked && i == stack.size() - 1) {
                JLabel topLabel = new JLabel("Top");
                box.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                box.add(topLabel);
                isFirstElementMarked = true;
            }
            boxesPanel.add(box);
        }
        boxesPanel.revalidate();
        boxesPanel.repaint();
    }

    private JPanel createBox(int value) {
        JPanel box = new JPanel();
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        box.setPreferredSize(new Dimension(50, 50));
        box.setMaximumSize(new Dimension(50, 50));
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(String.valueOf(value));
        box.add(label);
        return box;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pushButton) {
            String value = textField.getText();
            if (!value.isEmpty()) {
                int intValue = Integer.parseInt(value);
                stack.push(intValue);
                textField.setText("");
                isFirstElementMarked = false;
                updateBoxes();
            }
        } else if (e.getSource() == popButton) {
            if (!stack.isEmpty()) {
                stack.pop();
                isFirstElementMarked = false;
                updateBoxes();
            }
        } else if (e.getSource() == clearButton) {
            stack.clear();
            isFirstElementMarked = false;
            updateBoxes();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StackVisualizer::new);
    }
}
