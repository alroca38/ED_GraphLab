package core.views;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableProtector {

    public TableProtector(JTable table, DefaultTableModel model) {
        secureTable(table, model);
    }

    private void secureTable(JTable table, DefaultTableModel model) {
        //Cantidad de filas y columnas
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();
        //Copiado de datos de la tabla
        Object[][] tableData = new Object[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                tableData[row][col] = table.getValueAt(row, col);  // Llenar con los datos actuales
            }
        }
        // Guardado de los títulos de columna
        String[] columnNames = new String[columnCount];
        for (int col = 0; col < columnCount; col++) {
            columnNames[col] = table.getColumnName(col);
        }
        // Creación del modelo no editable
        DefaultTableModel newModel = new DefaultTableModel(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Deshabilitar la edición para todas las celdas
            }
        };
        table.setModel(newModel);
    }
}
