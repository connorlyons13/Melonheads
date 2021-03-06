/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

//imports here
import api.APIConnections;
import api.InvalidYoutubeVideoUrlException;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;





/**
 *
 * @author Connor
 * @author Carson
 */
public class MainjFrame extends javax.swing.JFrame{
    
    private JWebBrowser webBrowser; //webBrowser object is where video is played
    private GridLayout grd1col = new GridLayout(0, 1);
    private GridLayout grd3col = new GridLayout(0, 5);
    
    /**
     * Creates new form SeniorProjV2jFrame
     */
    public MainjFrame() {
        initComponents();
        //logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("src/resources/Melonheads50by50.png")));
        advSearchPannleButton.setVisible(false);
        jLabelSelectedMenu.setText("");
        jSeparator4.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jComboBox1.setVisible(false);
        jButtonSubimtSong.setVisible(false);
        jButtonSubmitPlaylist.setVisible(false);
        //songTitleLbl.setVisible(false);
        //songArtistLbl.setVisible(false);
        //songAlbumLbl.setVisible(false);
        //songPlaysLbl.setVisible(false);
        //musicSeparator.setVisible(false);
        jScrollPane1.setVisible(false);
        jLabel4.setText("Song title:");
        jLabel5.setText("artist:");
        jLabel6.setText("album (optional):");
        webBrowser = new JWebBrowser(JWebBrowser.destroyOnFinalization());
        webBrowser.setBarsVisible(false);
        playPnl.add(webBrowser, BorderLayout.CENTER);
        playEditButton.setVisible(false);
        publicBtn.setVisible(false);
        
        
        //FlowLayout playlistLayout = new FlowLayout();
        
        //ImageIcon icon = new ImageIcon(Main.class.getResource("/resources/Melonheads50by50.png"));
        //this.setIconImage(icon.getImage());
        Toolkit tk = Toolkit.getDefaultToolkit();
	int screenWidth = tk.getScreenSize().width;
	int screenHeight = tk.getScreenSize().height;
	setLocation(screenWidth/4, screenHeight/5);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                NativeInterface.close();
            }
        });
        
        //Create directory for playlist file storage on user's machine
        //and populate the playlist quick reference bar with playlists
        File playlists = new File("data/playlists/playlist.txt");
        try {
            new File("data/").mkdir(); // ensure that the data folder exists
            new File("data/playlists/").mkdir(); // ensure that the playlists folder exists
            new File("data/playlists/temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(playlists));
            String line;
            while((line = reader.readLine()) != null){
                JButton newBtn = new JButton(line);

                newBtn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae2){
                        
                        jPanel1.removeAll();
                        jPanel1.setLayout(grd1col);
                        jPanel1.validate();
                        jScrollPane1.validate();
                        
                        
                        //jPanel1.removeAll();
                        //jPanel1.revalidate();
                        //jPanel1.repaint();
                        jLabel2.setVisible(false);
                        jLabel3.setVisible(false);
                        jLabel4.setVisible(false);
                        jLabel5.setVisible(false);
                        jLabel6.setVisible(false);
                        jTextField2.setVisible(false);
                        jTextField3.setVisible(false);
                        jTextField4.setVisible(false);
                        jTextField5.setVisible(false);
                        jComboBox1.setVisible(false);
                        jButtonSubimtSong.setVisible(false);
                        jButtonSubmitPlaylist.setVisible(false);
                        //songTitleLbl.setVisible(true);
                        //songArtistLbl.setVisible(true);
                        //songAlbumLbl.setVisible(true);
                        //songPlaysLbl.setVisible(true);
                        //musicSeparator.setVisible(true);
                        jScrollPane1.setVisible(true);
                        playEditButton.setVisible(true);
                        publicBtn.setVisible(true);
                        jLabelSelectedMenu.setText(newBtn.getText());
                        String songLine;
                        File songs = new File("data/playlists/" + newBtn.getText() + ".txt");
                        try {
                            BufferedReader songRead = new BufferedReader(new FileReader(songs));
                            while((songLine = songRead.readLine()) != null)
                            {
                                Song target = APIConnections.getSongs(APIConnections.GET_ID, songLine).get(0);
                                sButton newSBtn = new sButton(Integer.parseInt(songLine), target.getArtist()+ " - " + target.getTitle() + " - " + target.getAlbum());
                                newSBtn.setHorizontalAlignment(SwingConstants.LEFT);
                                
                                
                                newSBtn.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent addToList){
                                        playSong(target);
                                    }
                                });
                                
                                
                                
                                
                                
                                
                                
                                
                                jPanel1.add(newSBtn);
                            }
                            songRead.close();
                            jPanel1.validate();
                            jPanel1.repaint();
                            jScrollPane1.validate();
                            jScrollPane1.repaint();
                            //Then populate with music from that playlist
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                jPanelMenuPlaylistDisplay.add(newBtn);
                jPanelMenuPlaylistDisplay.validate();
                jScrollPanePlaylistDisplay.validate();
            
            }
            reader.close();
        }catch(IOException e)
        {
            
        }
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoLabel = new javax.swing.JLabel();
        meloLabel = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        musicButton = new javax.swing.JButton();
        myPlaylistsButton = new javax.swing.JButton();
        popularPlaylistsButton3 = new javax.swing.JButton();
        newAdditionsButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPanePlaylistDisplay = new javax.swing.JScrollPane();
        jPanelMenuPlaylistDisplay = new javax.swing.JPanel();
        addSongButton = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        createNewPlaylistButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelSelectedMenu = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButtonSubimtSong = new javax.swing.JButton();
        jButtonSubmitPlaylist = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        advSearchPannleButton = new javax.swing.JButton();
        playEditButton = new javax.swing.JButton();
        publicBtn = new javax.swing.JButton();
        playPnl = new javax.swing.JPanel();
        advSearchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        meloLabel.setBackground(new java.awt.Color(255, 255, 255));
        meloLabel.setFont(new java.awt.Font("Bebas Neue", 0, 40)); // NOI18N
        meloLabel.setText("MELONHEADS");
        meloLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51)));

        jPanelMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        musicButton.setFont(new java.awt.Font("Bebas Neue", 0, 24)); // NOI18N
        musicButton.setText("Music");
        musicButton.setBorderPainted(false);
        musicButton.setContentAreaFilled(false);
        musicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicButtonActionPerformed(evt);
            }
        });

        myPlaylistsButton.setFont(new java.awt.Font("Bebas Neue", 0, 20)); // NOI18N
        myPlaylistsButton.setText("My Playlists");
        myPlaylistsButton.setBorderPainted(false);
        myPlaylistsButton.setContentAreaFilled(false);
        myPlaylistsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myPlaylistsButtonActionPerformed(evt);
            }
        });

        popularPlaylistsButton3.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        popularPlaylistsButton3.setText("Popular Playlists");
        popularPlaylistsButton3.setBorderPainted(false);
        popularPlaylistsButton3.setContentAreaFilled(false);
        popularPlaylistsButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popularPlaylistsButton3ActionPerformed(evt);
            }
        });

        newAdditionsButton.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        newAdditionsButton.setText("New Song Additions");
        newAdditionsButton.setBorderPainted(false);
        newAdditionsButton.setContentAreaFilled(false);
        newAdditionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAdditionsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(newAdditionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(myPlaylistsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addComponent(musicButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(popularPlaylistsButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator3)
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addComponent(musicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myPlaylistsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(popularPlaylistsButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newAdditionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPanePlaylistDisplay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPanePlaylistDisplay.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanelMenuPlaylistDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelMenuPlaylistDisplay.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPanePlaylistDisplay.setViewportView(jPanelMenuPlaylistDisplay);

        addSongButton.setText("+");
        addSongButton.setToolTipText("Click here to add music!");
        addSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongButtonActionPerformed(evt);
            }
        });

        jTextFieldSearch.setText("Search");
        jTextFieldSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextFieldSearch.setName("searchTxt"); // NOI18N
        jTextFieldSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldSearchMousePressed(evt);
            }
        });
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }
        });

        createNewPlaylistButton.setFont(new java.awt.Font("Bebas Neue", 0, 16)); // NOI18N
        createNewPlaylistButton.setText("Create a  new playlist");
        createNewPlaylistButton.setActionCommand("Create a new playlist");
        createNewPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewPlaylistButtonActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelSelectedMenu.setFont(new java.awt.Font("Bebas Neue", 0, 48)); // NOI18N
        jLabelSelectedMenu.setForeground(new java.awt.Color(51, 153, 0));
        jLabelSelectedMenu.setText("Create New Playlist");

        jLabel2.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        jLabel2.setText("playlist name:");

        jLabel3.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        jLabel3.setText("jLabel3");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        jLabel4.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        jLabel5.setText("jLabel5");

        jLabel6.setFont(new java.awt.Font("Bebas Neue", 0, 18)); // NOI18N
        jLabel6.setText("jLabel6");

        jButtonSubimtSong.setFont(new java.awt.Font("Bebas Neue", 0, 24)); // NOI18N
        jButtonSubimtSong.setText("Submit");
        jButtonSubimtSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubimtSongActionPerformed(evt);
            }
        });

        jButtonSubmitPlaylist.setFont(new java.awt.Font("Bebas Neue", 0, 24)); // NOI18N
        jButtonSubmitPlaylist.setText("Submit");
        jButtonSubmitPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitPlaylistActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(0, 3));
        jScrollPane1.setViewportView(jPanel1);

        advSearchPannleButton.setFont(new java.awt.Font("Bebas Neue", 0, 24)); // NOI18N
        advSearchPannleButton.setText("Search");
        advSearchPannleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advSearchPannleButtonActionPerformed(evt);
            }
        });

        playEditButton.setFont(new java.awt.Font("Bebas Neue", 0, 16)); // NOI18N
        playEditButton.setText("Edit");
        playEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playEditButtonActionPerformed(evt);
            }
        });

        publicBtn.setFont(new java.awt.Font("Bebas Neue", 0, 16)); // NOI18N
        publicBtn.setText("public");
        publicBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publicBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelSelectedMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(publicBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playEditButton))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSubmitPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSubimtSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(advSearchPannleButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSelectedMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playEditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(publicBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSubimtSong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(advSearchPannleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSubmitPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        playPnl.setLayout(new java.awt.BorderLayout());

        advSearchButton.setText("Advanced Search");
        advSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(meloLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addSongButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(advSearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanelMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPanePlaylistDisplay, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createNewPlaylistButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addSongButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(meloLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(advSearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPanePlaylistDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createNewPlaylistButton))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(playPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //NOT USED
    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
     
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    //Function used to go to the "add a song" page by clicking
    //on the "add song" button, the "+" button on the upper right
    private void addSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongButtonActionPerformed
        jLabelSelectedMenu.setText("Add a new song");
        jSeparator4.setVisible(true);
        jLabel2.setVisible(true);
        jLabel2.setText("link:");
        jTextField2.setVisible(true);
        jTextField2.setText("");
        jLabel3.setVisible(true);
        jLabel3.setText("Source:");
        jComboBox1.setVisible(true);
        jComboBox1.removeAllItems();
        jComboBox1.addItem("youtube");
        jComboBox1.addItem("soundCloud-NOT YET");
        jLabel4.setVisible(true);
        jLabel4.setText("song title:");
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        jTextField3.setVisible(true);
        jTextField3.setText("");
        jTextField4.setVisible(true);
        jTextField4.setText("");
        jTextField5.setVisible(true);
        jTextField5.setText("");
        jButtonSubimtSong.setVisible(true);
        jButtonSubmitPlaylist.setVisible(false);
        jScrollPane1.setVisible(false);
        advSearchPannleButton.setVisible(false);
        playEditButton.setVisible(false);
        publicBtn.setVisible(false);
    }//GEN-LAST:event_addSongButtonActionPerformed

    //Function to access the user's playlist and populate the main
    //panel with the list of playlists
    private void myPlaylistsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myPlaylistsButtonActionPerformed
        jLabelSelectedMenu.setText("My playlists");
        jSeparator4.setVisible(true);
        jLabel2.setVisible(false);
        jTextField2.setVisible(false);
        jLabel3.setVisible(false);
        jComboBox1.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jButtonSubimtSong.setVisible(false);
        jButtonSubmitPlaylist.setVisible(false);
        jScrollPane1.setVisible(false);
        advSearchPannleButton.setVisible(false);
        playEditButton.setVisible(false);
        publicBtn.setVisible(false);
        jPanel1.setVisible(true);
        jScrollPane1.setVisible(true);
        
        jPanel1.removeAll();
        jPanel1.setLayout(grd1col);
        jPanel1.validate();
        jPanel1.repaint();
        jScrollPane1.validate();
        jScrollPane1.repaint();
        
        File playlists = new File("data/playlists/playlist.txt");
        try {
            new File("data/").mkdir(); // ensure that the data folder exists
            new File("data/playlists/").mkdir(); // ensure that the playlists folder exists
            BufferedReader reader = new BufferedReader(new FileReader(playlists));
            String line;
            while((line = reader.readLine()) != null){
                JButton newBtn = new JButton(line);

                newBtn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae2){
                        
                        jPanel1.removeAll();
                        jPanel1.setLayout(grd1col);
                        jPanel1.validate();
                        jScrollPane1.validate();
                        
                        

                        jLabel2.setVisible(false);
                        jLabel3.setVisible(false);
                        jLabel4.setVisible(false);
                        jLabel5.setVisible(false);
                        jLabel6.setVisible(false);
                        jTextField2.setVisible(false);
                        jTextField3.setVisible(false);
                        jTextField4.setVisible(false);
                        jTextField5.setVisible(false);
                        jComboBox1.setVisible(false);
                        jButtonSubimtSong.setVisible(false);
                        jButtonSubmitPlaylist.setVisible(false);
                        jScrollPane1.setVisible(true);
                        playEditButton.setVisible(true);
                        publicBtn.setVisible(true);
                        jLabelSelectedMenu.setText(newBtn.getText());
                        String songLine;
                        File songs = new File("data/playlists/" + newBtn.getText() + ".txt");
                        try {
                            BufferedReader songRead = new BufferedReader(new FileReader(songs));
                            while((songLine = songRead.readLine()) != null)
                            {
                                Song target = APIConnections.getSongs(APIConnections.GET_ID, songLine).get(0);
                                sButton newSBtn = new sButton(Integer.parseInt(songLine), target.getArtist()+ " - " + target.getTitle() + " - " + target.getAlbum());
                                newSBtn.setHorizontalAlignment(SwingConstants.LEFT);
                                
                                
                                newSBtn.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent addToList){
                                        playSong(target);
                                    }
                                });
                                
                                
                                
                                
                                
                                
                                
                                
                                jPanel1.add(newSBtn);
                                
                            }
                            songRead.close();
                            jPanel1.validate();
                            jPanel1.repaint();
                            jScrollPane1.validate();
                            jScrollPane1.repaint();
                            //Then populate with music from that playlist
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                newBtn.setHorizontalAlignment(SwingConstants.LEFT);
                jPanel1.add(newBtn);
                jPanel1.validate();
                jScrollPane1.validate();
            
            }
            reader.close();
        }catch(IOException e)
        {
            
        }
    }//GEN-LAST:event_myPlaylistsButtonActionPerformed

    //Function to bring up music in the main panel, used for searches
    private void musicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musicButtonActionPerformed
        jLabelSelectedMenu.setText("Music");
        jSeparator4.setVisible(true);
        jLabel2.setVisible(false);
        jTextField2.setVisible(false);
        jLabel3.setVisible(false);
        jComboBox1.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jButtonSubimtSong.setVisible(false);
        jButtonSubmitPlaylist.setVisible(false);
        jScrollPane1.setVisible(true);
        advSearchPannleButton.setVisible(false);
        playEditButton.setVisible(false);
        publicBtn.setVisible(false);
        
        jPanel1.removeAll();
        jPanel1.setLayout(grd3col);
        jPanel1.validate();
        jPanel1.repaint();
        jScrollPane1.validate();
        jScrollPane1.repaint();
    }//GEN-LAST:event_musicButtonActionPerformed

    //Function used to bring up the page to create a new playlist in
    //the main panel
    private void createNewPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewPlaylistButtonActionPerformed
        jLabelSelectedMenu.setText("Create a New Playlist");
        jSeparator4.setVisible(true);
        jLabel2.setVisible(true);
        jLabel2.setText("Playlist name:");
        jTextField2.setVisible(true);
        jTextField2.setText("");
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jButtonSubimtSong.setVisible(false);
        jButtonSubmitPlaylist.setVisible(true);
        jScrollPane1.setVisible(false);
        advSearchPannleButton.setVisible(false);
        playEditButton.setVisible(false);
        publicBtn.setVisible(false);
    }//GEN-LAST:event_createNewPlaylistButtonActionPerformed

    //Function used to bring up the newest playlists in the main panel
    private void popularPlaylistsButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popularPlaylistsButton3ActionPerformed
        jLabelSelectedMenu.setText("Popular playlists");
        jSeparator4.setVisible(true);
        jLabel2.setVisible(false);
        jTextField2.setVisible(false);
        jLabel3.setVisible(false);
        jComboBox1.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jButtonSubimtSong.setVisible(false);
        jButtonSubmitPlaylist.setVisible(false);
        jScrollPane1.setVisible(false);
        advSearchPannleButton.setVisible(false);
        playEditButton.setVisible(false);
        publicBtn.setVisible(false);
        
        jPanel1.removeAll();
        jPanel1.setLayout(grd1col);
        jPanel1.validate();
        jPanel1.repaint();
        jScrollPane1.validate();
        jScrollPane1.repaint();
    }//GEN-LAST:event_popularPlaylistsButton3ActionPerformed

    //Function to bring up the newest songs added to the main panel
    private void newAdditionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAdditionsButtonActionPerformed
        jLabelSelectedMenu.setText("New Additions");
        jSeparator4.setVisible(true);
        jLabel2.setVisible(false);
        jTextField2.setVisible(false);
        jLabel3.setVisible(false);
        jComboBox1.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jButtonSubimtSong.setVisible(false);
        jButtonSubmitPlaylist.setVisible(false);

        jScrollPane1.setVisible(true);
        advSearchPannleButton.setVisible(false);
        playEditButton.setVisible(false);
        publicBtn.setVisible(false);
        
        jPanel1.removeAll();
        jPanel1.setLayout(grd3col);
        jPanel1.validate();
        jPanel1.repaint();
        jScrollPane1.validate();
        jScrollPane1.repaint();
        
        

        try {
            ArrayList<Integer> songIds = new ArrayList<Integer>();    
            songIds = APIConnections.getRecentSongIds();
            ArrayList<Song> songs = new ArrayList<Song>();
            System.out.println("size = " + songIds.size() + " api call size = " + APIConnections.getRecentSongIds().size());
            
            for(int i = 0; i < songIds.size(); i++){
                
                songs.add(APIConnections.getSongs(1, String.valueOf(songIds.get(i))).get(0));
                System.out.println(songIds.get(i));            
            }
            
            setResultPanel(songs);
        
        
        
        
        
        
        } catch (Exception ex) {
            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_newAdditionsButtonActionPerformed

    //NOT USED
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    //Function to submit the song to the Melonheads database.
    //Error checks the text fields for completeness, does not submit if incorrect.
    //Displays a confirmation option dialog box afterwards where the song is
    //played for the user to verify their submission.
    private void jButtonSubimtSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubimtSongActionPerformed
        try {
            //SUBMIT button for adding a new song
                //String title = jTextField3.getText().replaceAll(" ", "%20");
                String title = jTextField3.getText();
                if (title.equals("")) {
                    throw new NoTitleException();
                }
                //String artist = jTextField4.getText().replaceAll(" ","%20");
                String artist = jTextField4.getText();
                if (artist.equals("")) {
                    throw new NoArtistException();
                }
                //String album = jTextField5.getText().replaceAll(" ","%20");
                String album = jTextField5.getText();
                if (album.equals("")) {
                    album = " ";
                }
                Object[] options = {"Yes, submit song","No, do not submit"};
                Song tempSong = new Song();
                tempSong.setTitle(title);
                tempSong.setArtist(artist);
                tempSong.setAlbum(album);
                tempSong.setURL(APIConnections.getYoutubeVideoId(jTextField2.getText()));
                tempSong.setSource(jComboBox1.getSelectedItem().toString());
                playSong(tempSong);
                int option = JOptionPane.showOptionDialog(null,
                        "Please make sure the song you've selected is properly playing in the background,\n"
                                + "in the main window. Are you sure you wish to submit this song?",
                        "Confirm song submission",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        "Yes, submit song");
                if (option == 0) {
                    try {
                        APIConnections.createSong(title, artist, album, jTextField2.getText(), jComboBox1.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(null,"Add complete!", "Submit Complete", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(InvalidYoutubeVideoUrlException e) {
                        JOptionPane.showMessageDialog(null,"Invalid Youtube URL. Song not added.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    //code for when they click no
                }
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField2.setText("");
                
        } catch (NoTitleException ex) {
            JOptionPane.showMessageDialog(null, "Must add a song title!", null, JOptionPane.INFORMATION_MESSAGE);
            //Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoArtistException ex2) {
            JOptionPane.showMessageDialog(null, "Must add artist name!", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex3) {
            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex3);
        }
    }//GEN-LAST:event_jButtonSubimtSongActionPerformed

    private void jButtonSubmitPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitPlaylistActionPerformed
        //SUBMIT button for adding a new playlist
        
        //When button is pushed, first it needs to verify that the name field is filled
        //Then, it needs to store that name somewhere (considering storing all local
        //information, local playlists, to a text file in the application folder,
        //but that isn't final).
        //Then, it needs to clear the text field of the playlist name.
        //Then, a messagebox popup should occur letting the user know their playlist
        //was added successfully
        //Then, if the playlist is public, it needs to get added to the database
        //Then, if it is public or private, it needs to get added to the user's collection
        //of stored playlists and added to the jScrollPane of playlists so the user
        //can select it and edit it to add music to it.
        
        JButton newBtn = new JButton(jTextField2.getText());
        newBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae2){

                jPanel1.removeAll();
                        jPanel1.setLayout(grd1col);
                        jPanel1.validate();
                        jScrollPane1.validate();
                        
                        
                        //jPanel1.removeAll();
                        //jPanel1.revalidate();
                        //jPanel1.repaint();
                        jLabel2.setVisible(false);
                        jLabel3.setVisible(false);
                        jLabel4.setVisible(false);
                        jLabel5.setVisible(false);
                        jLabel6.setVisible(false);
                        jTextField2.setVisible(false);
                        jTextField3.setVisible(false);
                        jTextField4.setVisible(false);
                        jTextField5.setVisible(false);
                        jComboBox1.setVisible(false);
                        jButtonSubimtSong.setVisible(false);
                        jButtonSubmitPlaylist.setVisible(false);
                        //songTitleLbl.setVisible(true);
                        //songArtistLbl.setVisible(true);
                        //songAlbumLbl.setVisible(true);
                        //songPlaysLbl.setVisible(true);
                        //musicSeparator.setVisible(true);
                        jScrollPane1.setVisible(true);
                        playEditButton.setVisible(true);
                        publicBtn.setVisible(true);
                        jLabelSelectedMenu.setText(newBtn.getText());
                        String songLine;
                        File songs = new File("data/playlists/" + newBtn.getText() + ".txt");
                        try {
                            BufferedReader songRead = new BufferedReader(new FileReader(songs));
                            while((songLine = songRead.readLine()) != null)
                            {
                                Song target = APIConnections.getSongs(APIConnections.GET_ID, songLine).get(0);
                                sButton newSBtn = new sButton(Integer.parseInt(songLine), target.getArtist()+ " - " + target.getTitle() + " - " + target.getAlbum());
                                newSBtn.setHorizontalAlignment(SwingConstants.LEFT);
                                
                                
                                newSBtn.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent addToList){
                                        playSong(target);
                                    }
                                });

                                
                                jPanel1.add(newSBtn);
                            }
                            songRead.close();
                            jPanel1.validate();
                            jPanel1.repaint();
                            jScrollPane1.validate();
                            jScrollPane1.repaint();
                            //Then populate with music from that playlist
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
        });
        
         try {
             
            File playlists = new File("data/playlists/playlist.txt");
            FileWriter fileWriter = new FileWriter(playlists, true);
            PrintWriter playlistWriter = new PrintWriter(fileWriter, true);
            playlistWriter.println(jTextField2.getText());
            playlistWriter.close();
            fileWriter.close();
            
            File newPlaylist = new File("data/playlists/" + jTextField2.getText() +".txt");
            FileWriter fileWriter2 = new FileWriter(newPlaylist, true);
            PrintWriter playlistWriter2 = new PrintWriter(fileWriter2, true);
            playlistWriter2.close();
            fileWriter2.close();
        
        }catch(IOException e)
        {
            
        }
        
        
        
        
        
        jPanelMenuPlaylistDisplay.add(newBtn);
        jPanelMenuPlaylistDisplay.validate();
        jScrollPanePlaylistDisplay.validate();
        jTextField2.setText("");
        
        //This works to add new playlist buttons to the playlist quick menu
        //need to implement a file reader/writer to store the information
        //between sessions and rewrite the playlists into the program in the 
        //system init to recover them all
    }//GEN-LAST:event_jButtonSubmitPlaylistActionPerformed
  
    private void jTextFieldSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMousePressed
         jTextFieldSearch.setText("") ;
    }//GEN-LAST:event_jTextFieldSearchMousePressed

    //Function on what to display when a search is performed by the user
    //inputting text into the search bar and hitting Enter
    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try {
               // TODO: create list of songs to display and populate it with the getSongs search
               //test = APIConnections.getSongs(APIConnections.GET_SEARCH, jTextFieldSearch.getText()) ;
               musicButton.doClick();
               
               musicButton.doClick();
               ArrayList<Song> results = new ArrayList<Song>();
               results = APIConnections.getSongs(APIConnections.GET_SEARCH, jTextFieldSearch.getText().replaceAll(" ","%20"));
               
               
               setResultPanel(results);               
               /*
               jPanel1.removeAll();
               jPanel1.setLayout(grd3col);
               jPanel1.revalidate();
               jPanel1.repaint();
               
               JLabel ata = new JLabel("artist--title--album");
               ata.setFont(new java.awt.Font("Bebas Neue", 0, 40));
               ata.setForeground(new java.awt.Color(51, 153, 0));
               ata.setHorizontalAlignment(SwingConstants.CENTER);
               ata.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51)));
               JLabel plays = new JLabel("plays");
               plays.setFont(new java.awt.Font("Bebas Neue", 0, 40));
               plays.setForeground(new java.awt.Color(51, 153, 0));
               plays.setHorizontalAlignment(SwingConstants.CENTER);
               plays.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51)));
               JLabel ratingLb = new JLabel("rating");
               ratingLb.setFont(new java.awt.Font("Bebas Neue", 0, 40));
               ratingLb.setForeground(new java.awt.Color(51, 153, 0));
               ratingLb.setHorizontalAlignment(SwingConstants.CENTER);
               ratingLb.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51)));
               JLabel blank = new JLabel("");
               JLabel blank2 = new JLabel("");
               
               jPanel1.add(ata);
               jPanel1.add(plays);
               jPanel1.add(ratingLb);
               jPanel1.add(blank);
               jPanel1.add(blank2);
               
               for(int i = 0; i < results.size(); i++)
               {
                    final Song tempSong = results.get(i);
                    JButton newBtn = new JButton(tempSong.getArtist() + " - " + tempSong.getTitle() + " - " + tempSong.getAlbum());
                    JLabel playsLbl = new JLabel(""+tempSong.getPlays()+"");
                    playsLbl.setHorizontalAlignment(SwingConstants.CENTER);
                    int rating;
                    double value = 0.0;
                    double upVotes = 0.0;
                    double downVotes, total;
                    if (tempSong.getUpvotes() == 0)
                        rating = 0;
                    else
                        upVotes = tempSong.getUpvotes();
                        downVotes = tempSong.getDownvotes();
                        total = upVotes + downVotes;
                        value = (upVotes / total) * 100;
                        rating = (int)value;
                    JLabel ratingLbl = new JLabel(""+rating+"%");
                    ratingLbl.setHorizontalAlignment(SwingConstants.CENTER);
                    JButton likeBtn = new JButton("LIKE");
                    likeBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae4){
                            tempSong.addUpvote();
                            try {
                                APIConnections.voteSong(tempSong.getId(), APIConnections.VOTE_UP);
                            } catch (Exception e){}
                        }
                    });
                    JButton dislikeBtn = new JButton("DISLIKE");
                    dislikeBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae5){
                            tempSong.addDownvote();
                            try {
                                APIConnections.voteSong(tempSong.getId(), APIConnections.VOTE_DOWN);
                            } catch (Exception e){}
                        }
                    });
                    newBtn.setHorizontalAlignment(SwingConstants.LEFT);
                    newBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            playSong(tempSong);
                            tempSong.addPlays();
                            try {
                                APIConnections.playSong(tempSong.getId());
                            } catch (Exception ee){}
                        }
                    });
                    jPanel1.add(newBtn);
                    jPanel1.add(playsLbl);
                    jPanel1.add(ratingLbl);
                    jPanel1.add(likeBtn);
                    jPanel1.add(dislikeBtn);
                    jPanel1.validate();
                    jScrollPane1.validate();
               }
               */
            
            } catch (Exception ex) {
                Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    //Organizes the results in the main panel with headings
    private void setResultPanel(ArrayList<Song> results){
               
               jPanel1.removeAll();
               jPanel1.setLayout(grd3col);
               jPanel1.revalidate();
               jPanel1.repaint();
               
               JLabel ata = new JLabel("artist--title--album");
               ata.setFont(new java.awt.Font("Bebas Neue", 0, 40));
               ata.setForeground(new java.awt.Color(51, 153, 0));
               ata.setHorizontalAlignment(SwingConstants.CENTER);
               ata.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51)));
               JLabel plays = new JLabel("plays");
               plays.setFont(new java.awt.Font("Bebas Neue", 0, 40));
               plays.setForeground(new java.awt.Color(51, 153, 0));
               plays.setHorizontalAlignment(SwingConstants.CENTER);
               plays.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51)));
               JLabel ratingLb = new JLabel("rating");
               ratingLb.setFont(new java.awt.Font("Bebas Neue", 0, 40));
               ratingLb.setForeground(new java.awt.Color(51, 153, 0));
               ratingLb.setHorizontalAlignment(SwingConstants.CENTER);
               ratingLb.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51), new java.awt.Color(0, 153, 51)));
               JLabel blank = new JLabel("");
               JLabel blank2 = new JLabel("");
               
               jPanel1.add(ata);
               jPanel1.add(plays);
               jPanel1.add(ratingLb);
               jPanel1.add(blank);
               jPanel1.add(blank2);
               
               for(int i = 0; i < results.size(); i++)
               {
                    final Song tempSong = results.get(i);
                    JButton newBtn = new JButton(tempSong.getArtist() + " - " + tempSong.getTitle() + " - " + tempSong.getAlbum());
                    JLabel playsLbl = new JLabel(""+tempSong.getPlays()+"");
                    playsLbl.setHorizontalAlignment(SwingConstants.CENTER);
                    int rating;
                    double value = 0.0;
                    double upVotes = 0.0;
                    double downVotes, total;
                    if (tempSong.getUpvotes() == 0)
                        rating = 0;
                    else
                        upVotes = tempSong.getUpvotes();
                        downVotes = tempSong.getDownvotes();
                        total = upVotes + downVotes;
                        value = (upVotes / total) * 100;
                        rating = (int)value;
                    JLabel ratingLbl = new JLabel(""+rating+"%");
                    ratingLbl.setHorizontalAlignment(SwingConstants.CENTER);
                    JButton likeBtn = new JButton("LIKE");
                    likeBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae4){
                            tempSong.addUpvote();
                            try {
                                APIConnections.voteSong(tempSong.getId(), APIConnections.VOTE_UP);
                            } catch (Exception e){}
                        }
                    });
                    JButton dislikeBtn = new JButton("DISLIKE");
                    dislikeBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae5){
                            tempSong.addDownvote();
                            try {
                                APIConnections.voteSong(tempSong.getId(), APIConnections.VOTE_DOWN);
                            } catch (Exception e){}
                        }
                    });
                    newBtn.setHorizontalAlignment(SwingConstants.LEFT);
                    newBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            playSong(tempSong);
                            tempSong.addPlays();
                            try {
                                APIConnections.playSong(tempSong.getId());
                            } catch (Exception ee){}
                        }
                    });
                    jPanel1.add(newBtn);
                    jPanel1.add(playsLbl);
                    jPanel1.add(ratingLbl);
                    jPanel1.add(likeBtn);
                    jPanel1.add(dislikeBtn);
                    jPanel1.validate();
                    jScrollPane1.validate();
               }
               
    }
    
    
    
    
    
    private void advSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advSearchButtonActionPerformed
       jLabelSelectedMenu.setText("Advanced Search");
        jSeparator4.setVisible(true);
        jLabel2.setVisible(false);
        jTextField2.setVisible(false);
        jLabel3.setVisible(true);
        jLabel3.setText("Select Advanced Search Option: ");
        jComboBox1.setVisible(true);
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Artist");
        jComboBox1.addItem("Album");
        jComboBox1.addItem("Song Name");
        advSearchPannleButton.setVisible(true);
        
        
        jLabel4.setVisible(true);
        jLabel4.setText("Enter search term: ");
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jTextField3.setVisible(true);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jButtonSubimtSong.setVisible(false);
        jButtonSubmitPlaylist.setVisible(false);
        //songTitleLbl.setVisible(false);
        //songArtistLbl.setVisible(false);
        //songAlbumLbl.setVisible(false);
        //songPlaysLbl.setVisible(false);
        //musicSeparator.setVisible(false);
        jScrollPane1.setVisible(false);
        playEditButton.setVisible(false);
        publicBtn.setVisible(false);
    }//GEN-LAST:event_advSearchButtonActionPerformed

    private void advSearchPannleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advSearchPannleButtonActionPerformed
        String searchOpt;
        String searchTerm;
        
        searchOpt =  String.valueOf(jComboBox1.getSelectedItem());
        searchTerm = jTextField3.getText();
        
        if(searchOpt.contains("Artist")){
            try { 
                //APIConnections.getSongs(APIConnections.GET_ARTIST, searchTerm);
                musicButton.doClick();
                ArrayList<Song> results = new ArrayList<Song>();
                results = APIConnections.getSongs(APIConnections.GET_ARTIST, searchTerm.replaceAll(" ","%20"));
                
                setResultPanel(results);
                
        
            } catch (Exception ex) {
                Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(searchOpt.contains("Album")){
            try { 
                //APIConnections.getSongs(APIConnections.GET_ALBUM, searchTerm);
                musicButton.doClick();
                ArrayList<Song> results = new ArrayList<Song>();
                results = APIConnections.getSongs(APIConnections.GET_ALBUM, searchTerm.replaceAll(" ","%20"));
               
                
                setResultPanel(results);                

            } catch (Exception ex) {
                Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(searchOpt.contains("Song Name")){
            try { 
                //APIConnections.getSongs(APIConnections.GET_TITLE, searchTerm);
                musicButton.doClick();
                ArrayList<Song> results = new ArrayList<Song>();
                results = APIConnections.getSongs(APIConnections.GET_TITLE, searchTerm.replaceAll(" ","%20"));
               
                setResultPanel(results);                

            } catch (Exception ex) {
                Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_advSearchPannleButtonActionPerformed

    //Opens the playlist builder jFrame window
    private void playEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playEditButtonActionPerformed
       //Need to get whatever playlist is open to pass info to the playlist buiderframe
             PlaylistBuilderjFrame PlaylistBuiderFrame = new PlaylistBuilderjFrame( null,jLabelSelectedMenu.getText());
             PlaylistBuiderFrame.setVisible(true);
       
       
    }//GEN-LAST:event_playEditButtonActionPerformed

    //function to make a playlist public by pushing it and it's songs
    //to the database
    private void publicBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publicBtnActionPerformed
        Object[] options = {"Yes","No"};
 
        int choice = JOptionPane.showOptionDialog(null, 
                "Are you sure you want to make this playlist public?\nThis cannot be undone.",
                "Make playlist public",
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null,
                options,
                options[1]);
        
        if(choice == 0) {
            try {
                File currentPlaylist = new File("data/playlists/"+jLabelSelectedMenu.getText()+".txt");
                BufferedReader reader1 = new BufferedReader(new FileReader(currentPlaylist));
                String line = "";
                String content = "";
                while((line = reader1.readLine()) != null){
                    content += line + ",";
                }
                content = content.substring(0, content.length() - 1);
                APIConnections.createPlaylist(jLabelSelectedMenu.getText(), content);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            
        }
    }//GEN-LAST:event_publicBtnActionPerformed

    //Plays the song. Done automatically when clicking on a song
    private boolean playSong(Song song) {
        if(song.getSource().equals("youtube")) {
            webBrowser.navigate("https://www.youtube.com/v/" + song.getURL() + "&autoplay=1&autohide=0");
        }
        else {
            // no play info for other sources yet
        }
        return true;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void start() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainjFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainjFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainjFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainjFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainjFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSongButton;
    private javax.swing.JButton advSearchButton;
    private javax.swing.JButton advSearchPannleButton;
    private javax.swing.JButton createNewPlaylistButton;
    private javax.swing.JButton jButtonSubimtSong;
    private javax.swing.JButton jButtonSubmitPlaylist;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelSelectedMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelMenuPlaylistDisplay;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPanePlaylistDisplay;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel meloLabel;
    private javax.swing.JButton musicButton;
    private javax.swing.JButton myPlaylistsButton;
    private javax.swing.JButton newAdditionsButton;
    private javax.swing.JButton playEditButton;
    private javax.swing.JPanel playPnl;
    private javax.swing.JButton popularPlaylistsButton3;
    private javax.swing.JButton publicBtn;
    // End of variables declaration//GEN-END:variables
}
