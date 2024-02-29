package Charts.Line.blankchart;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.List;

public abstract class BlankPlotChatRenderLine {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChartLine chart, Graphics2D g2, SeriesSize size, int index);

    public abstract void renderSeries(BlankPlotChartLine chart, Graphics2D g2, SeriesSize size, int index, List<Path2D.Double> gra);

    public abstract boolean mouseMoving(BlankPlotChartLine chart, MouseEvent evt, Graphics2D g2, SeriesSize size, int index);

    public abstract void renderGraphics(Graphics2D g2, List<Path2D.Double> gra);

    public abstract int getMaxLegend();
}
