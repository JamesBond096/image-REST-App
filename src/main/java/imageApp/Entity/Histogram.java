package imageApp.Entity;
import org.jfree.data.statistics.HistogramDataset;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Histogram {

    private static final int BINS = 256;
    private BufferedImage image;
    private HistogramDataset dataset;


    public void setDataset() {
        dataset = new HistogramDataset();
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        dataset.addSeries("Red", r, BINS);
        r = raster.getSamples(0, 0, w, h, 1, r);
        dataset.addSeries("Green", r, BINS);
        r = raster.getSamples(0, 0, w, h, 2, r);
        dataset.addSeries("Blue", r, BINS);
    }

    public Histogram(BufferedImage img){
        image = img;
        setDataset();
    }
    public String toString(){
        return this.dataset.toString();
    }
}