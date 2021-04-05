package com.burakkaygusuz.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class PathUtils {

    private static final String OPERATING_SYSTEM = System.getProperty("os.name");
    private static String jsPaths = null;
    private static Process process;
    private static BufferedReader bufferedReader;

    public static File getNodeJSPath() throws IOException, InterruptedException {
        String nodePath = null;

        if (OPERATING_SYSTEM.contains("Win")) {
            process = Runtime.getRuntime().exec("where node");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((jsPaths = bufferedReader.readLine()) != null)
                nodePath = jsPaths;

            process.waitFor();
        } else {
            process = Runtime.getRuntime().exec("which node");
            process.waitFor();
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String readLine;
            while ((readLine = bufferedReader.readLine()) != null)
                nodePath = readLine;
        }
        process.destroy();
        bufferedReader.close();

        if (nodePath == null)
            Runtime.getRuntime().exit(0);
        return new File(nodePath);
    }

    public static File getAppiumJSPath() throws IOException, InterruptedException {
        String actualJSPath = null;

        if (OPERATING_SYSTEM.contains("Win")) {
            process = Runtime.getRuntime().exec("where appium");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((jsPaths = bufferedReader.readLine()) != null) {
                actualJSPath = jsPaths.replace("appium", "node_modules\\appium\\build\\lib\\main.js");
                break;
            }
            process.waitFor();
            process.destroy();
            bufferedReader.close();

            if (actualJSPath == null)
                Runtime.getRuntime().exit(0);
        } else {
            actualJSPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
        }
        return new File(actualJSPath);
    }
}
