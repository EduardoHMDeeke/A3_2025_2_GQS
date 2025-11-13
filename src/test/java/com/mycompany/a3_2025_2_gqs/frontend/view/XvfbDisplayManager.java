package com.mycompany.a3_2025_2_gqs.frontend.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to manage Xvfb (X Virtual Framebuffer) for GUI tests.
 * This allows GUI tests to run in headless environments like CI/CD.
 */
public class XvfbDisplayManager {
    
    private static Process xvfbProcess;
    private static String displayNumber;
    private static boolean xvfbAvailable = false;
    private static boolean xvfbStarted = false;
    
    /**
     * Checks if xvfb is available on the system.
     */
    public static boolean isXvfbAvailable() {
        if (xvfbAvailable) {
            return true;
        }
        
        try {
            Process process = new ProcessBuilder("which", "Xvfb").start();
            int exitCode = process.waitFor();
            xvfbAvailable = (exitCode == 0);
            return xvfbAvailable;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Starts Xvfb with a virtual display.
     * @return true if Xvfb was started successfully
     */
    public static boolean startXvfb() {
        if (xvfbStarted) {
            return true;
        }
        
        if (!isXvfbAvailable()) {
            System.out.println("Xvfb not available, GUI tests will be skipped");
            return false;
        }
        
        try {
            // Find an available display number
            displayNumber = findAvailableDisplay();
            
            // Start Xvfb
            List<String> command = new ArrayList<>();
            command.add("Xvfb");
            command.add(":" + displayNumber);
            command.add("-screen");
            command.add("0");
            command.add("1024x768x24");
            command.add("-ac");
            command.add("-nolisten");
            command.add("tcp");
            
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            xvfbProcess = pb.start();
            
            // Wait a bit to ensure Xvfb started
            Thread.sleep(1000);
            
            // Check if process is still running
            if (xvfbProcess.isAlive()) {
                // Set DISPLAY as system property
                // Note: Environment variables set in Java are not modifiable at runtime
                // The DISPLAY must be set before starting the JVM or via ProcessBuilder
                System.setProperty("DISPLAY", ":" + displayNumber);
                
                xvfbStarted = true;
                System.out.println("Xvfb started on display :" + displayNumber);
                System.out.println("DISPLAY=" + System.getProperty("DISPLAY"));
                System.out.println("Note: If tests fail, ensure DISPLAY=:" + displayNumber + " is set in environment");
                return true;
            } else {
                System.err.println("Xvfb failed to start");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error starting Xvfb: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Stops Xvfb process.
     */
    public static void stopXvfb() {
        if (xvfbProcess != null && xvfbProcess.isAlive()) {
            xvfbProcess.destroy();
            try {
                xvfbProcess.waitFor();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            xvfbStarted = false;
            System.out.println("Xvfb stopped");
        }
    }
    
    /**
     * Finds an available display number by checking if Xvfb is already running.
     */
    private static String findAvailableDisplay() throws IOException, InterruptedException {
        // Try display 99 first (common default)
        for (int i = 99; i >= 0; i--) {
            Process checkProcess = new ProcessBuilder("xdpyinfo", "-display", ":" + i).start();
            int exitCode = checkProcess.waitFor();
            if (exitCode != 0) {
                // Display not in use, we can use it
                return String.valueOf(i);
            }
        }
        // Fallback to 99
        return "99";
    }
    
    /**
     * Checks if we're in a headless environment but xvfb is available.
     */
    public static boolean shouldUseXvfb() {
        return java.awt.GraphicsEnvironment.isHeadless() && isXvfbAvailable();
    }
    
    /**
     * Gets the current display number.
     */
    public static String getDisplayNumber() {
        return displayNumber;
    }
}

