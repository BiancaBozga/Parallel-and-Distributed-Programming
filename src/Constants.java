public class Constants {
    public static String HOME_DIRECTOR = "C://Users//computer//IdeaProjects//untitled6//";
    public static String FILES_DIRECTOR = HOME_DIRECTOR + "files/";
    public static String INPUTS_DIRECTOR = FILES_DIRECTOR + "inputs/";
    public static String OUTPUT_DIRECTOR = FILES_DIRECTOR + "outputs/";
    public static int[][] TEST_NUMBERS = new int[][] {{10, 10, 3}, {1000, 1000, 5}, {10, 10000, 5}, {10000, 10, 5}};
    public static int[][] NUMBER_OF_THREADS = new int[][]{{4}, {2, 4, 8, 16}, {2, 4, 8, 16}, {2, 4, 8, 16}};
    public static String[] FILES_PREFIX = {
            "sequential_",
            "lines_batch_",
            "lines_cyclic_",
            "columns_batch_",
            "columns_cyclic_",
            "linearization_batch_",
            "linearization_cyclic_"
    };
}