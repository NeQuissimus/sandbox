import data.Bar;

class BarNameExtractor extends AbstractExtractor<Bar> {
    public BarNameExtractor() {
        super("BarNameExtractor");
    }

    @Override
    public String apply(final Bar bar) {
        return bar.name;
    }
}
