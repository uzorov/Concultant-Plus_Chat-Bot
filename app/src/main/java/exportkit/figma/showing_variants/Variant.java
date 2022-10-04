package exportkit.figma.showing_variants;

import java.util.List;



public class Variant {

    private String variant;

    public Variant() {
    }

    public Variant(String variant) {
        this.variant = variant;
    }

    public static final Variant[] SAMPLE_DATA = {
            new Variant("Вариант 1fff"),
            new Variant("Вариант 2gggggg"),
            new Variant("Вариант 3d"),
            new Variant("Вариант 4"),
            new Variant("Вариант 5ddd"),
            new Variant("Вариант 6fffff"),
            new Variant("Вариант 7dddddd"),
            new Variant("Вариант 8"),

    };

    public String getVariantText() {
        return variant;
    }
}
