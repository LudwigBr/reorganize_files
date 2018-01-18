import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {

    private JPanel createCsvPanel;

    private JLabel chooseBaseFolderLabel;
    private JLabel chooseSaveLocationCsvLabel;
    private JButton chooseBaseFolderButton;
    private JButton chooseSaveLocationCsvButton;
    private JFileChooser chooseBaseFolder;
    private JFileChooser chooseSaveLocationCsv;
    private File baseFolder;
    private File saveLocationCsv;
    private JLabel createCsvDoneLabel;
    private JButton createCsvButton;

    private JLabel chooseDestinationLabel;
    private JLabel chooseInputCsvLabel;
    private JButton chooseDestinationButton;
    private JButton chooseInputCsvButton;
    private JFileChooser chooseDestination;
    private JFileChooser chooseInputCsv;
    private File destination;
    private File inputCsv;
    private JLabel renameDoneLabel;
    private JButton renameButton;



    public GUI() {
        this.setTitle("Reorganize Files");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createCsvPanel = new JPanel();
        GridLayout layout = new GridLayout();
        layout.setRows(7);
        layout.setColumns(2);
        createCsvPanel.setLayout(layout);

        baseFolder = new File("");
        saveLocationCsv = new File("");
        destination = new File("");
        inputCsv = new File("");

        chooseBaseFolder = new JFileChooser();
        chooseBaseFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseBaseFolder.setCurrentDirectory(new File("."));
        chooseSaveLocationCsv = new JFileChooser();
        chooseSaveLocationCsv.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseSaveLocationCsv.setCurrentDirectory(new File("."));
        chooseDestination = new JFileChooser();
        chooseDestination.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseDestination.setCurrentDirectory(new File("."));
        chooseInputCsv = new JFileChooser();
        chooseInputCsv.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooseInputCsv.setCurrentDirectory(new File("."));


        chooseBaseFolderLabel = new JLabel("");
        chooseBaseFolderButton = new JButton("Choose Location");
        chooseBaseFolderButton.addActionListener(this);
        chooseSaveLocationCsvLabel = new JLabel("");
        chooseSaveLocationCsvButton = new JButton("Choose CSV Destination");
        chooseSaveLocationCsvButton.addActionListener(this);
        createCsvDoneLabel = new JLabel("", SwingConstants.CENTER);
        createCsvButton = new JButton("Create CSV File");
        createCsvButton.addActionListener(this);

        chooseDestinationLabel = new JLabel("");
        chooseDestinationButton = new JButton("Choose Destination");
        chooseDestinationButton.addActionListener(this);
        chooseInputCsvLabel = new JLabel("");
        chooseInputCsvButton = new JButton("Choose Input CSV");
        chooseInputCsvButton.addActionListener(this);
        renameDoneLabel = new JLabel("", SwingConstants.CENTER);
        renameButton = new JButton("Copy and Rename");
        renameButton.addActionListener(this);

        createCsvPanel.add(chooseBaseFolderButton);
        createCsvPanel.add(chooseBaseFolderLabel);

        createCsvPanel.add(chooseSaveLocationCsvButton);
        createCsvPanel.add(chooseSaveLocationCsvLabel);

        createCsvPanel.add(createCsvButton);
        createCsvPanel.add(createCsvDoneLabel);

        createCsvPanel.add(new JLabel(""));
        createCsvPanel.add(new JLabel(""));

        createCsvPanel.add(chooseDestinationButton);
        createCsvPanel.add(chooseDestinationLabel);

        createCsvPanel.add(chooseInputCsvButton);
        createCsvPanel.add(chooseInputCsvLabel);

        createCsvPanel.add(renameButton);
        createCsvPanel.add(renameDoneLabel);


        this.add(createCsvPanel);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.chooseBaseFolderButton) {
            if(chooseBaseFolder.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                baseFolder = chooseBaseFolder.getSelectedFile();
                chooseBaseFolderLabel.setText(baseFolder.toString());
            }
        } else if(actionEvent.getSource() == this.chooseSaveLocationCsvButton) {
            if(chooseSaveLocationCsv.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                saveLocationCsv = chooseSaveLocationCsv.getSelectedFile();
                chooseSaveLocationCsvLabel.setText(saveLocationCsv.toString());
                chooseInputCsv.setSelectedFile(new File(saveLocationCsv.toString() + "\\input.csv"));
                chooseInputCsvLabel.setText(saveLocationCsv.toString() + "\\input.csv");
            }
        } else if(actionEvent.getSource() == this.createCsvButton) {
            if(baseFolder.toString().isEmpty() || saveLocationCsv.toString().isEmpty()) {
                createCsvDoneLabel.setText("path is missing");
                return;
            }
            createCsvDoneLabel.setText("...");
            InputCSVCreator creator = new InputCSVCreator();
            try {
                creator.writeInputCsv(baseFolder.toString(), saveLocationCsv.toString());
                createCsvDoneLabel.setText("DONE");
            } catch (IOException e) {
                createCsvDoneLabel.setText("error with paths");
            }
        } else if(actionEvent.getSource() == this.chooseDestinationButton) {
            if(chooseDestination.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                destination = chooseDestination.getSelectedFile();
                chooseDestinationLabel.setText(destination.toString());
            }
        } else if(actionEvent.getSource() == this.chooseInputCsvButton) {
            if(chooseInputCsv.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                inputCsv = chooseInputCsv.getSelectedFile();
                chooseInputCsvLabel.setText(inputCsv.toString());
            }
        } else if(actionEvent.getSource() == this.renameButton) {
            if(destination.toString().isEmpty() || inputCsv.toString().isEmpty()) {
                renameDoneLabel.setText("path is missing");
                return;
            }
            renameDoneLabel.setText("...");
            StructureReorganizer changer = new StructureReorganizer();
            try {
                changer.applyCSVStructure(destination.toString(), inputCsv.toString());
                renameDoneLabel.setText("DONE");
            } catch (Exception e) {
                renameDoneLabel.setText("error with paths");
            }
        }
    }
}
