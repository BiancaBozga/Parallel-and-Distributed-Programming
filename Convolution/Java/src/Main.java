import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 5) {
//            System.out.println("Usage: java Main <input_filename> <{sequential/lines/columns/linearization}> <{batch/cyclic}> <threadsCount>");
            System.exit(1);
        }
        int p = Integer.parseInt(args[1]); // numărul de rulări
        String filename = args[2]; // numele fișierului
        String method = args[3]; // metoda de procesare
        int threadsCount = Integer.parseInt(args[0]); // numărul de thread-uri
        for (int i = 1; i < p; i++) {

            try {
                String outputFilename;

//                String filename="input10_10_3.txt";
                int[][][] matrices = File.readFromFile(filename);
                int n = matrices[0].length, m = matrices[0][0].length, k = matrices[1].length;


                Convolution conv;
                if (Objects.equals(method, "sequential")) {
                    conv = new SequentialConvolution(matrices[0], matrices[1]);
                    outputFilename = "sequential_" + n + "_" + m + "_" + k + ".txt";
                }
            else if (Objects.equals(method,"block")) {
                    conv = new BlockConvolution(matrices[0], matrices[1], threadsCount);
                    outputFilename = "block_" + n + "_" + m + "_" + k + "_" + threadsCount + ".txt";
                }
            else {
                    String parallelismMethod =args[4];

                    if (Objects.equals(method,"lines")) {

                            if (Objects.equals(parallelismMethod,"batch")) {
                                conv = new LinesBatchConvolution(matrices[0], matrices[1], threadsCount);
                            } else {
                                conv = new LinesCyclicConvolution(matrices[0], matrices[1], threadsCount);
                            }
                        }
                    else if(Objects.equals(method,"columns")){

                        if (Objects.equals(parallelismMethod,"batch")) {
                                conv = new ColumnsBatchConvolution(matrices[0], matrices[1], threadsCount);
                            } else {
                                conv = new ColumnsCyclicConvolution(matrices[0], matrices[1], threadsCount);
                            }
                        }
                    else if(Objects.equals(method,"linearization")){

                        if (Objects.equals(parallelismMethod,"batch")) {
                                conv = new LinearizationBatchConvolution(matrices[0], matrices[1], threadsCount);
                            } else {
                                conv = new LinearizationCyclicConvolution(matrices[0], matrices[1], threadsCount);
                            }
                        }
                    else{ throw new IllegalArgumentException("Unknown method: " + method);}

                    outputFilename = method + "_" + parallelismMethod + "_" + n + "_" + m + "_" + k + "_" + threadsCount + ".txt";
                }
                double start = System.nanoTime();
                int[][] answer = conv.apply();
                double end = System.nanoTime();
                File.writeAnswerToFile(answer, outputFilename);
                System.out.println((double)(end - start) /1E6);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}