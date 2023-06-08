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
                "Select an algorithm:\n1. Bubble Sort\n2. Heap Sort\n3. Quick Sort\n4. Insertion Sort\n5. Selection Sort"));
        
        
        
        // JFrame을 생성합니다.
        frame = new JFrame("Algorithm Result");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
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
        frame.setVisible(true);
        
        
          
         
         
      
        // 알고리즘 실행
       
        Thread algorithmThread = new Thread(() -> {
            
            AlgorithmRunnable algorithmRunnable = new AlgorithmRunnable(choice, textPane);
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
