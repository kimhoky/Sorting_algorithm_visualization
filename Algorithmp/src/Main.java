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
        frame.setSize(800, 500);
        frame.setLocation(100, 300);
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
        //all start(6)을 입력시 모든 초이스를 넣어 5개의 스레드 시작
        //그 이외는 선택된 초이스만 넣어 시작
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
            algorithmThread.interrupt(); //알고리즘 스레드를 인터럽트를 통해 정지시키며 알고리즘 스레드의 catch문을 통해 그래프 스레드를 정지시킴.
            

            frame.setVisible(false); // 현재 프레임을 숨깁니다.
            frame.dispose(); // 현재 프레임을 파괴합니다.
            showAlgorithmSelection(); // 알고리즘 선택 창을 다시 표시합니다.
        });
        JLabel label = new JLabel("");
        Font font = new Font("Serif", Font.BOLD, 25);
        label.setFont(font);

        if (choice != 6) {
            switch (choice) {
                case 1:
                    label.setText("Bubble Sort");
                    frame.add(label, BorderLayout.NORTH);
                    break;

                case 2:
                    label.setText("Heap Sort");
                    frame.add(label, BorderLayout.NORTH);
                    break;

                case 3:
                    label.setText("Quick Sort");
                    frame.add(label, BorderLayout.NORTH);
                    break;

                case 4:
                    label.setText("Insertion Sort");
                    frame.add(label, BorderLayout.NORTH);
                    break;

                case 5:
                    label.setText("Selection Sort");
                    frame.add(label, BorderLayout.NORTH);
                    break;
            }
        }

        // 프레임에 선택 창으로 돌아가는 버튼을 추가합니다.
        frame.add(backButton, BorderLayout.SOUTH);
    }

}