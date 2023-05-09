import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SortingAppGUI {

    public static void main(String[] args) {

        // UI Design using swing and awt frameworks
        String[] algorithms = {"BubbleSort","SelectionSort","QuickSort","RadixSort","MergeSort","HeapSort"};
        int[] N = {5,50,500,5000,50000};

        JFrame frame = new JFrame("Runtime Analyzer");
        frame.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());

        frame.add(bottomPanel,BorderLayout.SOUTH);

        DefaultTableModel model = new DefaultTableModel(new Object[] {"Algorithm (Runtime (ms))", "N = 5", "N = 50", "N = 500", "N = 5000", "N = 50000"},0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane,BorderLayout.CENTER);
        for (String name: algorithms) {
            model.addRow(new Object[]{name});
        }

        // Create button to generate recorded time per sorting
        Button generateButton = new Button("Sort");


        // Return values when click button "Sort"
        ActionListener listener = e -> {
            // Initialise an array with random integers & input size
            int size = N[4];

            int[] array = RandomArray.initialise(size);

            // Return time recorded per sorting algorithm as a HashMap
            HashMap<String, Long> timePerSorting = RecordTimesOfSorts.returnTimesRecorded(array);

            for (int i = model.getRowCount() -1; i >-1 ; i--) {
                model.removeRow(i);
            }

            // Add time recorded per sorting to the table
            for (String name: algorithms) {
                model.addRow(new Object[]{name, timePerSorting.get(name)});
            }
        };

        generateButton.addActionListener(listener);
        bottomPanel.add(generateButton,BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
