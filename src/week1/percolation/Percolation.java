/**
 *
 * @author Igor Bykov
 * @author igorbykov10@gmail.com
 */

public class Percolation {
    private boolean[][] field;
    private int[][] intfield;
    private int size;
    private WeightedQuickUnionUF connectionSolver;

    public Percolation(int N) {
        size = N;
        field = new boolean[N][N];
        intfield = new int[N][N];
        connectionSolver = new WeightedQuickUnionUF((size*size));
        createField();
    }

    private void createField() {
        int index = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                field[i][j] = false;
                intfield[i][j] = index++;
            }
    }

    public void open(int i, int j) {
        if (i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        field[i - 1][j - 1] = true;
        int k = i - 1;
        int l = j - 1;
        if (k - 1 >= 0 && field[k - 1][l])
            connectionSolver.union(intfield[k - 1][l], intfield[k][l]);

        if (k + 1 < size && field[k + 1][l])
            connectionSolver.union(intfield[k + 1][l], intfield[k][l]);

        if (l - 1 >= 0 && field[k][l - 1])
            connectionSolver.union(intfield[k][l - 1], intfield[k][l]);

        if (l + 1 < size && field[k][l + 1])
            connectionSolver.union(intfield[k][l + 1], intfield[k][l]);
    }
    public boolean isOpen(int i, int j) {
        if (i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        return field[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        if (i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        for (int k = 0; k < size; k++) {
            if (connectionSolver.connected(intfield[i-1][j-1], k) && isOpen(i, j))
                return true;
        }
        return false;
    }

    public boolean percolates() {

        if (size == 1)
            return isOpen(1, 1);

        for (int i = 1; i < size; i++) {
            if (!isOpen(1, i))
                continue;
            for (int j = 1; j < size; j++) {
                if (!isOpen(size, j))
                    continue;
                if (connectionSolver.connected(intfield[0][i-1], intfield[size-1][j-1]))
                    return true;
            }
        }
        return false;
    }
}