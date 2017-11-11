package jp.co.midhill.app.discsize;

import java.io.File;

public class DiscSizeResearch {

    int serialNo = 1;

    private DirSpeck dirSpeck = new DirSpeck();

    public void printFileSpeck(File file) {
        System.out.println(serialNo + "\t" + file.getPath() + "\t" + file.getName() + "\t" + file.length());
        serialNo++;
    }

    private boolean preCheck(File dir) {
        if(!dir.exists() || !dir.isDirectory()) {
            return false;
        } else {
            return true;
        }
    }

    private void researchDir(File dir) {
        File[] files = dir.listFiles();
        if(files == null || files.length == 0) {
            return;
        }
        for(File file : files) {
            dirSpeck.update(file);

            if(file.isDirectory()) {
                researchDir(file);
            }
        }
    }

    public void execute(String path) {
        File dir = new File(path);

        if(!preCheck(dir)) {
            return;
        }
        researchDir(dir);

        System.out.println("done.");
    }

    public static void main(String...args) {
        (new DiscSizeResearch()).execute(args[0]);
    }
}
