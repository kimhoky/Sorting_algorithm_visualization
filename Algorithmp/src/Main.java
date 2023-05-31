import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        // 사용자에게 알고리즘 선택을 요청합니다.
        int choice = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Select an algorithm:\n1. Bubble Sort\n2. Selection Sort\n3. Insertion Sort\n4. Quick Sort\n5. Merge Sort"));

        // JFrame을 생성합니다.
        JFrame frame = new JFrame("Algorithm Result");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        // JTextPane를 생성하고 스크롤 가능하도록 JScrollPane으로 감쌉니다.
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // JTextPane를 프레임의 중앙에 배치합니다.
        frame.add(scrollPane, BorderLayout.CENTER);

        // 프레임을 화면에 표시합니다.
        frame.setVisible(true);

        // 첫 번째 스레드를 시작합니다. 이 스레드는 선택된 알고리즘을 실행합니다.
        Thread algorithmThread = new Thread(new AlgorithmRunnable(choice, textPane));
        algorithmThread.start();

        // 두 번째 스레드를 시작합니다. 이 스레드는 비워두었습니다.
      
    }
}
