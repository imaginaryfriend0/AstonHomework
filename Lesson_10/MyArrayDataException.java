public class MyArrayDataException extends Exception {
    int row;
    int col;

    public MyArrayDataException(int row, int col) {
        super("Невозможно преобразовать символ в ячейке [" + row + "],[" + col + "].");
        this.row = row;
        this.col = col;
    }
}
