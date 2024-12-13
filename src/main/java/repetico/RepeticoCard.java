package repetico;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

public class RepeticoCard {
    private String question;
    private String answer;
    private List<String> categories;
    private List<String> keywords;

    public RepeticoCard() {
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String unescapeCategories(){
        return StringEscapeUtils.unescapeHtml4(String.join(" ", categories));
    }
}
