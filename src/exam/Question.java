package exam;

public class Question {

    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {

        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}