package panelPrincipal;

public class Tablero {
    private int dimension;
    private int[][] M;
    private boolean[][] X;

    public Tablero(int dimension)
    {
        this.dimension = dimension;
        this.M = new int[dimension][dimension];
        this.X = new boolean[dimension][dimension];
    }
    public int getPos(int i, int j)
    {
        return M[i][j];
    }
    public boolean esClic(int i, int j)
    {
        return X[i][j];
    }
    public void clic(int i, int j)
    {
        X[i][j] = !X[i][j];
    }
    public int getDimension()
    {
        return dimension;
    }
    public boolean esCompleto()
    {
        int c = 0;
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                if(X[i][j])
                    c++;
            }
        }
        if(c == dimension * dimension)
            return true;
        return false;
    }
    public void genAleatorio()
    {
        int g = 1;
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                M[i][j] = g++;
                if(g == 19)
                    g = 1;
            }
        }
        //esto intercambia los valores de la matriz aleatoriamente
        int x, y;
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                x = (int) (Math.random() * dimension);
                y = (int) (Math.random() * dimension);
                int aux = M[i][j];
                M[i][j] = M[x][y];
                M[x][y] = aux;
            }
        }
    }
}
