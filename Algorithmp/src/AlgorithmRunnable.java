import java.util.Random;
import javax.swing.JTextPane;
import javax.swing.text.*;

import java.awt.*;
import java.lang.reflect.Array;

import javax.swing.JPanel;

class sharedmemory{
	int[] array;
	private int redColumn=-1;
	private int greenColumn=-1;
    private int cyanColumn=-1;
	private int blueColumn=-1;
	public synchronized void array(int[] array) {
		this.array = array;
	}
	public synchronized void getredc(int redColumn) {
		this.redColumn = redColumn;
	}
    public synchronized void getgreenc(int greenColumn) {
        this.greenColumn = greenColumn;
    }
    public synchronized void getcyanc(int cyanColumn) {
		this.cyanColumn = cyanColumn;
	}
    public synchronized void getbluec(int blueColumn) {
    	this.blueColumn = blueColumn;
    }
    public synchronized int putcyanc() {
    	return this.cyanColumn;
    }
    public synchronized int putbluec() {
    	return this.blueColumn;
    }
    public synchronized int putredc() {
        return this.redColumn;
    }

    public synchronized int putgreenc() {
        return this.greenColumn;
    }

    public synchronized int[] putarray() {
        return this.array;
    }

}

class AlgorithmRunnable implements Runnable {
    private int algorithmChoice;
    private JTextPane textPane;


    // 생성자: 알고리즘 선택과 JTextPane를 받아서 초기화합니다.
    AlgorithmRunnable(int algorithmChoice, JTextPane textPane) {
        this.algorithmChoice = algorithmChoice;
        this.textPane = textPane;
    }

    Thread tr1;
    Thread tr2;
    Thread tr3;
    Thread tr4;
    Thread tr5;
    sharedmemory sm = new sharedmemory();

    @Override
    public void run() {
    	
   	 int[] array = new int[100];
     for (int i = 0; i < array.length; i++) {
         array[i] = i + 1;
     }
     
     // Shuffle the array using Fisher-Yates algorithm
     Random random = new Random();
     for (int i = array.length - 1; i > 0; i--) {
         int j = random.nextInt(i + 1);
         int temp = array[i];
         array[i] = array[j];
         array[j] = temp;
     }
     sm.array(array);
     tr1 = new Thread(new Bubble(sm));
     tr2 = new Thread(new Heap(sm));
     tr3 = new Thread(new Quick(sm));
     tr4= new Thread(new Insert(sm));

        switch (algorithmChoice) {
            case 1:
                appendText("버블 정렬 실행...\n", null);
                tr1.start();
                bubbleSort(array);

                break;
            case 2:
                appendText("힙 정렬 실행...\n", null);
                tr2.start();
                heapSort(array);

                break;
            case 3:
                appendText("퀵 정렬 실행...\n", null);
                tr3.start();
                quickSort(array, 0, array.length - 1); // 퀵 정렬 실행

                break;
            case 4:
                appendText("삽입 정렬 실행...\n", null);
                tr4.start();
                insertionSort(array);

                break;
            case 5:
                appendText("선택 정렬 실행...\n", null);
                tr1.start();
                selectionSort(array);
                break;
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
                sm.getredc(j);

                if (array[j] > array[j + 1]) {
                    // array[j]와 array[j + 1]을 교환합니다.
                    sm.getredc(j + 1);

                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;

                    // 교환 후 배열을 출력하고, 0.5초 동안 대기합니다.
                    printArray(array, j, j + 1);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // 인터럽트 발생 시 현재 스레드를 중단합니다.
                    }
                }
            }
            sm.getgreenc(array.length - (i + 1));

            // 내부 루프에서 두 요소가 교환되지 않았다면 배열이 정렬된 것입니다.
            if (!swapped) {
                break;
            }
        }
        sm.getgreenc(0);
        sm.getredc(-1);

    }

    // 힙 정렬 알고리즘
    private void heapSort(int[] array) {
        int n = array.length;

    // Build max heap
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(array, n, i);
       
    }
    

    // Heap sort
    for (int i = n - 1; i >= 0; i--) {
        // Move current root to end
        int temp = array[0];
        array[0] = array[i];
        array[i] = temp;
        sm.getredc(i);
        sm.getgreenc(i);
            printArray(array, 0, i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 인터럽트 발생 시 현재 스레드를 중단합니다.
            }

            // Heapify root element
            heapify(array, i, 0);
        }sm.getredc(-1);
    }

// To heapify a subtree rooted with node i which is an index in arr[]
private void heapify(int[] arr, int n, int i) {
    int largest = i;  // Initialize largest as root
    int left = 2 * i + 1;  // left = 2*i + 1
    int right = 2 * i + 2;  // right = 2*i + 2
    sm.getcyanc(left);
    sm.getbluec(arr[i]);

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest])
            largest = right;

    // If largest is not root
    if (largest != i) {
        int swap = arr[i];
        arr[i] = arr[largest];
        arr[largest] = swap;
       

            printArray(arr, i, largest);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 인터럽트 발생 시 현재 스레드를 중단합니다.
            }

        // Recursively heapify the affected sub-tree
        heapify(arr, n, largest);
    }
}

    // 퀵 정렬 알고리즘
    private void quickSort(int[] array, int low, int high) {
        sm.getgreenc(array.length - 1);
        if (low < high) {
            // pi is partitioning index, array[p] is now at right place
            int pi = partition(array, low, high);

            // Recursively sort elements before partition and after partition
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        sm.getredc(array[high]);
        int i = (low - 1); // index of smaller element
        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or equal to pivot
            if (array[j] < pivot) {
                i++;

                // swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == sm.putredc()) {
                    sm.getredc(j);
                } else if (j == sm.putredc()) {
                    sm.getredc(i);
                }

                // print array after swapping and delay
                printArray(array, i, j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        sm.getgreenc(low);

        // swap array[i+1] and array[high] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        // print array after swapping and delay
        printArray(array, i + 1, high);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return (i + 1);
    }

    // 삽입 정렬 알고리즘
    private void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            sm.getcyanc(i);
            sm.getredc(i);

            
            int j;
            /*
             * Move elements of arr[0..i-1], that are
             * greater than key, to one position ahead
             * of their current position
             */
            for (j= i - 1; j >= 0 && array[j] > array[j+1] ; j--) {

                sm.getredc(j + 1);
                int tmp = array[j+1];
                array[j + 1] = array[j];
                array[j]=tmp;
              
               
                printArray(array, j, j + 1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                
            }sm.getredc(j+1);
            
             
                
            
            
           
           // array[j + 1] = key;
            
        }
        sm.getgreenc(array.length-1);
        sm.getredc(-1);
    }

    // 선택 정렬 알고리즘
    private void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // Find the minimum element in unsorted array
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[minIdx])
                    minIdx = j;

            // Swap the found minimum element with the first
            // element of the unsorted part
            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;

            printArray(array, i, minIdx);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 인터럽트 발생 시 현재 스레드를 중단합니다.
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
