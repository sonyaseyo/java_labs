class immutableMatrix extends Matrix{

    public immutableMatrix(Matrix input) {
        super(input);
    }

    public void setCell(int x, int y, int value) throws Exception{
        throw new Exception("can't change immutable matrix :(");
    }

    public void setRow(int row_number, int[] row, int size) throws Exception{
        throw new Exception("can't change immutable matrix :(");
    }

    public void setColumn(int col_number, int[] column, int size) throws Exception{
        throw new Exception("can't change immutable matrix :(");
    }
}
