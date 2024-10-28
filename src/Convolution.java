public abstract class Convolution {
    protected final int rows, cols, kernelSize, nOfThreads;
    protected final int[][] matrix, conv;

    protected Convolution(int p, int[][] matrix, int[][] conv) {
        this.nOfThreads = p;
        this.matrix = matrix;
        this.conv = conv;
        rows = matrix.length;
        if (rows > 0) {
            cols = matrix[0].length;
        } else {
            cols = 0;
        }
        kernelSize = conv.length;
    }

    private int matrixClampingnOfThreadsadding(int i, int j) {
        if (i < 0) {
            i = 0;
        } else if (i >= rows) {
            i = rows - 1;
        }
        if (j < 0) {
            j = 0;
        } else if (j >= cols) {
            j = cols - 1;
        }
        return matrix[i][j];
    }
    protected int oneConv(int i, int j) {
        int sum = 0;
        for (int p = 0; p < kernelSize; ++p) {
            int index1 = i - kernelSize / 2 + p;
            for (int q = 0; q < kernelSize; ++q) {
                int index2 = j - kernelSize / 2 + q;
                sum += matrixClampingnOfThreadsadding(index1, index2) * conv[p][q];
            }
        }
        return sum;
    }

    public abstract int[][] apply();
}

class SequentialConvolution extends Convolution {
    public SequentialConvolution(int[][] matrix, int[][] conv) {
        super(0, matrix, conv);
    }

    @Override
    public int[][] apply() {
        int[][] answer = new int[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                answer[i][j] = oneConv(i, j);
            }
        }
        return answer;
    }
}
    class LinesBatchConvolution extends Convolution {
        public LinesBatchConvolution(int[][] matrix, int[][] conv, int nOfThreads) {
            super(nOfThreads,matrix, conv);
        }

        @Override
        public int[][] apply() {
            int[][] answer = new int[rows][cols];
            Thread[] threads = new Thread[nOfThreads];
            int threadId = 0;
            int linesPerThread = rows / nOfThreads;
            int linesLeft = rows % nOfThreads;
            for (int k = 0; k < rows; k += linesPerThread) {
                int start = k;
                if (linesLeft > 0) {
                    --linesLeft;
                    ++k;
                }
                int end = k + linesPerThread - 1;
                threads[threadId] = new Thread(() -> {
                    for (int i = start; i <= end; i++) {
                        for (int j = 0; j < cols; j++) {
                            answer[i][j] = oneConv(i, j);
                        }
                    }
                });
                threads[threadId++].start();
            }
            for (Thread thread : threads) {
                try {
                    if (thread != null) {
                        thread.join();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
    }

    class LinesCyclicConvolution extends Convolution {
        public LinesCyclicConvolution(int[][] matrix, int[][] conv, int nOfThreads) {
            super(nOfThreads, matrix,conv);
        }

        @Override
        public int[][] apply() {
            int[][] answer = new int[rows][cols];
            Thread[] threads = new Thread[nOfThreads];
            for (int k = 0; k < nOfThreads; ++k) {
                int finalK = k;
                threads[k] = new Thread(() -> {
                    for (int i = finalK; i < rows; i += nOfThreads) {
                        for (int j = 0; j < cols; ++j) {
                            answer[i][j] = oneConv(i, j);
                        }
                    }
                });
                threads[k].start();
            }
            for (Thread thread : threads) {
                try {
                    if (thread != null) {
                        thread.join();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
    }

    class ColumnsBatchConvolution extends Convolution {
        public ColumnsBatchConvolution(int[][] matrix, int[][] conv, int nOfThreads) {
            super(nOfThreads,matrix, conv);
        }

        @Override
        public int[][] apply() {
            int[][] answer = new int[rows][cols];
            Thread[] threads = new Thread[nOfThreads];
            int threadId = 0;
            int columnsPerThread = cols / nOfThreads;
            int columnsLeft = cols % nOfThreads;
            for (int k = 0; k < cols; k += columnsPerThread) {
                int start = k;
                if (columnsLeft > 0) {
                    --columnsLeft;
                    ++k;
                }
                int end = k + columnsPerThread - 1;
                threads[threadId] = new Thread(() -> {
                    for (int j = start; j <= end; j++) {
                        for (int i = 0; i < rows; i++) {
                            answer[i][j] = oneConv(i, j);
                        }
                    }
                });
                threads[threadId++].start();
            }
            for (Thread thread : threads) {
                try {
                    if (thread != null) {
                        thread.join();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
    }

    class ColumnsCyclicConvolution extends Convolution {
        public ColumnsCyclicConvolution(int[][] matrix, int[][] conv, int nOfThreads) {
            super(nOfThreads,matrix, conv);
        }

        @Override
        public int[][] apply() {
            int[][] answer = new int[rows][cols];
            Thread[] threads = new Thread[nOfThreads];
            for (int k = 0; k < nOfThreads; ++k) {
                int finalK = k;
                threads[k] = new Thread(() -> {
                    for (int j = finalK; j < cols; j += nOfThreads) {
                        for (int i = 0; i < rows; ++i) {
                            answer[i][j] = oneConv(i, j);
                        }
                    }
                });
                threads[k].start();
            }
            for (Thread thread : threads) {
                try {
                    if (thread != null) {
                        thread.join();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
    }

    class LinearizationBatchConvolution extends Convolution {
        public LinearizationBatchConvolution(int[][] matrix, int[][] conv, int nOfThreads) {
            super(nOfThreads,matrix, conv);
        }

        @Override
        public int[][] apply() {
            int[][] answer = new int[rows][cols];
            Thread[] threads = new Thread[nOfThreads];
            int threadId = 0;
            int elemsPerThread = rows * cols / nOfThreads;
            int elemsLeft = rows * cols % nOfThreads;
            for (int k = 0; k < rows * cols; k += elemsPerThread) {
                int start = k;
                if (elemsLeft > 0) {
                    --elemsLeft;
                    ++k;
                }
                int end = k + elemsPerThread - 1;
                threads[threadId] = new Thread(() -> {
                    for (int idx = start; idx <= end; ++idx) {
                        int i = idx / cols;
                        int j = idx % cols;
                        answer[i][j] = oneConv(i, j);
                    }
                });
                threads[threadId++].start();
            }
            for (Thread thread : threads) {
                try {
                    if (thread != null) {
                        thread.join();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
    }

    class LinearizationCyclicConvolution extends Convolution {
        public LinearizationCyclicConvolution(int[][] matrix, int[][] conv, int nOfThreads) {
            super(nOfThreads,matrix, conv);
        }

        @Override
        public int[][] apply() {
            int[][] answer = new int[rows][cols];
            Thread[] threads = new Thread[nOfThreads];
            for (int k = 0; k < nOfThreads; ++k) {
                int finalK = k;
                threads[k] = new Thread(() -> {
                    for (int idx = finalK; idx < rows * cols; idx += nOfThreads) {
                        int i = idx / cols;
                        int j = idx % cols;
                        answer[i][j] = oneConv(i, j);
                    }
                });
                threads[k].start();
            }
            for (Thread thread : threads) {
                try {
                    if (thread != null) {
                        thread.join();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
    }

    class BlockConvolution extends Convolution {
        public BlockConvolution(int[][] matrix, int[][] conv, int nOfThreads) {
            super(nOfThreads,matrix, conv);
        }

        @Override
        public int[][] apply() {
            int[][] answer = new int[rows][cols];
            Thread[] threads = new Thread[nOfThreads];
            int threadId = 0;
            int totalElems = rows * cols;
            int elemsPerThread = totalElems / nOfThreads;
            int elemsLeft = totalElems % nOfThreads;

            for (int k = 0; k < totalElems; k += elemsPerThread) {
                int startI = k / cols, startJ = k % cols;
                if (elemsLeft > 0) {
                    --elemsLeft;
                    ++k;
                }
                int endI = (k + elemsPerThread - 1) / cols, endJ = (k + elemsPerThread - 1) % cols;
                threads[threadId] = new Thread(() -> {
                    int i = startI, j = startJ;
                    while (!(i == endI && j == endJ)) {
                        answer[i][j] = oneConv(i, j);
                        ++j;
                        if (j == cols) {
                            j = 0;
                            ++i;
                        }
                    }
                });
                threads[threadId++].start();
            }
            for (Thread thread : threads) {
                try {
                    if (thread != null) {
                        thread.join();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
}