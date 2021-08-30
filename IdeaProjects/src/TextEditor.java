import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPAne;
    JLabel fontLabel;
    JSpinner spinner;
    JButton ColorButton;
    JComboBox combofont;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    TextEditor(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Chams text editor");
        this.setSize(500,500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);


        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("ARIAL",Font.PLAIN,20));

        scrollPAne = new JScrollPane(textArea);
        scrollPAne.setPreferredSize(new Dimension(450,450));
        scrollPAne.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontLabel = new JLabel("Font");

        spinner = new JSpinner();
        spinner.setPreferredSize(new Dimension(50,25));
        spinner.setValue(20);
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(),
                                    Font.PLAIN,
                        (int)spinner.getValue()));
            }
        });

        ColorButton = new JButton("Color");
        ColorButton.addActionListener(this);

        String[] fonts =
                GraphicsEnvironment.getLocalGraphicsEnvironment().
                        getAvailableFontFamilyNames();

        combofont = new JComboBox(fonts);
        combofont.addActionListener(this);
        combofont.setSelectedItem("Arial");

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("open");
        saveItem = new JMenuItem("save");
        exitItem = new JMenuItem("exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);
        this.add(fontLabel);
        this.add(spinner);
        this.add(ColorButton);
        this.add(combofont);
        this.add(scrollPAne);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==ColorButton){
            JColorChooser colorChooser = new JColorChooser();

            Color color = colorChooser.showDialog(null,"choose color",Color.BLACK);
            textArea.setForeground(color);

        }
        if (e.getSource()==combofont){
            textArea.setFont(new Font((String)combofont.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize() ));
        }

        if (e.getSource()==openItem){

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter  = new FileNameExtensionFilter("Text files","txt");
            fileChooser.setFileFilter(filter);

            int res = fileChooser.showOpenDialog(null);

            if (res == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()){
                        while (fileIn.hasNextLine()){
                            String line = fileIn.nextLine()+"\n";
                            textArea.append(line);
                        }
                    }
                }catch (FileNotFoundException e1){
                    e1.printStackTrace();
                }finally {
                    fileIn.close();
                }
            }

        }
        if (e.getSource()==saveItem){
            JFileChooser filechoose = new JFileChooser();
            filechoose.setCurrentDirectory(new File("."));

            int res = filechoose.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION){
                File file;
                PrintWriter fileout = null;

                file = new File(filechoose.getSelectedFile().getAbsolutePath());
                try{
                    fileout = new PrintWriter(file);
                    fileout.println(textArea.getText());
                }catch(FileNotFoundException e1){
                    e1.printStackTrace();
                }
                finally {
                    fileout.close();
                }
            }
        }

        if (e.getSource()==exitItem){
            System.exit(0);
        }
    }
}
