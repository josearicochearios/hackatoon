package pe.edu.vallegrande.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;
import pe.edu.vallegrande.controller.thingsController;
import pe.edu.vallegrande.model.thingsEntity;

public class Indumentaria extends JFrame {

    private thingsController tc = new thingsController();

    JPanel contentPane, barPane, thingPane, frmPane, listPane, btnsPane, radioPane, frmPane1, frmPane2;
    JScrollPane scrollFrmPane;
    JLabel nameLabel, requerimientoLabel, materialLabel, categoryLabel, proveedorLabel, stockLabel, creationLabel, titleFormLabel, descriptionLabel, companyLabel, closeSessionLabel, thingsHouseLabel;
    JTextField nameField;
    JCheckBox requerimientoField;
    JComboBox<String> categoryField, proveedorField;
    JDateChooser creationDateChooser;
    JTextArea descriptionArea;
    JSpinner stockSpinner;
    JRadioButton plasticoButon, papelButon, otrosButon;
    ButtonGroup buttonGroup;
    JTable tableList;
    DefaultTableModel tableModelList;
    JScrollPane scrollPaneTable;
    JButton btnSave, btnDelete, btnUpdate, btnCancel;

    public Indumentaria() {
        initComponets();
        loadTable();
    }

    public void initComponets() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        barPane = new JPanel();
        thingPane = new JPanel();
        frmPane = new JPanel();
        listPane = new JPanel();
        btnsPane = new JPanel();
        radioPane = new JPanel();
        frmPane1 = new JPanel();
        frmPane2 = new JPanel();

        Color white = Color.WHITE;
        contentPane.setBackground(white);
        barPane.setBackground(new Color(115, 209, 253));
        thingPane.setBackground(white);
        frmPane.setBackground(white);
        listPane.setBackground(white);
        btnsPane.setBackground(white);
        radioPane.setBackground(white);
        frmPane1.setBackground(white);
        frmPane2.setBackground(white);

        nameLabel = new JLabel("Objeto o Producto");
        requerimientoLabel = new JLabel("Requiere Mantenimiento");
        materialLabel = new JLabel("Material");
        categoryLabel = new JLabel("Categoría");
        proveedorLabel = new JLabel("Proveedor");
        stockLabel = new JLabel("Cantidad de Objetos");
        creationLabel = new JLabel("Fecha de Ingreso");
        titleFormLabel = new JLabel("INVENTARIO");
        descriptionLabel = new JLabel("Descripcion (opcional)");
        companyLabel = new JLabel("Saber Futuro");
        closeSessionLabel = new JLabel("Cerrar Sesion");
        thingsHouseLabel = new JLabel("Inventario");

        titleFormLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleFormLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        companyLabel.setFont(new Font("Blacksword", Font.BOLD, 20));

        nameField = new JTextField();
        stockSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        requerimientoField = new JCheckBox();
        categoryField = new JComboBox<>(new String[] {"Seleccionar","Lapiceras", "Lápices", "Cuadernos", "Mochilas", "Tijeras", "Colores", "Reglas", "Cartulinas"});
        proveedorField = new JComboBox<>(new String[] {"Selecionar","Librería Escolar S.A.", "Distribuidora El Aula", "Suministros Educativos SAC", "Papelera Escolar", "UtilesExpress"});
        creationDateChooser = new JDateChooser();
        descriptionArea = new JTextArea();

        plasticoButon = new JRadioButton("Plástico");
        papelButon = new JRadioButton("Papel");
        otrosButon = new JRadioButton("Otros");
        plasticoButon.setBackground(white);
        papelButon.setBackground(white);
        otrosButon.setBackground(white);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(plasticoButon);
        buttonGroup.add(papelButon);
        buttonGroup.add(otrosButon);

        tableModelList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModelList.setColumnIdentifiers(new String[]{"COD", "Nombre", "Material", "Mantenimiento", "Categoría", "Proveedor", "Stock", "Descripcion", "Fecha de almacenado"});
        tableList = new JTable(tableModelList);
        tableList.getTableHeader().setReorderingAllowed(false);
        scrollPaneTable = new JScrollPane(tableList);
        scrollPaneTable.setPreferredSize(new Dimension(getWidth() - 100, 150));
        scrollPaneTable.setBorder(null);

        btnSave = new JButton("Guardar");
        btnDelete = new JButton("Eliminar");
        btnDelete.setEnabled(false);
        btnUpdate = new JButton("Modificar");
        btnUpdate.setEnabled(false);
        btnCancel = new JButton("Cancelar");
        btnCancel.setEnabled(false);

        barPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        barPane.add(companyLabel);

        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        frmPane.setLayout(new BoxLayout(frmPane, BoxLayout.X_AXIS));
        frmPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmPane.add(frmPane1);
        frmPane.add(frmPane2);

        frmPane1.setLayout(new BoxLayout(frmPane1, BoxLayout.Y_AXIS));
        frmPane1.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmPane1.add(createFormRow(nameLabel, nameField));
        frmPane1.add(Box.createVerticalStrut(10));
        frmPane1.add(createFormRow(requerimientoLabel, requerimientoField));
        frmPane1.add(Box.createVerticalStrut(10));
        frmPane1.add(createFormRow(materialLabel, radioPane));
        frmPane1.add(Box.createVerticalStrut(10));
        frmPane1.add(createFormRow(categoryLabel, categoryField));
        frmPane1.add(Box.createVerticalStrut(10));
        frmPane1.add(createFormRow(proveedorLabel, proveedorField));

        frmPane2.setLayout(new BoxLayout(frmPane2, BoxLayout.Y_AXIS));
        frmPane2.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmPane2.add(createFormRow(stockLabel, stockSpinner));
        frmPane2.add(Box.createVerticalStrut(10));
        frmPane2.add(createFormRow(creationLabel, creationDateChooser));
        frmPane2.add(Box.createVerticalStrut(10));
        frmPane2.add(descriptionLabel);
        JScrollPane scrollDescription = new JScrollPane(descriptionArea);
        scrollDescription.setPreferredSize(new Dimension(250, 80));
        frmPane2.add(scrollDescription);

        listPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        scrollFrmPane = new JScrollPane(frmPane);
        scrollFrmPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollFrmPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrmPane.setPreferredSize(new Dimension(getWidth(), 350));
        scrollFrmPane.setBorder(null);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        thingPane.setLayout(new BoxLayout(thingPane, BoxLayout.Y_AXIS));
        btnsPane.setLayout(new BoxLayout(btnsPane, BoxLayout.X_AXIS));
        listPane.setLayout(new BorderLayout());

        barPane.setPreferredSize(new Dimension(200, getHeight()));
        thingPane.setPreferredSize(new Dimension(1000, getHeight()));
        listPane.setPreferredSize(new Dimension(getWidth(), 200));
        btnsPane.setPreferredSize(new Dimension(getWidth(), 100));

        radioPane.add(papelButon);
        radioPane.add(plasticoButon);
        radioPane.add(otrosButon);

        btnsPane.add(btnSave);
        btnsPane.add(btnDelete);
        btnsPane.add(btnUpdate);
        btnsPane.add(btnCancel);

        listPane.add(scrollPaneTable, BorderLayout.CENTER);
        thingPane.add(Box.createVerticalStrut(20));
        thingPane.add(titleFormLabel);
        thingPane.add(Box.createVerticalStrut(20));
        thingPane.add(scrollFrmPane);
        thingPane.add(Box.createVerticalStrut(20));
        thingPane.add(listPane);
        thingPane.add(btnsPane);
        contentPane.add(barPane);
        contentPane.add(thingPane);

        btnSave.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    int confirm = JOptionPane.showConfirmDialog(null, "Desea registrar este nuevo objeto?", "Confirmar Registro", JOptionPane.YES_NO_OPTION);
                    if (confirm != JOptionPane.YES_OPTION) {
                        return;
                    }
                    thingsEntity th = readForm();
                    tc.save(th);
                    loadTable();
                    cleanForm();
                    JOptionPane.showMessageDialog(null, "Registro guardado exitosamente.");
                } catch (Exception err) {
                    err.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al guardar el registro.");
                }
            }
        });

        btnCancel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cleanForm();
                btnSave.setEnabled(true);
                btnDelete.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnCancel.setEnabled(false);
            }
        });

        btnDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    int selectedRow = tableList.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione una fila para eliminar.");
                        return;
                    }
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm != JOptionPane.YES_OPTION) {
                        return;
                    }
                    int id = Integer.parseInt(tableModelList.getValueAt(selectedRow, 0).toString());
                    tc.delete(id);
                    cleanForm();
                    loadTable();
                    btnDelete.setEnabled(false);
                    btnUpdate.setEnabled(false);
                    btnCancel.setEnabled(false);
                    btnSave.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el registro.");
                }
            }
        });

        btnUpdate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    int selectedRow = tableList.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Seleccione un registro para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Desea modificar este registro?", "Confirmar Modificación", JOptionPane.YES_NO_OPTION);
                    if (confirm != JOptionPane.YES_OPTION) {
                        return;
                    }
                    thingsEntity th = readForm();
                    int id = Integer.parseInt(tableModelList.getValueAt(selectedRow, 0).toString());
                    th.setId(id);
                    tc.update(th);
                    loadTable();
                    cleanForm();
                    btnSave.setEnabled(true);
                    btnDelete.setEnabled(false);
                    btnUpdate.setEnabled(false);
                    btnCancel.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Registro modificado correctamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al modificar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tableList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    singleClickRow();
                } else if (e.getClickCount() == 2) {
                    doubleClickRow(e);
                }
            }
        });

        setContentPane(contentPane);
    }

    private void loadTable() {
        tableModelList.setRowCount(0);
        tc.getAll(tableModelList);
    }

    private thingsEntity readForm() {
        thingsEntity th = new thingsEntity();
        th.setName(nameField.getText());
        if (papelButon.isSelected()) th.setMaterial("Papel");
        else if (plasticoButon.isSelected()) th.setMaterial("Plástico");
        else th.setMaterial("Otros");
        th.setIs_required(requerimientoField.isSelected() ? "SI" : "NO");
        th.setCategory((String) categoryField.getSelectedItem());
        th.setProveedor((String) proveedorField.getSelectedItem());
        th.setStock((Integer) stockSpinner.getValue());
        th.setCreation_date(new java.sql.Date(creationDateChooser.getDate().getTime()));
        th.setDescription(descriptionArea.getText());
        return th;
    }

    private void cleanForm() {
        nameField.setText("");
        requerimientoField.setSelected(false);
        buttonGroup.clearSelection();
        categoryField.setSelectedIndex(0);
        proveedorField.setSelectedIndex(0);
        stockSpinner.setValue(0);
        creationDateChooser.setDate(null);
        descriptionArea.setText("");
    }

    private JPanel createFormRow(JComponent label, JComponent field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row.setBackground(Color.WHITE);
        label.setPreferredSize(new Dimension(180, 30));
        field.setPreferredSize(new Dimension(250, 30));
        row.add(label);
        row.add(field);
        return row;
    }

    private void doubleClickRow(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int row = tableList.getSelectedRow();
            if (row >= 0) {
                nameField.setText(tableModelList.getValueAt(row, 1).toString());
                String material = tableModelList.getValueAt(row, 2).toString();
                String isRequired = tableModelList.getValueAt(row, 3).toString();
                Object descVal = tableModelList.getValueAt(row, 7);
                Object dateVal = tableModelList.getValueAt(row, 8);
                if (material.equals("Papel")) papelButon.setSelected(true);
                else if (material.equals("Plástico")) plasticoButon.setSelected(true);
                else otrosButon.setSelected(true);
                requerimientoField.setSelected(isRequired.equalsIgnoreCase("SI"));
                categoryField.setSelectedItem(tableModelList.getValueAt(row, 4).toString());
                proveedorField.setSelectedItem(tableModelList.getValueAt(row, 5).toString());
                stockSpinner.setValue(Integer.parseInt(tableModelList.getValueAt(row, 6).toString()));
                descriptionArea.setText(descVal != null ? descVal.toString() : "");
                btnSave.setEnabled(false);
                btnCancel.setEnabled(true);
                btnDelete.setEnabled(true);
                btnUpdate.setEnabled(true);
                if (dateVal != null && !dateVal.toString().trim().isEmpty()) {
                    try {
                        String fullDateTime = dateVal.toString().trim();
                        String onlydate = fullDateTime.substring(0, 10).trim();
                        Date date = Date.valueOf(onlydate);
                        creationDateChooser.setDate(date);
                    } catch (IllegalArgumentException err) {
                        err.printStackTrace();
                        creationDateChooser.setDate(null);
                    }
                } else {
                    creationDateChooser.setDate(null);
                }
            }
        }
    }

    private void singleClickRow() {
        int row = tableList.getSelectedRow();
        if (row >= 0) {
            btnSave.setEnabled(false);
            btnCancel.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }
}
