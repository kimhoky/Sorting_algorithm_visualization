import java.util.Random;
import javax.swing.JTextPane;
import javax.swing.text.*;
import java.awt.*;


class AlgorithmRunnable implements Runnable {
    private int algorithmChoice;
    private JTextPane textPane;

    // 생성자: 알고리즘 선택과 JTextPane를 받아서 초기화합니다.
    AlgorithmRunnable(int algorithmChoice, JTextPane textPane) {
        this.algorithmChoice = algorithmChoice;
        this.textPane = textPane;
    }

    @Override
    public void run() {
        // 0에서 999까지의 원소를 가진 배열을 생성합니다.
        int[] array = new int[1000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }

        switch (algorithmChoice) {
            case 1:
                appendText("버블 정렬 실행...\n", null);
                bubbleSort(array); // 버블 정렬 실행
                break;
            // ... 다른 알고리즘들 ...
            default:
                appendText("잘못된 선택.\n", null);
                break;
        }
    }

    // 버블 정렬 알고리즘
    private void bubbleSort(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // array[j]와 array[j + 1]을 교환합니다.
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;

                    // 교환 후 배열을 출력하고, 0.5초 동안 대기합니다.
                    printArray(array, j, j + 1);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // 인터럽트 발생 시 현재 스레드를 중단합니다.
                    }
                }
            }

            // 내부 루프에서 두 요소가 교환되지 않았다면 배열이 정렬된 것입니다.
            if (!swapped) {
                break;
            }
        }
    }

    // 배열 출력
    private void printArray(int[] array, int index1, int index2) {
        StyledDocument doc = textPane.getStyledDocument();
        Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);

        try {
            doc.remove(0, doc.getLength()); // 이전 내용을 모두 지웁니다.

            for (int i = 0; i < array.length; i++) {
                Style style = defaultStyle;
                if (i == index1 || i == index2) {
                    StyleConstants.setForeground(style, Color.RED);
                } else {
                    StyleConstants.setForeground(style, Color.BLACK);
                }

                doc.insertString(doc.getLength(), array[i] + " ", style);

                if ((i + 1) % 30 == 0) {
                    doc.insertString(doc.getLength(), "\n", defaultStyle);
                }
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // JTextPane에 텍스트를 추가합니다.
    private void appendText(String text, AttributeSet attributes) {
        StyledDocument doc = textPane.getStyledDocument();

        try {
            doc.insertString(doc.getLength(), text, attributes);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

class EmptyRunnable implements Runnable {
    @Override
    public void run() {
        // 두 번째 스레드가 비워두었던 기능을 추가할 수 있습니다.
    }
}