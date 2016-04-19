package gluttonousSnake;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Land extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static final int GRID_SIZE = 16;
	private static final int MAX_FOOD = 3;
	//	private static int HEAD = 0;
	//	private static int TAIL = 1;
	
	private int size;			//陆地的尺寸
	private Point[][] land;
	private int numofFood = 0;	//目前陆地上还未食用的食物总数量
	private int headx;			//蛇头的X坐标值<0~size-1>
	private int heady;
	private int tailx;			//蛇尾的X坐标值<0~size-1>
	private int taily;
	private int direction = Point.UP;	//蛇将要爬行的方向
	private int lastDirec = Point.UP;	//蛇上次的爬行方向。即用来标记蛇身的走向，防止倒行
	private boolean steptAlready = true;//用来标识转换方向后是否已经走过一步。防止多次转换方向。
	
	public Land(int size)
	{
		this.size = size;
		land = new Point[size][size];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				land[i][j] = new Point();
		}
		
		headx = this.size/2 - 1;
		heady = this.size/2 - 1;
		tailx = this.size/2 - 1;
		taily = this.size/2 - 1;
		land[headx][heady].changeStateTo(Point.ISSNAKE);//初始化一个位置为蛇身。
	}
	
	//获取蛇头
	public Point getHead()
	{
		return this.land[headx][heady];
	}
	
	//改变蛇的前进方向
	public void changeDirecTo(int direc)
	{
		if(this.steptAlready)
		{
			this.lastDirec = this.direction;
			this.steptAlready = false;
		}
		if((this.lastDirec + direc) == 3)//不可立即转向反方向
			return;
		this.direction = direc;
	}
	
	//运行一步
	public boolean step()
	{
		this.steptAlready = true;
//		switch(land[headx][heady].getDirection())
		switch(this.direction)
		{
		case Point.UP:
			land[headx][heady].changeDirecTo(Point.UP);
			this.heady = (this.heady + this.size - 1) % this.size;
			break;
		case Point.DOWN:
			land[headx][heady].changeDirecTo(Point.DOWN);
			this.heady = (this.heady + 1) % this.size;
			break;
		case Point.LEFT:
			land[headx][heady].changeDirecTo(Point.LEFT);
			this.headx = (this.headx + this.size - 1) % this.size;
			break;
		case Point.RIGHT:
			land[headx][heady].changeDirecTo(Point.RIGHT);
			this.headx = (this.headx + 1) % this.size;
			break;
		}
		switch(land[headx][heady].getState())
		{
		case Point.ISSNAKE:
			return false;
		case Point.ISFOOD:
			land[headx][heady].changeStateTo(Point.ISSNAKE);
			this.numofFood--;
			return true;
		case Point.ISEMPTY:
			land[headx][heady].changeStateTo(Point.ISSNAKE);
		}
		land[tailx][taily].changeStateTo(Point.ISEMPTY);
		switch(land[tailx][taily].getDirection())
		{
		case Point.UP:
			this.taily = (this.taily + this.size - 1) % this.size;
			break;
		case Point.DOWN:
			this.taily = (this.taily + 1) % this.size;
			break;
		case Point.LEFT:
			this.tailx = (this.tailx + this.size - 1) % this.size;
			break;
		case Point.RIGHT:
			this.tailx = (this.tailx + 1) % this.size;
			break;
		}
		return true;
	}
	
	public boolean makeFood()
	{
		int row = (int)(0 + Math.random()*(this.size * 2 - 0));//并不是百分百产生食物
		int col = (int)(0 + Math.random()*(this.size * 2 - 0));
		if((row < this.size)&&(col < this.size)&&(this.numofFood < MAX_FOOD)&&(land[row][col].getState() == Point.ISEMPTY))
		{
			land[row][col].changeStateTo(Point.ISFOOD);
			this.numofFood++;
			return true;
		}
		return false;
	}

	@Override
	public void paint(Graphics arg0)
	{
		super.paint(arg0);
		for(int j = 0; j < this.size; j++)
		{
			for(int i = 0; i < this.size; i++)
			{
				land[i][j].draw(arg0, i*Land.GRID_SIZE, j*Land.GRID_SIZE, Land.GRID_SIZE);
			}
		}
	}
		
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.size*GRID_SIZE+1, this.size*GRID_SIZE+1);
	}
}
