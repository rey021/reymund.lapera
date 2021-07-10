package tools;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class FileHandler {

    public String fileName = "";
    String fileData = "";
    private Runtime rt;
    private Process proc;
    private InputStream err;
    private InputStream std;
    private BufferedReader stdInput;
    private BufferedReader stdErr;
    private StringBuffer result;
    private File file;

    public FileHandler() {
    }

    public FileHandler(String fileName) {
        setFileName(fileName);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;

        file = new File(fileName);
    }

    public FileHandler(File file) {

        this.file = file;
        setFileName(file.getPath());
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }


    /*public void createFile() throws IOException, Exception {
    File theFile = new File(fileName);
    FileWriter out = new FileWriter(theFile, true);
    out.write(fileData +"\n");
    out.close();
    }*/
    public File getFile() {
        return this.file;
    }

    public void createFile() throws IOException, Exception {
        BufferedWriter bWriter;
        bWriter = new BufferedWriter(new FileWriter(fileName, true));
        bWriter.write(fileData + "\r\n");
        bWriter.flush();
        bWriter.close();
    }

    public void deleteFile() {
        File deleteFile = new File(fileName);
        deleteFile.delete();
    }

    public long lastModified() {
        long dateTime;
        File theFile = new File(fileName);
        dateTime = theFile.lastModified();

        return dateTime;

    }

    public boolean checkFlagIfExist(String location, String name) {
        boolean exist = false;
        try {
            setFileName(location + name + ".txt");

            exist = isExist();

            renameTo(location + name + ".prc");
        } catch (Exception ex) {
        }

        //System.out.println("FILE existing["+fileName+"]? "+exist);

        return exist;
    }

    public boolean isExist() {
        boolean bln = false;
        File fileCheck = new File(fileName);
        bln = fileCheck.exists();

        return bln;
    }

    public long fileLength() {
        long len;
        File fileLen = new File(fileName);
        len = fileLen.length();

        return len;
    }

    public boolean isDirectory() {
        boolean bln = false;
        File dir = new File(fileName);
        bln = dir.isDirectory();

        return bln;
    }

    public void addLine(String line) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileName, true));

            bWriter.write(line + "\r\n");
            bWriter.flush();
            bWriter.close();

        //System.out.println(line);		
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void moveFile(String dir) throws Exception {
        boolean bln;
        File currFile = new File(fileName);
        File remdir = new File(dir);
        bln = currFile.renameTo(new File(remdir, currFile.getName()));
    }

    /*---------------------------------------------------------------------------------------------------------------------*/
//	public synchronized boolean moveFile(String moduleid, String filename, LogManager logManager, File targetDir)
//	{
//		File        file     = new File(filename);
//		FileHandler moveFile = new FileHandler ();
//		String      newName  = file.getName();
//		
//		try
//		{	
//    		logManager.info("         moving  file: "+filename+" to "+targetDir+"/"+newName);    		    					
//			
//    		moveFile.setFileName(file.getAbsolutePath ());
//    		moveFile.renameTo(targetDir.getAbsolutePath()+"/"+newName);
//			
//    		logManager.info("FINISHED moving  file: "+filename+" to "+targetDir+"/"+newName);
//    		
//	    	return true;		
//		}
//		catch(Exception e)
//		{ 
//			logManager.printErrorLog(moduleid, e, "30020", "10"); 
//			logManager.error("CANNOT   move    file: "+filename+" to "+targetDir+"/"+newName);
//			
//			return false;
//		}
//	}	

    /*---------------------------------------------------------------------------------------------------------------------*/
//	public synchronized boolean moveFile(String moduleid, String filename, String newFilename, LogManager logManager, File targetDir)
//	{
//		File        file     = new File(filename),
//		            newFile  = new File(newFilename);
//		FileHandler moveFile = new FileHandler ();
//		String      newName  = newFile.getName();
//		
//		try
//		{
//    		logManager.info("         moving  file: "+filename+" to "+targetDir+"/"+newName);    		    					
//			
//    		moveFile.setFileName(file.getAbsolutePath ());
//    		moveFile.renameTo(targetDir.getAbsolutePath()+"/"+newName);
//			
//    		logManager.info("FINISHED moving  file: "+filename+" to "+targetDir+"/"+newName);
//    		
//	    	return true;		
//		}
//		catch(Exception e)
//		{ 
//			logManager.printErrorLog(moduleid, e, "30020", "10"); 
//			logManager.error("CANNOT   move    file: "+filename+" to "+targetDir+"/"+newName);
//			
//			return false;
//		}
//	}
    /*public void renameTo(String newFile) {
    boolean bln;			
    File currFile = new File(fileName);	
    bln=currFile.renameTo(new File(newFile));
    }
    public synchronized void renameTo(String newFile) throws Exception{
    rt = Runtime.getRuntime();
    result = new StringBuffer();
    /*LOW LEVEL PROCEDURE FOR RENAME**********
    String moveCom = "mv -f " + fileName + " " + newFile;
    String moveCom = "move " + fileName + " " + newFile;
    System.out.println("Executing OS-Level mv command: " + moveCom);
    String[] cmdArray=moveCom.split(" ");
    rt.exec(cmdArray);
     *************************
    File tmpFile = new File(fileName);
    File newFiled = new File(newFile);
    tmpFile.renameTo(newFiled);
    //		Thread.sleep(100);
    // 		std = proc.getInputStream();
    // 		err = proc.getInputStream();
    // 		stdInput = new BufferedReader(new InputStreamReader(std));
    // 		stdErr = new BufferedReader(new InputStreamReader(err));
    // 		
    // 		System.out.println("Output " + stdInput + ". " + stdErr);
    }*/
    public synchronized void copyTo(String newFile) throws Exception {
        File inputFile = new File(fileName);
        File outputFile = new File(newFile);

        FileReader in = new FileReader(inputFile);
        FileWriter out = new FileWriter(outputFile);
        int c;

        while ((c = in.read()) != -1) {
            out.write(c);
        }

        in.close();
        out.close();
    }

    public synchronized void renameTo(String newFile) throws Exception {
        rt = Runtime.getRuntime();
        result = new StringBuffer();
        String moveCom = "mv -f " + fileName + " " + newFile;
        //System.out.println("Executing OS-Level mv command: " + moveCom);
        String[] cmdArray = moveCom.split(" ");
        Process proc = rt.exec(cmdArray);

        InputStream std = proc.getErrorStream();

        stdInput = new BufferedReader(new InputStreamReader(std));

        int input = 0;
        String line = "";
        String error = "";

        line = stdInput.readLine();
        while ((line != null) && ("".equals(line) == false)) {
            error = error + " " + line.trim();
            line = stdInput.readLine();
            input += 1;
        }

        if (input > 0) {
            //System.out.println ("----------->> Error: ");    
            //System.out.println ("---------->> " + error);

            throw new Exception(error);
        }
        Thread.sleep(100);

// 		std = proc.getInputStream();
// 		err = proc.getInputStream();
// 		stdInput = new BufferedReader(new InputStreamReader(std));
// 		stdErr = new BufferedReader(new InputStreamReader(err));
// 		
// 		System.out.println("Output " + stdInput + ". " + stdErr);
    }

    public synchronized void createDumFile() throws Exception {
        rt = Runtime.getRuntime();
        String com = "touch " + fileName;
        System.out.println("Executing OS-Level command:" + com);
        String[] cmdArray = com.split(" ");
        rt.exec(cmdArray);
    }

    public synchronized void byebyeFile() throws Exception {
        rt = Runtime.getRuntime();
        //result = new StringBuffer();
        String removeCom = "rm -f " + fileName;
        System.out.println("Executing OS-Level rm command: " + removeCom);
        String[] cmdArray = removeCom.split(" ");
        rt.exec(cmdArray);

    //Thread.sleep(100);
    }

    public String[] listFile() {  //dir
        String[] filenames;
        File fileDir = new File(fileName);
        filenames = fileDir.list();

        return filenames;
    }

//	public String [] listFileFilter (WildCardFilter filter) {  
//	    String [] filenames;
//		File fileDir = new File(fileName);
//	    filenames = fileDir.list(filter);
//	    
//	    return filenames;	
//	}	
    public BufferedReader readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        return br;

    }

    public Vector readTheFile() throws Exception {
        BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
        Vector vFile = new Vector();
        String readlyn;

        while ((readlyn = inputStream.readLine()) != null && !readlyn.trim().equals("")) {
            vFile.add(readlyn);
        }

        inputStream.close();

        return vFile;
    }

    /*public DataInputStream readFile () throws IOException {
    FileInputStream fstream = new FileInputStream(fileName);
    DataInputStream in = new DataInputStream(fstream);
    return in; 
    }*/
    public void makeDir() throws IOException, Exception {
        (new File(this.fileName)).mkdir();
    }

//	public File[] getAllFiles (String dir) throws Exception {
//		File[] fileList;
//		WildCardFilter wcFilter = new WildCardFilter("*");
//		File fileDir = new File(dir);
//		fileList = fileDir.listFiles(wcFilter);
//	    java.util.Arrays.sort( fileList, new Comparer());
//	    return fileList;
//	}		
    public File[] removeAllDirectories(File[] files) throws Exception {
        int valid = 0;

        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) {
                valid++;
            }
        }

        File[] newFiles = new File[valid];

        for (int i = 0,  j = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) {
                newFiles[j] = files[i];
                j++;
            }
        }

        return newFiles;
    }

    public String[] getAllFileString(File[] files) throws Exception {
        String[] filenames = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            filenames[i] = files[i].getName();
        }

        return filenames;
    }

    public boolean checkDirectoryIfExisting() throws Exception {
        boolean bExist = false;
        BufferedReader inputStream = null;

        File dir = new File(".");
        File[] files = dir.listFiles();

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {

            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);

        for (int i = 0; i < files.length; i++) {
            if (fileName.equals(files[i].toString().substring(2, files[i].toString().length()))) {
                bExist = true;
            }
        }

        return bExist;
    }

    public void makeDirectory() throws Exception {
        (new File(fileName)).mkdir();
    }

    public void makeAndCheckDailyDirectory(File directory) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        String dateNow = sdf.format(new java.util.Date()),
                dateChopped[] = dateNow.split("/");

        setFileName(directory + "/" + dateChopped[2] + "-" + dateChopped[0]);
        if (!checkDirectoryIfExisting()) {
            makeDirectory();
        }

        setFileName(directory + "/" + dateChopped[2] + "-" + dateChopped[0] + "/" + dateChopped[0] + dateChopped[1] + dateChopped[2]);
        if (!checkDirectoryIfExisting()) {
            makeDirectory();
        }
    }

    public boolean createFlag(String FlagLoc, String threadname) {
        boolean created = true;
        try {
            setFileName(FlagLoc + "/" + threadname + ".txt");
            if (isExist()) {
                return true;
            } else {
                setFileName(FlagLoc + "/" + threadname + ".prc");
                if (isExist()) {
                    renameTo(FlagLoc + "/" + threadname + ".txt");
                } else {
                    setFileName(FlagLoc + "/" + threadname + ".txt");
                    setFileData(" ");
                    createFile();
                //  System.out.println("File created @@@@@@@@@@@@@@@@@@");
                }
            }
        } catch (Exception ex) {

            System.out.println(ex.toString());
            created = false;
        }
        return created;
    }

    /**************************************************************************************************/
    public void createFile(String filepath) throws IOException, Exception {
        BufferedWriter bWriter;
        bWriter = new BufferedWriter(new FileWriter(filepath, true));
        bWriter.write(fileData + "\r\n");
        bWriter.flush();
        bWriter.close();
    }

    public synchronized void renameTo(String fileName, String newFile) throws Exception {
        rt = Runtime.getRuntime();
        result = new StringBuffer();
        String moveCom = "mv -f " + fileName + " " + newFile;
        System.out.println("Executing OS-Level mv command: " + moveCom);
        String[] cmdArray = moveCom.split(" ");
        Process proc = rt.exec(cmdArray);

        InputStream std = proc.getErrorStream();

        stdInput = new BufferedReader(new InputStreamReader(std));

        int input = 0;
        String line = "";
        String error = "";

        line = stdInput.readLine();
        while ((line != null) && ("".equals(line) == false)) {
            error = error + " " + line.trim();
            line = stdInput.readLine();
            input += 1;
        }

        if (input > 0) {
            throw new Exception(error);
        }
        Thread.sleep(100);

    }

    class Comparer implements Comparator {

        public int compare(Object a, Object b) {
            Long n1 = new Long(((File) a).lastModified());
            Long n2 = new Long(((File) b).lastModified());
            int n3 = n1.intValue() - n2.intValue();
            return n3;
        }
    }

    public void renameFile(String oldFileName, String newFileName) {
        File file = new File(oldFileName);
        if (file.renameTo(new File(newFileName))) {
            // if file copied successfully then delete the original file
            file.delete();
            System.out.println("File moved successfully");
        } else {
            System.out.println("Failed to move the file");
        }
    }
}	
