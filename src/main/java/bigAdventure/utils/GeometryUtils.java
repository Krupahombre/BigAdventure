package bigAdventure.utils;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GeometryUtils {

    public enum ShapeOutOfBoundsStatus {
        SHAPE_RIGHT_OF_BOUNDS,
        SHAPE_LEFT_OF_BOUNDS,
        SHAPE_BELOW_OF_BOUNDS,
        SHAPE_ABOVE_OF_BOUNDS,
    }

        public static ShapeOutOfBoundsStatus checkShapeCenterInBounds(final Shape shape, final Rectangle bounds, double originX, double originY) {

        var figureBounds = shape.getBounds();
        var figureCenter = new Vector2D(
                figureBounds.x + figureBounds.width * 0.5f,
                figureBounds.y + figureBounds.height * 0.5f);

        if (figureCenter.getX() > originX + bounds.getWidth()){
            return ShapeOutOfBoundsStatus.SHAPE_RIGHT_OF_BOUNDS;
        }
        else if (figureCenter.getX() < originX){
            return ShapeOutOfBoundsStatus.SHAPE_LEFT_OF_BOUNDS;
        }

        if (figureCenter.getY() > originY + bounds.getHeight()){
            return ShapeOutOfBoundsStatus.SHAPE_BELOW_OF_BOUNDS;
        }
        else if (figureCenter.getY() < originY){
            return ShapeOutOfBoundsStatus.SHAPE_BELOW_OF_BOUNDS;
        }
        return null;
    }

//    public static void keepScaleInBounds(final Figure figure){
//        var figureBounds = figure.getArea().getBounds();
//
//        if (figureBounds.width > figure.getInitialWidth() * figure.getPulsingAmplitude() ||
//                figureBounds.height > figure.getInitialHeight() * figure.getPulsingAmplitude())
//            if (figure.getPulsingSpeed() > 1)
//                figure.setPulsingSpeed(1 / figure.getPulsingSpeed());
//
//        if (figureBounds.width < figure.getInitialWidth() / figure.getPulsingAmplitude() ||
//                figureBounds.height < figure.getInitialHeight() / figure.getPulsingAmplitude())
//            if (figure.getPulsingSpeed() < 1)
//                figure.setPulsingSpeed(1 / figure.getPulsingSpeed());
//
//    }

}
