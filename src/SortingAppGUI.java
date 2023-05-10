import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SortingAppGUI {
    

    public static void main(String[] args) {

        // UI Design using swing and awt frameworks
        String[] algorithms = {"BubbleSort","SelectionSort","QuickSort","RadixSort","MergeSort","HeapSort"};

        JFrame frame = new JFrame("Runtime Analyzer (in milliseconds)");
        frame.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());

        frame.add(bottomPanel,BorderLayout.SOUTH);

        

        DefaultTableModel model = new DefaultTableModel(new Object[] {"Size\\Algorithms", "BubbleSort","SelectionSort","QuickSort","RadixSort","MergeSort","HeapSort"},0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        JTable table = new JTable(model);
        // Resize columns
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane,BorderLayout.CENTER);
        model.addRow(new Object[]{"Array Size"});

        // Create button to generate recorded time per sorting
        JButton generateButton = new JButton("Sort");

        // Return values when click button "Sort"
        ActionListener listener = e -> {
            for (int i = 0; i < table.getRowCount(); i++) {
                // Initialise an array with random integers & input size
                try {
                    int size = Integer.parseInt(table.getValueAt(i, 0).toString());
                    int[] array = RandomArray.initialise(size);

                    // Return time recorded per sorting algorithm as a HashMap
                    HashMap<String, Long> timePerSorting = RecordTimesOfSorts.returnTimesRecorded(array);

                    // Add time recorded per sorting to the table
                    for (String name: algorithms) {
                        table.setValueAt(timePerSorting.get(name), i, getColumnByName(table, name));
                    }
                }
                catch(Exception exception) {
                    JOptionPane.showMessageDialog(frame, "Please input array size as an integer to continue!",
                            "Swing Tester", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        generateButton.addActionListener(listener);
        bottomPanel.add(generateButton,BorderLayout.EAST);

        ActionListener addListener = e -> model.addRow(new Object[]{});
        JButton addRowButton = new JButton("Insert row");
        addRowButton.addActionListener(addListener);
        bottomPanel.add(addRowButton);



        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Function to get column of JTable by name
    private static int getColumnByName(JTable table, String name) {
        for (int i = 0; i < table.getColumnCount(); ++i)
            if (table.getColumnName(i).equals(name))
                return i;
        return -1;
    }
}
