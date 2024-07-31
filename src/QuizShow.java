import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuizShow {
    private final Map<Integer, Quiz> quizMap;
    private final Scanner scan;

    private final ArrayList<String> userAnswers;
    private int totalScore = 0;

    QuizShow() {
        quizMap = new HashMap<>();
        scan = new Scanner(System.in);
        userAnswers = new ArrayList<>();
        this.initQuizMap();
    }

    private void addQuizToMap(Quiz quiz) {
        quizMap.put(quiz.getId(), quiz);
    }

    private void initQuizMap() {
        addQuizToMap(new Quiz("Python에서 변수를 선언하는 방법은?", "1) var name 2) name = value 3) set name 4) name == value", "2", 10));
        addQuizToMap(new Quiz("Python에서 리스트(List)의 특징은 무엇인가요?", "1) 순서가 있고 변경 가능하다, 2) 중복된 값을 가질 수 없다, 3) 원소를 추가하거나 삭제할 수 없다, 4) 정렬된 상태로 유지된다", "1", 15));
        addQuizToMap(new Quiz("Python에서 조건문을 작성하는 방법은?", "1) if-else, 2) for-in, 3) while, 4) def", "1", 10));
        addQuizToMap(new Quiz("Python에서 함수를 정의하는 방법은?", "1) class, 2) def, 3) import, 4) return", "2", 5));
    }

    public void run() {
        Map<Integer, Quiz> quizMap = this.getQuizMap();
        int index = 1;
        while(true) {
            Quiz quiz = quizMap.get(index++);
            this.printQuestion(quiz);
            this.printSelects(quiz);
            String answer = this.getAnswer();
            this.addUserAnswer(answer);
            if(quiz.getAnswer().equals(answer)) {
                this.addScore(quiz.getPoint());
            }
            if(quizMap.get(index) == null) {
                break;
            }
        }
        this.printResult();
    }

    private void printQuestion(Quiz quiz) {
        System.out.println(quiz.getId() + ". " + quiz.getQuestion() + " " + "(점수: " + quiz.getPoint() + "점)");
    }

    private void printSelects(Quiz quiz) {
        String quizString = IntStream.rangeClosed(1, quiz.getSelects().size())
                .mapToObj(index -> {
                    return index + ") " + quiz.getSelects().get(index - 1);
                })
                .collect(Collectors.joining(" "));
        System.out.println(quizString);
    }

    public String getAnswer() {
        System.out.print("-정답 : ");
        return scan.nextLine().trim();
    }

    public Map<Integer, Quiz> getQuizMap() {
        return this.quizMap;
    }

    private void addScore(int score) {
        this.totalScore += score;
    }

    private void addUserAnswer(String userAnswer) {
        userAnswers.add(userAnswer);
    }

    private ArrayList<String> getUserAnswers() {
        return this.userAnswers;
    }

    private int getTotalScore () {
        return this.totalScore;
    }

    private void printResult() {
        System.out.println("—----- 결과 —-------------");
        System.out.print("응답한 내용 : ");
        String userAnswerString = IntStream.rangeClosed(1, getUserAnswers().size())
                .mapToObj(index -> {
                    return index + "번 " + getUserAnswers().get(index - 1);
                })
                .collect(Collectors.joining(", "));
        System.out.println(userAnswerString);
        System.out.println("당신 응답 합계 : " + getTotalScore());
        System.out.println("학점은 F 입니다");
    }
}
