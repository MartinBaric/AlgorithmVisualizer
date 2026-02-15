package animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.List;

public class SortingAnimation {

    private final List<Rectangle> bars ;
    private final Slider speedSlider;
    private final Timeline timeline;

    private static final double BRIGHTNESS = 0.3d;

    public SortingAnimation(List<Rectangle> bars, Slider speedSlider, Timeline timeline) {
        this.bars = bars;
        this.speedSlider = speedSlider;
        this.timeline = timeline;
    }

    public HighlightListener createHighlightListener() {
        return this::addHighlightBar;
    }

    public SwapListener createSwapListener() {
        return this::addSwapFrame;
    }

    public void addSwapFrame(int i, int j) {
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis((timeline.getKeyFrames().size() + 1) * 100 / speedSlider.getValue()),
                ev -> {
                    double h1 = bars.get(i).getHeight();
                    double h2 = bars.get(j).getHeight();
                    bars.get(i).setHeight(h2);
                    bars.get(j).setHeight(h1);
                    markBar(bars.get(j), false);
                    markBar(bars.get(i), true);
                }
        ));
    }

    public void dehighlightAll(Timeline timeline, List<Rectangle> bars, Slider slider) {
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis((timeline.getKeyFrames().size() + 1) * 100 / slider.getValue()),
                ev -> {
                    for (Rectangle bar : bars)
                        markBar(bar, false);
                }
        ));
    }

    public static void markBar(Rectangle bar, boolean mark) {
        ColorAdjust adjust = bar.getEffect() instanceof ColorAdjust ? (ColorAdjust) bar.getEffect() : new ColorAdjust();
        double brightness;

        if (mark) {
            brightness = BRIGHTNESS;
            adjust.setHue(-0.08);        // cool blue
            adjust.setSaturation(0.15);
            adjust.setBrightness(0.12);
        }
        else
            brightness = -BRIGHTNESS;

        adjust.setBrightness(brightness);
        bar.setEffect(adjust);
    }

    private void addHighlightBar(int i) {
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis((timeline.getKeyFrames().size() + 1) * 100 / speedSlider.getValue()),
                ev -> {
                    markBar(bars.get(i), true);
                    for (int k = 0; k < bars.size(); k++) {
                        if (k != i)
                            markBar(bars.get(k), false);
                    }
                }
        ));
    }
}
