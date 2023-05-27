import java.util.Random;

import javax.swing.JTextArea;

class AlgorithmRunnable implements Runnable {
    private int algorithmChoice;
    private JTextArea textArea;

    // 생성자: 알고리즘 선택과 JTextArea를 받아서 초기화합니다.
    AlgorithmRunnable(int algorithmChoice, JTextArea textArea) {
        this.algorithmChoice = algorithmChoice;
        this.textArea = textArea;
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
                textArea.append("버블 정렬 실행...\n");
                bubbleSort(array); // 버블 정렬 실행
                break;
            // ... 다른 알고리즘들 ...
            default:
                textArea.append("잘못된 선택.\n");
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
                    printArray(array);
                    try {
                        Thread.sleep(500);
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
    private void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
