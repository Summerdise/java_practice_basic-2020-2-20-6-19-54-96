package com.thoughtworks.io;

import java.io.*;

public class FileUtil {

    /**
     * 完成复制文件夹方法:
     * 1. 把给定文件夹from下的所有文件(包括子文件夹)复制到to文件夹下
     * 2. 保证to文件夹为空文件夹，如果to文件夹不存在则自动创建
     * <p>
     * 例如把a文件夹(a文件夹下有1.txt和一个空文件夹c)复制到b文件夹，复制完成以后b文件夹下也有一个1.txt和空文件夹c
     */
    public static void copyDirectory(File from, File to) throws IOException {
        if (to.exists()) {
            deleteAllFile(to);
        } else {
            to.mkdir();
        }
        File[] fromList = from.listFiles();
        if (fromList != null) {
            for (File f : fromList) {
                File fromFile = new File(from.getPath() + File.separator + f.getName());
                File toFile = new File(to.getPath() + File.separator + f.getName());
                if (f.isDirectory()) {
                    copyDirectory(fromFile, toFile);
                } else {
                    copyFile(fromFile, toFile);
                }
            }
        }
    }

    public static void deleteAllFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteAllFile(f);
            }
        } else {
            System.out.println("delete文件：" + file.delete());
        }
    }

    public static void copyFile(File from, File to) throws IOException {
        try {
            FileInputStream input = new FileInputStream(from);
            FileOutputStream output = new FileOutputStream(to);
            int n;
            while ((n = input.read()) != -1) {
                output.write(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
