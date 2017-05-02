/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

//imports here
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import api.APIConnections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.time.Clock.system;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
/**
 *
 * @author Carson Murray
 */
public class PlaylistBuilderjFrame extends javax.swing.JFrame {
    String filePath;
    String playlistName;
    /**
     * Creates new form PlaylistBuilderjFrame
     */
    public PlaylistBuilderjFrame(String file, String playlist) {
        initComponents();
        filePath = file;
        playlistName = playlist;
        jLabel8.setText(playlistName);
        

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneSongDisplay = new javax.swing.JScrollPane();
        jPanelSongDisplay = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanelSongDisplay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelSongDisplay.setLayout(new java.awt.GridLayout(0, 5));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("<html>Welcome to the Playlist Editor! <br>Here you will be able to add songs to your playlist!<br>Simply search for a song, click on the button, and it will be added to your playlist!</html>");
        jPanelSongDisplay.add(jLabel11);

        jScrollPaneSongDisplay.setViewportView(jPanelSongDisplay);

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

        jLabel9.setFont(new java.awt.Font("Bebas Neue", 0, 40)); // NOI18N
        jLabel9.setText("Melonheads");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 153, 0), new java.awt.Color(51, 153, 0), new java.awt.Color(51, 153, 0), new java.awt.Color(51, 153, 0)));

        jLabel1.setFont(new java.awt.Font("Bebas Neue", 2, 24)); // NOI18N
        jLabel1.setText("Editor");

        jLabel8.setFont(new java.awt.Font("Bebas Neue", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 153, 0));
        jLabel8.setText("jLabel8");

        jButton1.setText("Delete Songs");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneSongDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPaneSongDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMousePressed
        jTextFieldSearch.setText("") ;
    }//GEN-LAST:event_jTextFieldSearchMousePressed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed

    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String test = null;
            try {
               // TODO: create list of songs to display and populate it with the getSongs search
               //test = APIConnections.getSongs(APIConnections.GET_SEARCH, jTextFieldSearch.getText()) ;
               //musicButton.doClick();
               
               //musicButton.doClick();
               ArrayList<Song> results = new ArrayList<Song>();
               results = APIConnections.getSongs(APIConnections.GET_SEARCH, jTextFieldSearch.getText().replaceAll(" ","%20"));
               
               jPanelSongDisplay.removeAll();
               jPanelSongDisplay.revalidate();
               jPanelSongDisplay.repaint();
               
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
               
               jPanelSongDisplay.add(ata);
               jPanelSongDisplay.add(plays);
               jPanelSongDisplay.add(ratingLb);
               jPanelSongDisplay.add(blank);
               jPanelSongDisplay.add(blank2);
               for(int i = 0; i < results.size(); i++)
               {
                    sButton newBtn = new sButton(results.get(i).getId(), results.get(i).getArtist() + " - " + results.get(i).getTitle() + " - " + results.get(i).getAlbum());
                    Song tempSong = results.get(i);
                    JLabel playsLbl = new JLabel(""+tempSong.getPlays()+"");
                    playsLbl.setHorizontalAlignment(SwingConstants.CENTER);
                    int rating;
                    if (tempSong.getUpvotes() == 0)
                        rating = 0;
                    else
                        rating = (tempSong.getUpvotes() + tempSong.getDownvotes()) / tempSong.getUpvotes();
                    JLabel ratingLbl = new JLabel(""+rating+"%");
                    ratingLbl.setHorizontalAlignment(SwingConstants.CENTER);
                    JButton likeBtn = new JButton("LIKE");
                    likeBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae4){
                            tempSong.addUpvote();
                        }
                    });
                    JButton dislikeBtn = new JButton("DISLIKE");
                    dislikeBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae5){
                            tempSong.addDownvote();
                        }
                    });
                    
                    newBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent addToList){
                            try{

                                JOptionPane.showMessageDialog(null,
                                "Song added to " + jLabel8.getText()  +" playlist!",
                                "Song Add",
                                JOptionPane.PLAIN_MESSAGE);
                                
                                File newPlaylist = new File("data/playlists/" + jLabel8.getText() +".txt");
                                FileWriter fileWriter2 = new FileWriter(newPlaylist, true);
                                PrintWriter playlistWriter2 = new PrintWriter(fileWriter2, true);
                                playlistWriter2.println(newBtn.getID());
                                playlistWriter2.close();
                            } catch(IOException e)
                            {
                                
                            }
                        
                    
                    }
                });
                    

                    jPanelSongDisplay.add(newBtn);
                    jPanelSongDisplay.add(playsLbl);
                    jPanelSongDisplay.add(ratingLbl);
                    jPanelSongDisplay.add(likeBtn);
                    jPanelSongDisplay.add(dislikeBtn);
                    jPanelSongDisplay.validate();
                    jScrollPaneSongDisplay.validate();  
                    
               }                        
               
            } catch (Exception ex) {
                Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             
        };

    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // TODO add your handling code here:
    
        jPanelSongDisplay.removeAll();
        String songLine;

                        File songs = new File("data/playlists/" + jLabel8.getText() + ".txt");
                        try {                           
                            BufferedReader songRead = new BufferedReader(new FileReader(songs));
                            while((songLine = songRead.readLine()) != null){
                                Song target = APIConnections.getSongs(APIConnections.GET_ID, songLine).get(0);
                                sButton newSBtn = new sButton(Integer.parseInt(songLine), target.getArtist()+ " - " + target.getTitle() + " - " + target.getAlbum());
                                newSBtn.setHorizontalAlignment(SwingConstants.LEFT);
                                
                                
                                newSBtn.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent addToList){
                                        File inputFile = new File("data/playlists/" + jLabel8.getText() + ".txt");
                                        File temp = new File("data/playlists/temp.txt");

                                        try {
                                            BufferedReader lineRead = new BufferedReader(new FileReader(inputFile));
                                            BufferedWriter lineWrite = new BufferedWriter(new FileWriter(temp));
                                        
                                            int lineToRemove = newSBtn.getID();
                                            String currentLine;
                                            while((currentLine = lineRead.readLine()) != null) {
                                            // trim newline when comparing with lineToRemove
                                                String trimmedLine = currentLine.trim();
                                                if(Integer.parseInt(trimmedLine) == lineToRemove) continue;
                                                lineWrite.write(currentLine + System.getProperty("line.separator"));
                                            }
                                            lineWrite.close(); 
                                            lineRead.close(); 
                                            inputFile.delete();
                                            temp.renameTo(inputFile); 
                                            
                                            jPanelSongDisplay.remove(newSBtn);
                                            jPanelSongDisplay.validate();
                                            jPanelSongDisplay.repaint();
                                            jScrollPaneSongDisplay.validate();
                                            jScrollPaneSongDisplay.repaint();
                                            
      
                                        } catch (IOException ex) {
                                            Logger.getLogger(PlaylistBuilderjFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
     
                                        
//code for remove                                        
                                    }
                                });
                                
                                
                                jPanelSongDisplay.add(newSBtn);
                            }
                            songRead.close();
                            jPanelSongDisplay.validate();
                            jPanelSongDisplay.repaint();
                            jScrollPaneSongDisplay.validate();
                            jScrollPaneSongDisplay.repaint();
                        }catch (FileNotFoundException ex) {
                            Logger.getLogger(MainjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(PlaylistBuilderjFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(PlaylistBuilderjFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlaylistBuilderjFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlaylistBuilderjFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlaylistBuilderjFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlaylistBuilderjFrame(null,"build").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelSongDisplay;
    private javax.swing.JScrollPane jScrollPaneSongDisplay;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
