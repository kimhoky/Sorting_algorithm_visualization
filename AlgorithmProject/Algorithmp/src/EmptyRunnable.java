import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




public class EmptyRunnable implements Runnable {
	private int[] data;
	GraphPanel graphPanel = new GraphPanel();
    @Override
    public void run() {
        // 비워두었습니다.
    	 SwingUtilities.invokeLater(Graph::new);
    	while(true) {
    		graphPanel.repaint();
    		
    	}
    	 
    	
    }
    public EmptyRunnable(int[]array) {
    	this.data = array;
    }
    public class Graph extends JFrame {
    	 
    public Graph() {
        setTitle("Graph Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        // 데이터 생성
        
        
        // 생성된 난수 출력
         

        
        add(graphPanel);

        setVisible(true);
    }
   
  
 

     
}
    class GraphPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 그래프 그리기
            int barWidth = getWidth() / data.length;
            int maxHeight = getHeight() - 10; // 그래프의 최대 높이를 패널의 높이보다 20px 작게 설정
            int x = 0;

            for (Integer value : data) {
                int barHeight = maxHeight * value / 200; 
                int y = getHeight() - barHeight;

                g.setColor(Color.BLUE);
                g.fillRect(x, y, barWidth, barHeight);

                g.setColor(Color.BLACK);
                g.drawRect(x, y, barWidth, barHeight);

                x += barWidth;
            }
        }
    }
}
