package anki;

import repetico.RepeticoCard;
import repetico.RepeticoSet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class AnkiWriter {
    private String separator = "\t";
    private String header = """
            #separator:Tab
            #html:true
            #deck column:1
            #tags column:4
            """;
    File outFolder = new File("out");

    public void writeAnkiFile(List<RepeticoSet> sets) throws IOException {
        StringBuilder text = new StringBuilder(header);

        for (RepeticoSet set : sets) {
            String folderName = set.getFolderName();
            String setName = set.getSetName();

            for (RepeticoCard card : set.getCards()) {
                text.append(folderName)
                    .append("::")
                    .append(setName)
                    .append(separator)
                    .append(card.getQuestion())
                    .append(separator)
                    .append(card.getAnswer())
                    .append(separator)
                    .append(card.unescapeCategories())
                    .append("\n");
//                text.append(String.format("%s::%s|%s|%s|%s%n", folderName, setName, card.getQuestion(), card.getAnswer(), card.unescapeCategories()));
            }
        }

        outFolder.mkdir();

        Files.write(outFolder.toPath().resolve("importMe.txt"), text.toString().getBytes());
    }
}
