package exam;

import javax.swing.*;
import java.awt.*;

public class ExamFrame extends JFrame {

    Question[] questions;
    int currentQuestion = 0;

    JLabel questionLabel;
    JLabel timerLabel;

    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;

    ButtonGroup optionsGroup;

    JButton previousButton;
    JButton nextButton;
    JButton submitButton;

    int[] selectedAnswers;

    Timer timer;

    // 30 minutes = 30 × 60 seconds
    int timeRemaining = 30 * 60;


    // ==============================
    // CONSTRUCTOR
    // ==============================

    ExamFrame() {

        setTitle("Online Examination");

        setSize(700, 450);

        setDefaultCloseOperation(
            JFrame.DO_NOTHING_ON_CLOSE
        );

        setLocationRelativeTo(null);


        // ==============================
        // QUESTIONS
        // ==============================

        questions = new Question[] {

            new Question(
                "Which language is used to develop Android applications?",
                new String[]{
                    "Java",
                    "HTML",
                    "SQL",
                    "CSS"
                },
                0
            ),

            new Question(
                "Which keyword is used to create a class in Java?",
                new String[]{
                    "function",
                    "class",
                    "define",
                    "new"
                },
                1
            ),

            new Question(
                "Which method is the starting point of a Java program?",
                new String[]{
                    "start()",
                    "run()",
                    "main()",
                    "begin()"
                },
                2
            ),

            new Question(
                "Which symbol is used to end a statement in Java?",
                new String[]{
                    ".",
                    ",",
                    ";",
                    ":"
                },
                2
            )
        };


        // ==============================
        // STORE ANSWERS
        // ==============================

        selectedAnswers =
            new int[questions.length];

        for (int i = 0;
             i < selectedAnswers.length;
             i++) {

            selectedAnswers[i] = -1;
        }


        // ==============================
        // MAIN PANEL
        // ==============================

        JPanel panel = new JPanel();

        panel.setLayout(
            new BorderLayout(10, 10)
        );


        // ==============================
        // QUESTION LABEL
        // ==============================

        questionLabel = new JLabel();

        questionLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                18
            )
        );


        // ==============================
        // TIMER LABEL
        // ==============================

        timerLabel =
            new JLabel(
                "Time Remaining: 30:00"
            );

        timerLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                16
            )
        );


        // ==============================
        // TOP PANEL
        // ==============================

        JPanel topPanel =
            new JPanel(
                new BorderLayout()
            );

        topPanel.add(
            questionLabel,
            BorderLayout.CENTER
        );

        topPanel.add(
            timerLabel,
            BorderLayout.EAST
        );

        panel.add(
            topPanel,
            BorderLayout.NORTH
        );


        // ==============================
        // OPTIONS
        // ==============================

        JPanel optionsPanel =
            new JPanel();

        optionsPanel.setLayout(
            new GridLayout(
                4,
                1,
                5,
                5
            )
        );


        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();


        // ==============================
        // BUTTON GROUP
        // ==============================

        optionsGroup =
            new ButtonGroup();

        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);


        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);


        panel.add(
            optionsPanel,
            BorderLayout.CENTER
        );


        // ==============================
        // NAVIGATION BUTTONS
        // ==============================

        JPanel buttonPanel =
            new JPanel();


        previousButton =
            new JButton("Previous");

        nextButton =
            new JButton("Next");

        submitButton =
            new JButton("Submit");


        buttonPanel.add(
            previousButton
        );

        buttonPanel.add(
            nextButton
        );

        buttonPanel.add(
            submitButton
        );


        panel.add(
            buttonPanel,
            BorderLayout.SOUTH
        );


        add(panel);


        // ==============================
        // DISPLAY FIRST QUESTION
        // ==============================

        showQuestion();


        // ==============================
        // BUTTON ACTIONS
        // ==============================

        nextButton.addActionListener(
            e -> nextQuestion()
        );

        previousButton.addActionListener(
            e -> previousQuestion()
        );

        submitButton.addActionListener(
            e -> confirmSubmit()
        );


        // ==============================
        // WINDOW CLOSE BUTTON
        // ==============================

        addWindowListener(
            new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosing(
                    java.awt.event.WindowEvent e
                ) {

                    confirmQuit();
                }
            }
        );


        // ==============================
        // START TIMER
        // ==============================

        startTimer();
    }


    // ==================================================
    // SHOW QUESTION
    // ==================================================

    void showQuestion() {

        Question q =
            questions[currentQuestion];


        questionLabel.setText(
            "Question "
            + (currentQuestion + 1)
            + ": "
            + q.question
        );


        option1.setText(
            q.options[0]
        );

        option2.setText(
            q.options[1]
        );

        option3.setText(
            q.options[2]
        );

        option4.setText(
            q.options[3]
        );


        // Clear old selection

        optionsGroup.clearSelection();


        // Restore selected answer

        if (
            selectedAnswers[currentQuestion]
            == 0
        ) {

            option1.setSelected(true);

        }
        else if (
            selectedAnswers[currentQuestion]
            == 1
        ) {

            option2.setSelected(true);

        }
        else if (
            selectedAnswers[currentQuestion]
            == 2
        ) {

            option3.setSelected(true);

        }
        else if (
            selectedAnswers[currentQuestion]
            == 3
        ) {

            option4.setSelected(true);
        }
    }


    // ==================================================
    // SAVE ANSWER
    // ==================================================

    void saveAnswer() {

        if (option1.isSelected()) {

            selectedAnswers[currentQuestion] =
                0;
        }

        else if (option2.isSelected()) {

            selectedAnswers[currentQuestion] =
                1;
        }

        else if (option3.isSelected()) {

            selectedAnswers[currentQuestion] =
                2;
        }

        else if (option4.isSelected()) {

            selectedAnswers[currentQuestion] =
                3;
        }
    }


    // ==================================================
    // NEXT QUESTION
    // ==================================================

    void nextQuestion() {

        saveAnswer();


        if (
            currentQuestion
            < questions.length - 1
        ) {

            currentQuestion++;

            showQuestion();

        }
        else {

            JOptionPane.showMessageDialog(
                this,
                "This is the last question."
            );
        }
    }


    // ==================================================
    // PREVIOUS QUESTION
    // ==================================================

    void previousQuestion() {

        saveAnswer();


        if (currentQuestion > 0) {

            currentQuestion--;

            showQuestion();

        }
        else {

            JOptionPane.showMessageDialog(
                this,
                "This is the first question."
            );
        }
    }


    // ==================================================
    // TIMER
    // ==================================================

    void startTimer() {

        timer =
            new Timer(
                1000,
                e -> {

                    timeRemaining--;


                    int minutes =
                        timeRemaining / 60;


                    int seconds =
                        timeRemaining % 60;


                    timerLabel.setText(
                        String.format(
                            "Time Remaining: %02d:%02d",
                            minutes,
                            seconds
                        )
                    );


                    // TIME OVER

                    if (
                        timeRemaining <= 0
                    ) {

                        timer.stop();


                        JOptionPane.showMessageDialog(
                            this,
                            "Time is over! "
                            + "Your exam will be submitted."
                        );


                        submitExam();
                    }
                }
            );


        timer.start();
    }


    // ==================================================
    // CONFIRM SUBMIT
    // ==================================================

    void confirmSubmit() {

        int answer =
            JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want "
                + "to submit the exam?",
                "Confirm Submission",
                JOptionPane.YES_NO_OPTION
            );


        if (
            answer
            == JOptionPane.YES_OPTION
        ) {

            submitExam();
        }
    }


    // ==================================================
    // SUBMIT EXAM
    // ==================================================

    void submitExam() {

        // Stop timer

        if (timer != null) {

            timer.stop();
        }


        // Save current answer

        saveAnswer();


        // Calculate score

        int score = 0;


        for (
            int i = 0;
            i < questions.length;
            i++
        ) {

            if (
                selectedAnswers[i]
                ==
                questions[i].correctAnswer
            ) {

                score++;
            }
        }


        // Calculate time taken

        int totalTime =
            30 * 60;

        int timeTaken =
            totalTime - timeRemaining;


        // Close exam window

        dispose();


        // Open result screen

        ResultFrame resultFrame =
            new ResultFrame(
                score,
                questions.length,
                timeTaken
            );


        resultFrame.setVisible(true);
    }


    // ==================================================
    // CONFIRM QUIT
    // ==================================================

    void confirmQuit() {

        int answer =
            JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit?",
                "Quit Exam",
                JOptionPane.YES_NO_OPTION
            );


        if (
            answer
            == JOptionPane.YES_OPTION
        ) {

            if (timer != null) {

                timer.stop();
            }

            dispose();
        }
    }


    // ==================================================
    // MAIN METHOD
    // ==================================================

    public static void main(
        String[] args
    ) {

        ExamFrame frame =
            new ExamFrame();

        frame.setVisible(true);
    }
}