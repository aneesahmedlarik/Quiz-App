
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QUIZAPP implements ActionListener {



    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel, timerLabel, resultLabel;
    private JRadioButton[] options;
    private JButton submitButton;
    private ButtonGroup optionsGroup;
    private Timer timer;
    private int totalTime = 480; // Total time for the quiz (in seconds)
    private int score = 0;

    private String[][] questions = {
            {"What is the capital of France?", "Paris", "Berlin", "Madrid", "Rome","Paris"},
            {"Who was the first man to land on the Moon?", "Isaac Newton ", "Edwin Buzz", "Alan B", "Neil Armstrong ","Neil Armstrong "},
            {"Who painted the Mona Lisa?", "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Salvador Dali", "Leonardo da Vinci"},
            {"Which planet in our solar system is the closest to the sun?", "Mercury", "Venus", "Earth", "Mars", "Mercury"},
            {"Which statement is true about Java?", "Java is a sequence-dependent programming language", "Java is a code dependent programming language", "Java is a platform-dependent programming language", "Java is a platform-independent programming language", "Java is a platform-independent programming language"},
            {" Which component is used to compile, debug and execute the java programs?"," JRE ", "JIT", "JDK","JVM","JDK"},
            { "Which one of the following is not a Java feature?","Object-oriented", "Use of pointers", "Portable", "Dynamic and Extensible","Use of pointers"},
            {"Which of these cannot be used for a variable name in Java?","identifier & keyword","identifier","keyword","none of the mentioned","keyword"},
            {"Who is the father of Java? ", "James Gosling","Bjarne Stroutstop ","Oak Team","N.O.T","James Gosling"},
            {"What is the extension of java code files?"," .js"," .txt"," .class"," .java"," .java"},
            {"Which of the following is not an OOPS concept in Java?"," Polymorphism"," Inheritance"," Compilation","Encapsulation"," Compilation"},
            {"What is not the use of “this” keyword in Java?","Referring to the instance variable when a local variable has the same name","Passing itself to the method of the same class","Passing itself to another method","Calling another constructor in constructor chaining","Passing itself to another method"},
            {"Which of the following is a type of polymorphism in Java Programming?","Multiple polymorphism"," Compile time polymorphism","Multilevel polymorphism","Execution time polymorphism"," Compile time polymorphism"},
            {"What is the extension of compiled java classes?"," .txt "," .js"," .class"," .java"," .class"},
            {"Which of these are selection statements in Java?","  break"," continue","for()","if()","if()"}

    };

    private int currentQuestion = 0;

    public QUIZAPP() {
        frame = new JFrame("Quiz App");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(7, 1));

        questionLabel = new JLabel(questions[currentQuestion][0]);
        panel.add(questionLabel);

        optionsGroup = new ButtonGroup();
        options = new JRadioButton[4];
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton(questions[currentQuestion][i + 1]);
            optionsGroup.add(options[i]);
            panel.add(options[i]);
        }

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        panel.add(submitButton);

        timerLabel = new JLabel("Time remaining: " + totalTime + "s");
        panel.add(timerLabel);

        resultLabel = new JLabel();
        panel.add(resultLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalTime--;
                timerLabel.setText("Time remaining: " + totalTime + "s");

                if (totalTime <= 0) {
                    timer.stop();
                    displayResult();
                }
            }
        });
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            checkAnswer();

            currentQuestion++;
            if (currentQuestion < questions.length) {
                updateQuestion();
            } else {
                timer.stop();
                displayResult();
            }
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected() && options[i].getText().equals(questions[currentQuestion][5])) {
                score++;
                break;
            }
        }
    }

    private void updateQuestion() {
        questionLabel.setText(questions[currentQuestion][0]);
        for (int i = 0; i < options.length; i++) {
            options[i].setText(questions[currentQuestion][i + 1]);
            options[i].setSelected(false);
        }
    }

    private void displayResult() {
        int totalQuestions = questions.length;
        double percentage = (double) score / totalQuestions * 100;

        String message = "Quiz completed!\n";
        message += "  Total questions: " + totalQuestions + "\n";
        message += "  Correct answers: " + score + "\n";
        message += "   Percentage: " + String.format("%.2f", percentage) + "%";

        resultLabel.setText(message);
    }

    public static void main(String[] args) {
        new QUIZAPP();
    }
}

