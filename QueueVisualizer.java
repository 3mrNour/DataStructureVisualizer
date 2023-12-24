import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class QueueVisualizer extends JFrame implements ActionListener {
    private JPanel boxesPanel;
    private JTextField textField;
    private JButton enqueueButton, dequeueButton, clearButton;
    private Queue<Integer> queue;
    private boolean isFirstElementMarked;

    public QueueVisualizer() {
        queue = new LinkedList<>();
        isFirstElementMarked = false;

        textField = new JTextField(10);

        enqueueButton = new JButton("Enqueue");
        dequeueButton = new JButton("Dequeue");
        clearButton = new JButton("Clear");

        enqueueButton.addActionListener(this);
        dequeueButton.addActionListener(this);
        clearButton.addActionListener(this);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(textField);
        inputPanel.add(enqueueButton);
        inputPanel.add(dequeueButton);
        inputPanel.add(clearButton);

        boxesPanel = new JPanel();
        boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.X_AXIS));

        JScrollPane scrollPane = new JScrollPane(boxesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setTitle("Queue Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateBoxes() {
        boxesPanel.removeAll();
        for (Integer element : queue) {
            JPanel box = createBox(element);
            if (!isFirstElementMarked && element == queue.peek()) {
                JLabel frontLabel = new JLabel("Front");
                box.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                box.add(frontLabel);
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
        if (e.getSource() == enqueueButton) {
            String value = textField.getText();
            if (!value.isEmpty()) {
                int intValue = Integer.parseInt(value);
                queue.add(intValue);
                textField.setText("");
                isFirstElementMarked = false;
                updateBoxes();
            }
        } else if (e.getSource() == dequeueButton) {
            if (!queue.isEmpty()) {
                queue.poll();
                isFirstElementMarked = false;
                updateBoxes();
            }
        } else if (e.getSource() == clearButton) {
            queue.clear();
            isFirstElementMarked = false;
            updateBoxes();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QueueVisualizer::new);
    }
}
