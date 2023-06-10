import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Insert implements Runnable {
    private int[] data;
    GraphPanel graphPanel = new GraphPanel();
    int redColumn;
    int greenColumn;
    int cyanColumn;
    int blueColumn;
    int allgo;
    boolean runval = true;
    sharedmemory sm = new sharedmemory();

    @Override
    public void run() {
        // 비워두었습니다.
        data = sm.putarray();
        allgo = sm.putallgo();
        Graph graph = new Graph();
        while (runval) {

            runval = sm.putrunval();
            if (!runval) {
                graph.setVisible(runval);
                graph.dispose();

                break;
            }
            redColumn = sm.putredc();
            greenColumn = sm.putgreenc();
            cyanColumn = sm.putcyanc();
            blueColumn = sm.putbluec();
            System.out.println(redColumn + " " + greenColumn + " " + cyanColumn + " " + blueColumn);

            graphPanel.repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 인터럽트 발생 시 현재 스레드를 중단합니다.
            }
        }

    }

    public Insert(sharedmemory sm) {
        this.sm = sm;
        data = sm.putarray();
        redColumn = sm.putredc();
        greenColumn = sm.putgreenc();
    }

    public class Graph extends JFrame {

        public Graph() {
            if (allgo == 1) {
                setSize(800, 500);
                setLocation(150, 500);

            } else {
                setSize(800, 500);
                int x = sm.putblocx();
                int y = sm.putblocy();
                setLocation(x, y);
            }
            setTitle("Insert Sort Graph");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            add(graphPanel);

            JLabel label = new JLabel("Insert Sort");
            Font font = new Font("Serif", Font.BOLD, 25);
            label.setFont(font);
            graphPanel.setLayout(new BorderLayout());
            graphPanel.add(label, BorderLayout.NORTH);

            setVisible(true);
        }

    }

    class GraphPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int BORDER_WIDTH = 10;
            int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / data.length;
            int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / data.length;

            for (int i = (greenColumn == -1 ? 0 : greenColumn); i < data.length; i++) {
                g.setColor(Color.WHITE);
                g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH,
                        columnWidth, data[i] * columnHeight);
                g.setColor(Color.BLACK);
                g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH,
                        columnWidth, data[i] * columnHeight);
            }
            for (int i = 0; i <= cyanColumn; i++) {
                g.setColor(Color.CYAN);
                g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH,
                        columnWidth, data[i] * columnHeight);
                g.setColor(Color.BLACK);
                g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH,
                        columnWidth, data[i] * columnHeight);
            }
            for (int i = 0; i <= greenColumn; i++) {
                g.setColor(Color.GREEN);
                g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH,
                        columnWidth, data[i] * columnHeight);
                g.setColor(Color.BLACK);
                g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH,
                        columnWidth, data[i] * columnHeight);
            }
            if (redColumn != -1) {
                g.setColor(Color.RED);
                g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn,
                        getHeight() - data[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
                        data[redColumn] * columnHeight);
                g.setColor(Color.BLACK);
                g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn,
                        getHeight() - data[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
                        data[redColumn] * columnHeight);
            }

        }
    }
}
