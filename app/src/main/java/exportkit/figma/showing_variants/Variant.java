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
            new Variant("Что ты умеешь?"),
            new Variant("Могу ли я стать самозанятым?"),
            new Variant("Про постановку и снятие с учёта"),
            new Variant("Шаблоны документов для самозанятых"),
            new Variant("Обратная связь"),
            new Variant("О нас"),
            new Variant("Вариант 7dddddd"),
            new Variant("Вариант 8"),

    };

    public String getVariantText() {
        return variant;
    }
}
