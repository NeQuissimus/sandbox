import data.Foo;

class FooAgeExtractor extends AbstractExtractor<Foo> {
    public FooAgeExtractor() {
        super("FooAgeExtractor");
    }

    @Override
    public String apply(final Foo foo) {
        return String.valueOf(foo.age);
    }
}
