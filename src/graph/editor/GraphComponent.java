package graph.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

public class GraphComponent extends JComponent implements MouseInputListener {

	private static final long serialVersionUID = 1L;
	private List<RectangularShape> shapes = new ArrayList<RectangularShape>();
	private List<Color> colors = new ArrayList<>();
	private RectangularShape currentShape = null;
	private int dx = 0;
	private int dy = 0;
	private static final Color[] colorList = new Color[] { Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN,
			Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW };

	private RectangularShape shapeSample = new Ellipse2D.Double(0, 0, 10, 10);

	public GraphComponent() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	protected void paintComponent(Graphics g) {
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < shapes.size(); i++) {
			RectangularShape s = shapes.get(i);
			g.setColor(colors.get(i));
			g2.fill(s);
		}
	}

	private RectangularShape getShape(int x, int y) {
		for (int i = shapes.size() - 1; i >= 0; i--) {
			RectangularShape s = shapes.get(i);
			if (s.contains(x, y)) {
				dx = (int) (x - s.getCenterX());
				dy = (int) (y - s.getCenterY());
				System.out.println(dx);
				System.out.println(dy);
				return s;
			}
		}
		return null;
	}

	public void setShapeType(RectangularShape sample) {
		shapeSample = sample;
	}

	private RectangularShape createShape(int x, int y) {
		RectangularShape rs = (RectangularShape) shapeSample.clone();
		// moveShape(rs, x, y);
		double height = rs.getHeight();
		double width = rs.getWidth();
		rs.setFrameFromCenter(x, y, x + height / 2, y + width / 2);
		shapes.add(rs);
		Random r = new Random();
		colors.add(colorList[r.nextInt(9)]);
		return rs;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mouseClicked");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mouseExited");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		currentShape = getShape(x, y);
		if (currentShape == null) {
			currentShape = createShape(x, y);
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mouseReleased");
		currentShape = null;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mouseDragged");
		if (currentShape != null) {
			double height = currentShape.getHeight();
			double width = currentShape.getWidth();
			int x = arg0.getX();
			int y = arg0.getY();
			currentShape.setFrameFromCenter(x - dx, y - dy, x + height / 2 - dx, y + width / 2 - dy);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mouseMoved");
	}

}
