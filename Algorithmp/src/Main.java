import javax.swing.*;
import java.awt.*;

public class Main {
    private static JFrame frame;

    public static void main(String[] args) {

        showAlgorithmSelection();
    }

    private static void showAlgorithmSelection() {

        // 사용자에게 알고리즘 선택을 요청합니다.
        int choice = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Select an algorithm:\n1. Bubble Sort\n2. Heap Sort\n3. Quick Sort\n4. Insertion Sort\n5. Selection Sort\n6. All Start"));

        // JFrame을 생성합니다.
        frame = new JFrame("Algorithm Result");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocation(100, 150);
        frame.setLayout(new BorderLayout());

        // JTextPane를 생성하고 스크롤 가능하도록 JScrollPane으로 감쌉니다.
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // JTextPane를 프레임의 중앙에 배치합니다.
        frame.add(scrollPane, BorderLayout.CENTER);
        // 프레임을 화면에 표시합니다.
        if (choice != 6) {
            frame.setVisible(true);
        } else {
            frame.setVisible(false);
        }

        // 알고리즘 실행
        if (choice == 6) {
            Thread bubbleThread = new Thread(() -> {

                AlgorithmRunnable algorithmRunnable = new AlgorithmRunnable(1, textPane, 1);
                algorithmRunnable.run();

            });
            bubbleThread.start();

            Thread HeapThread = new Thread(() -> {

                AlgorithmRunnable algorithmRunnable = new AlgorithmRunnable(2, textPane, 1);
                algorithmRunnable.run();

            });
            HeapThread.start();

            Thread QuikThread = new Thread(() -> {

                AlgorithmRunnable algorithmRunnable = new AlgorithmRunnable(3, textPane, 1);
                algorithmRunnable.run();

            });
            QuikThread.start();

            Thread InsertThread = new Thread(() -> {

                AlgorithmRunnable algorithmRunnable = new AlgorithmRunnable(4, textPane, 1);
                algorithmRunnable.run();

            });
            InsertThread.start();

            Thread SelectionThread = new Thread(() -> {

                AlgorithmRunnable algorithmRunnable = new AlgorithmRunnable(5, textPane, 1);
                algorithmRunnable.run();

            });
            SelectionThread.start();

        }

        Thread algorithmThread = new Thread(() -> {

            AlgorithmRunnable algorithmRunnable = new AlgorithmRunnable(choice, textPane, 0);
            algorithmRunnable.run();

        });
        algorithmThread.start();

        // 알고리즘 실행이 끝난 후에 선택 창으로 돌아가는 버튼을 생성합니다.
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            algorithmThread.interrupt();

            frame.setVisible(false); // 현재 프레임을 숨깁니다.
            frame.dispose(); // 현재 프레임을 파괴합니다.
            showAlgorithmSelection(); // 알고리즘 선택 창을 다시 표시합니다.
        });

        // 프레임에 선택 창으로 돌아가는 버튼을 추가합니다.
        frame.add(backButton, BorderLayout.SOUTH);
    }

}