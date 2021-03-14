package com.burakkaygusuz.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PathUtils {

    private static final String operatingSystem = System.getProperty("os.name");
    private static String jsPaths = null;
    private static String actualJSPath = null;
    private static String nodePath = null;
    private static Process process;
    private static BufferedReader bufferedReader;

    public static String getNodePath() throws IOException, InterruptedException {
        if (operatingSystem.contains("Win")) {
            process = Runtime.getRuntime().exec("where" + " " + "node");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((jsPaths = bufferedReader.readLine()) != null)
                nodePath = jsPaths;

            process.waitFor();
        } else {
            process = Runtime.getRuntime().exec("which" + " " + "node");
            process.waitFor();
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String readLine;
            while ((readLine = bufferedReader.readLine()) != null)
                nodePath = readLine;
        }
        process.destroy();
        if (nodePath == null) Runtime.getRuntime().exit(0);
        return nodePath;
    }

    public static String getJSPath() throws IOException, InterruptedException {
        if (operatingSystem.contains("Win")) {
            process = Runtime.getRuntime().exec("where" + " " + "appium");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((jsPaths = bufferedReader.readLine()) != null) {
                actualJSPath = jsPaths.replace("appium", "node_modules\\appium\\build\\lib\\main.js");
                break;
            }
            process.waitFor();
            process.destroy();

            if (actualJSPath == null) Runtime.getRuntime().exit(0);
        } else {
            actualJSPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
        }
        return actualJSPath;
    }

}
