package edu.caltech.cms.intelliviz.graph.ui;

import edu.caltech.cms.intelliviz.graph.GraphEdge;
import edu.caltech.cms.intelliviz.graph.INode;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PrimitiveNode implements INode {

    private double x, y;
    private double width, height;
    private String label;

    private int TEXT_PADDING = 4;

    public PrimitiveNode(double x, double y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
        this.height = 20;
    }

    @Override
    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(GREEN);
        g2d.setFont(boldItalic);
        FontMetrics fm = g2d.getFontMetrics();

        this.width = fm.stringWidth(this.label) + 2 * TEXT_PADDING;

        Rectangle2D.Double rect = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
        g2d.fill(rect);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(rect);
        g2d.drawString(label, (int)this.x + TEXT_PADDING, (int)this.y + (int)this.height - TEXT_PADDING);
    }


    @Override
    public boolean contains(double x, double y) {
        Rectangle2D.Double rect = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
        return rect.contains(x, y);
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public Point2D getTarget(double originX, double originY) {
        return GraphEdge.getCenterTargetingProjection(this, originX, originY);
    }

    @Override
    public Point2D getOrigin(GraphEdge edge) {
        return new Point2D.Double(this.x + this.width, this.y + this.height / 2);
    }

    @Override
    public ArrayList<GraphEdge> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public void highlightChanges(INode ref) {

    }
}
