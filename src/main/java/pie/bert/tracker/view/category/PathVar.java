package pie.bert.tracker.view.category;

public class PathVar {

    public static final String CATEGORY_CODE = "categoryCode";
    public static final String CATEGORY_CODE_BRACKETS = "{categoryCode}";

    private static String brackets(String pathVariable) {
        return "{" + pathVariable + "}";
    }

    private PathVar() {
        // private to prevent instantiation
    }
}
