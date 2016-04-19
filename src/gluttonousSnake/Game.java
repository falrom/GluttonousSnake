package gluttonousSnake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
	{
		Land land = new Land(20);
		Game game = new Game();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setResizable(false);
		game.setTitle("Gluttonous Snake");
//		game.setLocationRelativeTo(null);
		game.setUndecorated(false);
		game.add(land);
		
		game.addKeyListener(new MyKeyListener(land));
		
		game.pack();
		game.setVisible(true);
		
		while(land.step())
		{
			//每次循环必须先走步再生成食物，防止生成食物正好在蛇头前，食物被吃掉用户却看不到
			land.makeFood();
			game.repaint();
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		JOptionPane.showMessageDialog(null, "You've just lost the game.");
		System.exit(0);
		
	}

}

//键盘监听类
class MyKeyListener extends KeyAdapter
{
	private Land land;
		
	public MyKeyListener(Land land)
	{
		super();
		
		//得到传递过来的Land用于处理其方向
		this.land = land;
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			this.land.changeDirecTo(Point.UP);
			System.out.println("UP");
			break;
		case KeyEvent.VK_DOWN:
			this.land.changeDirecTo(Point.DOWN);
			System.out.println("DOWN");
			break;
		case KeyEvent.VK_LEFT:
			this.land.changeDirecTo(Point.LEFT);
			System.out.println("LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			this.land.changeDirecTo(Point.RIGHT);
			System.out.println("RIGHT");
			break;
		}
	}
}
