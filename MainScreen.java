import javax.swing.*;
import java.awt.*;

public class MainScreen {
    public MainScreen() {
        showDataStructureSelection();
    }

    private void showDataStructureSelection() {
        JFrame frame = new JFrame("Data Structure Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JButton linkedListButton = new JButton("Linked List");
        JButton stackButton = new JButton("Stack");
        JButton queueButton = new JButton("Queue");

        linkedListButton.addActionListener(e -> showLinkedListVisualization());
        stackButton.addActionListener(e -> showStackVisualization());
        queueButton.addActionListener(e -> showQueueVisualization());

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(linkedListButton);
        panel.add(stackButton);
        panel.add(queueButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void showLinkedListVisualization() {
        LinkedListVisualizer linkedListVisualizer = new LinkedListVisualizer();
        showInNewWindow(linkedListVisualizer, "Linked List Visualizer");
    }

    private void showStackVisualization() {
        StackVisualizer stackVisualizer = new StackVisualizer();
        showInNewWindow(stackVisualizer, "Stack Visualizer");
    }

    private void showQueueVisualization() {
        QueueVisualizer queueVisualizer = new QueueVisualizer();
        showInNewWindow(queueVisualizer, "Queue Visualizer");
    }

    private void showInNewWindow(JFrame frame, String title) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainScreen());
    }
}
