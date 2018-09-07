/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.mouseutils;

/**
 *
 * @author 2110111
 */
public class MouseMovementThread extends Thread{

    private volatile boolean isRunning = true;
    private volatile boolean isPaused = false;
    private final Object pau = new Object();
    
    
    public MouseMovementThread() {
    }
    
}
