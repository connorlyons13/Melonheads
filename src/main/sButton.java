/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JButton;

/**
 *
 * @author Drew
 */

/* sButton is just a standard JButton but contains an id value for the song it displays

*/
public class sButton extends JButton
{
    private int id;
    
    public sButton(int newId, String text)
    {
        id = newId;
        this.setText(text);
    }
    
    public int getID()
    {
        return id;
    }
    
    public void setID(int newId)
    {
        id = newId;
    }
    
    
}
