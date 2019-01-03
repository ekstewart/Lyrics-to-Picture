package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Window extends JFrame {

    private static final String DIRECTORY_NAME = "/Users/pedrosuazo/IdeaProjects/Picture with Lyrics";
    private JMenu songMenu;
    //private JMenuItem[] songOptions;
    private JMenuBar menuBar;
    private JButton createPicture;
    private JButton addSong;
    private JButton createFile;
    private JTextArea lyrics;
    private JTextArea songName;
    private JLabel lyricsLabel;
    private JLabel songLabel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel picturePanel;


    public Window(){
        super("Pictures with Lyrics");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400,400);

        //Create panel for main window
        panel3 = new JPanel();
        panel5 = new JPanel();

        addSong = new JButton("Add New Song to Menu");
        addSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSongFrame();
            }
        });

        //Create JMenuBar, JMenu and label, and JMenuItem array
        menuBar = new JMenuBar();
        songLabel = new JLabel("Main Menu");
        songMenu = new JMenu("Songs");
        updateMenu(DIRECTORY_NAME);

        menuBar.add(songMenu);
        //panel5.add(songLabel);
        panel5.add(menuBar);
        panel3.add(addSong);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        //panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

        add(panel5);
        add(panel3);
        getContentPane().setLayout(new FlowLayout());
        setVisible(true);
        pack();

    }

    private void createPictureFrame(String songName){
        JFrame frame = new JFrame(songName);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        //Create the panel whose pixels will be colored
        picturePanel = new JPanel();




        frame.setVisible(true);
        frame.pack();
    }

    private void addSongFrame(){
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        //Create panels for the addSong Frame
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel4 = new JPanel();

        //Create JTextArea
        lyrics = new JTextArea(5, 5);
        songName = new JTextArea(5, 5);

        //Create labels for the JTextAreas
        lyricsLabel = new JLabel("Enter song lyrics here");
        songLabel = new JLabel("Enter song name here");

        //Create createFile button and add ActionListener
        createFile = new JButton("Create New File");
        createFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createLyricFile(songName.getText(), lyrics.getText());
            }
        });

        //Add the labels and text areas to the panels
        panel2.add(songLabel);
        panel2.add(songName);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel1.add(lyricsLabel);
        panel1.add(lyrics);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        panel4.add(createFile);

        frame.add(panel2);
        frame.add(panel1);
        frame.add(panel4);
        frame.getContentPane().setLayout(new FlowLayout());

        frame.setVisible(true);
        frame.pack();
    }

    private void createLyricFile(String songName, String lyrics){
        songName += ".txt";
        PrintWriter input = null;

        try{
            input = new PrintWriter(new FileOutputStream(songName));
            input.println(lyrics);
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            JOptionPane.showMessageDialog(null, "File Created Successfully. You may close this window now.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            updateMenu(DIRECTORY_NAME);
            input.close();
        }
    }

    /*
    method that will check how many txt files are in a given directory
    and then create a JMenuItem array that can be added to the main menu
    of the program
    */
    private void updateMenu(String directoryName){

        System.out.println("Updating menu...");

        //Access to the specific directory
        File search = new File(directoryName);

        //Gives us an array of pathnames i.e text file names
        File[] txtFiles = search.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File search, String name) {
                return name.endsWith(".txt");
            }
        });

        if(txtFiles.length != 0){
            songMenu.removeAll();
            JMenuItem[] songOptions = new JMenuItem[txtFiles.length];
            for(int i = 0; i < songOptions.length; i++){
                String fileName = txtFiles[i].getName();
                System.out.println("File Name: " + fileName.substring(0, fileName.lastIndexOf(".txt")));
                songOptions[i] = new JMenuItem(fileName.substring(0, fileName.lastIndexOf(".txt")));
            }

            System.out.println("Adding to the menu...");
            for(int i = 0; i < songOptions.length; i++){
                songMenu.add(songOptions[i]);
                songOptions[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JMenuItem source = (JMenuItem)e.getSource();
                        createPictureFrame(source.getText());
                    }
                });
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No song lyrics exist in the project directory. Let's add some!", "Add Songs", JOptionPane.INFORMATION_MESSAGE);
            addSongFrame();
        }
    }

    private ArrayList<Character> parseSong(String songName){
        Scanner input = null;
        String line = null;
        ArrayList<Character> letters = new ArrayList<>();
        songName+= ".txt";
        try{
            input = new Scanner(new FileInputStream(songName));
            while(input.hasNext()){
                line = input.next().toLowerCase();
                for(int i = 0; i < line.length(); i++){
                    if(Character.isAlphabetic(line.charAt(i))){
                        letters.add(line.charAt(i));
                    }
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        return letters;
    }

    private void colorPanel(ArrayList<Character> letters){
        for(int i = 0; i < letters.size(); i++){
            
        }
    }

}
