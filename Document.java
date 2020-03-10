import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class Document {
    private final List<String> lines;
    public String name;
    public Document(List<String> lines, String name){
        this.lines = lines;
        this.name = name;
    }

    public List<String> getLines(){
        return lines;
    }

    static Document fromFile(File file) throws IOException {
        List<String> lines = new LinkedList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while (line != null) {

                lines.add(line);

                line = reader.readLine();
            }
        }

        return new Document(lines, file.getName());
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}