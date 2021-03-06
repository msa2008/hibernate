package com.citi.utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class FileReadingOps {

	public static void main(String[] args) {

		String path = "resources/testfile.txt";
		measureTime("BufferedReader.readLine() into ArrayList", FileReadingOps::bufferReaderToLinkedList, path);
		measureTime("BufferedReader.readLine() into LinkedList", FileReadingOps::bufferReaderToArrayList, path);
		measureTime("Files.readAllLines()", FileReadingOps::readAllLines, path);
		measureTime("Scanner.nextLine() into ArrayList", FileReadingOps::scannerArrayList, path);
		measureTime("Scanner.nextLine() into LinkedList", FileReadingOps::scannerLinkedList, path);
		measureTime("RandomAccessFile.readLine() into ArrayList", FileReadingOps::randomAccessFileArrayList, path);
		measureTime("RandomAccessFile.readLine() into LinkedList", FileReadingOps::randomAccessFileLinkedList, path);
		System.out.println("-----------------------------------------------------------");
	}

	private static void measureTime(String name, Function<String, List<String>> fn, String path) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("run: " + name);
		long startTime = System.nanoTime();
		List<String> l = fn.apply(path);
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("lines: " + l.size());
		System.out.println("estimatedTime: " + estimatedTime / 1_000_000_000.);
	}

	public static List<String> bufferReaderToLinkedList(String path) {
		return bufferReaderToList(path, new LinkedList<>());
	}

	public static List<String> bufferReaderToArrayList(String path) {
		return bufferReaderToList(path, new ArrayList<>());
	}

	private static List<String> bufferReaderToList(String path, List<String> list) {
		try {
			final BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
			String line;
			while ((line = in.readLine()) != null) {
				list.add(line);
			}
			in.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static List<String> readAllLines(String path) {
		try {
			return Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<String> randomAccessFileLinkedList(String path) {
		return randomAccessFile(path, new LinkedList<>());
	}

	private static List<String> randomAccessFileArrayList(String path) {
		return randomAccessFile(path, new ArrayList<>());
	}

	private static List<String> randomAccessFile(String path, List<String> list) {
		try {
			RandomAccessFile file = new RandomAccessFile(path, "r");
			String str;
			while ((str = file.readLine()) != null) {
				list.add(str);
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static List<String> scannerLinkedList(String path) {
		return scanner(path, new LinkedList<>());
	}

	private static List<String> scannerArrayList(String path) {
		return scanner(path, new ArrayList<>());
	}

	private static List<String> scanner(String path, List<String> list) {
		try {
			Scanner scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				list.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public static String[] getListofFiles(String path) {
		File file = new File(path);
		String[] flist = file.list();
		return flist;
	}
	
	public static void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	   }
	}
	
	public static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
}