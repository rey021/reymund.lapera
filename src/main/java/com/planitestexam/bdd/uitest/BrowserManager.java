package com.planitestexam.bdd.uitest;

public class BrowserManager {
    private static boolean doScreenshotCapture = true;
    private static BrowserManager instance = null;

    private BrowserManager() throws Exception {
        if(instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    public static BrowserManager getInstance() {
        if(instance == null) {
            synchronized(BrowserManager.class) {
                if(instance == null) {
                    try {
                        instance = new BrowserManager();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
            }
        }
        return instance;
    }
    public static boolean getScreenshotCaptureStatus() {
        return doScreenshotCapture;
    }
}
