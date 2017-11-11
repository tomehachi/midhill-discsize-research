package jp.co.midhill.app.discsize;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DirSpeck {
    public long totalSize = 0;
    public long fileCount = 0;
    public long directoryCount = 0;
    public File newestFile = null;
    public File oldestFile = null;

    public Map<String, Long> extensionCounts = new LinkedHashMap<String, Long>();
    private static final Pattern EXTENTION_ONLYNUMBER = Pattern.compile("\\d+");

    public void update(File file) {
        if(file.isDirectory()) {
            directoryCount++;

        } else {
            fileCount++;

            totalSize += file.length();

            if(newestFile == null && oldestFile == null) {
                newestFile = file;
                oldestFile = file;

            } else {
                if(file.lastModified() > newestFile.lastModified()) {
                    newestFile = file;
                }
                if(file.lastModified() < oldestFile.lastModified()) {
                    oldestFile = file;
                }
            }

            String[] dotSeparate = file.getName().split("\\.");
            String extension = dotSeparate[dotSeparate.length -1];

            if(extension.length() > 5 || EXTENTION_ONLYNUMBER.matcher(extension).matches()) {
                extension = "other";
            }
            if(extensionCounts.containsKey(extension)) {
                extensionCounts.put( extension, extensionCounts.get(extension).longValue() + 1 );
            } else {
                extensionCounts.put( extension, 1L);
            }
        }
    }
}
