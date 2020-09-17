package GUI;

import Analizadores.Cup.parser;
import Analizadores.Flex.Lexer;
import Logica.CargaArchivo;
import Objetos.Etiquetas;
import Tablas.TablaSimbolos.TablaSimbolos;
import java.io.File;
import java.io.StringReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import Objetos.Logica;
import Objetos.Temporales;
import Tablas.TablaTipos.TablaTipos;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author jpmazate
 */
public class EditorTexto extends javax.swing.JFrame {

    /**
     * Creates new form Editor
     */
    File file;

    public EditorTexto() {
        initComponents();
        asignar();

    }

    public void textAreaActualCaretUpdate(javax.swing.event.CaretEvent evt) {
        int pos = evt.getDot();
        try {
            int fila = textArea.getLineOfOffset(pos) + 1;
            int columna = pos - textArea.getLineStartOffset(fila - 1);
            textoPosicion.setText("Línea: " + fila + " Columna: " + columna);
            textoPosicion.setVisible(true);
        } catch (BadLocationException exc) {
            exc.printStackTrace();
        }
    }

    public void abrir() {
        JFileChooser buscadorArchivos = new JFileChooser();
        int opcion = buscadorArchivos.showOpenDialog(this);
        // si se acepta el archivo entra
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String archivo = buscadorArchivos.getSelectedFile().getAbsolutePath();
            String archivo1 = buscadorArchivos.getSelectedFile().toString();
            // si se encuentra el archivo pide el tiempo en milisegundos
            System.out.println("Se ha encontrado el archivo: " + archivo1);
            try {

                File file = new File(archivo1);
                if (file.exists()) {
                    CargaArchivo carga = new CargaArchivo(file);
                    this.file = file;
                    carga.leerArchivo(this.textArea);
                    asignarNombreArchivo(this.file.getName());
                }

            } catch (Exception e) {
                System.out.println("Hubo algun error");

            }
        } else if (opcion == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "No se ha cargado ningun archivo");
        }
        try {
            // crea el archivo que se selecciono

        } catch (Exception e) {
            System.out.println("No se selecciono ningun archivo");
        }
    }

    public void asignarNombreArchivo(String nombre) {
        this.labelArchivo.setText("Archivo: " + nombre);
    }

    public void guardar() {
        if (file == null) {
            file = guardarComo();
            if (file != null) {
                asignarNombreArchivo(this.file.getName());
            }
        } else {
            CargaArchivo carga = new CargaArchivo(file);
            carga.guardarArchivo(file, textArea);
        }

    }

    public File guardarComo() {
        CargaArchivo carga = new CargaArchivo(file);
        File file = carga.guardarComoArchivo(textArea);
        return file;
    }

    public void generar3D() {
        reiniciar();
        try {
            TablaTipos tipos = Logica.crearTabla();
            StringReader r = new StringReader(textArea.getText());
            Lexer lex = new Lexer(r,textResultado);
            parser p = new parser(lex, new TablaSimbolos(), tipos, textResultado);
            p.parse();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "PROBLEMA AL INTENTAR GENERAR EL CODIGO 3 DIRECCIONES");
            e.printStackTrace();
        }

    }
    
    public void reiniciar(){
        textResultado.setText("");
        Errores.Errores.vaciarErrores();
        Temporales.reiniciar();
        Etiquetas.reiniciar();
    }

    public void asignar() {
        KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, Event.CTRL_MASK);
        KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, Event.CTRL_MASK);

        UndoManager undoManager = new UndoManager();

        Document document = textArea.getDocument();
        document.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        // Add ActionListeners 
        undo.addActionListener((ActionEvent e) -> {
            try {
                undoManager.undo();
            } catch (CannotUndoException cue) {
            }
        });
        redo.addActionListener((ActionEvent e) -> {
            try {
                undoManager.redo();
            } catch (CannotRedoException cre) {
            }
        });

        // Map undo action 
        textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(undoKeyStroke, "undoKeyStroke");
        textArea.getActionMap().put("undoKeyStroke", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    undoManager.undo();
                } catch (CannotUndoException cue) {
                }
            }
        });
        // Map redo action 
        textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(redoKeyStroke, "redoKeyStroke");
        textArea.getActionMap().put("redoKeyStroke", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    undoManager.redo();
                } catch (CannotRedoException cre) {
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        textResultado = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoPosicion = new javax.swing.JLabel();
        labelArchivo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undo = new javax.swing.JMenuItem();
        redo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textAreaCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(textArea);

        desktopPane.add(jScrollPane1);
        jScrollPane1.setBounds(20, 130, 500, 470);

        textResultado.setEditable(false);
        textResultado.setColumns(20);
        textResultado.setRows(5);
        jScrollPane2.setViewportView(textResultado);

        desktopPane.add(jScrollPane2);
        jScrollPane2.setBounds(530, 130, 530, 470);

        jLabel1.setText("AREA PARA ESCRIBIR:");
        desktopPane.add(jLabel1);
        jLabel1.setBounds(20, 90, 160, 18);

        jLabel2.setText("AREA RESULTADO:");
        desktopPane.add(jLabel2);
        jLabel2.setBounds(790, 100, 140, 18);

        textoPosicion.setText("Linea: 1 Columna: 1");
        desktopPane.add(textoPosicion);
        textoPosicion.setBounds(790, 20, 250, 18);

        labelArchivo.setText("Archivo: Sin asignar");
        desktopPane.add(labelArchivo);
        labelArchivo.setBounds(380, 90, 360, 18);

        jButton1.setText("Generar Codigo 3 Direcciones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton1);
        jButton1.setBounds(20, 50, 270, 30);

        fileMenu.setMnemonic('f');
        fileMenu.setText("ARCHIVO");

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setMnemonic('o');
        openMenuItem.setText("ABRIR");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("GUARDAR");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("GUARDAR COMO");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("SALIR");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("EDITAR");

        undo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undo.setMnemonic('t');
        undo.setText("DESHACER");
        editMenu.add(undo);

        redo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redo.setMnemonic('y');
        redo.setText("REHACER");
        editMenu.add(redo);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void textAreaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textAreaCaretUpdate
        textAreaActualCaretUpdate(evt);
    }//GEN-LAST:event_textAreaCaretUpdate

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        abrir();
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        guardar();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        guardarComo();
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        generar3D();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem redo;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextArea textResultado;
    private javax.swing.JLabel textoPosicion;
    private javax.swing.JMenuItem undo;
    // End of variables declaration//GEN-END:variables

}
