package gluttonousSnake;

import java.awt.Color;
import java.awt.Graphics;

public class Point
{
	//定义每个点的三种状态：空，蛇身，食物
	public static final int ISEMPTY = 0;
	public static final int ISSNAKE = 1;
	public static final int ISFOOD = 2;
	
	//定义每个点的四种方向：上下左右
	public static final int UP = 0;
	public static final int DOWN = 3;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	
	//该点的状态
	private int state = ISEMPTY;
	//记录蛇头经过该点时的运行方向，方便蛇尾行进时判断方向
	private int direction = UP;
	
	public int getState()
	{
		return this.state;
	}
	
	public int getDirection()
	{
		return this.direction;
	}
	
	public void changeDirecTo(int direc)
	{
		this.direction = direc;
	}
	
	public void changeStateTo(int sta)
	{
		this.state = sta;
	}
	
	//在画板上绘制该点
	public void draw(Graphics g, int x, int y, int size)
	{
		g.setColor(Color.BLACK);
		switch(this.state)
		{
		case ISEMPTY:
			g.drawRect(x, y, size, size);
			break;
		case ISSNAKE:
			g.fillRect(x, y, size, size);
			break;
		case ISFOOD:
			g.setColor(Color.BLUE);
			g.fillRect(x, y, size, size);
			g.setColor(Color.BLACK);
			break;
		}
	}
	
}
