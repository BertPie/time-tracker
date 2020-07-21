package pie.bert.tracker.view.category;

public class PathVariable {

    public static final String CATEGORY_CODE = "categoryCode";
    public static final String CATEGORY_CODE_BRACKETS = brackets(CATEGORY_CODE);

    private static String brackets(String pathVariable) {
        return "{" + pathVariable + "}";
    }

    private PathVariable() {
        // private to prevent instantiation
    }
}
