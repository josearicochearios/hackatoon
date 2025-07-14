package pe.edu.vallegrande.view;

import pe.edu.vallegrande.controller.userController;
import pe.edu.vallegrande.model.userEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

    userController userC = new userController();
    Indumentaria ind = new Indumentaria();

    private JPanel contentPane, topBarPane;
    private JTextField username;
    private JPasswordField password;
    private JLabel usernameLabel, passwordLabel, welcomLabel, closeLabel;
    private JSeparator separatorUser, separatorPassword;
    private JButton btnLogin;

    public Login() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(400,500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        topBarPane = new JPanel(new BorderLayout());

        username = new JTextField();
        password = new JPasswordField();

        usernameLabel = new JLabel("usuario");
        passwordLabel = new JLabel("contraseña");
        welcomLabel = new JLabel("BIENVENIDO");
        closeLabel = new JLabel("X");

        separatorUser = new JSeparator();
        separatorPassword = new JSeparator();

        btnLogin = new JButton("Login");

        topBarPane.setBackground(Color.BLUE);
        topBarPane.setPreferredSize(new Dimension(getWidth(),30));

        closeLabel.setForeground(Color.WHITE);
        closeLabel.setPreferredSize(new Dimension(25,25));
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topBarPane.add(closeLabel, BorderLayout.EAST);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(40,40,40,40));
        contentPane.setBackground(Color.WHITE);

        welcomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomLabel.setFont(new Font("Arial", Font.BOLD, 24));
        closeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        username.setBorder(null);
        password.setBorder(null);
        username.setHorizontalAlignment(JTextField.CENTER);
        password.setHorizontalAlignment(JTextField.CENTER);
        username.setMaximumSize(new Dimension(Short.MAX_VALUE,25));
        password.setMaximumSize(new Dimension(Short.MAX_VALUE,25));

        contentPane.add(Box.createRigidArea(new Dimension(0, 50)));
        contentPane.add(welcomLabel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 70)));
        contentPane.add(usernameLabel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPane.add(username);
        contentPane.add(separatorUser);
        contentPane.add(passwordLabel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPane.add(password);
        contentPane.add(separatorPassword);
        contentPane.add(btnLogin);

        btnLogin.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                loginMouseClicked();
            }
        });

        closeLabel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int resp = JOptionPane.showConfirmDialog(null, "Sí o No",
                        "Deseas salir del aplicativo?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println(resp);
                if (resp == JOptionPane.YES_OPTION){
                    dispose();
                }
            }
        });


        add(topBarPane, BorderLayout.NORTH);
        add(contentPane, BorderLayout.CENTER);

    }

    public void loginMouseClicked(){

        String usernameText = username.getText();
        String passwordText = String.valueOf(password.getPassword());

        if(usernameText.isEmpty() || passwordText.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingresa tus credenciales");
        } else {
            try {
                userEntity userE = userC.verifyUser(usernameText, passwordText);
                if (userE != null) {
                    ind.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                }

            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al validar usuario");
            }
        }

    }

}
