package me.doublenico.hypeapi;

public abstract class ListGuiBuilder extends GuiBuilder {

    protected int page = 0;
    public int maxItemsPerPage = 28;
    protected int index = 0;

    public ListGuiBuilder(String name, int size) {
        super(name, size);
    }

    public ListGuiBuilder(String name, int size, int maxItemsPerPage) {
        super(name, size);
        this.maxItemsPerPage = maxItemsPerPage;
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }
}
