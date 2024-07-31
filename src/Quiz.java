import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Quiz {
    private static int num = 0;
    private int id;
    private String question;
    private ArrayList<String> selects;
    private String answer;
    private int point;

    Quiz(String question, String selects, String answer, int point) {
        this.id = ++num;
        this.question = question;
        this.answer = answer;
        this.point = point;
        this.selects = this.createSelects(selects);
    }

    private ArrayList<String> createSelects(String selectString) {
        return (ArrayList<String>) Arrays.stream(selectString.split("[0-9)]"))
                .map(String::trim)
                .filter(item -> !item.isEmpty())
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getSelects() {
        return selects;
    }

    public String getAnswer() {
        return answer;
    }

    public int getPoint() {
        return point;
    }
}
