package main;

import anki.AnkiWriter;
import repetico.RepeticoJsonReader;
import repetico.RepeticoSet;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        RepeticoJsonReader reader = new RepeticoJsonReader();
        List<RepeticoSet> repeticoSets = reader.readAllSets();

        AnkiWriter ankiWriter = new AnkiWriter();
        ankiWriter.writeAnkiFile(repeticoSets);

        System.out.printf("");
    }
}
