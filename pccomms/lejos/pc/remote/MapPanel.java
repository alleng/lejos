package lejos.pc.remote;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashMap;

import lejos.geom.Line;
import lejos.geom.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;

public class MapPanel extends RemotePanel {
	private static final long serialVersionUID = 1L;
	protected LineMap map;
	protected Line[] lines;
	protected float xOffset, yOffset;
	protected float pixelsPerUnit;
	protected Rectangle boundingRect;
	protected static final float ARROW_LENGTH = 10f;
	protected static final int ROBOT_SIZE = 2;
	
	protected Pose pose = new Pose();
	
	public MapPanel(LineMap map, float xOffset, float yOffset, float pixelsPerUnit) {
		this.map = map;
		this.lines = map.getLines();
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.pixelsPerUnit = pixelsPerUnit;
		this.boundingRect = map.getBoundingRect();
		Dimension size = new Dimension((int) (boundingRect.width * pixelsPerUnit + 1), (int) (boundingRect.height * pixelsPerUnit + 1));
		this.setPreferredSize(size);
	}
	
	/**
	 * Draw the map using Line2D objects
	 * 
	 * @param g2d the Graphics2D object
	 */
	public void paintMap(Graphics2D g2d) {
		g2d.setColor(Color.black);
		for (int i = 0; i < lines.length; i++) {
			Line2D line = new Line2D.Float(
    		  xOffset + lines[i].x1 * pixelsPerUnit, 
    		  CANVAS_OFFSET + yOffset + lines[i].y1 * pixelsPerUnit, 
    		  xOffset + lines[i].x2 * pixelsPerUnit, 
    		  CANVAS_OFFSET + yOffset + lines[i].y2 * pixelsPerUnit);
			g2d.draw(line);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintMap((Graphics2D) g);
		paintRobot((Graphics2D) g);
	}
	
	protected void paintRobot(Graphics2D g) {
		paintPose(g, pose);
	}
	
	/**
	 * Paint the pose using Ellipse2D
	 * 
	 * @param g2d the Graphics2D object
	 */
	public void paintPose(Graphics2D g2d, Pose pose) {
		Ellipse2D c = new Ellipse2D.Float(xOffset + pose.getX() * pixelsPerUnit - 1, CANVAS_OFFSET + yOffset + pose.getY() * pixelsPerUnit - 1, ROBOT_SIZE, ROBOT_SIZE);
		Line rl = getArrowLine(pose);
		Line2D l2d = new Line2D.Float(rl.x1, rl.y1, rl.x2, rl.y2);
		g2d.draw(l2d);
		g2d.draw(c);
	}
  
	/**
	 * Create a Line that represents the direction of the pose
	 * 
	 * @param pose the pose
	 * @return the arrow line
	 */
	protected Line getArrowLine(Pose pose) {
		return new Line(xOffset + pose.getX() * pixelsPerUnit,
    		        CANVAS_OFFSET + yOffset + pose.getY() * pixelsPerUnit, 
    		        xOffset + pose.getX() * pixelsPerUnit + ARROW_LENGTH * (float) Math.cos(Math.toRadians(pose.getHeading())), 
    		        CANVAS_OFFSET + yOffset + pose.getY() * pixelsPerUnit + ARROW_LENGTH * (float) Math.sin(Math.toRadians(pose.getHeading())));
	}
	
	/** 
	 * Update relevant data items from data returned from NXT
	 */
	protected void updateData(Message reply, HashMap<String,Number> data) {
		if (data == null) return;
		if (reply.getName().equals("getPose")) {
			pose = new Pose((Float) data.get("x"), (Float) data.get("y"), (Float) data.get("heading"));
		}	  
	}
}
