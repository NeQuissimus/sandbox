@FunctionalInterface
interface Extractor<T> {
    String apply(final T t);
}
