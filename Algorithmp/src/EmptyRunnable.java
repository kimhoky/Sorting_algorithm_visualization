import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class valueincome {
	private int redColumn = -1;
	private int greenColumn = -1;

	public synchronized void getredc(int redColumn) {
		this.redColumn = redColumn;
	}

	public synchronized void getgreenc(int greenColumn) {
		this.greenColumn = greenColumn;
	}

	public synchronized int putredc() {
		return this.redColumn;
	}

	public synchronized int putgreenc() {
		return this.greenColumn;
	}
}

public class EmptyRunnable implements Runnable {
	private int[] data;
	GraphPanel graphPanel = new GraphPanel();
	int redColumn;
	int greenColumn;
	sharedmemory sm = new sharedmemory();

	@Override
	public void run() {
		// 비워두었습니다.

		SwingUtilities.invokeLater(Graph::new);
		while (true) {

			redColumn = sm.putredc();
			greenColumn = sm.putgreenc();
			System.out.println(redColumn + "+" + greenColumn);

			graphPanel.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // 인터럽트 발생 시 현재 스레드를 중단합니다.
			}
		}

	}

	public EmptyRunnable(sharedmemory sm) {
		this.sm = sm;
		data = sm.putarray();
		redColumn = sm.putredc();
		greenColumn = sm.putgreenc();
	}

	public class Graph extends JFrame {

		public Graph() {
			setTitle("Graph Example");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(800, 500);

			// 데이터 생성

			// 생성된 난수 출력

			int x = 0;
			int y = 150;
			setLocation(x, y);

			add(graphPanel);

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

			for (int i = 0; i < (greenColumn == -1 ? data.length : greenColumn); i++) {
				g.setColor(Color.WHITE);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH,
						columnWidth, data[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH,
						columnWidth, data[i] * columnHeight);
			}
			if (greenColumn != -1) {
				for (int i = greenColumn; i < data.length; i++) {
					g.setColor(Color.GREEN);
					g.fillRect(2 * BORDER_WIDTH + columnWidth * i,
							getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
							data[i] * columnHeight);
					g.setColor(Color.BLACK);
					g.drawRect(2 * BORDER_WIDTH + columnWidth * i,
							getHeight() - data[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth,
							data[i] * columnHeight);
				}
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

			//
			// for (int i = 0; i < (greenColumn == -1 ? data.length : greenColumn); i++) {
			// barHeight = maxHeight * data[i] / 200;
			// y = getHeight() - barHeight;
			// x += barWidth;
			// g.setColor(Color.WHITE);
			// g.fillRect(barWidth*i, getHeight()-barHeight, barWidth, barHeight);
			// g.setColor(Color.BLACK);
			// g.drawRect(x, y, barWidth, barHeight);
			//
			// }
			// if(greenColumn != -1) {
			// for (int i = greenColumn; i < data.length; i++) {
			// g.setColor(Color.GREEN);
			// g.fillRect(x, y, barWidth, barHeight);
			// g.setColor(Color.BLACK);
			// g.drawRect(x, y, barWidth, barHeight);
			// }
			// }
			// if(redColumn != -1) {
			// g.setColor(Color.RED);
			// g.fillRect(x, y, barWidth, barHeight);
			// g.setColor(Color.BLACK);
			// g.drawRect(x, y, barWidth, barHeight);
			// }

			// for (Integer value : data) {
			//
			//
			// g.setColor(Color.BLUE);
			// g.fillRect(x, y, barWidth, barHeight);
			//
			// g.setColor(Color.BLACK);
			// g.drawRect(x, y, barWidth, barHeight);
			//
			//
			// }
		}
	}
}
