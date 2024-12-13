package repetico;

import java.util.List;

public class RepeticoSet {
    private String folderName;
    private String setName;
    private List<RepeticoCard> cards;

    public RepeticoSet(String folderName, String setName, List<RepeticoCard> cards) {
        this.folderName = folderName;
        this.setName = setName;
        this.cards = cards;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getSetName() {
        return setName;
    }

    public List<RepeticoCard> getCards() {
        return cards;
    }
}
