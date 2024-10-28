public class Constants {
    public static String HOME_DIRECTOR = "C://Users//computer//IdeaProjects//Lab2_PPD//";
    public static String FILES_DIRECTOR = HOME_DIRECTOR + "files/";
    public static String INPUTS_DIRECTOR = FILES_DIRECTOR + "inputs/";
    public static String OUTPUT_DIRECTOR = FILES_DIRECTOR + "outputs/";
    public static int[][] TEST_NUMBERS = new int[][] {{10, 10, 3}, {1000, 1000, 3}, {10000, 10000, 3}};
    public static int[][] THREADS_COUNT = new int[][]{{2}, {2, 4, 8, 16}, {2, 4, 8, 16}};
    public static String[] FILES_PREFIX = {
            "sequential_",
            "lines_",
    };
}
