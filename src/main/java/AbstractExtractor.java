abstract class AbstractExtractor<T> implements Extractor<T> {
    private final String name;

    public AbstractExtractor(final String name) {
        this.name = name;
    }
}
