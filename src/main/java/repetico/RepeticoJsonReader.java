package repetico;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RepeticoJsonReader {
    private Gson gson = new Gson();
    private File repeticoFolder = new File("repetico");

    public List<RepeticoSet> readAllSets() throws IOException {
        List<RepeticoSet> result = new ArrayList<>();

        List<File> folders = getAllFoldersInRepeticoFolder();

        for (File folder : folders) {
            String folderName = folder.getName();

            List<File> repeticoFiles = getAllFilesInRepeticoFolder(folder);

            for (File repeticoFile : repeticoFiles) {
                String name = repeticoFile.getName();
                name = name.substring(0, name.lastIndexOf("."));

                String jsonString = Files.readString(folder.toPath().resolve(repeticoFile.getName()));

                Type listType = new TypeToken<List<RepeticoCard>>() {
                }.getType();

                List<RepeticoCard> cards = gson.fromJson(jsonString, listType);

                result.add(new RepeticoSet(folderName, name, cards));
            }
        }

        checkForSeparators(result);

        return result;
    }

    public void checkForSeparators(List<RepeticoSet> sets) {
        String separators = ",;\t |:";
        String[] separatorsArray = separators.split("");

        Map<String, Integer> counter = new HashMap<>();

        for (String separator : separatorsArray) {
            counter.put(separator, 0);
        }

        for (RepeticoSet set : sets) {
            for (RepeticoCard card : set.getCards()) {
                for (String separator : separatorsArray) {
                    if (card.getQuestion().contains(separator)) {
                        System.out.printf("Set: %s, contains in question \"%s\" separator!\n", set.getSetName(), separator);
                        counter.put(separator, counter.get(separator) + 1);
                    }

                    if (card.getAnswer().contains(separator)) {
                        System.out.printf("Set: %s, contains in answer \"%s\" separator!\n", set.getSetName(), separator);
                        counter.put(separator, counter.get(separator) + 1);
                    }
                }
            }
        }

        counter.forEach((separator, count) -> {
            System.out.printf("%s - %d\n", separator, count);
        });
    }

    private List<File> getAllFoldersInRepeticoFolder() {
        return Stream.of(repeticoFolder.listFiles())
                .filter(File::isDirectory)
                .toList();
    }

    private List<File> getAllFilesInRepeticoFolder(File folder) {
        return Stream.of(folder.listFiles())
                .filter(file -> !file.isDirectory() && file.getName().endsWith(".json"))
                .toList();
    }
}
