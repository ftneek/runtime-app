import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class SortingAppGUI {

    public static void main(String[] args) {
        Random random = new Random();
        String[] algorithms = {"BubbleSort","SelectionSort","QuickSort","RadixSort","MergeSort","HeapSort"};

        JFrame frame = new JFrame("Runtime Analyzer");
        frame.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel sizeLabel = new JLabel("Size of array:");
//        sizeLabe
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
        ActionListener listener = e -> {
            try {
                int size = Integer.parseInt(arraySizeTextField.getText());
                if (size < 1) {
                    throw new NumberFormatException();
                }
                int[] array = random.ints(size).toArray();
                StringBuilder arrayString= new StringBuilder();
                for (Object element: array) {
                    arrayString.append(element.toString()).append(" ");
                }
                arraySizeTextField.setText(arrayString.toString());

                for (int i = model.getRowCount() -1; i >-1 ; i--) {
                    model.removeRow(i);
                }
                for (String name: algorithms) {
                    model.addRow(new Object[]{name, random.nextInt(213,2000)});
                }
            } catch (NumberFormatException ex) {
                arraySizeTextField.setText("Integers only");
            }

        };

        generateButton.addActionListener(listener);
        frame.add(generateButton,BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
