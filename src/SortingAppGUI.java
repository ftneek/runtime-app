import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SortingAppGUI {

    public static void main(String[] args) {

        // UI Design using swing and awt frameworks
        String[] algorithms = {"BubbleSort","SelectionSort","QuickSort","RadixSort","MergeSort","HeapSort"};

        JFrame frame = new JFrame("Runtime Analyzer");
        frame.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel sizeLabel = new JLabel("Size of array:");

        TextField arraySizeTextField = new TextField();
        bottomPanel.add(sizeLabel, BorderLayout.WEST);
        bottomPanel.add(arraySizeTextField,BorderLayout.CENTER);
        frame.add(bottomPanel,BorderLayout.SOUTH);

        DefaultTableModel model = new DefaultTableModel(new Object[] {"Algorithm", "Runtime (ms)"},0) {
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

        Button generateButton = new Button("Generate & Sort");

        // Return values when click button "Generate & Sort"
        ActionListener listener = e -> {
            try {
                int size = Integer.parseInt(arraySizeTextField.getText());
                if (size < 1) {
                    throw new NumberFormatException();
                }
                // Initialise an array with random integers & input size
                int[] array = RandomArray.initialise(size);

                // Return time recorded per sorting algorithm as a HashMap
                HashMap<String, Long> timePerSorting = RecordTimesOfSorts.returnTimesRecorded(array);
                // Sort array ( can use any algorithm )
                HeapSort.sort(array);
                // Display sorted array of integers
                StringBuilder arrayString= new StringBuilder();
                for (Object element: array) {
                    arrayString.append(element.toString()).append(" ");
                }
                arraySizeTextField.setText(arrayString.toString());

                for (int i = model.getRowCount() -1; i >-1 ; i--) {
                    model.removeRow(i);
                }

                // Add time recorded per sorting to the table
                for (String name: algorithms) {
                    model.addRow(new Object[]{name, timePerSorting.get(name)});
                }
                sizeLabel.setText("Sorting Array: ");
            } catch (NumberFormatException ex) {
                sizeLabel.setText("Please input size as integer: ");
            }

        };

        generateButton.addActionListener(listener);
        frame.add(generateButton,BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
